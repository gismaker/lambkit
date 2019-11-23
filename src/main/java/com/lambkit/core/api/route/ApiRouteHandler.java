package com.lambkit.core.api.route;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.jfinal.log.Log;
import com.lambkit.Lambkit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.common.util.EncryptUtils;
import com.lambkit.core.aop.AopKit;
import com.lambkit.core.api.Token;
import com.lambkit.core.api.TokenService;

/**
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.api.route
 */
public class ApiRouteHandler extends Handler {
	static Log log = Log.getLog(ApiRouteHandler.class);

	private String targetName = "/api";
	private ApiStore apiStore;

	public ApiRouteHandler(String targetName) {
		this.targetName = targetName;
	}

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		// TODO Auto-generated method stub
		if (targetName.equals(target)) {
			System.out.println("Api handle: " + target);
			isHandled[0] = true;
			if (apiStore == null) {
				apiStore = new ApiStore();
				apiStore.loadApiFromSerices();
			}
			handleApiRequest(target, request, response);
		} else {
			next.handle(target, request, response, isHandled);
		}
	}

	private void handleApiRequest(String target, HttpServletRequest request, HttpServletResponse response) {
		// 系统参数验证
		String params = request.getParameter(ApiRoute.PARAMS);
		String method = request.getParameter(ApiRoute.METHOD);
		ApiResult result;

		ApiAction apiAction = null;
		ApiRequest apiRequest = null;
		try {
			// 校验请求参数
			apiAction = requestParamsValidate(request);

			// 构建apiRequest
			apiRequest = buildApiRequest(request);

			// 签名验证
			if (apiRequest.getAccessToken() != null) {
				signCheck(apiRequest);
			}

			// 用户登录验证
			if (apiAction.getMapping().useLogin()) {
				if (apiRequest.isLogin()) {
					throw new ApiException(402, "调用失败：用户未登陆");
				}
			}
			
			log.info("请求接口={" + method + "} 参数=" + params + "");
			Object[] args = ApiRoute.me().getParamsBuilder().buildParams(apiAction, params, request, apiRequest);
			ApiInvocation inv = new ApiInvocation(apiAction, args);
			if (Lambkit.isDevMode()) {
				if (ApiActionReporter.isReportAfterInvocation(request)) {
					inv.invoke();
					ApiActionReporter.report(target, request, apiAction);
				} else {
					ApiActionReporter.report(target, request, apiAction);
					inv.invoke();
				}
			}
			else {
				inv.invoke();
			}
			Object error = inv.getErrorValue();
			Object data = inv.getReturnValue();
			if(error!=null) {
				result = ApiResult.fail("校验失败", data).setError(error);
			} else {
				result = ApiResult.ok(data);
			}
		} catch (ApiException e) {
			response.setStatus(500);// 封装异常并返回
			log.error("调用接口={" + method + "}异常  参数=" + params + "", e);
			result = handleError(e);
		} catch (Exception e) {
			response.setStatus(500);// 封装业务异常并返回
			log.error("其他异常", e);
			result = handleError(e);
		}

		// 统一返回结果
		returnResult(result, apiAction, request, response);
	}

	/**
	 * 处理异常
	 *
	 * @param throwable
	 * @return
	 */
	private ApiResult handleError(Throwable throwable) {
		ApiResult result;
		if (throwable instanceof ApiException) {
			result = ApiResult.by((ApiException) throwable);
		} else {
			result = ApiResult.fail(throwable.getMessage(), null);
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(out);
		throwable.printStackTrace(stream);
		return result;
	}

	private ApiAction requestParamsValidate(HttpServletRequest request) throws ApiException {
		String apiName = request.getParameter(ApiRoute.METHOD);
		String apiParam = request.getParameter(ApiRoute.PARAMS);

		ApiAction apiRunnable;
		if (apiName == null || apiName.trim().equals("")) {
			throw new ApiException("调用失败：参数'method'为空");
		} else if (apiParam == null) {
			throw new ApiException("调用失败：参数'params'为空");
		} else if ((apiRunnable = apiStore.findApiRunnable(apiName)) == null) {
			throw new ApiException("调用失败：指定API不存在，API:" + apiName);
		}

		return apiRunnable;
	}

	/**
	 * 构建apiRequest对象
	 * 
	 * @param request
	 * @return
	 */
	private ApiRequest buildApiRequest(HttpServletRequest request) {
		ApiRequest apiRequest = new ApiRequest();
		apiRequest.setAccessToken(request.getParameter("token"));
		apiRequest.setSign(request.getParameter("sign"));
		apiRequest.setTimestamp(request.getParameter("timestamp"));
		apiRequest.seteCode(request.getParameter("eCode"));
		apiRequest.setuCode(request.getParameter("uCode"));
		apiRequest.setParams(request.getParameter("params"));
		return apiRequest;
	}

	/**
	 * 签名验证
	 * 
	 * @param apiRequest
	 * @return
	 * @throws ApiException
	 */
	private ApiRequest signCheck(ApiRequest apiRequest) throws ApiException {
		TokenService tokenService = ServiceKit.inject(TokenService.class);
		if (tokenService == null) {
			return apiRequest;
		}
		Token token = tokenService.getToken(apiRequest.getAccessToken());
		if (token == null) {
			throw new ApiException(402, "验证失败：指定'token'不存在");
		}
		if (token.getExpiresTime().before(new Date())) {
			throw new ApiException(402, "验证失败：指定'token'已失效");
		}

		// 生成签名
		String methodName = apiRequest.getMethodName();
		//String accessToken = token.getAccessToken();
		String secret = token.getSecret();
		String params = apiRequest.getParams();
		String timestamp = apiRequest.getTimestamp();
		String sign = EncryptUtils.MD5(secret + methodName + params + token + timestamp + secret);

		// 比较两次签名，防止数据篡改
		if (!sign.toUpperCase().equals(apiRequest.getSign())) {
			throw new ApiException(402, "验证失败：签名非法");
		}

		// 时间验证:防止第三者伪造同样的请求
		if (Math.abs(Long.valueOf(timestamp) - System.currentTimeMillis()) > 10 * 60 * 1000) {
			throw new ApiException(402, "验证失败：签名失效");
		}

		apiRequest.setLogin(true);
		apiRequest.setMemberId(token.getMemberId());
		return apiRequest;
	}

	/**
	 * 构建响应结果
	 *
	 * @param result
	 * @param response
	 */
	private void returnResult(ApiResult result, ApiAction action, HttpServletRequest request, HttpServletResponse response) {
		ApiBody body = action.getBody();
		if(body==null) {
			AopKit.singleton(ApiRenderJson.class).Render(result, request, response);
		} else {
			ApiRender render = AopKit.singleton(body.value());
			render.setView(body.view());
			render.Render(result, request, response);
		}
	}

}
