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
package com.lambkit.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lambkit.distributed.node.NodeManager;

public class RequestManager {
    private static RequestManager manager = new RequestManager();
    private ThreadLocal<HttpServletRequest> requests = new ThreadLocal<>();
    private ThreadLocal<HttpServletResponse> responses = new ThreadLocal<>();

    private RequestManager() {
    }

    public static RequestManager me() {
        return manager;
    }

    public void handle(HttpServletRequest req, HttpServletResponse response) {
    	NodeManager.me().initWeb(req);
        requests.set(req);
        responses.set(response);
    }

    public HttpServletRequest getRequest() {
        return requests.get();
    }

    public HttpServletResponse getResponse() {
        return responses.get();
    }

    public void release() {
        requests.remove();
        responses.remove();
    }


    public <T> T getRequestAttr(String key) {
        HttpServletRequest request = requests.get();
        if (request == null) {
            return null;
        }

        return (T) request.getAttribute(key);
    }

    public void setRequestAttr(String key, Object value) {
        HttpServletRequest request = requests.get();
        if (request == null) {
            return;
        }

        request.setAttribute(key, value);
    }
    
    /**
     * Get cookie value by cookie name.
     */
    public String getCookie(String name) {
        Cookie cookie = getCookieObject(name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * Get cookie object by cookie name.
     */
    public Cookie getCookieObject(String name) {
        Cookie[] cookies = RequestManager.me().getRequest().getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(name))
                    return cookie;
        return null;
    }

    /**
     * @param name
     * @param value
     * @param maxAgeInSeconds
     */
    public void setCookie(String name, String value, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        getResponse().addCookie(cookie);
    }

    /**
     * 就是取得客户端的系统版本     
     */
    public String agent() {
    	return getRequest().getHeader("User-Agent");    
    }
    /**
     * 取得客户端的IP 
     * @return
     */
    public String remoteAddr() {
    	return getRequest().getRemoteAddr();    
    }
    /**
     * 取得客户端的主机名     
     * @return
     */
    public String remoteHost() {
    	return getRequest().getRemoteHost();    
    }
    /**
     * 取得客户端的端口 
     * @return
     */
    public int remotePort() {
    	return getRequest().getRemotePort();    
    }
    /**
     * 取得客户端的用户     
     * @return
     */
    public String remoteUser() {
    	return getRequest().getRemoteUser();    
    }
    /**
     * 取得服务器IP 
     * @return
     */
    public String localAddr() {
    	return getRequest().getLocalAddr();    
    }
    /**
     * 取得服务器端口
     * @return
     */
    public int port() {
    	return getRequest().getLocalPort();    
    }
    
    public String contexPath() {
    	return getRequest().getContextPath();
    }
}
