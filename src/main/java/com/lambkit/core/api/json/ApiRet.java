package com.lambkit.core.api.json;

import com.jfinal.core.Controller;
import com.lambkit.core.api.route.ApiResult;

public class ApiRet extends ApiResult {

	public ApiRet(ApiRetConsts sdssResultConsts, Object data) {
		super(sdssResultConsts.getCode(), sdssResultConsts.getMessage(), data);
	}

	public ApiRet(ApiRetConsts sdssResultConsts, String message, Object data) {
		super(sdssResultConsts.getCode(), message, data);
	}

	public ApiRet(int code, String message, Object data) {
		super(code, message, data);
	}
	
	public static ApiRet json(ApiRetConsts sdssResultConsts) {
		return new ApiRet(sdssResultConsts, null);
	}

	public static ApiRet json(ApiRetConsts sdssResultConsts, Object data) {
		return new ApiRet(sdssResultConsts, data);
	}

	public static ApiRet json(ApiRetConsts sdssResultConsts, String message, Object data) {
		return new ApiRet(sdssResultConsts, message, data);
	}

	public static ApiRet json(int code, String message, Object data) {
		return new ApiRet(code, message, data);
	}

	public static void render(Controller c, ApiRetConsts sdssResultConsts, String message, Object data) {
		c.renderJson(new ApiRet(sdssResultConsts, message, data));
	}

	public static void render(Controller c, int code, String message, Object data) {
		c.renderJson(new ApiRet(code, message, data));
	}

	public static void render(Controller c, ApiRetConsts sdssResultConsts, Object data) {
		c.renderJson(new ApiRet(sdssResultConsts, data));
	}

	public static void render(Controller c, ApiRetConsts sdssResultConsts) {
		c.renderJson(new ApiRet(sdssResultConsts, null));
	}

}
