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
package com.lambkit.plugin.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lambkit.Lambkit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * FOR : Jwt操作工具类
 */
public class JwtKit {

	public static IJwtUserService userService = null;// 需要注入的服务参数

	private static final String CLAIM_KEY_USERNAME = "sub";
	private static final String CLAIM_KEY_CREATED = "created";

	public static void resetCache(String userName) {
		JwtConfig config = Lambkit.config(JwtConfig.class);
		String cacheName = config.getCacheName();
		Lambkit.getCache().remove(cacheName, userName);
		IJwtAble user = userService.getJwtAbleInfo(userName);
		if (user != null) {
			Lambkit.getCache().put(cacheName, userName, user);// 在服务器端储存jwtBean
		}
	}

	/**
	 * 通过 用户名密码 获取 token 如果为null，那么用户非法
	 *
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String getToken(String userName, String password) {
		if (userService == null)
			throw new TokenConfigException("userService", "空/null");
		IJwtAble user = userService.login(userName, password);
		if (user == null)
			return null;
		JwtConfig config = Lambkit.config(JwtConfig.class);
		String cacheName = config.getCacheName();
		String tokenPrefix = config.getTokenPrefix();
		// 构建服务器端储存对象
		Lambkit.getCache().put(cacheName, userName, user);// 在服务器端储存jwtBean
		// jwtStore.set(userName, user);
		// writeFile();
		// 用userName创建token
		String token = generateToken(userName);
		return tokenPrefix + token;
	}

	/**
	 * 用户通过其他认证方式登录，这里仅用于管理token，不验证用户
	 * 
	 * @param userName
	 * @return
	 */
	public static String getToken(String userName) {
		// 用userName创建token
		JwtConfig config = Lambkit.config(JwtConfig.class);
		String tokenPrefix = config.getTokenPrefix();
		String token = generateToken(userName);
		return tokenPrefix + token;
	}

	/**
	 * 通过 旧的token来交换新的token
	 *
	 * @param token
	 * @return
	 */
	public static String refreshToken(String token) {
		if (userService == null)
			throw new TokenConfigException("userService", "空/null");
		JwtConfig config = Lambkit.config(JwtConfig.class);
		String cacheName = config.getCacheName();
		String tokenPrefix = config.getTokenPrefix();
		if (token == null || token.length() < tokenPrefix.length())
			throw new TokenConfigException("token", "被解析");
		String trueToken = token.substring(tokenPrefix.length(), token.length());
		if (isTokenExpired(trueToken)) { // 如果已经过期
			// 解析出用户名
			String userName = getJwtUser(trueToken);
			IJwtAble jwtBean = (IJwtAble) Lambkit.getCache().get(cacheName, userName);
			if (jwtBean == null)
				return token;
			return generateToken(userName); // 在此匹配生成token
		}
		return token;
	}

	/**
	 * 验证token
	 * @param token
	 * @return
	 */
	public static IJwtAble validateToken(String token) {
		String jwtUser = getJwtUser(token); // 从token中解析出jwtAble
		if (jwtUser != null) {
			Date created = getCreatedDateFormToken(token);
			IJwtAble me = getJwtBean(jwtUser, created);
			if(me!=null) {
				refreshExpirationDate(token);
			}
			return me;
		}
		return null;
	}

	/**
	 * 从用户Token中获取用户名信息
	 *
	 * @param authToken
	 * @return
	 */
	public static String getJwtUser(String authToken) {
		String jwtUser = null;
		try {
			final Claims claims = getClaimsFromToken(authToken);
			jwtUser = claims != null ? claims.getSubject() : null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return jwtUser;
		}
	}

	/**
	 * 获取 getJwtBean 对象
	 *
	 * @param jwtUser
	 * @return
	 */
	public static IJwtAble getJwtBean(String jwtUser, Date created) {
		IJwtAble jwtBean = null;
		JwtConfig config = Lambkit.config(JwtConfig.class);
		String cacheName = config.getCacheName();
		try {
			jwtBean = (IJwtAble) Lambkit.getCache().get(cacheName, jwtUser);
			if (created == null || jwtBean == null
					|| created.before(jwtBean.getLastModifyPasswordTime()))/* 如果创建时间在修改密码之前 **/ {
				jwtBean = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jwtBean;
	}

	/**
	 * 获取Token的过期日期
	 *
	 * @param token
	 * @return
	 */
	public static Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	/**
	 * 获取用户Token的创建日期
	 *
	 * @param authToken
	 * @return
	 */
	public static Date getCreatedDateFormToken(String authToken) {
		Date creatd;
		try {
			final Claims claims = getClaimsFromToken(authToken);
			creatd = new Date((Long) claims.get(CLAIM_KEY_CREATED)); // 把时间戳转化为日期类型
		} catch (Exception e) {
			creatd = null;
		}

		return creatd;
	}

	/**
	 * 判断Token是否已经过期
	 *
	 * @param token
	 * @return
	 */
	private static Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * 将Token信息解析成Claims
	 *
	 * @param token
	 * @return
	 */
	private static Claims getClaimsFromToken(String token) {
		Claims claims;
		JwtConfig config = Lambkit.config(JwtConfig.class);
		String secret = config.getSecret();
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	/**
	 * 根据用户信息生成Token
	 *
	 * @param userName
	 * @return
	 */
	private static String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(CLAIM_KEY_USERNAME, userName);
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}

	/**
	 * 根据Claims信息来创建Token
	 *
	 * @param claims
	 * @returns
	 */
	private static String generateToken(Map<String, Object> claims) {
		JwtConfig config = Lambkit.config(JwtConfig.class);
		String secret = config.getSecret();
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * 生成令牌的过期日期
	 *
	 * @return
	 */
	private static Date generateExpirationDate() {
		JwtConfig config = Lambkit.config(JwtConfig.class);
		Long expirationSecond = config.getExpirationSecond();
		return new Date(System.currentTimeMillis() + expirationSecond * 1000);
	}

	/**
	 * 刷新令牌的过期时间
	 * 
	 * @param claims
	 */
	private static void refreshExpirationDate(String authToken) {
		final Claims claims = getClaimsFromToken(authToken);
		JwtConfig config = Lambkit.config(JwtConfig.class);
		Long expirationSecond = config.getExpirationSecond();
		Date date = new Date(System.currentTimeMillis() + expirationSecond * 1000);
		claims.setExpiration(date);
	}
}