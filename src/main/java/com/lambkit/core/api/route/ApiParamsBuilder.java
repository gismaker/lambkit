package com.lambkit.core.api.route;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;
import com.lambkit.common.util.JsonUtils;

public class ApiParamsBuilder {

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
	public Object[] buildParams(ApiAction apiRunnable, String jsonParam, HttpServletRequest request, ApiRequest apiRequest) throws ApiException {
		Map<String, Object> paramMap = null;
		try {
			paramMap = JsonUtils.toMapValue(jsonParam);
		} catch (IllegalArgumentException e) {
			throw new ApiException("调用失败：json字符串格式异常，请检查params参数 ");
		}

		if (paramMap == null) {
			paramMap = new HashMap<>();
		}

		Method method = apiRunnable.getMethod();

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
			//System.out.println("method param: " + parameterName);
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
}
