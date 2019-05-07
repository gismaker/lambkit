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
 */package com.lambkit.plugin.jwt;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.IPlugin;
import com.lambkit.Lambkit;

/**
 * FOR : 配置插件的接口
 */
public class JwtTokenPlugin implements IPlugin {
	/**
     * 默认构造器
     *
     * @param userService       登录方法的接口实现
     */
    public JwtTokenPlugin(IJwtUserService userService) {
    	if (userService == null)
            throw new TokenConfigException("userService", "空/null");
    	JwtKit.userService = userService;
    }

    /**
     * 简单构造器--提供私钥和Token有效时长
     *
     * @param secret             秘钥
     * @param expiration        失效时长（秒）
     * @param userService       登录方法的接口实现
     */
    public JwtTokenPlugin(String secret, Long expiration, IJwtUserService userService) {
        this(null, null, secret, expiration, userService);
    }

    /**
     * 全参构造器
     *
     * @param header            请求头中的Header
     * @param tokenPrefix      Token的前缀
     * @param secret            秘钥
     * @param expiration       失效时长（秒）
     * @param userService      登录方法的接口实现
     */
    public JwtTokenPlugin(String header, String tokenPrefix, String secret, Long expiration, IJwtUserService userService) {
        if (StrKit.notBlank(header)) 
        	Lambkit.setBootArg("lambkit.jwt.header", header);
        if (StrKit.notBlank(header)) 
        	Lambkit.setBootArg("lambkit.jwt.tokenPrefix", tokenPrefix);
        if (StrKit.notBlank(secret))
        	Lambkit.setBootArg("lambkit.jwt.secret", secret);
        if (StrKit.notNull(expiration) && StrKit.notBlank(expiration.toString()))
        	Lambkit.setBootArg("lambkit.jwt.expirationSecond", expiration);
        if (userService == null)
            throw new TokenConfigException("userService", "空/null");
        JwtKit.userService = userService;
    }
        
   public void check() {
	   JwtConfig config = Lambkit.config(JwtConfig.class);
	   String header = config.getHeader();
	   String tokenPrefix = config.getTokenPrefix();
	   String secret = config.getSecret();
	   Long expiration = config.getExpirationSecond();
	   if (StrKit.isBlank(header)) 
           throw new TokenConfigException("Header", "空/null");
       if (null == tokenPrefix)
           throw new TokenConfigException("TokenPrefix", "null");
       if (StrKit.isBlank(secret))
           throw new TokenConfigException("私钥", "空/null");
       if (!StrKit.notNull(expiration) || StrKit.isBlank(expiration.toString()))
           throw new TokenConfigException("失效时间", "空/null");
       if (JwtKit.userService == null)
           throw new TokenConfigException("userService", "空/null");
   }

    @Override
    public boolean start() {
    	check();
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }

}
