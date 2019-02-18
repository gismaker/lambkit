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
package com.lambkit.web.cache;

import com.jfinal.core.Action;
import com.jfinal.core.JFinal;
import com.jfinal.handler.Handler;
import com.jfinal.log.Log;
import com.jfinal.render.RenderManager;
import com.lambkit.common.util.ArrayUtils;
import com.lambkit.common.util.StringUtils;
import com.lambkit.core.cache.CacheManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionCacheHandler extends Handler {

    private static String[] urlPara = {null};
    private static Log LOG = Log.getLog(ActionCacheHandler.class);

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {

        Action action = JFinal.me().getAction(target, urlPara);
        if (action == null) {
            next.handle(target, request, response, isHandled);
            return;
        }

        ActionCacheClear actionClear = action.getMethod().getAnnotation(ActionCacheClear.class);
        if (actionClear != null) {
            clearActionCache(action, actionClear);
            next.handle(target, request, response, isHandled);
            return;
        }

        EnableActionCache actionCache = getActionCache(action);
        if (actionCache == null) {
            next.handle(target, request, response, isHandled);
            return;
        }

        try {
            exec(target, request, response, isHandled, action, actionCache);
        } finally {
            ActionCacheContext.release();
        }

    }

    /**
     * 清空 页面缓存
     *
     * @param action
     * @param actionClear
     */
    private void clearActionCache(Action action, ActionCacheClear actionClear) {
        String[] cacheNames = actionClear.value();
        if (ArrayUtils.isNullOrEmpty(cacheNames)) {
            throw new IllegalArgumentException("ActionCacheClear annotation argument must not be empty " +
                    "in " + action.getControllerClass().getName() + "." + action.getMethodName());
        }

        for (String cacheName : cacheNames) {
            if (StringUtils.isNotBlank(cacheName)) {
                CacheManager.me().getCache().removeAll(cacheName);
            }
        }
    }

    public EnableActionCache getActionCache(Action action) {
        EnableActionCache actionCache = action.getMethod().getAnnotation(EnableActionCache.class);
        return actionCache != null ? actionCache : action.getControllerClass().getAnnotation(EnableActionCache.class);
    }

    private void exec(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled, Action action, EnableActionCache actionCacheEnable) {
        String cacheName = actionCacheEnable.group();
        if (StringUtils.isBlank(cacheName)) {
            throw new IllegalArgumentException("EnableActionCache group must not be empty " +
                    "in " + action.getControllerClass().getName() + "." + action.getMethodName());
        }

        if (cacheName.contains("#(") && cacheName.contains(")")) {
            cacheName = regexGetCacheName(cacheName, request);
        }

        String cacheKey = target;
        String queryString = request.getQueryString();
        if (queryString != null) {
            queryString = "?" + queryString;
            cacheKey += queryString;
        }

        ActionCacheInfo info = new ActionCacheInfo();
        info.setGroup(cacheName);
        info.setKey(cacheKey);
        info.setLiveSeconds(actionCacheEnable.liveSeconds());

        ActionCacheContext.hold(info);

        ActionCacheContent actionCache = CacheManager.me().getCache().get(cacheName, cacheKey);
        if (actionCache == null) {
            next.handle(target, request, response, isHandled);
            return;
        }

        response.setContentType(actionCache.getContentType());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(actionCache.getContent());
            writer.flush();
            isHandled[0] = true;
        } catch (Exception e) {
            LOG.error(e.toString(), e);
            RenderManager.me()
                    .getRenderFactory()
                    .getErrorRender(500).setContext(request, response, action.getViewPath())
                    .render();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private static final Pattern pattern = Pattern.compile("#\\(\\S+?\\)");

    private String regexGetCacheName(String cacheName, HttpServletRequest request) {
        Matcher m = pattern.matcher(cacheName);
        while (m.find()) {
            // find 的值 ： #(id)
            String find = m.group(0);
            String parameterName = find.substring(2, find.length() - 1);
            String value = request.getParameter(parameterName);
            if (StringUtils.isBlank(value)) value = "";
            cacheName = cacheName.replace(find, value);
        }

        return cacheName;
    }


}
