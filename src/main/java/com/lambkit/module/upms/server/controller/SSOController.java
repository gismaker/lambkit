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
package com.lambkit.module.upms.server.controller;

import com.jfinal.aop.Clear;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.redis.Redis;
import com.lambkit.web.controller.LambkitController;
import com.lambkit.common.util.DateTimeUtils;
import com.lambkit.common.util.EncryptUtils;
import com.lambkit.common.util.RedisUtil;
import com.lambkit.component.shiro.session.ShiroSession;
import com.lambkit.component.swagger.annotation.Api;
import com.lambkit.component.swagger.annotation.ApiOperation;
import com.lambkit.core.aop.AopKit;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.UpmsConstant;
import com.lambkit.module.upms.UpmsResult;
import com.lambkit.module.upms.UpmsResultConstant;
import com.lambkit.module.upms.rpc.model.UpmsSystem;
import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.module.upms.rpc.model.UpmsUserRole;
import com.lambkit.module.upms.rpc.model.sql.UpmsSystemCriteria;
import com.lambkit.module.upms.rpc.api.UpmsApiService;
import com.lambkit.module.upms.rpc.api.UpmsUserService;
import com.lambkit.module.upms.rpc.service.impl.UpmsApiServiceImpl;
import com.lambkit.module.upms.rpc.service.impl.UpmsUserServiceImpl;
import com.lambkit.module.upms.shiro.ShiroRedisSessionDao;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.UUID;

/**
 * 单点登录管理
 */
//@RequestMapping("/sso")
@Api(tag = "sso", description = "单点登录管理")
@RequiresUser
public class SSOController extends LambkitController {

    private final static Logger _log = LoggerFactory.getLogger(SSOController.class);
    protected ShiroRedisSessionDao upmsSessionDao = AopKit.get(ShiroRedisSessionDao.class);

    @ApiOperation(url = "/sso", tag = "sso", httpMethod = "get", description = "认证中心首页")
    @Clear
    public void index() throws Exception {
        String appid = getRequest().getParameter("appid");
        String backurl = getRequest().getParameter("backurl");
        if (StringUtils.isBlank(appid)) {
            throw new RuntimeException("无效访问！");
        }
        // 判断请求认证系统是否注册
        Example upmsSystemExample = UpmsSystemCriteria.create()
                .andNameEqualTo(appid).example();
        Long count = UpmsSystem.service().count(upmsSystemExample);
        if (count!=null && 0 == count) {
            throw new RuntimeException(String.format("未注册的系统:%s", appid));
        }
        redirect("/sso/login?appid="+appid+"&backurl=" + URLEncoder.encode(backurl, "utf-8"));
    }

