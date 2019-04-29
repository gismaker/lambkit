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
package com.lambkit.module.upms.client.auth;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.redis.Redis;
import com.lambkit.common.util.RedisUtil;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.gateway.GatewayRender;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.module.upms.common.UpmsConfig;
import com.lambkit.module.upms.common.UpmsConstant;

import redis.clients.jedis.Jedis;

public class SsoAuthenticate {

	public static boolean validate(Controller c) {
		UpmsConfig upmsConfig = ConfigManager.me().get(UpmsConfig.class);
	    GatewayRender proxy = new GatewayRender("client", upmsConfig.getSsoServerUrl() + "/sso/authenticate");
    	Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) session.getTimeout() / 1000;
        
        Map<String, String> params = new HashMap<>();
    	params.put("appid", upmsConfig.getAppId());
    	params.put("lussid", sessionId);
        try {
			JSONObject result = JSONObject.parseObject(proxy.accessStr(c.getRequest(), params));
			System.out.println("result:"+result.getIntValue("code") + ", message:"+result.getString("message") + ", data:" + result.getString("data"));
	        if(result == null || 0 == result.getIntValue("code")) {
	        	// 认证失败
	        	String username = (String) subject.getPrincipal();
                if (StrKit.notBlank(username) || AuthManager.me().hasUser(c)) {
                	AuthManager.me().getService().logout(c.getRequest());
                }
            	return false;
            } else {
            	// 登录状态
            	 String cacheClientSession = Redis.use().get(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + session.getId());
            	 if(StringUtils.isNotBlank(cacheClientSession)) {
            		 //更新code有效期
            		 RedisUtil.set(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + sessionId, cacheClientSession, timeOut);
                     Jedis jedis = Redis.use().getJedis();
                     jedis.expire(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + cacheClientSession, timeOut);
                     jedis.close();
            	 } else {
            		 //新增code
            		 cacheClientSession = result.getString("data");
            		// code校验正确，创建局部会话
                     RedisUtil.set(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + sessionId, cacheClientSession, timeOut);
                     // 保存code对应的局部会话sessionId，方便退出操作
                     Redis.use().sadd(UpmsConstant.LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + cacheClientSession, sessionId, timeOut);
            	 }
            	 //本地是否登录
                 String username = (String) subject.getPrincipal();
                 if (StrKit.isBlank(username) || username.equals("null") || !AuthManager.me().hasUser(c)) {
                	// client无密认证
                	 username = result.getString("message");
                	 System.out.println("client login: " + username);
                	 AuthManager.me().getService().login(c.getRequest(), username, "", false);
                 }
            }
            return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }
}
