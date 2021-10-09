package com.lambkit.core.api.route;

/**
 * API路由模块的入口
 * @author yangyong
 *
 */
public class ApiRoute {

	public static final String API_METHOD = "method";
	public static final String API_PARAMS = "params";
	
	private ApiParamsBuilder paramsBuilder;
	private ApiStore apiStore;
	
	private static final ApiRoute apiRoute = new ApiRoute();
	
	public static ApiRoute me() {
		return apiRoute;
	}
	
	public ApiRoute() {
		paramsBuilder = new ApiParamsBuilder();
		setApiStore(new ApiStore());
	}
	
	public ApiRouteHandler getHandler(String targetName) {
		return new ApiRouteHandler(targetName);
	}
	
	public void onStart() {
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
