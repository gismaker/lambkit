/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.distributed.token;

import java.util.Random;

import org.apache.commons.codec.binary.Base64;

import com.jfinal.kit.StrKit;

public class TokenBuilder {
	private static Random random = new Random();

	public static String createToken() {
		// String tokenId = String.valueOf(random.nextLong());
		return String.valueOf(random.nextLong());
	}
	/**
	 * 加密，加盐
	 * @param info
	 * @param token
	 * @return
	 */
	public static String encodeToken(String info, String token) {
		String tt = StrKit.getRandomUUID();
		String newToken = token + tt.substring(0, 4) + info + tt.substring(4, 8);
		byte[] encodeBytes = Base64.encodeBase64(newToken.getBytes());	
		String encode = new String (encodeBytes);
		return encode;
	}
	
	/**
	 * 解密，比对
	 * @param encode
	 * @param token
	 * @return
	 */
	public static String decodeToken(String encode, String token) {
		byte[] decodeBytes = Base64.decodeBase64(encode);
		String decode = new String(decodeBytes);
		String newToken = decode.substring(0, token.length());
		String newInfo = decode.substring(token.length() + 4, decode.length() - 4);
		return token.equals(newToken) ? newInfo : null;
	}
}
