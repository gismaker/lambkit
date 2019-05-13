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

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.lambkit.module.upms.UpmsConfig;
import com.lambkit.module.upms.UpmsManager;
import com.lambkit.module.upms.rpc.model.UpmsPermission;
import com.lambkit.module.upms.rpc.model.UpmsRole;

public class UpmsApiQuery {

	// 根据用户id获取所拥有的权限
	public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Object upmsUserId) {
		UpmsConfig config = UpmsManager.me().getConfig();
		String dbconfig = config.getDbconfig();
		SqlPara sqlPara;
		if(StrKit.notBlank(dbconfig)) {
			sqlPara = Db.use(dbconfig).getSqlPara("upms.selectUpmsPermissionByUpmsUserId", upmsUserId);
		} else {
			sqlPara = Db.getSqlPara("upms.selectUpmsPermissionByUpmsUserId", upmsUserId);
		}
		return UpmsPermission.service().dao().find(sqlPara);
	}

	// 根据用户id获取所属的角色
	public List<UpmsRole> selectUpmsRoleByUpmsUserId(Object upmsUserId) {
		UpmsConfig config = UpmsManager.me().getConfig();
		String dbconfig = config.getDbconfig();
		SqlPara sqlPara;
		if(StrKit.notBlank(dbconfig)) {
			sqlPara = Db.use(dbconfig).getSqlPara("upms.selectUpmsRoleByUpmsUserId", upmsUserId);
		} else {
			sqlPara = Db.getSqlPara("upms.selectUpmsRoleByUpmsUserId", upmsUserId);
		}
		return UpmsRole.service().dao().find(sqlPara);
	}
}
