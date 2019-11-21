package com.lambkit.core.api.route;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.jfinal.aop.Aop;
import com.jfinal.aop.AopManager;

public class ApiInterceptorManager {

	public static final ApiInterceptor[] NULL_INTERS = new ApiInterceptor[0];

	// 控制层与业务层全局拦截器
	private ApiInterceptor[] globalActionInters = NULL_INTERS;
	private ApiInterceptor[] globalServiceInters = NULL_INTERS;

	// 单例拦截器
	private final ConcurrentHashMap<Class<? extends ApiInterceptor>, ApiInterceptor> singletonMap = new ConcurrentHashMap<Class<? extends ApiInterceptor>, ApiInterceptor>(
			32, 0.5F);

	private static final ApiInterceptorManager me = new ApiInterceptorManager();

	private ApiInterceptorManager() {
	}

	public static ApiInterceptorManager me() {
		return me;
	}

	public ApiInterceptor[] createServiceInterceptor(Class<?> serviceClass) {
		return createInterceptor(serviceClass.getAnnotation(ApiBefore.class));
	}

	public ApiInterceptor[] buildServiceMethodInterceptor(Class<?> serviceClass, Method method) {
		return doBuild(globalServiceInters, NULL_INTERS, createServiceInterceptor(serviceClass), serviceClass, method);
	}

	private ApiInterceptor[] doBuild(ApiInterceptor[] globalInters, ApiInterceptor[] routesInters,
			ApiInterceptor[] classInters, Class<?> targetClass, Method method) {
		ApiInterceptor[] methodInters = createInterceptor(method.getAnnotation(ApiBefore.class));

		Class<? extends ApiInterceptor>[] clearIntersOnMethod;
		ApiClear clearOnMethod = method.getAnnotation(ApiClear.class);
		if (clearOnMethod != null) {
			clearIntersOnMethod = clearOnMethod.value();
			if (clearIntersOnMethod.length == 0) { // method 级 @ApiClear 且不带参
				return methodInters;
			}
		} else {
			clearIntersOnMethod = null;
		}

		Class<? extends ApiInterceptor>[] clearIntersOnClass;
		ApiClear clearOnClass = targetClass.getAnnotation(ApiClear.class);
		if (clearOnClass != null) {
			clearIntersOnClass = clearOnClass.value();
			if (clearIntersOnClass.length == 0) { // class 级 @clear 且不带参
				globalInters = NULL_INTERS;
				routesInters = NULL_INTERS;
			}
		} else {
			clearIntersOnClass = null;
		}

		ArrayList<ApiInterceptor> result = new ArrayList<ApiInterceptor>(
				globalInters.length + routesInters.length + classInters.length + methodInters.length);
		for (ApiInterceptor inter : globalInters) {
			result.add(inter);
		}
		for (ApiInterceptor inter : routesInters) {
			result.add(inter);
		}
		if (clearIntersOnClass != null && clearIntersOnClass.length > 0) {
			removeInterceptor(result, clearIntersOnClass);
		}
		for (ApiInterceptor inter : classInters) {
			result.add(inter);
		}
		if (clearIntersOnMethod != null && clearIntersOnMethod.length > 0) {
			removeInterceptor(result, clearIntersOnMethod);
		}
		for (ApiInterceptor inter : methodInters) {
			result.add(inter);
		}
		return result.toArray(new ApiInterceptor[result.size()]);
	}

	private void removeInterceptor(ArrayList<ApiInterceptor> target, Class<? extends ApiInterceptor>[] clearInters) {
		for (Iterator<ApiInterceptor> it = target.iterator(); it.hasNext();) {
			ApiInterceptor curInter = it.next();
			if (curInter != null) {
				Class<? extends ApiInterceptor> curInterClass = curInter.getClass();
				for (Class<? extends ApiInterceptor> ci : clearInters) {
					if (curInterClass == ci) {
						it.remove();
						break;
					}
				}
			} else {
				it.remove();
			}
		}
	}

	public ApiInterceptor[] createInterceptor(ApiBefore beforeAnnotation) {
		if (beforeAnnotation == null) {
			return NULL_INTERS;
		}
		return createInterceptor(beforeAnnotation.value());
	}

	public ApiInterceptor[] createInterceptor(Class<? extends ApiInterceptor>[] interceptorClasses) {
		if (interceptorClasses == null || interceptorClasses.length == 0) {
			return NULL_INTERS;
		}

		ApiInterceptor[] result = new ApiInterceptor[interceptorClasses.length];
		try {
			for (int i = 0; i < result.length; i++) {
				result[i] = singletonMap.get(interceptorClasses[i]);
				if (result[i] == null) {
					result[i] = (ApiInterceptor) interceptorClasses[i].newInstance();
					if (AopManager.me().isInjectDependency()) {
						Aop.inject(result[i]);
					}
					singletonMap.put(interceptorClasses[i], result[i]);
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void addGlobalActionInterceptor(ApiInterceptor... inters) {
		addGlobalInterceptor(true, inters);
	}

	public void addGlobalServiceInterceptor(ApiInterceptor... inters) {
		addGlobalInterceptor(false, inters);
	}

	private synchronized void addGlobalInterceptor(boolean forAction, ApiInterceptor... inters) {
		if (inters == null || inters.length == 0) {
			throw new IllegalArgumentException("interceptors can not be null.");
		}

		for (ApiInterceptor inter : inters) {
			if (inter == null) {
				throw new IllegalArgumentException("interceptor can not be null.");
			}
			if (singletonMap.containsKey(inter.getClass())) {
				throw new IllegalArgumentException(
						"interceptor already exists, interceptor must be singlton, do not create more then one instance of the same ApiInterceptor Class.");
			}
		}

		for (ApiInterceptor inter : inters) {
			if (AopManager.me().isInjectDependency()) {
				Aop.inject(inter);
			}
			singletonMap.put(inter.getClass(), inter);
		}

		ApiInterceptor[] globalInters = forAction ? globalActionInters : globalServiceInters;
		ApiInterceptor[] temp = new ApiInterceptor[globalInters.length + inters.length];
		System.arraycopy(globalInters, 0, temp, 0, globalInters.length);
		System.arraycopy(inters, 0, temp, globalInters.length, inters.length);

		if (forAction) {
			globalActionInters = temp;
		} else {
			globalServiceInters = temp;
		}
	}

	public java.util.List<Class<?>> getGlobalServiceInterceptorClasses() {
		ArrayList<Class<?>> ret = new ArrayList<>(globalServiceInters.length + 3);
		for (ApiInterceptor i : globalServiceInters) {
			ret.add(i.getClass());
		}
		return ret;
	}
}
