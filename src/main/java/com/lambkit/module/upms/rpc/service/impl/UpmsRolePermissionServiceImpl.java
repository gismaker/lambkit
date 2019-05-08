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

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambkit.common.service.BaseModelServiceImpl;
import com.lambkit.core.aop.AopKit;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.rpc.api.UpmsRolePermissionService;
import com.lambkit.module.upms.rpc.model.UpmsRolePermission;
import com.lambkit.module.upms.rpc.model.sql.UpmsRolePermissionCriteria;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-10-25
 * @version 1.0
 * @since 1.0
 * ${tbcnn}
 */
public class UpmsRolePermissionServiceImpl extends BaseModelServiceImpl<UpmsRolePermission> implements UpmsRolePermissionService {
	
	private UpmsRolePermission DAO = null;
	
	public UpmsRolePermission dao() {
		if(DAO==null) {
			DAO = AopKit.singleton(UpmsRolePermission.class);
		}
		return DAO;
	}
	
	public int rolePermission(JSONArray datas, Long id) {
        List<Long> deleteIds = new ArrayList<Long>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getLong("id"));
            } else {
                // 新增权限
                UpmsRolePermission upmsRolePermission = new UpmsRolePermission();
                upmsRolePermission.setRoleId(id);
                upmsRolePermission.setPermissionId(json.getLongValue("id"));
                upmsRolePermission.save();
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            Example upmsRolePermissionExample = UpmsRolePermissionCriteria.create()
            		.andPermissionIdIn(deleteIds)
                    .andRoleIdEqualTo(id).example();
            delete(upmsRolePermissionExample);
        }
        return datas.size();
    }
}
