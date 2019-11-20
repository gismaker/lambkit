package com.lambkit.core.api.route;

/**
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.api.route
 */
public class ApiException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private int code;

	public ApiException(String message) {
		super(message);
		this.code = 400;
	}

	public ApiException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
