package com.lambkit.core.api.route;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.lambkit.common.service.ServiceManager;

/**
 * 用于执行对应的API方法
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.api.route
 */
public class ApiRunnable {

	String apiName; // lambkit.api.user.getUser
	String targetName; // interface Service 名称
	// ServiceObject target; // UserServiceImpl 实例
	Method targetMethod; // 目标方法 getUser
	ApiMapping apiMapping;

	public Object run(Object... args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object target = ServiceManager.me().inject(targetName);
		return targetMethod.invoke(target, args);
	}

	public Class<?>[] getParamTypes() {
		return targetMethod.getParameterTypes();
	}

	public String getApiName() {
		return apiName;
	}

	public String getTargetName() {
		return targetName;
	}

	public Object getTarget() {
		return ServiceManager.me().inject(targetName);
	}

	public Method getTargetMethod() {
		return targetMethod;
	}

	public ApiMapping getApiMapping() {
		return apiMapping;
	}
}
