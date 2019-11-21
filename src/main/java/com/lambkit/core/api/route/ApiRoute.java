package com.lambkit.core.api.route;

public class ApiRoute {

	public static final String METHOD = "method";
	public static final String PARAMS = "params";
	
	private ApiParamsBuilder paramsBuilder;
	
	private static final ApiRoute apiRoute = new ApiRoute();
	
	public static ApiRoute me() {
		return apiRoute;
	}
	
	public ApiRoute() {
		paramsBuilder = new ApiParamsBuilder();
	}

	public ApiParamsBuilder getParamsBuilder() {
		return paramsBuilder;
	}

	public void setParamsBuilder(ApiParamsBuilder paramsBuilder) {
		this.paramsBuilder = paramsBuilder;
	}
	
}
