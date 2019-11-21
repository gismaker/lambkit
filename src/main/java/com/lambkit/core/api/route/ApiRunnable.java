package com.lambkit.core.api.route;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.lambkit.common.service.ServiceManager;
import com.lambkit.common.service.ServiceObject;

/**
 * 用于执行对应的API方法
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.api.route
 */
public class ApiRunnable {

	private String apiName; // lambkit.api.user.getUser
	private String targetName; // interface Service 名称
	//Object target; // UserServiceImpl 实例
	private Method targetMethod; // 目标方法 getUser
	private ApiMapping apiMapping;
	private final ApiInterceptor[] interceptors;
	
	public ApiRunnable(String apiName, String targetName, Method targetMethod, ApiMapping apiMapping, ApiInterceptor[] interceptors) {
		this.apiName = apiName;
		this.targetName = targetName;
		this.targetMethod = targetMethod;
		this.apiMapping = apiMapping;
		this.interceptors = interceptors;
	}

	public Object run(Object... args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object target = ServiceManager.me().inject(targetName);
		return targetMethod.invoke(target, args);
	}
	
	public Class<?>[] getParamTypes() {
		return targetMethod.getParameterTypes();
	}
	
	public String[] getParamNames() {
		final int paraCount = targetMethod.getParameterCount();
		String[] resultList = new String[paraCount];
		if (paraCount == 0) {
			return resultList;
		}
		Parameter[] paras = targetMethod.getParameters();
		for (int i = 0; i < paraCount; i++) {
			Parameter p = paras[i];
			String parameterName = p.getName();
			System.out.println("method param: " + parameterName);
			resultList[i] = parameterName;
		}
		return resultList;
	}
	
	public Object getTarget() {
		return ServiceManager.me().inject(targetName);
	}
	
	public ServiceObject getServiceObject() {
		return ServiceManager.me().get(targetName);
	}
	
	public String getMethodName() {
		return targetMethod.getName();
	}

	public String getApiName() {
		return apiName;
	}

	public String getTargetName() {
		return targetName;
	}

	public Method getMethod() {
		return targetMethod;
	}

	public ApiMapping getApiMapping() {
		return apiMapping;
	}

	public ApiInterceptor[] getInterceptors() {
		return interceptors;
	}
}
