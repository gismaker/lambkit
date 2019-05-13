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

import com.jfinal.core.Controller;
import com.lambkit.plugin.auth.IRole;
import com.lambkit.plugin.auth.IUser;
import com.lambkit.plugin.auth.RoleService;

/**
 * 未完成
 * @author yangyong
 */
public class UmpsAuthRoleServiceImpl implements RoleService {

	private UpmsAuthServiceImpl authService;
	
	public UmpsAuthRoleServiceImpl(UpmsAuthServiceImpl authService) {
		// TODO Auto-generated constructor stub
		this.authService = authService;
	}
	
	@Override
	public boolean isSuperAdmin() {
		// TODO Auto-generated method stub
		if(authService.user()) {
			return authService.hasRole("super");
		}
		return false;
	}

	@Override
	public boolean isAdmin() {
		// TODO Auto-generated method stub
		if(authService.user()) {
			return authService.hasRole("super") || authService.hasRole("admin");
		}
		return false;
	}

	@Override
	public boolean isThisRole(String roleid) {
		// TODO Auto-generated method stub
		if(authService.user()) {
			return authService.hasRole(roleid);
		}
		return false;
	}

	@Override
	public Integer getRoleId() {
		// TODO Auto-generated method stub
		if(authService.user()) {
			IUser user = authService.getUser();
			List<?> list = authService.getRoles(user.getId());
			if(list!=null && list.size() > 0) {
				IRole role = (IRole) list.get(0);
				return Integer.valueOf(role.getId().toString());
			}
		}
		return null;
	}

	@Override
	public boolean ownTask(String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ownTask(Controller c, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryOwnTaskById(Object uid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryOwnTaskById(Controller c, Object uid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryOwnTask(String tbname, String idname, Object idvalue, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryOwnTask(Controller c, String tbname, String idname, Object idvalue, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPermission(Object roleId, Object permissionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRole(Object userId, Object roleId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRole(Object roleId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRole(String roleFlag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRole(Object userId, String roleFlag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAnyRole(Object userId, String[] roles) {
		// TODO Auto-generated method stub
		return false;
	}

}
