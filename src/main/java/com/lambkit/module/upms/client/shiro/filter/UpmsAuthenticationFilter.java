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
package com.lambkit.module.upms.client.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Enhancer;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.redis.Redis;
import com.lambkit.common.util.RedisUtil;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.module.upms.client.shiro.ShiroRedisSessionDao;
import com.lambkit.module.upms.common.UpmsConfig;
import com.lambkit.module.upms.common.UpmsConstant;
import com.lambkit.web.RequestParameterUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 重写authc过滤器
 */
public class UpmsAuthenticationFilter extends AuthenticationFilter {

    private final static Logger _log = LoggerFactory.getLogger(UpmsAuthenticationFilter.class);

    // 局部会话key
    private final static String LAMBKIT_UPMS_CLIENT_SESSION_ID = "lambkit-upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String LAMBKIT_UPMS_CLIENT_SESSION_IDS = "lambkit-upms-client-session-ids";

    private ShiroRedisSessionDao upmsSessionDao = Enhancer.enhance(ShiroRedisSessionDao.class);
    
    private UpmsConfig upmsConfig = ConfigManager.me().get(UpmsConfig.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        // 判断请求类型
        String upmsType = upmsConfig.getType();
        session.setAttribute(UpmsConstant.UPMS_TYPE, upmsType);
        if ("client".equals(upmsType)) {
            return validateClient(request, response);
        }
        if ("server".equals(upmsType)) {
            return subject.isAuthenticated();
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        StringBuffer sso_server_url = new StringBuffer(upmsConfig.getSsoServerUrl());
        // server需要登录
        String upmsType = upmsConfig.getType();
        
        if ("server".equals(upmsType)) {
            WebUtils.toHttp(response).sendRedirect(sso_server_url.append("/sso/login").toString());
            return false;
        }
        sso_server_url.append("/sso/index").append("?").append("appid").append("=").append(upmsConfig.getAppId());
        // 回跳地址
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        StringBuffer backurl = httpServletRequest.getRequestURL();
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            backurl.append("?").append(queryString);
        }
        sso_server_url.append("&").append("backurl").append("=").append(URLEncoder.encode(backurl.toString(), "utf-8"));
        WebUtils.toHttp(response).sendRedirect(sso_server_url.toString());
        return false;
    }

    /**
     * 认证中心登录成功带回code
     * @param request
     */
    private boolean validateClient(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) session.getTimeout() / 1000;
        // 判断局部会话是否登录
        String cacheClientSession = Redis.use().get(LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + session.getId());
        if (StringUtils.isNotBlank(cacheClientSession)) {
            // 更新code有效期
            RedisUtil.set(LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + sessionId, cacheClientSession, timeOut);
            Jedis jedis = Redis.use().getJedis();
            jedis.expire(LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + cacheClientSession, timeOut);
            jedis.close();
            // 移除url中的code参数
            if (null != request.getParameter("code")) {
                String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                try {
                    httpServletResponse.sendRedirect(backUrl.toString());
                } catch (IOException e) {
                    _log.error("局部会话已登录，移除code参数跳转出错：", e);
                }
            } else {
                return true;
            }
        }
        // 判断是否有认证中心code
        String code = request.getParameter("upms_code");
        // 已拿到code
        if (StringUtils.isNotBlank(code)) {
            // HttpPost去校验code
            try {
                StringBuffer sso_server_url = new StringBuffer(upmsConfig.getSsoServerUrl());
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(sso_server_url.toString() + "/sso/code");

                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("code", code));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse httpResponse = httpclient.execute(httpPost);
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    JSONObject result = JSONObject.parseObject(EntityUtils.toString(httpEntity));
                    if (1 == result.getIntValue("code") && result.getString("data").equals(code)) {
                        // code校验正确，创建局部会话
                        RedisUtil.set(LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + sessionId, code, timeOut);
                        // 保存code对应的局部会话sessionId，方便退出操作
                        Redis.use().sadd(LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + code, sessionId, timeOut);
                        _log.debug("当前code={}，对应的注册系统个数：{}个", code, Redis.use().getJedis().scard(LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + code));
                        // 移除url中的token参数
                        String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                        // 返回请求资源
                        try {
                            // client无密认证
                            String username = request.getParameter("upms_username");
                            subject.login(new UsernamePasswordToken(username, ""));
                            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                            httpServletResponse.sendRedirect(backUrl.toString());
                            return true;
                        } catch (IOException e) {
                            _log.error("已拿到code，移除code参数跳转出错：", e);
                        }
                    } else {
                        _log.warn(result.getString("data"));
                    }
                }
            } catch (IOException e) {
                _log.error("验证token失败：", e);
            }
        }
        return false;
    }

}
