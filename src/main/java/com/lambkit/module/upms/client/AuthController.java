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
package com.lambkit.module.upms.client;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Clear;
import com.lambkit.common.BaseResult;
import com.lambkit.core.gateway.GatewayRender;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.module.upms.SsoAuthenticate;
import com.lambkit.module.upms.UpmsResult;
import com.lambkit.module.upms.UpmsResultConstant;
import com.lambkit.web.controller.BaseController;

public class AuthController extends BaseController {

	@Clear
    public void login() {
    	if (getRequest().getMethod().equals("GET")) {
    		Subject subject = SecurityUtils.getSubject();
            if(SsoAuthenticate.validate(this)) {
            	// 已登陆
				String backurl = getRequest().getParameter("backurl");
                if (StringUtils.isBlank(backurl)) {
                    backurl = "/";
                    backurl = subject.hasRole("admin") ? "/manage" : "";
                    backurl = subject.hasRole("super") ? "/manage" : "";
                }
                redirect(backurl);
            } else {
            	keepPara();
            	renderTemplate("login.html");
            }
    	} else {
    		render(new GatewayRender("auth/login", "http://localhost:8080/sso/login"));
    		//renderJson(doLogin());
    	}
    }
	
	private UpmsResult doLogin() {
		GatewayRender pr = new GatewayRender("auth/login", "http://localhost:8080/sso/login");
		JSONObject result = JSONObject.parseObject(pr.accessStr(getRequest()));
		System.out.println("result:"+result.getIntValue("code") + ", data:" + result.getString("data"));
        if(result!=null) {
        	if(1 == result.getIntValue("code")) {
        		String username = getPara("username");
        		//String password = getPara("password");
        		BaseResult br = AuthManager.me().getService().login(this.getRequest(), username, "", false);
        	}
        	return new UpmsResult(result.getIntValue("code"), result.getString("message"), result.getString("data"));
        }
		/*
		HttpResponse httpResponse = pr.access(getRequest());
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = httpResponse.getEntity();
            JSONObject result;
			try {
				result = JSONObject.parseObject(EntityUtils.toString(httpEntity));
				System.out.println("result:"+result.getIntValue("code") + ", data:" + result.getString("data"));
	            if(result!=null) {
	            	return new UpmsResult(result.getIntValue("code"), result.getString("message"), result.getString("data"));
	            }
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
		return new UpmsResult(UpmsResultConstant.FAILED, "登录失败！");
		*/
        return new UpmsResult(UpmsResultConstant.FAILED, "登录失败！");
	}
    
    @Clear
    public void captcha() {
    	render(new GatewayRender("captcha", "http://localhost:8080/sso/captcha"));
    }

    //@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout() {
    	GatewayRender pr = new GatewayRender("login", "http://localhost:8080/sso/ajaxLogout");
    	pr.accessStr(getRequest());
		BaseResult result = AuthManager.me().getService().logout(this.getRequest());
        String redirectUrl = result.getData().toString();
        if (null == redirectUrl) {
            redirectUrl = "/";
        }
        redirect(redirectUrl);
    }
    
    @RequiresAuthentication
    public void repswd() {
    	render(new GatewayRender("repswd", "http://localhost:8080/sso/repswd"));
    }
    
    /**
	 * 用户注册
	 */
	public void regist() {
		if (getRequest().getMethod().equals("GET")) {
			keepPara();
			renderTemplate("regist.html");
		} else {
			renderJson(doRegist());
		}
	}

	private UpmsResult doRegist() {
		GatewayRender pr = new GatewayRender("regist", "http://localhost:8080/sso/regist");
		JSONObject result = JSONObject.parseObject(pr.accessStr(getRequest()));
		System.out.println("result:"+result.getIntValue("code") + ", data:" + result.getString("data"));
        if(result == null || 0 == result.getIntValue("code")) {
        	return new UpmsResult(UpmsResultConstant.FAILED, "注册失败！");
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, result.getString("data"));
        /*
		HttpResponse httpResponse = pr.access(getRequest());
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = httpResponse.getEntity();
            JSONObject result;
			try {
				result = JSONObject.parseObject(EntityUtils.toString(httpEntity));
				System.out.println("result:"+result.getIntValue("code") + ", data:" + result.getString("data"));
	            if(result == null || 0 == result.getIntValue("code")) {
	            	return new UpmsResult(UpmsResultConstant.FAILED, "注册失败！");
	            }
	            return new UpmsResult(UpmsResultConstant.SUCCESS, result.getString("data"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
		return new UpmsResult(UpmsResultConstant.FAILED, "注册失败！");
		*/
	}
}
