package com.lambkit.core.api.route;

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
		apiStore.loadApiFromSerices();
	}
	
	public void addService(Class<?> service) {
		apiStore.addService(service);
	}
	
	public void addService(Class<?> service, Class<?> implementClass) {
		apiStore.addService(service, implementClass);
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
