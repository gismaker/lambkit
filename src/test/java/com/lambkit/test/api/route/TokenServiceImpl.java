package com.lambkit.test.api.route;

import com.lambkit.core.api.Token;
import com.lambkit.core.api.TokenService;

/**
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.test.api.route
 */
public class TokenServiceImpl implements TokenService {

	@Override
	public Token createToken() {
		// TODO Auto-generated method stub
		Token token = new Token();
		return token;
	}

	@Override
	public Token getToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
