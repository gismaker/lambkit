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
package com.lambkit.module.upms;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.gateway.GatewayRender;
import com.lambkit.plugin.auth.AuthManager;

public class SsoAuthenticate {
	
	/**
	 * client模式下，采用http方式实时校验用户
	 * @param controller
	 * @return
	 */
	public static boolean validate(Controller controller) {
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
			JSONObject result = JSONObject.parseObject(proxy.accessStr(controller.getRequest(), params));
			System.out.println("result:"+result.getIntValue("code") + ", message:"+result.getString("message") + ", data:" + result.getString("data"));
	        if(result == null || 0 == result.getIntValue("code")) {
	        	// 认证失败
	        	String username = (String) subject.getPrincipal();
                if (StrKit.notBlank(username) || AuthManager.me().hasUser(controller)) {
                	AuthManager.me().getService().logout(controller.getRequest());
                }
            	return false;
            } else {
            	// 登录状态
            	 String cacheClientSession = UpmsManager.me().getCache().getClientSession(session.getId().toString());
            	 if(StringUtils.isNotBlank(cacheClientSession)) {
            		 //更新code有效期
            		 UpmsManager.me().getCache().refreshClientSession(sessionId, cacheClientSession, timeOut);
            	 } else {
            		 //新增code
            		 cacheClientSession = result.getString("data");
            		// code校验正确，创建局部会话
            		 UpmsManager.me().getCache().saveClientSession(sessionId, cacheClientSession, timeOut);
            	 }
            	 //本地是否登录
                 String username = (String) subject.getPrincipal();
                 if (StrKit.isBlank(username) || username.equals("null") || !AuthManager.me().hasUser(controller)) {
                	// client无密认证
                	 username = result.getString("message");
                	 System.out.println("client login: " + username);
                	 AuthManager.me().getService().login(controller.getRequest(), username, "", false);
                 }
            }
            return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }
	
	/**
	 * server 模式下的code值
	 * @param sessionId
	 * @return
	 */
	public static String code(String sessionId) {
		// 判断是否已登录，如果已登录，则回跳，防止重复登录
        String code = UpmsManager.me().getCache().getSession(sessionId);
        // code校验值
        return code;
	}
	
}
