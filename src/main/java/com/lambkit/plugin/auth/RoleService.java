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

import com.jfinal.core.Controller;

public interface RoleService {
	
	public boolean isSuperAdmin();
	
	public boolean isAdmin();
	
	/*
	public boolean isManager() {
		return isThisRole(sessionid, "2");
	}
	
	public boolean isAreaMgr() {
		return isThisRole(sessionid, "3");
	}

	public boolean isRecorder() {
		return isThisRole(sessionid, "4");
	}
	*/
	
	public boolean isThisRole(String roleid);
	
	/**
	 * 获取用户的角色ID
	 * @param sessionid
	 * @return
	 */
	public Integer getRoleId();
	
	/**
	 * 自己填报的数据自己修改
	 * @param sessionid
	 * @param uuid
	 * @return
	 */
	public boolean ownTask(String uuid);
	
	public boolean ownTask(Controller c, String uuid);
	
	/**
	 * 自己填报的数据自己修改
	 * @param sessionid
	 * @param uid
	 * @return
	 */
	public boolean tryOwnTaskById(Object uid);
	
	public boolean tryOwnTaskById(Controller c, Object uid);
	
	public boolean tryOwnTask(String tbname, String idname, Object idvalue, String uuid);
	
	public boolean tryOwnTask(Controller c, String tbname, String idname, Object idvalue, String uuid);
	
	//
	public boolean hasPermission(Object roleId, Object permissionId);

    public boolean hasRole(Object userId, Object roleId);

    public boolean hasRole(Object roleId);

    public boolean hasRole(String roleFlag);

    public boolean hasRole(Object userId, String roleFlag);

	public boolean hasAnyRole(Object userId, String[] roles);

}
