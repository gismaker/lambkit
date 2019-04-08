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

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.StrKit;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * FOR : Jwt插件核心拦截方法
 */
public class JwtTokenInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        Integer code = recoverAuthFromRequest(inv.getController().getRequest());
        switch (code) {
            case 200: {
                inv.invoke();
                break;
            }
            default: {
                inv.getController().renderError(401);
            }
        }
        inv.getController().getRequest().removeAttribute("me");// 移除避免暴露当前角色信息
    }

    /**
     * 读取Token从请求中 判断是否允许访问
     *
     * @param request
     * @return
     */
    private Integer recoverAuthFromRequest(HttpServletRequest request) {
        //没有权限用户
        try {
            IJwtAble jwtBean = getMe(request);
            if (jwtBean == null) return 404;
            return 200;
        } catch (Exception e) {
            e.printStackTrace();
            return 404;
        }
    }

    /**
     * 从请求头解析出me
     *
     * @param request
     * @return
     */
    protected static IJwtAble getMe(HttpServletRequest request) {
        IJwtAble me = (IJwtAble) request.getAttribute("me");
        if (null != me) return me;
        String authHeader = request.getHeader(JwtKit.header);
        if (StrKit.isBlank(authHeader) || authHeader.length() < JwtKit.tokenPrefix.length()) return null;
        String authToken = authHeader.substring(JwtKit.tokenPrefix.length());
        String jwtUser = JwtKit.getJwtUser(authToken);                      // 从token中解析出jwtAble
        if (jwtUser != null){
            Date created = JwtKit.getCreatedDateFormToken(authToken);
            me = JwtKit.getJwtBean(jwtUser,created);
            request.setAttribute("me", me);
        }
        return me;
    }
}