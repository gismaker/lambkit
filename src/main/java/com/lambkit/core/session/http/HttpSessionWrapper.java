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

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.jfinal.kit.StrKit;
import com.lambkit.core.session.SessionManager;
import com.lambkit.web.RequestManager;

import java.util.Enumeration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class HttpSessionWrapper implements HttpSession {

    private static final long SESSION_TIME = TimeUnit.DAYS.toSeconds(2);
    public static final String SESSION_CACHE_NAME = "SESSION";

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public String getId() {
        return getOrCreatSessionId();
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int interval) {

    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        throw new RuntimeException("getSessionContext method not finished.");
    }

    @Override
    public Object getAttribute(String name) {
    	System.out.println("get session attr of key:"+buildKey(name));
        return SessionManager.me().getSession().get(SESSION_CACHE_NAME, buildKey(name));
    }

    @Override
    public Object getValue(String name) {
        return SessionManager.me().getSession().get(SESSION_CACHE_NAME, buildKey(name));
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        throw new RuntimeException("getAttributeNames method not finished.");
    }

    @Override
    public String[] getValueNames() {
        throw new RuntimeException("getValueNames method not finished.");
    }

    @Override
    public void setAttribute(String name, Object value) {
    	System.out.println("save session attr of key:"+buildKey(name));
    	SessionManager.me().getSession().put(SESSION_CACHE_NAME, buildKey(name), value);
    }

    @Override
    public void putValue(String name, Object value) {
    	SessionManager.me().getSession().put(SESSION_CACHE_NAME, buildKey(name), value);
    }


    @Override
    public void removeAttribute(String name) {
    	SessionManager.me().getSession().remove(SESSION_CACHE_NAME, buildKey(name));
    }

    @Override
    public void removeValue(String name) {

    }

    @Override
    public void invalidate() {

    }

    @Override
    public boolean isNew() {
        return false;
    }


    private Object buildKey(String name) {
        return String.format("%s:%s", getOrCreatSessionId(), name);
    }

    private String getOrCreatSessionId() {
        String sessionid = getCookie("JSESSIONID");
        if (StrKit.notBlank(sessionid)) {
            return sessionid;
        }

        sessionid = RequestManager.me().getRequestAttr("JSESSIONID");
        if (StrKit.notBlank(sessionid)) {
            return sessionid;
        }

        sessionid = UUID.randomUUID().toString().replace("-", "");
        RequestManager.me().setRequestAttr("JSESSIONID", sessionid);
        setCookie("JSESSIONID", sessionid, (int) SESSION_TIME);
        return sessionid;
    }


    /**
     * Get cookie value by cookie name.
     */
    private String getCookie(String name) {
        Cookie cookie = getCookieObject(name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * Get cookie object by cookie name.
     */
    private Cookie getCookieObject(String name) {
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
    private void setCookie(String name, String value, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        RequestManager.me().getResponse().addCookie(cookie);
    }

}
