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
package com.lambkit.module.upms;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.redis.Redis;
import com.lambkit.common.LambkitResult;
import com.lambkit.common.util.EncryptUtils;
import com.lambkit.common.util.RedisUtil;
import com.lambkit.component.shiro.session.ShiroSession;
import com.lambkit.core.aop.AopKit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.plugin.auth.AuthService;
import com.lambkit.plugin.auth.IUser;
import com.lambkit.plugin.auth.RoleService;
import com.lambkit.plugin.auth.cache.UserInfo;
import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.module.upms.rpc.api.UpmsApiService;
import com.lambkit.module.upms.rpc.service.impl.UpmsApiServiceImpl;
import com.lambkit.module.upms.shiro.ShiroRedisSessionDao;

public class UpmsAuthServiceImpl implements AuthService {
	
	ShiroRedisSessionDao upmsSessionDao = AopKit.get(ShiroRedisSessionDao.class);

	private UmpsAuthRoleServiceImpl roleService;
	
	private UpmsApiService upmsApiService;
    
    private UpmsApiService getUpmsApiService() {
    	if(upmsApiService==null) {
    		UpmsConfig upmsConfig = ConfigManager.me().get(UpmsConfig.class);
    		if("client".equals(upmsConfig.getType())) {
    			upmsApiService = RpcKit.obtain(UpmsApiService.class);
    		} else {
    			upmsApiService = AopKit.get(UpmsApiServiceImpl.class);
    		}
    	}
    	return upmsApiService;
    }
	
	@Override
	public RoleService getRoleService() {
		// TODO Auto-generated method stub
		if (roleService == null) {
			roleService = new UmpsAuthRoleServiceImpl(this);
		}
		return roleService;
	}

	@Override
	public boolean user() {
		// TODO Auto-generated method stub
		Subject subject = org.apache.shiro.SecurityUtils.getSubject();
		Session session = subject.getSession();
        String serverSessionId = session.getId().toString();
		String code = SsoAuthenticate.code(serverSessionId);
		return StrKit.notBlank(code) ? true : false;
	}

	@Override
	public IUser getUser() {
		// TODO Auto-generated method stub
		Subject subject = org.apache.shiro.SecurityUtils.getSubject();
		Object obj = subject.getPrincipal();
		if (obj == null) {
			return null;
		} else {
			String username = (String) subject.getPrincipal();
			UpmsAuth user = new UpmsAuth(getUpmsApiService().selectUpmsUserByUsername(username));
			return user;
		}
	}
	
	public LambkitResult login(HttpServletRequest request, String username, String password, boolean rememberMe) {
		UpmsResult upmsResult = doLogin(request, username, password, rememberMe);
		if(upmsResult.getCode()==UpmsResultConstant.SUCCESS.getCode()) {
			loginSuccess(request, username);
		}
		return upmsResult;
	}
	
	public LambkitResult login(Controller controller, String username, String password, boolean rememberMe) {
		UpmsResult upmsResult = doLogin(controller.getRequest(), username, password, rememberMe);
		if(upmsResult.getCode()==UpmsResultConstant.SUCCESS.getCode()) {
			loginSuccess(controller, username);
		}
		return upmsResult;
	}

