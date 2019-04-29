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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lambkit.common.aop.AopKit;
import com.lambkit.common.service.BaseModelServiceImpl;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.rpc.api.UpmsUserRoleService;
import com.lambkit.module.upms.rpc.model.UpmsUserRole;
import com.lambkit.module.upms.rpc.model.sql.UpmsUserRoleCriteria;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-10-25
 * @version 1.0
 * @since 1.0
 */
public class UpmsUserRoleServiceImpl extends BaseModelServiceImpl<UpmsUserRole> implements UpmsUserRoleService {
	
	private UpmsUserRole DAO = null;
	
	public UpmsUserRole dao() {
		if(DAO==null) {
			DAO = AopKit.singleton(UpmsUserRole.class);
		}
		return DAO;
	}

	public int role(String[] roleIds, Long id) {
        int result = 0;
        // 删除旧记录
        Example upmsUserRoleExample = UpmsUserRoleCriteria.create()
                .andUserIdEqualTo(id).example();
        delete(upmsUserRoleExample);
        // 增加新记录
        if (null != roleIds) {
            for (String roleId : roleIds) {
                if (StringUtils.isBlank(roleId)) {
                    continue;
                }
                UpmsUserRole upmsUserRole = new UpmsUserRole();
                upmsUserRole.setUserId(id);
                upmsUserRole.setRoleId(NumberUtils.toInt(roleId));
                result = upmsUserRole.save() ? 1 : -1;
            }
        }
        return result;
    }
	
	@Override
	public boolean hasRole(Long userId, Integer roleId) {
		Example upmsUserRoleExample = UpmsUserRoleCriteria.create()
                .andUserIdEqualTo(userId).andRoleIdEqualTo(roleId).example();
		UpmsUserRole urole = UpmsUserRole.service().findFirst(upmsUserRoleExample);
		return urole !=null ? true : false;
	}
}
