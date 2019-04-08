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
package com.lambkit.module.upms.client.shiro;

import com.lambkit.common.aop.AopKit;
import com.lambkit.common.util.EncryptUtils;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.module.upms.common.UpmsConfig;
import com.lambkit.module.upms.rpc.model.UpmsPermission;
import com.lambkit.module.upms.rpc.model.UpmsRole;
import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.module.upms.rpc.api.UpmsApiService;
import com.lambkit.module.upms.rpc.service.impl.UpmsApiServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户认证和授权
 */
public class UpmsRealm extends AuthorizingRealm {

    //private static Logger _log = LoggerFactory.getLogger(UpmsRealm.class);

    private UpmsApiService upmsApiService;
    
    private UpmsConfig upmsConfig = ConfigManager.me().get(UpmsConfig.class);
    
    private UpmsApiService getUpmsApiService() {
    	if(upmsApiService==null) {
    		if("client".equals(upmsConfig.getType())) {
    			upmsApiService = RpcKit.obtain(UpmsApiService.class);
    		} else {
    			upmsApiService = AopKit.newInstance(UpmsApiServiceImpl.class);
    		}
    	}
    	return upmsApiService;
    }
    /**
     * 授权：验证权限时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("realm username: " + username);
        UpmsUser upmsUser = getUpmsApiService().selectUpmsUserByUsername(username);

        // 当前用户所有角色
        List<UpmsRole> upmsRoles = getUpmsApiService().selectUpmsRoleByUpmsUserId(upmsUser.getUserId());
        Set<String> roles = new HashSet<>();
        for (UpmsRole upmsRole : upmsRoles) {
            if (StringUtils.isNotBlank(upmsRole.getName())) {
                roles.add(upmsRole.getName());
            }
        }

        // 当前用户所有权限
        List<UpmsPermission> upmsPermissions = getUpmsApiService().selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        Set<String> permissions = new HashSet<>();
        for (UpmsPermission upmsPermission : upmsPermissions) {
            if (StringUtils.isNotBlank(upmsPermission.getPermissionValue())) {
                permissions.add(upmsPermission.getPermissionValue());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证：登录时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        // client无密认证
        String upmsType = upmsConfig.getType();
        if ("client".equals(upmsType)) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }
        // 查询用户信息
        UpmsUser upmsUser = getUpmsApiService().selectUpmsUserByUsername(username);
        if (null == upmsUser) {
            throw new UnknownAccountException();
        }
        if (!upmsUser.getPassword().equals(EncryptUtils.MD5(password + upmsUser.getSalt()))) {
            throw new IncorrectCredentialsException();
        }
        if (upmsUser.getLocked() == 1) {
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
