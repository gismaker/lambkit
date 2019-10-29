package com.lambkit.module.lms.controller;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jfinal.aop.Clear;
import com.jfinal.kit.StrKit;
import com.lambkit.Lambkit;
import com.lambkit.common.LambkitResult;
import com.lambkit.component.shiro.ShiroConfig;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.info.NodeBuilder;
import com.lambkit.module.upms.UpmsInterceptor;
import com.lambkit.module.upms.UpmsResult;
import com.lambkit.module.upms.UpmsResultConstant;
import com.lambkit.module.upms.shiro.ShiroSsoInterceptor;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.web.controller.LambkitController;

public class IndexLmsController extends LambkitController {

	@Clear
	public void index() {
		//user
		if(hasUser()) {
			set("auth", getUser());
		}
		//node
		NodeBuilder nb = new NodeBuilder();
		setAttr("node", nb.resetNodeInfo(NodeManager.me().getNode()));
		int size = 0;
		if(NodeManager.me().getNodeTable()!=null) {
			setAttr("ntable", NodeManager.me().getNodeTable().getValues());
			size = NodeManager.me().getNodeTable().getNodes().size();
		}
		setAttr("ntsize", size);
		//render
		renderTemplate("index.html");
	}
	
	@Clear
	public void captcha() {
		renderCaptcha();
	}
	
	@Clear({ShiroSsoInterceptor.class, UpmsInterceptor.class})
	public void login() {
		if (getRequest().getMethod().equals("GET")) {
    		Subject subject = SecurityUtils.getSubject();
            String username = (String) subject.getPrincipal();
            if (AuthManager.me().getService().user() && StrKit.notBlank(username) && !username.equals("null")) {
                String backurl = getRequest().getParameter("backurl");
                if (StringUtils.isBlank(backurl)) {
                    backurl = "/lambkit";
                }
                System.out.println("认证中心帐号通过，带code回跳：{}" + backurl);
                redirect(backurl);
            } else {
            	keepPara();
            	render("login.html");
            }
    	} else {
    		renderJson(tologinResult());
    	}
	}
	
	private UpmsResult tologinResult() {
        String username = getRequest().getParameter("username");
        String password = getRequest().getParameter("password");
        String captcha = getRequest().getParameter("captcha");
        String rememberMe = getRequest().getParameter("rememberMe");
        if (StringUtils.isBlank(username)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME, "帐号不能为空！");
        }
        if (StringUtils.isBlank(password)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_PASSWORD, "密码不能为空！");
        }
        if (StringUtils.isBlank(captcha)) {
            return new UpmsResult(UpmsResultConstant.EMPTY_CAPTCHA, "验证码不能为空！");
        }
        if (!validateCaptcha("captcha")) {
            return new UpmsResult(UpmsResultConstant.INVALID_CAPTCHA, "验证码不正确！");
        }
        if (!AuthManager.me().getService().user()) {
            LambkitResult result = AuthManager.me().getService().login(getRequest(), username, password, BooleanUtils.toBoolean(rememberMe));
            if(result.getCode()!=UpmsResultConstant.SUCCESS.getCode()) {
            	return (UpmsResult) result;
            }
        }
        // 回跳登录前地址
        String backurl = getRequest().getParameter("backurl");
        System.out.println("backurl:"+backurl);
        if (StringUtils.isBlank(backurl)) {
        	return new UpmsResult(UpmsResultConstant.SUCCESS, "/lambkit");
        } else {
            return new UpmsResult(UpmsResultConstant.SUCCESS, backurl);
        }
    }
    
    public void logout() {
    	LambkitResult result = AuthManager.me().getService().logout(getRequest());
        String redirectUrl = result.getData().toString();
        if (StrKit.isBlank(redirectUrl)) {
            redirectUrl = getPara("backurl", "/lambkit");
        }
        redirect(redirectUrl);
    }
    
    public void ajaxLogout() {
    	AuthManager.me().getService().logout(getRequest());
    	renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, "logout"));
    }
    
    @Clear
	public void needPermission() {
		ShiroConfig config = Lambkit.config(ShiroConfig.class);
		render(config.getUnauthorizedUrl());
	}
}
