package com.lambkit.core.api.route;

import com.lambkit.common.LambkitResult;

public class ApiResult extends LambkitResult {

	public ApiResult(int code, String message, Object data) {
		super(code, message, data);
	}
	
	public static ApiResult ok(String message, Object data) {
		return new ApiResult(200, message, data);
	}
	
	public static ApiResult ok(Object data) {
		return new ApiResult(200, "success", data);
	}
	
	public static ApiResult fail(String message, Object data) {
		return new ApiResult(400, message, data);
	}
	
	public static ApiResult fail(Object data) {
		return new ApiResult(400, "failed to respond", data);
	}
	
	public static ApiResult unauth(String message, Object data) {
		return new ApiResult(402, message, data);
	}
	
	public static ApiResult unauth(Object data) {
		return new ApiResult(402, "unauthorized for api", data);
	}
	
	public static ApiResult by(int code, String message) {
		return new ApiResult(code, message, null);
	} 
	
	public static ApiResult by(ApiException exception) {
		return new ApiResult(exception.getCode(), exception.getMessage(), null);
	} 
	
}
