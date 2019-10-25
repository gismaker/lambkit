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
package com.lambkit.module.upms.shiro;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.redis.Redis;
import com.lambkit.common.util.RedisUtil;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.module.upms.UpmsConfig;
import com.lambkit.module.upms.UpmsConstant;
import com.lambkit.component.shiro.ShiroConfig;
import com.lambkit.component.shiro.ShiroManager;
import com.lambkit.component.shiro.processer.AuthorizeResult;
import com.lambkit.web.RequestParameterUtil;

import redis.clients.jedis.Jedis;

/**
 * Shiro 拦截器
 */
public class ShiroSsoInterceptor implements Interceptor {
	
	private static final Log _log = Log.getLog(ShiroSsoInterceptor.class);

    private ShiroConfig config = ConfigManager.me().get(ShiroConfig.class);

    // 局部会话key
    private final static String LAMBKIT_UPMS_CLIENT_SESSION_ID = "lambkit-upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String LAMBKIT_UPMS_CLIENT_SESSION_IDS = "lambkit-upms-client-session-ids";

    //private ShiroRedisSessionDao upmsSessionDao = ClassNewer.newInstance(ShiroRedisSessionDao.class);
    
    private UpmsConfig upmsConfig = ConfigManager.me().get(UpmsConfig.class);
    
    @Override
    public void intercept(Invocation inv) {
    	Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        // 判断请求类型
        String upmsType = upmsConfig.getType();
        session.setAttribute(UpmsConstant.UPMS_TYPE, upmsType);
        if ("client".equals(upmsType)) {
        	//System.out.println("client shiro sso interceptor");
        	boolean flag = validateClient(inv.getController());
        	//System.out.println("client ShiroManager");
        	AuthorizeResult result = ShiroManager.me().invoke(inv.getActionKey());
            if (result == null || result.isOk()) {
            	//System.out.println("client ShiroManager success");
            	flag = flag ? flag : authenticate(inv.getController());
            	inv.invoke();
            } else if(!flag) {
        		doProcessUnauthenticated(inv.getController());
        	} else {
            	//System.out.println("client ShiroManager fail");
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
        }
        if ("server".equals(upmsType)) {
        	isAuthenticated(inv);
        }
        /*
    	if(config.getShiroType()==ShiroConfig.TYPE_CLIENT) {
    		boolean flag = validateClient(inv.getController());
    		if(flag) inv.invoke();
    		else doProcessUnauthenticated(inv.getController());
    	} else {
    		isAuthenticated(inv);
    	}
    	*/
        return;
    }
    
    /**
     * 判断局部会话是否登录,否则，存在code参数，则去upms认证
     * @param c
     * @return
     */
    private boolean validateClient(Controller controller) {
    	ServletRequest request = controller.getRequest();
    	Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) session.getTimeout() / 1000;
        // 判断局部会话是否登录
        String cacheClientSession = Redis.use().get(LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + session.getId());
        if (StringUtils.isNotBlank(cacheClientSession)) {
            // 如果是登录状态，更新code有效期
        	System.out.println("登录状态，更新code有效期");
            RedisUtil.set(LAMBKIT_UPMS_CLIENT_SESSION_ID + "_" + sessionId, cacheClientSession, timeOut);
            Jedis jedis = Redis.use().getJedis();
            jedis.expire(LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + cacheClientSession, timeOut);
            jedis.close();
            // 移除url中的code参数
            if (null != request.getParameter("code")) {
                String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                controller.redirect(backUrl.toString());
                /*
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                try {
                	httpServletResponse.sendRedirect(backUrl.toString());
                } catch (IOException e) {
                    _log.error("局部会话已登录，移除code参数跳转出错：", e);
                }
                */
            } else {
                return true;
            }
        }
        // 判断是否有认证中心code
        String code = request.getParameter("upms_code");
        // 已拿到code
        if (StringUtils.isNotBlank(code)) {
            // HttpPost去校验code
        	System.out.println("HttpPost去校验code");
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
                        _log.debug("当前code={"+code+"}，对应的注册系统个数：{" + Redis.use().getJedis().scard(LAMBKIT_UPMS_CLIENT_SESSION_IDS + "_" + code)+"}个");
                        // 移除url中的token参数
                        String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                        // 返回请求资源
                        // client无密认证
                        String username = request.getParameter("upms_username");
                        subject.login(new UsernamePasswordToken(username, ""));
                        controller.redirect(backUrl.toString());
                        return true;
                        /*
                        try {
                            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                            httpServletResponse.sendRedirect(backUrl.toString());
                            return true;
                        } catch (IOException e) {
                            _log.error("已拿到code，移除code参数跳转出错：", e);
                        }
                        */
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
    
    /**
     * 去认证中心查看登录状态
     * @param controller
     * @return
     */
    public boolean authenticate(Controller controller) {
    	System.out.println("去认证中心查看登录状态");
    	ServletRequest request = controller.getRequest();
    	Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) session.getTimeout() / 1000;
        HttpResponse httpResponse;
		try {
			StringBuffer sso_server_url = new StringBuffer(upmsConfig.getSsoServerUrl());
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost(sso_server_url.toString() + "/sso/authenticate");
	        
			List<NameValuePair> nameValuePairs = new ArrayList<>();
	        nameValuePairs.add(new BasicNameValuePair("appid", upmsConfig.getAppId()));
	        nameValuePairs.add(new BasicNameValuePair("lussid", sessionId));
	        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        
			httpResponse = httpclient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
                JSONObject result = JSONObject.parseObject(EntityUtils.toString(httpEntity));
                System.out.println("result:"+result.getIntValue("code") + ", data:" + result.getString("data"));
                if(0 == result.getIntValue("code")) {
                	// 认证失败
                	_log.warn(result.getString("data"));
                	return false;
                }
                onAccessDenied(controller);
                return true;
	        }
		} catch (Exception e) {
			 _log.error("验证token失败：", e);
		}
    	return false;
    }
    
    /**
     * 权限过滤
     * @param inv
     */
    private void isAuthenticated(Invocation inv) {
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
        /*
        String url = config.getLoginUrl();
        if(url.startsWith("/")) url = url.substring(1);
        controller.redirect(url);
        
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
    
    /**
     * 跳转到upms登录页面
     * @param controller
     * @throws Exception
     */
    protected void onAccessDenied(Controller controller) throws Exception {
        StringBuffer sso_server_url = new StringBuffer(upmsConfig.getSsoServerUrl());
        // server需要登录
        String upmsType = upmsConfig.getType();
        if ("server".equals(upmsType)) {
        	String url = sso_server_url.append(config.getLoginUrl()).toString();
        	if(url.startsWith("//")) url = url.substring(2);
            if(url.startsWith("/")) url = url.substring(1);
            controller.redirect(url);
            return;
        }
        sso_server_url.append(upmsConfig.getSsoIndexUrl()).append("?").append("appid").append("=").append(upmsConfig.getAppId());
        // 回跳地址
        HttpServletRequest httpServletRequest = controller.getRequest();
        StringBuffer backurl = httpServletRequest.getRequestURL();
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            backurl.append("?").append(queryString);
        }
        sso_server_url.append("&").append("backurl").append("=").append(URLEncoder.encode(backurl.toString(), "utf-8"));
        String url = sso_server_url.toString();
        if(url.startsWith("/")) url = url.substring(1);
        controller.redirect(url);
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
