package com.lambkit.core.api.json;

import com.lambkit.core.aop.AopKit;
import com.lambkit.core.api.route.ApiException;
import com.lambkit.core.api.route.ApiParamsBuilder;
import com.lambkit.core.api.route.ApiStore;

public class ApiServiceManager {

	private static ApiServiceManager manage = null;
	
	public static ApiServiceManager me() {
		if(manage==null) {
			manage = AopKit.singleton(ApiServiceManager.class);
		}
		return manage;
	}
	
	private ApiParamsBuilder paramsBuilder;
	private ApiStore apiStore;
	
	
	public void onStart() {
		if(paramsBuilder==null) {
			paramsBuilder = new ApiParamsBuilder();
		}
		if(apiStore == null) {
			apiStore = new ApiStore();
		}
		try {
			apiStore.loadApiFromSerices();
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
	
	public void addService(Class<?> service) {
		try {
			apiStore.addService(service);
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
	
	public void addService(Class<?> service, Class<?> implementClass) {
		try {
			apiStore.addService(service, implementClass);
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

	public ApiParamsBuilder getParamsBuilder() {
		return paramsBuilder;
	}

	public void setParamsBuilder(ApiParamsBuilder paramsBuilder) {
		this.paramsBuilder = paramsBuilder;
	}

	public ApiStore getApiStore() {
		return apiStore;
	}

	public void setApiStore(ApiStore apiStore) {
		this.apiStore = apiStore;
	}
}
