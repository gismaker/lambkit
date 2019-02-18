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
package com.lambkit.plugin.auth.cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.cache.ICache;
import com.lambkit.common.util.RequestUtils;
import com.lambkit.plugin.auth.IUser;
import com.lambkit.plugin.auth.cache.UserInfo;

public abstract class UserCacheBase implements IUserCache {

	public abstract ICache getCache();
	
	@Override
	public boolean hasUser(String sessionid) {
		// TODO Auto-generated method stub
		IUser user = getUser(sessionid);
		if (user == null) {
			return false;
		}
		System.out.println("hasUser info : the user is " + user.getName());
		String session_id = getCache().get("ehCacheSession", user.getName());
		if (StrKit.notBlank(session_id) && !session_id.equals(sessionid)) {
			return false;
		}
		return true;
	}

	/*
	@Override
	public boolean hasUser(Controller c) {
		// TODO Auto-generated method stub
		return hasUser(c.getSession().getId());
	}
	*/

	@Override
	public UserInfo getUserInfo(String sessionid) {
		// TODO Auto-generated method stub
		return getCache().get("ehCacheSession", sessionid);
	}

	/*
	@Override
	public UserInfo getUserInfo(Controller c) {
		// TODO Auto-generated method stub
		return getUserInfo(c.getSession().getId());
	}
	*/

	@Override
	public void saveInfo(String key, Object value, String sessionid) {
		// TODO Auto-generated method stub
		UserInfo uc = getUserInfo(sessionid);
		if (uc != null)
			uc.put(key, value);
	}

	@Override
	public Object getInfo(String key, String sessionid) {
		// TODO Auto-generated method stub
		UserInfo uc = getUserInfo(sessionid);
		if (uc != null)
			return uc.get(key);
		return null;
	}

	@Override
	public IUser getUser(String sessionid) {
		// TODO Auto-generated method stub
		UserInfo uc = getUserInfo(sessionid);
		if (uc != null)
			return uc.getUser();
		return null;
	}

	/*
	@Override
	public IUser getUser(Controller c) {
		// TODO Auto-generated method stub
		return getUser(c.getSession().getId());
	}
	*/

	@Override
	public String getSessionId(String username) {
		// TODO Auto-generated method stub
		String oldsessionid = getCache().get("ehCacheSession", username);
		if (StrKit.notBlank(oldsessionid)) {
			return oldsessionid;
		}
		return null;
	}

	@Override
	public int loginStatus(String username, String sessionid) {
		// TODO Auto-generated method stub
		// 用户已登录
		if (hasUser(sessionid))
			return 1;
		else {
			String oldsessionid = getCache().get("ehCacheSession", username);
			System.out.println("loginStatus :" + oldsessionid);
			if (StrKit.notBlank(oldsessionid)) {
				// 用户在别处已登录
				return 2;
			} else {
				return 0;
			}
		}
	}

	@Override
	public UserInfo saveUser(IUser user, String sessionid) {
		// TODO Auto-generated method stub
		String oldsessionid = getCache().get("ehCacheSession", user.getName());
		if (StrKit.notBlank(oldsessionid)) {
			removeUser(oldsessionid);
		}
		UserInfo userinfo = new UserInfo(user);
		// 保存uset
		getCache().put("ehCacheSession", sessionid, userinfo);
		// 保存session
		getCache().put("ehCacheSession", user.getName(), sessionid);
		return userinfo;
	}

	@Override
	public UserInfo saveLoginUser(IUser user, Controller c, String sessionid) {
		// TODO Auto-generated method stub
		//c.setSessionAttr("lambuser", user.getId());
		//Timestamp time = new Timestamp(System.currentTimeMillis());
		//DateTimeUtils.format(time, "yyyy-MM-dd HH:mm:ss")
		c.setSessionAttr("lambkituser", user.getName());
		// new OnlineSession(getRemoteLoginUserIp(), user.getName(), ctime));
		System.out.println("login: " + user.getName() + " [" + getRemoteLoginUserIp(c.getRequest()) + "]");
		return saveUser(user, sessionid);
	}
	
	@Override
	public UserInfo saveLoginUser(IUser user, HttpServletRequest request, String sessionid) {
		// TODO Auto-generated method stub
		//c.setSessionAttr("lambuser", user.getId());
		//Timestamp time = new Timestamp(System.currentTimeMillis());
		//DateTimeUtils.format(time, "yyyy-MM-dd HH:mm:ss")
		request.getSession(true).setAttribute("lambkituser", user.getName());
		// new OnlineSession(getRemoteLoginUserIp(), user.getName(), ctime));
		System.out.println("login: " + user.getName() + " [" + getRemoteLoginUserIp(request) + "]");
		return saveUser(user, sessionid);
	}

	@Override
	public void removeLoginUser(Controller c, String sessionid) {
		// TODO Auto-generated method stub
		c.removeSessionAttr("lambkituser");
		removeUser(sessionid);
	}
	
	@Override
	public void removeLoginUser(HttpServletRequest request, String sessionid) {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.removeAttribute("lambkituser");
		removeUser(sessionid);
	}

	@Override
	public void removeUser(String sessionid) {
		// TODO Auto-generated method stub
		// 移除session
		IUser user = getUser(sessionid);
		if (user != null) {
			getCache().remove("ehCacheSession", user.getName());
		}
		// 移除user
		getCache().remove("ehCacheSession", sessionid);
	}

	@Override
	public String getRemoteLoginUserIp(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return RequestUtils.getIpAddress(request);
	}

}
