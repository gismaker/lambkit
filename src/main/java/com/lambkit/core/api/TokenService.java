package com.lambkit.core.api;

/**
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.api
 */
public interface TokenService {

	public Token createToken();

	public Token getToken(String token);
}