	private UpmsResult doLogin(HttpServletRequest request, String username, String password, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        usernamePasswordToken.setRememberMe(rememberMe);
        try {
            subject.login(usernamePasswordToken);
    	} catch (UnknownAccountException e) {
            return new UpmsResult(UpmsResultConstant.INVALID_USERNAME, "帐号不存在！");
        } catch (IncorrectCredentialsException e) {
            return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD, "密码错误！");
        } catch (LockedAccountException e) {
            return new UpmsResult(UpmsResultConstant.INVALID_ACCOUNT, "帐号已锁定！");
        }
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        // 更新session状态
        ShiroRedisSessionDao upmsSessionDao = Enhancer.enhance(ShiroRedisSessionDao.class);
        upmsSessionDao.updateStatus(sessionId, ShiroSession.OnlineStatus.on_line);
        // 全局会话sessionId列表，供会话管理
        Redis.use().lpush(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_IDS, sessionId.toString());
        // 默认验证帐号密码正确，创建code
        String code = UUID.randomUUID().toString();
        // 全局会话的code
        RedisUtil.set(UpmsConstant.LAMBKIT_UPMS_SERVER_SESSION_ID + "_" + sessionId, code, (int) subject.getSession().getTimeout() / 1000);
        // code校验值
        RedisUtil.set(UpmsConstant.LAMBKIT_UPMS_SERVER_CODE + "_" + code, code, (int) subject.getSession().getTimeout() / 1000);
        //loginSuccess(request, username);
        return new UpmsResult(UpmsResultConstant.SUCCESS, username);
	}

	public void loginSuccess(Controller controller, String username) {
		loginSuccess(controller.getRequest(), username);
	}
	
	public void loginSuccess(HttpServletRequest request, String username) {
		UpmsAuth user = new UpmsAuth(getUpmsApiService().selectUpmsUserByUsername(username));
		UserInfo usercache = AuthManager.me().getCache().saveLoginUser(user, request, getSessionId(request));
		// 保存用户role和类型type信息
		usercache.put("usertype", user.getType());
	}

	public LambkitResult logout(HttpServletRequest request) {
		// shiro退出登录
		SecurityUtils.getSubject().logout();
		logoutSuccess(request);
		// 跳回原地址
		String redirectUrl = request.getHeader("Referer");
		return new UpmsResult(UpmsResultConstant.SUCCESS, redirectUrl);
	}
	
	public LambkitResult logout(Controller controller) {
		// shiro退出登录
		SecurityUtils.getSubject().logout();
		logoutSuccess(controller);
		// 跳回原地址
		String redirectUrl = controller.getHeader("Referer");
		return new UpmsResult(UpmsResultConstant.SUCCESS, redirectUrl);
	}

	public void logoutSuccess(HttpServletRequest request) {
		request.getSession().invalidate();
		AuthManager.me().getCache().removeLoginUser(request, getSessionId(request));
	}
	
	public void logoutSuccess(Controller controller) {
		controller.getSession().invalidate();
		AuthManager.me().getCache().removeLoginUser(controller.getRequest(), getSessionId(controller.getRequest()));
	}

	@Override
	public String getPasswordSecurity(String username, String password) {
		// TODO Auto-generated method stub
		UpmsUser user = getUpmsApiService().selectUpmsUserByUsername(username);
		if(user!=null && StrKit.notBlank(password)) {
			return EncryptUtils.MD5(password + user.getSalt());
		}
		return null;
	}

	@Override
	public IUser findByUsernameForLogin(String userName) {
		// TODO Auto-generated method stub
		UpmsUser user = getUpmsApiService().selectUpmsUserByUsername(userName);
		return new UpmsAuth(user);
	}

	@Override
	public Boolean isGuestRule(String controlkey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getRoles(Object userid) {
		// TODO Auto-generated method stub
		if(userid!=null) {
			return getUpmsApiService().selectUpmsRoleByUpmsUserId(Long.valueOf(userid.toString()));
		}
		return null;
	}

	@Override
	public List<?> getRules(Object userid) {
		// TODO Auto-generated method stub
		if(userid!=null) {
			return getUpmsApiService().selectUpmsPermissionByUpmsUserId(Long.valueOf(userid.toString()));
		}
		return null;
	}

	@Override
	public String getSessionId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Subject subject = SecurityUtils.getSubject();
		return subject.getSession().getId().toString();
	}

	@Override
	public Boolean hasRole(String roleid) {
		// TODO Auto-generated method stub
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null) subject.hasRole(roleid);
		return false;
	}

	@Override
	public Boolean hasRule(int ruleid) {
		// TODO Auto-generated method stub
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null) subject.isPermitted(String.valueOf(ruleid));
		return false;
	}

	@Override
	public boolean authenticated() {
		// TODO Auto-generated method stub
		Subject subject = org.apache.shiro.SecurityUtils.getSubject();
		return subject.isAuthenticated();
	}

	@Override
	public boolean notAuthenticated() {
		// TODO Auto-generated method stub
		Subject subject = org.apache.shiro.SecurityUtils.getSubject();
		return subject.isRemembered();
	}

	@Override
	public boolean guest() {
		// TODO Auto-generated method stub
		Subject subject = org.apache.shiro.SecurityUtils.getSubject();
		return subject.hasRole("guest");
	}

	@Override
	public Boolean hasRole(int roleid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean lacksRole(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAnyRoles(String roleNames) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAllRoles(String roleNames) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRule(String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lacksRule(String permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
