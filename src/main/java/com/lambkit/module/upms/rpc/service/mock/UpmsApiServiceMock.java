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
package com.lambkit.module.upms.rpc.service.mock;

import java.lang.annotation.Annotation;
import java.util.List;

import com.lambkit.component.shiro.processer.AuthorizeResult;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.rpc.api.UpmsApiService;
import com.lambkit.module.upms.rpc.model.UpmsLog;
import com.lambkit.module.upms.rpc.model.UpmsOrganization;
import com.lambkit.module.upms.rpc.model.UpmsPermission;
import com.lambkit.module.upms.rpc.model.UpmsRole;
import com.lambkit.module.upms.rpc.model.UpmsRolePermission;
import com.lambkit.module.upms.rpc.model.UpmsSystem;
import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.module.upms.rpc.model.UpmsUserPermission;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-12-26
 * @version 1.0
 * @since 1.0
 */
public class UpmsApiServiceMock implements UpmsApiService {

	@Override
	public AuthorizeResult invoke(String target, Annotation[] annotations) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Long upmsUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Long upmsUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpmsRole> selectUpmsRoleByUpmsUserId(Long upmsUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Long upmsUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Long upmsRoleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Long upmsUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpmsSystem> selectUpmsSystemByQuery(QueryParas paras) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpmsSystem> selectUpmsSystemByExample(Example upmsSystemExample) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpmsOrganization> selectUpmsOrganizationByQuery(QueryParas paras) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UpmsOrganization> selectUpmsOrganizationByExample(Example upmsOrganizationExample) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpmsUser selectUpmsUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertUpmsLogSelective(UpmsLog record) {
		// TODO Auto-generated method stub
		return 0;
	}
}