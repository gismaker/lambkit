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

import com.jfinal.core.Controller;
import com.lambkit.plugin.auth.IUser;

public interface IUserCache {

	/**
	 * 用户是否存在，(等同于用户是否登陆)
	 * 
	 * @return
	 */
	public boolean hasUser(String sessionid);
	
	//public boolean hasUser(Controller c);

	/**
	 * 用户缓存信息
	 * 
	 * @return
	 */
	public UserInfo getUserInfo(String sessionid);
	
	//public UserInfo getUserInfo(Controller c);

	public void saveInfo(String key, Object value, String sessionid);

	public Object getInfo(String key, String sessionid);

	/**
	 * 用户
	 * 
	 * @return
	 */
	public IUser getUser(String sessionid);
	
	//public IUser getUser(Controller c);
	
	public String getSessionId(String username);
	
	public int loginStatus(String username, String sessionid);

	public UserInfo saveUser(IUser user, String sessionid);
	
	public void removeUser(String sessionid);
	
	public UserInfo saveLoginUser(IUser user, Controller c, String sessionid);
	
	public UserInfo saveLoginUser(IUser user, HttpServletRequest request, String sessionid);

	public void removeLoginUser(Controller c, String sessionid);
	
	public void removeLoginUser(HttpServletRequest request, String sessionid);
	
	/**
	 * 获取客户端真实的ip地址
	 * @return
	 */
	public String getRemoteLoginUserIp(HttpServletRequest request);

}
