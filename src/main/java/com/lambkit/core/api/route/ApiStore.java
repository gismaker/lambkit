package com.lambkit.core.api.route;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.lambkit.common.service.ServiceManager;
import com.lambkit.common.service.ServiceObject;

/**
 * 保存Api方法
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.api.route
 */
public class ApiStore {

	// API 接口住的地方
	private ConcurrentHashMap<String, ApiAction> apiMap = new ConcurrentHashMap<String, ApiAction>();

	public void loadApiFromSerices() {
		// 获取所有calss
		Class<?> clazz;
		Map<String, ServiceObject> map = ServiceManager.me().getServices();
		for (Entry<String, ServiceObject> service : map.entrySet()) {
			clazz = service.getValue().getInterfaceClass();
			for (Method method : clazz.getDeclaredMethods()) {
				// 通过反谢拿到APIMapping注解
				ApiMapping apiMapping = method.getAnnotation(ApiMapping.class);
				if (apiMapping != null) {
					// 找到了目标方法
					addApiItem(apiMapping, service.getKey(), clazz, method);
				}
			}
		}
	}
	
	public void addService(Class<?> service) {
		if(service==null) return;
		ServiceObject serviceObject = ServiceManager.me().get(service);
		if(serviceObject==null) {
			ServiceManager.me().mapping(service, service, null);
		}
		for (Method method : service.getDeclaredMethods()) {
			// 通过反谢拿到APIMapping注解
			ApiMapping apiMapping = method.getAnnotation(ApiMapping.class);
			if (apiMapping != null) {
				// 找到了目标方法
				addApiItem(apiMapping, service.getName(), service, method);
			}
		}
	}
	
	public void addService(Class<?> service, Class<?> implementClass) {
		if(service == null || implementClass == null) return;
		ServiceManager.me().getOrNew(service, implementClass);
		for (Method method : service.getDeclaredMethods()) {
			// 通过反谢拿到APIMapping注解
			ApiMapping apiMapping = method.getAnnotation(ApiMapping.class);
			if (apiMapping != null) {
				// 找到了目标方法
				addApiItem(apiMapping, service.getName(), service, method);
			}
		}
	}

	/**
	 * 通过apiName获取apiRunable
	 *
	 * @param apiName
	 * @return
	 */
	public ApiAction findApiRunnable(String apiName) {
		return apiMap.get(apiName);
	}

	/**
	 * 添加api <br/>
	 *
	 * @param apiMapping api配置
	 * @param beanName
	 * @param method
	 */
	private void addApiItem(ApiMapping apiMapping, String beanName, Class<?> serviceClas, Method method) {
		String apiName = apiMapping.value();
		String targetName = beanName;
		ApiInterceptor[] methodInters = ApiInterceptorManager.me().buildServiceMethodInterceptor(serviceClas, method);
		ApiBody apiBody = method.getAnnotation(ApiBody.class);
		ApiAction apiRun = new ApiAction(apiName, targetName, method, apiMapping, apiBody, methodInters);
		apiMap.put(apiMapping.value(), apiRun);
	}

	public ApiAction findApiRunnable(String apiName, String version) {
		return apiMap.get(apiName + "_" + version);
	}

	public List<ApiAction> getAll() {
		List<ApiAction> list = new ArrayList<ApiAction>(20);
		list.addAll(apiMap.values());
		Collections.sort(list, new Comparator<ApiAction>() {
			public int compare(ApiAction o1, ApiAction o2) {
				return o1.getApiName().compareTo(o2.getApiName());
			}
		});
		return list;
	}

	public boolean containsApi(String apiName, String version) {
		return apiMap.containsKey(apiName + "_" + version);
	}
}
