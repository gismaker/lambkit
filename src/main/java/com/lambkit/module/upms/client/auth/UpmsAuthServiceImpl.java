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
package com.lambkit.module.upms.client.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.kit.StrKit;
import com.lambkit.common.base.BaseResult;
import com.lambkit.common.util.ClassNewer;
import com.lambkit.common.util.EncryptUtils;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.plugin.auth.AuthService;
import com.lambkit.plugin.auth.IUser;
import com.lambkit.plugin.auth.RoleService;
import com.lambkit.plugin.auth.cache.UserInfo;
import com.lambkit.module.upms.client.shiro.ShiroRedisSessionDao;
import com.lambkit.module.upms.common.UpmsConfig;
import com.lambkit.module.upms.common.UpmsResult;
import com.lambkit.module.upms.common.UpmsResultConstant;
import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.module.upms.rpc.api.UpmsApiService;
import com.lambkit.module.upms.rpc.service.impl.UpmsApiServiceImpl;

public class UpmsAuthServiceImpl implements AuthService {
	
	ShiroRedisSessionDao upmsSessionDao = ClassNewer.newInstance(ShiroRedisSessionDao.class);

	private UmpsAuthRoleServiceImpl roleService;
	
	private UpmsApiService upmsApiService;
    
    private UpmsApiService getUpmsApiService() {
    	if(upmsApiService==null) {
    		UpmsConfig upmsConfig = ConfigManager.me().get(UpmsConfig.class);
    		if("client".equals(upmsConfig.getType())) {
    			upmsApiService = RpcKit.obtain(UpmsApiService.class);
    		} else {
    			upmsApiService = ClassNewer.newInstance(UpmsApiServiceImpl.class);
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
		return subject.isAuthenticated();
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
			UpmsClientUser user = new UpmsClientUser(getUpmsApiService().selectUpmsUserByUsername(username));
			return user;
		}
	}

	public BaseResult login(HttpServletRequest request, String username, String password, boolean rememberMe) {
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
        loginSuccess(request, username);
        return new UpmsResult(UpmsResultConstant.SUCCESS, username);
	}

	public void loginSuccess(HttpServletRequest request, String username) {
		UpmsClientUser user = new UpmsClientUser(getUpmsApiService().selectUpmsUserByUsername(username));
		UserInfo usercache = AuthManager.me().getCache().saveLoginUser(user, request, getSessionId(request));
		// 保存用户role和类型type信息
		usercache.put("usertype", user.getType());
	}

	public BaseResult logout(HttpServletRequest request) {
		// shiro退出登录
		SecurityUtils.getSubject().logout();
		logoutSuccess(request);
		// 跳回原地址
		String redirectUrl = request.getHeader("Referer");
		if (null == redirectUrl) {
			redirectUrl = "/";
		}
		return new UpmsResult(UpmsResultConstant.SUCCESS, redirectUrl);
	}

	public void logoutSuccess(HttpServletRequest request) {
		request.getSession().invalidate();
		AuthManager.me().getCache().removeLoginUser(request, getSessionId(request));
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
		return new UpmsClientUser(user);
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
