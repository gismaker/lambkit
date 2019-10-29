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
package com.lambkit.module.upms.server;

import java.util.UUID;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.redis.Redis;
import com.lambkit.common.LambkitResult;
import com.lambkit.common.util.RedisUtil;
import com.lambkit.component.shiro.session.ShiroSession;
import com.lambkit.core.aop.AopKit;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.module.upms.LoginService;
import com.lambkit.module.upms.UpmsConstant;
import com.lambkit.module.upms.UpmsResult;
import com.lambkit.module.upms.UpmsResultConstant;
import com.lambkit.module.upms.shiro.ShiroRedisSessionDao;

public class UpmsEmbeddedLoginService implements LoginService {

	public void captcha(Controller c) {
    	c.renderCaptcha();
	}
	
    public void login(Controller c) {
    	if (c.getRequest().getMethod().equals("GET")) {
    		Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            String serverSessionId = session.getId().toString();
            // 判断是否已登录，如果已登录，则回跳
            String code = Redis.use().get(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
            String username = (String) subject.getPrincipal();
            // code校验值
            if (StringUtils.isNotBlank(code) && StrKit.notBlank(username) && !username.equals("null")) {
                // 回跳
                String backurl = c.getRequest().getParameter("backurl");
                //String username = (String) subject.getPrincipal();
                if (StringUtils.isBlank(backurl)) {
                    backurl = "/";
                    backurl = subject.hasRole("admin") ? "/manage" : "";
                    backurl = subject.hasRole("super") ? "/manage" : "";
                } else {
                    if (backurl.contains("?")) {
                        backurl += "&upms_code=" + code + "&upms_username=" + username;
                    } else {
                        backurl += "?upms_code=" + code + "&upms_username=" + username;
                    }
                }
                System.out.println("认证中心帐号通过，带code回跳：{}" + backurl);
                if(backurl.startsWith("/")) backurl = backurl.substring(1);
                c.redirect(backurl);
            } else {
            	c.keepPara();
            	//renderJsp("login.jsp");
            	c.renderTemplate("login.html");
            }
    	} else {
    		c.renderJson(tologinResult(c));
    	}
    }
    
    private UpmsResult tologinResult(Controller c) {
        String username = c.getRequest().getParameter("username");
        String password = c.getRequest().getParameter("password");
        String captcha = c.getRequest().getParameter("captcha");
        String rememberMe = c.getRequest().getParameter("rememberMe");
        if (StringUtils.isBlank(username)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME, "帐号不能为空！");
        }
        if (StringUtils.isBlank(password)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_PASSWORD, "密码不能为空！");
        }
        if (StringUtils.isBlank(captcha)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_CAPTCHA, "验证码不能为空！");
        }
        if (!c.validateCaptcha("captcha")) {
            return new UpmsResult(UpmsResultConstant.INVALID_CAPTCHA, "验证码不正确！");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        System.out.println("sessionId: "+sessionId);
        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = Redis.use().get(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_ID + "_" + sessionId);
        // code校验值
        if (StringUtils.isBlank(hasCode)) {
            LambkitResult result = AuthManager.me().getService().login(c.getRequest(), username, password, BooleanUtils.toBoolean(rememberMe));
            if(result.getCode()!=UpmsResultConstant.SUCCESS.getCode()) {
            	return (UpmsResult) result;
            }
            // 更新session状态
            ShiroRedisSessionDao upmsSessionDao = AopKit.get(ShiroRedisSessionDao.class);
            upmsSessionDao.updateStatus(sessionId, ShiroSession.OnlineStatus.on_line);
            // 全局会话sessionId列表，供会话管理
            Redis.use().lpush(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_IDS, sessionId.toString());
            // 默认验证帐号密码正确，创建code
            String code = UUID.randomUUID().toString();
            // 全局会话的code
            RedisUtil.set(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_ID + "_" + sessionId, code, (int) subject.getSession().getTimeout() / 1000);
            // code校验值
            RedisUtil.set(UpmsConstant.LAMBKIT_UPMS_SERVER_CODE + "_" + code, code, (int) subject.getSession().getTimeout() / 1000);
        }
        // 回跳登录前地址
        String backurl = c.getRequest().getParameter("backurl");
        System.out.println("backurl:"+backurl);
        if (StringUtils.isBlank(backurl)) {
        	if("admin".equals(username)) {
        		return new UpmsResult(UpmsResultConstant.SUCCESS, "/manage");
        	} else {
        		return new UpmsResult(UpmsResultConstant.SUCCESS, "/");
        	}
        } else {
            return new UpmsResult(UpmsResultConstant.SUCCESS, backurl);
        }
    }
	
    
    public void logout(Controller c) {
    	LambkitResult result = AuthManager.me().getService().logout(c.getRequest());
        String redirectUrl = result.getData().toString();
        if (null == redirectUrl) {
            redirectUrl = "/";
        }
        if(redirectUrl.startsWith("/")) redirectUrl = redirectUrl.substring(1);
        c.redirect(redirectUrl);
    }
    
    public void ajaxLogout(Controller c) {
    	AuthManager.me().getService().logout(c.getRequest());
    	c.renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, "logout"));
    }
    
}
