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
package com.lambkit.web.handler;

import com.jfinal.handler.Handler;
import com.jfinal.log.Log;
import com.lambkit.core.session.http.HttpSessionServletRequestWrapper;
import com.lambkit.web.RequestManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LambkitHandler extends Handler {
    static Log log = Log.getLog(LambkitHandler.class);

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {

        if (target.indexOf('.') != -1 && !target.startsWith("/lambkit/dev/druid")) {
            return;
        }
        
        /**
         * 通过 RequestManager 去保存 request，然后可以在当前线程的任何地方
         * 通过 RequestManager.me().getRequest() 去获取。
         */
    	RequestManager.me().handle(request, response);

        try {
        	
            /**
             * 执行请求逻辑
             */
        	System.out.println("handler first....");
            doHandle(target, new HttpSessionServletRequestWrapper(request), response, isHandled);

		} finally {
            try {
                RequestManager.me().release();
            } catch (Throwable ex) {
                log.error(ex.toString(), ex);
            }
        }

    }

    private void doHandle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
        //request.setAttribute("REQUEST", request);
        //request.setAttribute("CPATH", request.getContextPath());
        //request.setAttribute("host", request.getLocalPort());
        next.handle(target, request, response, isHandled);
    }


}