    @ApiOperation(url = "/sso/login", tag = "sso", httpMethod = "get", description = "登录")
    @Clear
    public void login() {
    	if (getRequest().getMethod().equals("GET")) {
    		Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            String serverSessionId = session.getId().toString();
            // 判断是否已登录，如果已登录，则回跳
            String code = Redis.use().get(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
            String username = (String) subject.getPrincipal();
            // code校验值
            if (StringUtils.isNotBlank(code) && StrKit.notBlank(username) && !username.equals("null")) {
                // 回跳
                String backurl = getRequest().getParameter("backurl");
                //String username = (String) subject.getPrincipal();
                if (StringUtils.isBlank(backurl)) {
                    backurl = "/";
                    backurl = subject.hasRole("admin") ? "/manage" : "";
                    backurl = subject.hasRole("super") ? "/manage" : "";
                } else {
                    if (backurl.contains("?")) {
                        backurl += "&upms_code=" + code + "&upms_username=" + username;
                    } else {
                        backurl += "?upms_code=" + code + "&upms_username=" + username;
                    }
                }
                System.out.println("认证中心帐号通过，带code回跳：{}" + backurl);
                _log.debug("认证中心帐号通过，带code回跳：{}", backurl);
                if(backurl.startsWith("/")) backurl = backurl.substring(1);
                redirect(backurl);
            } else {
            	keepPara();
            	renderTemplate("login.html");
            }
    	} else {
    		renderJson(tologinResult());
    	}
    }
    
    @Clear
    public void captcha() {
    	renderCaptcha();
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
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        System.out.println("sessionId: "+sessionId);
        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = Redis.use().get(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_ID + "_" + sessionId);
        // code校验值
        if (StringUtils.isBlank(hasCode)) {
            // 使用shiro认证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                if (BooleanUtils.toBoolean(rememberMe)) {
                    usernamePasswordToken.setRememberMe(true);
                } else {
                    usernamePasswordToken.setRememberMe(false);
                }
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_USERNAME, "帐号不存在！");
            } catch (IncorrectCredentialsException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD, "密码错误！");
            } catch (LockedAccountException e) {
                return new UpmsResult(UpmsResultConstant.INVALID_ACCOUNT, "帐号已锁定！");
            }
            // 更新session状态
            upmsSessionDao.updateStatus(sessionId, ShiroSession.OnlineStatus.on_line);
            // 全局会话sessionId列表，供会话管理
            Redis.use().lpush(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_IDS, sessionId.toString());
            // 默认验证帐号密码正确，创建code
            String code = UUID.randomUUID().toString();
            // 全局会话的code
            RedisUtil.set(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_ID + "_" + sessionId, code, (int) subject.getSession().getTimeout() / 1000);
            // code校验值
            RedisUtil.set(UpmsConstant.LAMBKIT_UPMS_SERVER_CODE + "_" + code, code, (int) subject.getSession().getTimeout() / 1000);
        }
        // 回跳登录前地址
        String backurl = getRequest().getParameter("backurl");
        System.out.println("backurl:"+backurl);
        if (StringUtils.isBlank(backurl)) {
        	if("admin".equals(username)) {
        		return new UpmsResult(UpmsResultConstant.SUCCESS, "/manage");
        	} else {
        		return new UpmsResult(UpmsResultConstant.SUCCESS, "/");
        	}
        } else {
            return new UpmsResult(UpmsResultConstant.SUCCESS, backurl);
        }
    }
    
    @ApiOperation(url = "/sso/authenticate", tag = "sso", httpMethod = "get", description = "用户认证")
    @Clear
    public void authenticate() {
    	String appid = getRequest().getParameter("appid");
    	String serverSessionId = getRequest().getParameter("lussid");
        if (StringUtils.isBlank(appid) || StringUtils.isBlank(serverSessionId)) {
        	System.out.println("无效访问appid is null");
        	renderJson(new UpmsResult(UpmsResultConstant.FAILED, "无效访问"));
        	return;
        }
        // 判断请求认证系统是否注册
        Example upmsSystemExample = UpmsSystemCriteria.create()
                .andNameEqualTo(appid).example();
        Long count = UpmsSystem.service().count(upmsSystemExample);
        if (count!=null && 0 == count) {
        	System.out.println(String.format("未注册的系统:%s", appid));
            renderJson(new UpmsResult(UpmsResultConstant.FAILED, String.format("未注册的系统:%s", appid)));
        	return;
        }
        // 判断是否已登录，如果已登录，则回跳
        String code = Redis.use().get(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
        // code校验值
        if (StringUtils.isNotBlank(code)) {
        	// 登录信息
            Subject subject = SecurityUtils.getSubject();
            String username = (String) subject.getPrincipal();
        	System.out.println("authenticate: "+username);
            renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, username, code));
        } else {
        	System.out.println("无效访问unlogin");
        	renderJson(new UpmsResult(UpmsResultConstant.FAILED, "unlogin"));
        }
    }

    @ApiOperation(url = "/sso/code", tag = "sso", httpMethod = "get", description = "校验code")
    @Clear
    public void code() {
    	renderJson(codeResult());
    }
    private Object codeResult() {
        String codeParam = getRequest().getParameter("code");
        String code = Redis.use().get(UpmsConstant.LAMBKIT_UPMS_SERVER_CODE + "_" + codeParam);
        if (StringUtils.isBlank(codeParam) || !codeParam.equals(code)) {
            new UpmsResult(UpmsResultConstant.FAILED, "无效code");
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, code);
    }

    @ApiOperation(url = "/sso/logout", tag = "sso", httpMethod = "get", description = "退出登录")
    public void logout() {
        // shiro退出登录
    	SecurityUtils.getSubject().logout();
        // 跳回原地址
        String redirectUrl = getRequest().getHeader("Referer");
        if (StrKit.isBlank(redirectUrl)) {
            redirectUrl = getPara("backurl", "/");
        }
        if(redirectUrl.startsWith("/")) redirectUrl = redirectUrl.substring(1);
        redirect(redirectUrl);
    }
    
    public void ajaxLogout() {
    	SecurityUtils.getSubject().logout();
    	renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, "logout"));
    }
    
    @RequiresAuthentication
    public void repswd() {
    	//原密码
    	String ypass = getPara("ypass");
		String xpass = getPara("password");
		String qrxpass = getPara("qrxpass");
		
		// 当前登录用户权限
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		
		UpmsApiService upmsApiService = AopKit.get(UpmsApiServiceImpl.class);
		UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
		if(upmsUser==null) {
			// shiro退出登录
			SecurityUtils.getSubject().logout();
	        renderJson(new UpmsResult(UpmsResultConstant.FAILED, "未登录"));
			return;
		}
		String md5pswd = EncryptUtils.MD5(xpass+upmsUser.getSalt());
		if(ypass.equals("")||xpass.equals("")||qrxpass.equals("")){
			renderJson(new UpmsResult(UpmsResultConstant.FAILED, "请填写完整信息"));
			return;
		}else if(!md5pswd.equals(upmsUser.getPassword())){
			renderJson(new UpmsResult(UpmsResultConstant.FAILED, "原密码错误"));
			return;
		}else if(ypass.equals(xpass)){
			renderJson(new UpmsResult(UpmsResultConstant.FAILED, "原密码不能和新密码一样"));
			return;
		}else if(!xpass.equals(qrxpass)){
			renderJson(new UpmsResult(UpmsResultConstant.FAILED, "新密码必须和确认新密码一样"));
			return;
		}else if(xpass.length()<6||xpass.length()>12){
			renderJson(new UpmsResult(UpmsResultConstant.FAILED, "新密码的长度为6-12位"));
			return;
		}
		upmsUser.setPassword(xpass);
		boolean flag = upmsUser.save();
		if(flag) renderJson(new UpmsResult(UpmsResultConstant.SUCCESS, "更新成功"));
		else renderJson(new UpmsResult(UpmsResultConstant.FAILED, "更新失败"));
    }
    
    /**
	 * 用户注册
	 */
    @Clear
	public void regist() {
		if (getRequest().getMethod().equals("GET")) {
			keepPara();
			renderTemplate("regist.html");
		} else {
			renderJson(doRegist());
		}
	}

	private UpmsResult doRegist() {
		final String username = getRequest().getParameter("username");
		final String password = getRequest().getParameter("password");
		String repswd = getRequest().getParameter("repswd");
		String captcha = getRequest().getParameter("captcha");
		if (StringUtils.isBlank(username)) {
			return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME, "帐号不能为空！");
		}
		if (StringUtils.isBlank(password)) {
			return new UpmsResult(UpmsResultConstant.EMPTY_PASSWORD, "密码不能为空！");
		}
		if (StringUtils.isBlank(repswd)) {
			return new UpmsResult(UpmsResultConstant.EMPTY_REPSWD, "确认密码不能为空！");
		}
		if (!password.equals(repswd)) {
			return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD, "密码不一致！");
		}
		if (StringUtils.isBlank(captcha)) {
			return new UpmsResult(UpmsResultConstant.EMPTY_CAPTCHA, "验证码不能为空！");
		}
		if (!validateCaptcha("captcha")) {
			return new UpmsResult(UpmsResultConstant.INVALID_CAPTCHA, "验证码错误！");
		}

		boolean flag = Db.tx(new IAtom() {
			
			public boolean run() throws SQLException {
				// TODO Auto-generated method stub
				UpmsUserService upmsUserService = AopKit.get(UpmsUserServiceImpl.class);
				UpmsUser upmsUser = getModel(UpmsUser.class, "user");
				upmsUser.setUsername(username);
				upmsUser.setSalt(StrKit.getRandomUUID());
				String md5pswd = EncryptUtils.MD5(password+upmsUser.getSalt());
				upmsUser.setPassword(md5pswd);
				String realname = getPara("realname");
				if(StringUtils.isNotBlank(realname)) upmsUser.setRealname(realname);
				String email = getPara("email");
				if(StringUtils.isNotBlank(email)) upmsUser.setEmail(email);
				String phone = getPara("phone");
				if(StringUtils.isNotBlank(phone)) upmsUser.setPhone(phone);
				upmsUser.setLocked(getParaToInt("locked", 0));
				upmsUser.setSex(getParaToInt("sex", 1));
				upmsUser.setCtime(DateTimeUtils.getCurrentTimeLong());
				upmsUser.setAvatar(getPara("avatar", "/lambkit/assets/upms/images/avatar.jpg"));
				UpmsUser user = upmsUserService.createUser(upmsUser);
				if (user == null) {
					return false;
				}
				UpmsUserRole upmsUserRole = new UpmsUserRole();
				upmsUserRole.setUserId(upmsUser.getUserId());
				upmsUserRole.setRoleId(3);
				boolean rflag = upmsUserRole.save();
				return rflag;
			}
		});
		if (!flag) {
			return new UpmsResult(UpmsResultConstant.FAILED, "注册失败！");
		}
		
		// 回跳登录前地址
		String backurl = getRequest().getParameter("backurl");
		System.out.println("backurl:" + backurl);
		if (StringUtils.isBlank(backurl)) {
			if ("admin".equals(username)) {
				return new UpmsResult(UpmsResultConstant.SUCCESS, "/manage");
			} else {
				return new UpmsResult(UpmsResultConstant.SUCCESS, "/");
			}
		} else {
			return new UpmsResult(UpmsResultConstant.SUCCESS, backurl);
		}
	}
	
	@Clear
	@ApiOperation(url = "/sso/needPermission", tag = "sso", httpMethod = "get", description = "权限提示页面")
	public void needPermission() {
		renderTemplate("/lambkit/errors/needPermission.html");
	}
}