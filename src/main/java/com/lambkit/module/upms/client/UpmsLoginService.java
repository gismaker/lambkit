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
package com.lambkit.module.upms.client;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.lambkit.Lambkit;
import com.lambkit.common.util.StringUtils;
import com.lambkit.core.http.proxy.ProxyRender;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.module.upms.client.auth.SsoAuthenticate;
import com.lambkit.module.upms.common.UpmsConfig;
import com.lambkit.module.upms.common.UpmsResult;
import com.lambkit.module.upms.common.UpmsResultConstant;
import com.lambkit.web.WebConfig;

public class UpmsLoginService implements LoginService {
	public void captcha(Controller c) {
		UpmsConfig config = Lambkit.config(UpmsConfig.class);
		c.render(new ProxyRender("center/captcha", config.getSsoServerUrl() + "/sso/captcha"));
	}
	
    public void login(Controller c) {
    	String username = c.getPara("username");
		String password = c.getPara("password");
		
        if(SsoAuthenticate.validate(c)) {
        	// 已登陆
			String backurl = c.getRequest().getParameter("p");
            if (StringUtils.isBlank(backurl)) {
                backurl = "/";
                WebConfig web = Lambkit.config(WebConfig.class);
                Subject subject = SecurityUtils.getSubject();
                backurl = subject.hasRole("admin") ? web.getManagePage() : "";
                backurl = subject.hasRole("super") ? web.getAdminPage() : "";
            }
            c.redirect(backurl);
            return;
        }
        
		if (!StringUtils.areNotEmpty(username, password)) {
			c.createToken("loginToken");
			c.renderTemplate("login.html");
			return;
		}
		
		UpmsConfig config = Lambkit.config(UpmsConfig.class);
		c.render(new ProxyRender("center/login", config.getSsoServerUrl() + "/sso/login"));
	}
	
	public void logout(Controller c) {
		UpmsConfig config = Lambkit.config(UpmsConfig.class);
		AuthManager.me().getService().logout(c.getRequest());
		c.render(new ProxyRender("center/logout", config.getSsoServerUrl() + "/sso/logout"));
	}
    
    public void ajaxLogout(Controller c) {
    	AuthManager.me().getService().logout(c.getRequest());
    	c.renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, "logout"));
    }
    
}
