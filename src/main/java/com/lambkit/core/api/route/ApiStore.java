package com.lambkit.core.api.route;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	private Map<String, ApiRunnable> apiMap = new HashMap<String, ApiRunnable>();

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
					addApiItem(apiMapping, service.getKey(), method);
				}
			}
		}
	}

	/**
	 * 通过apiName获取apiRunable
	 *
	 * @param apiName
	 * @return
	 */
	public ApiRunnable findApiRunnable(String apiName) {
		return apiMap.get(apiName);
	}

	/**
	 * 添加api <br/>
	 *
	 * @param apiMapping api配置
	 * @param beanName   spring context中的名称
	 * @param method
	 */
	private void addApiItem(ApiMapping apiMapping, String beanName, Method method) {
		ApiRunnable apiRun = new ApiRunnable();
		apiRun.apiName = apiMapping.value();
		apiRun.targetMethod = method;
		apiRun.targetName = beanName;
		apiRun.apiMapping = apiMapping;
		apiMap.put(apiMapping.value(), apiRun);
	}

	public ApiRunnable findApiRunnable(String apiName, String version) {
		return apiMap.get(apiName + "_" + version);
	}

	public List<ApiRunnable> findApiRunnables(String apiName) {
		if (apiName == null) {
			throw new IllegalArgumentException("api name must not null!");
		}
		List<ApiRunnable> list = new ArrayList<ApiRunnable>(20);
		for (ApiRunnable api : apiMap.values()) {
			if (api.apiName.equals(apiName)) {
				list.add(api);
			}
		}
		return list;
	}

	public List<ApiRunnable> getAll() {
		List<ApiRunnable> list = new ArrayList<ApiRunnable>(20);
		list.addAll(apiMap.values());
		Collections.sort(list, new Comparator<ApiRunnable>() {
			public int compare(ApiRunnable o1, ApiRunnable o2) {
				return o1.getApiName().compareTo(o2.getApiName());
			}
		});
		return list;
	}

	public boolean containsApi(String apiName, String version) {
		return apiMap.containsKey(apiName + "_" + version);
	}
}
