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
package com.lambkit.plugin.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.lambkit.common.util.EncryptUtils;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.plugin.auth.AuthService;
import com.lambkit.plugin.auth.IUser;
import com.lambkit.plugin.auth.cache.IUserCache;

public class AuthManager {
	private static AuthManager me = new AuthManager();
	
	private IUserCache cache;
	private AuthService service;

	private AuthManager() {
	}

	public static AuthManager me() {
		return me;
	}
	
	public void init(Class<? extends IUserCache> cacheClazz, Class<? extends AuthService> authClazz) {
		try {
			setCache(cacheClazz.newInstance());
			setService(authClazz.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
	}
	
	public IUserCache getCache() {
		return cache;
	}

	public void setCache(IUserCache cache) {
		this.cache = cache;
	}
	
	public AuthService getService() {
		return service;
	}

	public void setService(AuthService auth) {
		this.service = auth;
	}
	
	public RoleService getRoleService() {
		if(this.service!=null) {
			return this.service.getRoleService();
		}
		return null;
 	}
	
	public boolean hasUser(Controller c) {
		if(cache!=null && service!=null) {
			if(service.user() && !cache.hasUser(sesseionId(c))) {
				cache.saveUser(service.getUser(), sesseionId(c));
			} else if(!service.user() && cache.hasUser(sesseionId(c))) {
				cache.removeLoginUser(c, sesseionId(c));
			}
			return service.user() && cache.hasUser(sesseionId(c));
		}
		return false;
	}
	
	public boolean hasUser(HttpServletRequest request) {
		if(cache!=null && service!=null) {
			if(service.user() && !cache.hasUser(service.getSessionId(request))) {
				cache.saveUser(service.getUser(), service.getSessionId(request));
			} else if(!service.user() && cache.hasUser(service.getSessionId(request))) {
				cache.removeLoginUser(request, service.getSessionId(request));
			}
			return service.user() && cache.hasUser(service.getSessionId(request));
		}
		return false;
	}
	
	public IUser getUser(Controller c) {
		if(cache!=null && service!=null) {
			return cache.getUser(service.getSessionId(c.getRequest()));
		}
		return null;
	}
	
	public IUser getUser(HttpServletRequest request) {
		if(cache!=null && service!=null) {
			return cache.getUser(service.getSessionId(request));
		}
		return null;
	}
	
	public String sesseionId(Controller c) {
		if(service!=null) {
			return service.getSessionId(c.getRequest());
		}
		return c.getSession().getId();
	}

	/**
	 * 获取加密后的密码
	 * 
	 * @param pass
	 *            未加密的密码
	 * @return
	 */
	public String getPasswordSecurity(String username, String password) {
		if (!StrKit.notBlank(password))
			return null;
		return service != null ? service.getPasswordSecurity(username, password) : EncryptUtils.md5WithPrefix(password);
	}

	/**
	 * 获取登录的用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public IUser findByUsernameForLogin(String userName) {
		return service != null ? service.findByUsernameForLogin(userName) : null;
	}
	
	/**
	 * shiro use that where rule be require guest
	 * 判断rule权限是否为guest权限
	 * @param controlkey
	 * @return
	 */
	public Boolean isGuestRule(String controlkey) {
		return service != null ? service.isGuestRule(controlkey) : null;
	}
	
	public List<?> getRoles(Object id) {
		return service != null ? service.getRoles(id) : null;
	}
	
	public List<?> getRules(Object id) {
		return service != null ? service.getRules(id) : null;
	}
}
