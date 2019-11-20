package com.lambkit.core.api.route;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.jfinal.handler.Handler;
import com.jfinal.log.Log;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.common.util.EncryptUtils;
import com.lambkit.common.util.JsonUtils;
import com.lambkit.core.api.Token;
import com.lambkit.core.api.TokenService;

/**
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.api.route
 */
public class ApiRouteHandler extends Handler {
	static Log log = Log.getLog(ApiRouteHandler.class);

	private static final String METHOD = "method";
	private static final String PARAMS = "params";
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
			handleApiRequest(request, response);
		} else {
			next.handle(target, request, response, isHandled);
		}
	}

	private void handleApiRequest(HttpServletRequest request, HttpServletResponse response) {
		// 系统参数验证
		String params = request.getParameter(PARAMS);
		String method = request.getParameter(METHOD);
		ApiResult result;

		ApiRunnable apiRunable = null;
		ApiRequest apiRequest = null;
		try {
			// 校验请求参数
			apiRunable = sysParamsValidate(request);

			// 构建apiRequest
			apiRequest = buildApiRequest(request);

			// 签名验证
			if (apiRequest.getAccessToken() != null) {
				signCheck(apiRequest);
			}

			// 用户登录验证
			if (apiRunable.getApiMapping().useLogin()) {
				if (apiRequest.isLogin()) {
					throw new ApiException(402, "调用失败：用户未登陆");
				}
			}

			log.info("请求接口={" + method + "} 参数=" + params + "");
			Object[] args = buildParams(apiRunable, params, request, response, apiRequest);
			Object data = apiRunable.run(args);
			result = ApiResult.ok(data);
		} catch (ApiException e) {
			response.setStatus(500);// 封装异常并返回
			log.error("调用接口={" + method + "}异常  参数=" + params + "", e);
			result = handleError(e);
		} catch (InvocationTargetException e) {
			response.setStatus(500);// 封装业务异常并返回
			log.error("调用接口={" + method + "}异常  参数=" + params + "", e.getTargetException());
			result = handleError(e.getTargetException());
		} catch (Exception e) {
			response.setStatus(500);// 封装业务异常并返回
			log.error("其他异常", e);
			result = handleError(e);
		}

		// 统一返回结果
		returnResult(result, response);
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

	private ApiRunnable sysParamsValidate(HttpServletRequest request) throws ApiException {
		String apiName = request.getParameter(METHOD);
		String apiParam = request.getParameter(PARAMS);

		ApiRunnable apiRunnable;
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

	/***
	 * 验证业务参数，和构建业务参数对象
	 * 
	 * @param apiRunnable
	 * @param jsonParam
	 * @param request
	 * @param response
	 * @param apiRequest
	 * @return
	 * @throws ApiException
	 */
	private Object[] buildParams(ApiRunnable apiRunnable, String jsonParam, HttpServletRequest request,
			HttpServletResponse response, ApiRequest apiRequest) throws ApiException {
		Map<String, Object> paramMap = null;
		try {
			paramMap = JsonUtils.toMapValue(jsonParam);
		} catch (IllegalArgumentException e) {
			throw new ApiException("调用失败：json字符串格式异常，请检查params参数 ");
		}

		if (paramMap == null) {
			paramMap = new HashMap<>();
		}

		Method method = apiRunnable.getTargetMethod();

		// javassist
		List<String> paramNames = getNames(method);

		// 反射获取api方法中参数类型
		Class<?>[] paramTypes = method.getParameterTypes(); // 反射

		// 校验api方法中是否存在对应的参数
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			String key = entry.getKey();
			if (!paramNames.contains(key)) {
				throw new ApiException("调用失败：接口不存在‘" + key + "’参数");
			}
		}

		Object[] args = new Object[paramTypes.length];
		for (int i = 0; i < paramTypes.length; i++) {
			if (paramTypes[i].isAssignableFrom(HttpServletRequest.class)) {
				args[i] = request;
			} else if (paramTypes[i].isAssignableFrom(ApiRequest.class)) {
				args[i] = apiRequest;
			} else if (paramMap.containsKey(paramNames.get(i))) {
				try {
					args[i] = convertJsonToBean(paramMap.get(paramNames.get(i)), paramTypes[i]);
				} catch (Exception e) {
					throw new ApiException("调用失败：指定参数格式错误或值错误‘" + paramNames.get(i) + "’" + e.getMessage());
				}
			} else {
				args[i] = null;
			}
		}
		return args;
	}

	private List<String> getNames(Method method) {
		final int paraCount = method.getParameterCount();
		List<String> resultList = Lists.newArrayList();

		// 无参 action 共享同一个对象，该分支以外的所有 ParaProcessor 都是有参 action，不必进行 null 值判断
		if (paraCount == 0) {
			return resultList;
		}

		Parameter[] paras = method.getParameters();
		for (int i = 0; i < paraCount; i++) {
			Parameter p = paras[i];
			String parameterName = p.getName();
			System.out.println("method param: " + parameterName);
			resultList.add(parameterName);
		}

		return resultList;
	}

	/**
	 * 将MAP转换成具体的目标方方法参数对象
	 */
	private <T> Object convertJsonToBean(Object val, Class<T> targetClass) throws Exception {
		Object result = null;
		if (val == null) {
			return null;
		} else if (Integer.class.equals(targetClass)) {
			result = Integer.parseInt(val.toString());
		} else if (Long.class.equals(targetClass)) {
			result = Long.parseLong(val.toString());
		} else if (Date.class.equals(targetClass)) {
			if (val.toString().matches("[0-9]+")) {
				result = new Date(Long.parseLong(val.toString()));
			} else {
				throw new IllegalArgumentException("日期必须是长整型的时间戳");
			}
		} else if (String.class.equals(targetClass)) {
			if (val instanceof String) {
				result = val;
			} else {
				throw new IllegalArgumentException("转换目标类型为字符串");
			}
		} else {
			result = JsonUtils.convertValue(val, targetClass);
		}
		return result;
	}

	/**
	 * 构建响应结果
	 *
	 * @param result
	 * @param response
	 */
	private void returnResult(Object result, HttpServletResponse response) {
		try {
			JsonUtils.JSON_MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
			String json = JsonUtils.writeValueAsString(result);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html/json;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			if (json != null) {
				response.getWriter().write(json);
			}
		} catch (IOException e) {
			log.error("服务中心响应异常", e);
			throw new RuntimeException(e);
		}
	}

}
