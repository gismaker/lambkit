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
package com.lambkit.component.shiro;

import java.io.IOException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.lambkit.component.shiro.processer.AuthorizeResult;
import com.lambkit.core.config.ConfigManager;

/**
 * Shiro 拦截器
 */
public class ShiroInterceptor implements Interceptor {


    private ShiroConfig config = ConfigManager.me().get(ShiroConfig.class);


    @Override
    public void intercept(Invocation inv) {
        if (!config.isConfigOK()) {
            inv.invoke();
            return;
        }

        AuthorizeResult result = ShiroManager.me().invoke(inv.getActionKey());

        if (result == null || result.isOk()) {
            inv.invoke();
            return;
        }

        int errorCode = result.getErrorCode();
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
        String url = config.getLoginUrl();
        if(url.startsWith("/")) url = url.substring(1);
        controller.redirect(url);
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
        String url = config.getUnauthorizedUrl();
        if(url.startsWith("/")) url = url.substring(1);
        controller.redirect(url);
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
