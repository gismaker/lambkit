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
 */package com.lambkit.plugin.jwt.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;

import com.lambkit.common.util.EncryptUtils;
import com.lambkit.core.aop.AopKit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.module.upms.UpmsConfig;
import com.lambkit.module.upms.rpc.api.UpmsApiService;
import com.lambkit.module.upms.rpc.model.UpmsPermission;
import com.lambkit.module.upms.rpc.model.UpmsRole;
import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.module.upms.rpc.service.impl.UpmsApiServiceImpl;
import com.lambkit.plugin.jwt.IJwtAble;
import com.lambkit.plugin.jwt.IJwtUserService;

public class UpmsJwtUserService implements IJwtUserService {

	private static UpmsJwtUserService service;

	private UpmsApiService upmsApiService;

	private UpmsConfig upmsConfig = ConfigManager.me().get(UpmsConfig.class);

	private UpmsApiService getUpmsApiService() {
		if (upmsApiService == null) {
			if ("client".equals(upmsConfig.getType())) {
				upmsApiService = RpcKit.obtain(UpmsApiService.class);
			} else {
				upmsApiService = AopKit.get(UpmsApiServiceImpl.class);
			}
		}
		return upmsApiService;
	}

	public static UpmsJwtUserService me() {
		if (service == null) {
			service = new UpmsJwtUserService();
		}
		return service;
	}

	@Override
	public IJwtAble login(String userName, String password) {
		// TODO Auto-generated method stub
		// 查询用户信息
		UpmsUser upmsUser = getUpmsApiService().selectUpmsUserByUsername(userName);
		if (null == upmsUser) {
			throw new UnknownAccountException();
		}
		if (!upmsUser.getPassword().equals(EncryptUtils.MD5(password + upmsUser.getSalt()))) {
			throw new IncorrectCredentialsException();
		}
		if (upmsUser.getLocked() == 1) {
			throw new LockedAccountException();
		}
		return createJwtUser(upmsUser);
	}

	public JwtUser createJwtUser(UpmsUser upmsUser) {
		JwtUser user = new JwtUser();
		user.setUserId(upmsUser.getUserId());
		user.setUserName(upmsUser.getUsername());

		// 当前用户所有角色
		List<UpmsRole> upmsRoles = getUpmsApiService().selectUpmsRoleByUpmsUserId(upmsUser.getUserId());
		Set<String> roles = new HashSet<>();
		for (UpmsRole upmsRole : upmsRoles) {
			if (StringUtils.isNotBlank(upmsRole.getName())) {
				roles.add(upmsRole.getName());
			}
		}
		user.setRoles(roles);

		// 当前用户所有权限
		List<UpmsPermission> upmsPermissions = getUpmsApiService()
				.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
		Set<String> permissions = new HashSet<>();
		for (UpmsPermission upmsPermission : upmsPermissions) {
			if (StringUtils.isNotBlank(upmsPermission.getPermissionValue())) {
				permissions.add(upmsPermission.getPermissionValue());
			}
		}
		user.setForces(permissions);

		return user;
	}

	@Override
	public IJwtAble getJwtAbleInfo(String userName) {
		// 查询用户信息
		UpmsUser upmsUser = getUpmsApiService().selectUpmsUserByUsername(userName);
		if (null == upmsUser) {
			return null;
		}
		return createJwtUser(upmsUser);
	}

}
