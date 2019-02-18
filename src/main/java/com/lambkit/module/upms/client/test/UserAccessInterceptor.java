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
package com.lambkit.module.upms.client.test;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.component.shiro.ShiroConfig;
import com.lambkit.component.shiro.processer.AuthorizeResult;
import com.lambkit.module.upms.common.UpmsConfig;

public class UserAccessInterceptor implements Interceptor {

	private UpmsConfig upmsConfig = ConfigManager.me().get(UpmsConfig.class);

	private ShiroConfig config = ConfigManager.me().get(ShiroConfig.class);
	
	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		isAuthenticated(inv);
	}
	
	/**
     * server类型过滤方法
     * @param inv
     */
    private void isAuthenticated(Invocation inv) {
    	/*
        if (!config.isConfigOK()) {
        	inv.invoke();
            return;
        }
        */
    	System.out.println("user : " +inv.getActionKey() );
        AuthorizeResult result = ShiroAnnotionManager.me().invoke(inv.getActionKey());

        if (result == null || result.isOk()) {

            if(result==null) System.out.println("user : result null");
            else System.out.println("user : result is OK");
        	inv.invoke();
            return;
        }

        int errorCode = result.getErrorCode();
        System.out.println("user : " + errorCode);
        
        switch (errorCode) {
            case AuthorizeResult.ERROR_CODE_UNAUTHENTICATED:
                doProcessUnauthenticated(inv.getController());
                break;
            case AuthorizeResult.ERROR_CODE_UNAUTHORIZATION:
                doProcessuUnauthorization(inv.getController());
                break;
            case AuthorizeResult.ERROR_CODE_SESSION_FORCE_LOGOUT:
            	doProcessuSessionForceLogout(inv.getController());
            	break;
            default:
                inv.getController().renderError(404);
        }
    }
    
    /**
     * 未认证处理
     *
     * @param controller
     */
    private void doProcessUnauthenticated(Controller controller) {
        if (StrKit.isBlank(config.getLoginUrl())) {
            controller.renderError(401);
            return;
        }
        controller.redirect(config.getLoginUrl());
        /*
        String upmsType = upmsConfig.getType();
        if ("server".equals(upmsType)) {
        	controller.redirect(config.getLoginUrl());
        } else {
        	controller.redirect(upmsConfig.getSsoServerUrl()+"/sso/login");
        }
        */
        try {
			onAccessDenied(controller);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected void onAccessDenied(Controller controller) throws Exception {
        StringBuffer sso_server_url = new StringBuffer(upmsConfig.getSsoServerUrl());
        // server需要登录
        String upmsType = upmsConfig.getType();
        if ("server".equals(upmsType)) {
        	controller.redirect(sso_server_url.append("/sso/login").toString());
            return;
        }
        sso_server_url.append("/sso/index").append("?").append("appid").append("=").append(upmsConfig.getAppId());
        // 回跳地址
        HttpServletRequest httpServletRequest = controller.getRequest();
        StringBuffer backurl = httpServletRequest.getRequestURL();
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            backurl.append("?").append(queryString);
        }
        sso_server_url.append("&").append("backurl").append("=").append(URLEncoder.encode(backurl.toString(), "utf-8"));
        controller.redirect(sso_server_url.toString());
        return;
    }


    /**
     * 未授权处理
     *
     * @param controller
     */
    private void doProcessuUnauthorization(Controller controller) {
        if (StrKit.isBlank(config.getUnauthorizedUrl())) {
            controller.renderError(403);
            return;
        }
        controller.redirect(config.getUnauthorizedUrl());
    }
    
    /**
     * 强制退出
     * @param controller
     */
    private void doProcessuSessionForceLogout(Controller controller) {
    	SecurityUtils.getSubject().logout();
    	if (StrKit.isBlank(config.getLoginUrl())) {
            controller.renderError(401);
            return;
        }
        String loginUrl = config.getLoginUrl() + (config.getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";
        try {
			WebUtils.issueRedirect(controller.getRequest(), controller.getResponse(), loginUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //controller.redirect(config.getUnauthorizedUrl());
    }

}
