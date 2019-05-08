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
package com.lambkit.module.upms.rpc.service.impl;

import com.lambkit.component.shiro.processer.AuthorizeResult;
import com.lambkit.component.shiro.processer.IShiroAuthorizeProcesser;
import com.lambkit.component.shiro.processer.ShiroRequiresAuthenticationProcesser;
import com.lambkit.component.shiro.processer.ShiroRequiresGuestProcesser;
import com.lambkit.component.shiro.processer.ShiroRequiresPermissionsProcesser;
import com.lambkit.component.shiro.processer.ShiroRequiresRolesProcesser;
import com.lambkit.component.shiro.processer.ShiroRequiresUserProcesser;
import com.lambkit.core.aop.AopKit;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.rpc.model.*;
import com.lambkit.module.upms.rpc.model.sql.*;
import com.lambkit.module.upms.rpc.api.UpmsApiService;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * UpmsApiService实现
 */


public class UpmsApiServiceImpl implements UpmsApiService {

    private static Logger _log = LoggerFactory.getLogger(UpmsApiServiceImpl.class);

    UpmsApiQuery upmsApiQuery = AopKit.newInstance(UpmsApiQuery.class);
    
    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Long upmsUserId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = UpmsUser.service().findByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            _log.info("selectUpmsPermissionByUpmsUserId : upmsUserId={}", upmsUserId);
            return null;
        }
        List<UpmsPermission> upmsPermissions = upmsApiQuery.selectUpmsPermissionByUpmsUserId(upmsUserId);
        return upmsPermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    
    //@Cacheable(value = "zheng-upms-rpc-service-ehcache", key = "'selectUpmsPermissionByUpmsUserId_' + #upmsUserId")
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Long upmsUserId) {
        return selectUpmsPermissionByUpmsUserId(upmsUserId);
    }

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Long upmsUserId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = UpmsUser.service().findByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            _log.info("selectUpmsRoleByUpmsUserId : upmsUserId={}", upmsUserId);
            return null;
        }
        List<UpmsRole> upmsRoles = upmsApiQuery.selectUpmsRoleByUpmsUserId(upmsUserId);
        return upmsRoles;
    }

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    
    //@Cacheable(value = "zheng-upms-rpc-service-ehcache", key = "'selectUpmsRoleByUpmsUserId_' + #upmsUserId")
    public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Long upmsUserId) {
        return selectUpmsRoleByUpmsUserId(upmsUserId);
    }

    /**
     * 根据角色id获取所拥有的权限
     * @param upmsRoleId
     * @return
     */
    
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Long upmsRoleId) {
        Example upmsRolePermissionExample = UpmsRolePermissionCriteria.create().andRoleIdEqualTo(upmsRoleId).example();
        List<UpmsRolePermission> upmsRolePermissions = UpmsRolePermission.service().find(upmsRolePermissionExample);
        return upmsRolePermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Long upmsUserId) {
        Example upmsUserPermissionExample = UpmsUserPermissionCriteria.create()
                .andUserIdEqualTo(new Long(upmsUserId)).example();
        List<UpmsUserPermission> upmsUserPermissions = UpmsUserPermission.service().find(upmsUserPermissionExample);
        return upmsUserPermissions;
    }

    /**
     * 根据条件获取系统数据
     * @param upmsSystemExample
     * @return
     */
    
    public List<UpmsSystem> selectUpmsSystemByQuery(QueryParas paras) {
        return UpmsSystem.service().find(paras);
    }

    
	public List<UpmsSystem> selectUpmsSystemByExample(Example upmsSystemExample) {
		// TODO Auto-generated method stub
		return UpmsSystem.service().find(upmsSystemExample);
	}

    /**
     * 根据条件获取组织数据
     * @param upmsOrganizationExample
     * @return
     */
    
    public List<UpmsOrganization> selectUpmsOrganizationByQuery(QueryParas paras) {
        return UpmsOrganization.service().find(paras);
    }
    
    
	public List<UpmsOrganization> selectUpmsOrganizationByExample(Example upmsOrganizationExample) {
		// TODO Auto-generated method stub
		return UpmsOrganization.service().find(upmsOrganizationExample);
	}

    /**
     * 根据username获取UpmsUser
     * @param username
     * @return
     */
    
    public UpmsUser selectUpmsUserByUsername(String username) {
        Example upmsUserExample = UpmsUserCriteria.create()
                .andUsernameEqualTo(username).example();
        List<UpmsUser> upmsUsers = UpmsUser.service().find(upmsUserExample);
        if (null != upmsUsers && upmsUsers.size() > 0) {
            return upmsUsers.get(0);
        }
        return null;
    }

    /**
     * 写入操作日志
     * @param record
     * @return
     */
    
    public int insertUpmsLogSelective(UpmsLog record) {
        return record.save() ? 1 : -1;
    }

	@Override
	public AuthorizeResult invoke(String target, Annotation[] annotations) {
		// TODO Auto-generated method stub
		if (annotations == null || annotations.length == 0) {
            return AuthorizeResult.ok();
        }
		for (Annotation annotation : annotations) {
			System.out.println("Annotation:" + annotation.annotationType().getName());
			IShiroAuthorizeProcesser processer = null;
			if (annotation.annotationType() == RequiresPermissions.class) {
                processer = new ShiroRequiresPermissionsProcesser((RequiresPermissions) annotation);
            } else if (annotation.annotationType() == RequiresRoles.class) {
                processer = new ShiroRequiresRolesProcesser((RequiresRoles) annotation);
            } else if (annotation.annotationType() == RequiresUser.class) {
            	processer = new ShiroRequiresUserProcesser();
            } else if (annotation.annotationType() == RequiresAuthentication.class) {
            	processer = new ShiroRequiresAuthenticationProcesser();
            } else if (annotation.annotationType() == RequiresGuest.class) {
            	processer = new ShiroRequiresGuestProcesser();
            }
			if (processer != null) {
				AuthorizeResult result = processer.authorize();
				if (result.isFail()) {
	                return result;
	            }
	        }
		}
		return AuthorizeResult.ok();
	}

}