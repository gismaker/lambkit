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
package com.lambkit.core.session.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import com.lambkit.core.session.SessionManager;

public class HttpSessionServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest originHttpServletRequest;
    HttpSession httpSession;

    public HttpSessionServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.originHttpServletRequest = request;
    }

    @Override
    public HttpSession getSession() {
        return getSession(true);
    }


    @Override
    public HttpSession getSession(boolean create) {

        if (httpSession == null) {
            /**
             * 没有启用缓存的话，就用系统自带的session
             */
            if (SessionManager.me().getSession().isNone()) {
            	System.out.println("using default session wapper");
                httpSession = super.getSession(create);
            } else {
            	System.out.println("using my session wapper");
                httpSession = new HttpSessionWrapper();
            }
        }

        return httpSession;


    }


}
