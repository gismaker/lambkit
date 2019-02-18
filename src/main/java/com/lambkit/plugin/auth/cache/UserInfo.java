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

import java.util.HashMap;
import java.util.Map;

import com.lambkit.plugin.auth.IUser;


public class UserInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1807371433833181263L;
	
	private IUser user = null;
	private Map<String, Object> info = null;
	
	public UserInfo(IUser u) {
		setUser(u);
		info = new HashMap<String, Object>();
	}
	
	public void put(String key, Object value) {
		info.put(key, value);
	}
	
	public Object get(String key) {
		return info.get(key);
	}

	public Map<String, Object> getInfo() {
		return info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}

	public IUser getUser() {
		return user;
	}

	public void setUser(IUser user) {
		this.user = user;
	}
}
