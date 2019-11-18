package com.lambkit.core.api.route;

/**
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.api.route
 */
public class ApiException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String code;

	public ApiException(String message) {
		super(message);
	}

	public ApiException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
