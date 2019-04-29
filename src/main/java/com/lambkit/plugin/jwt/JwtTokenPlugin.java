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
        this(JwtKit.secret,JwtKit.expirationSecond,userService);
    }

    /**
     * 简单构造器--提供私钥和Token有效时长
     *
     * @param secret             秘钥
     * @param expiration        失效时长（秒）
     * @param userService       登录方法的接口实现
     */
    public JwtTokenPlugin(String secret, Long expiration, IJwtUserService userService) {
        this(JwtKit.header,JwtKit.tokenPrefix,secret,expiration,userService);
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
        if (StrKit.isBlank(header))
            throw new TokenConfigException("Header", "空/null");
        JwtKit.header = header;
        if (null == tokenPrefix)
            throw new TokenConfigException("TokenPrefix", "null");
        JwtKit.tokenPrefix = tokenPrefix;
        if (StrKit.isBlank(secret))
            throw new TokenConfigException("私钥", "空/null");
        JwtKit.secret = secret;
        if (!StrKit.notNull(expiration) || StrKit.isBlank(expiration.toString()))
            throw new TokenConfigException("失效时间", "空/null");
        JwtKit.expirationSecond = expiration;
        if (userService == null)
            throw new TokenConfigException("userService", "空/null");
        JwtKit.userService = userService;
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }

}
