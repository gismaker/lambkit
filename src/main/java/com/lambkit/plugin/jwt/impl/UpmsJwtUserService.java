package com.lambkit.plugin.jwt.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;

import com.lambkit.common.util.ClassNewer;
import com.lambkit.common.util.EncryptUtils;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.module.upms.common.UpmsConfig;
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
				upmsApiService = ClassNewer.newInstance(UpmsApiServiceImpl.class);
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
