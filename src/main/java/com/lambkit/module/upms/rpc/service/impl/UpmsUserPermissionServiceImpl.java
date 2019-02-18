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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Enhancer;
import com.lambkit.common.service.BaseModelServiceImpl;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.rpc.api.UpmsUserPermissionService;
import com.lambkit.module.upms.rpc.model.UpmsUserPermission;
import com.lambkit.module.upms.rpc.sql.UpmsUserPermissionCriteria;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-10-25
 * @version 1.0
 * @since 1.0
 * ${tbcnn}
 */
public class UpmsUserPermissionServiceImpl extends BaseModelServiceImpl<UpmsUserPermission> implements UpmsUserPermissionService {
	
	private UpmsUserPermission DAO = null;
	
	public UpmsUserPermission dao() {
		if(DAO==null) {
			DAO = Enhancer.enhance(UpmsUserPermission.class.getName(), UpmsUserPermission.class);
		}
		return DAO;
	}
	
	public int permission(JSONArray datas, Long id) {
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (json.getBoolean("checked")) {
                // 新增权限
                UpmsUserPermission upmsUserPermission = new UpmsUserPermission();
                upmsUserPermission.setUserId(id);
                upmsUserPermission.setPermissionId(json.getLongValue("id"));
                upmsUserPermission.setType(json.getInteger("type"));
                upmsUserPermission.save();
            } else {
                // 删除权限
                Example upmsUserPermissionExample = UpmsUserPermissionCriteria.create()
                        .andPermissionIdEqualTo(json.getLongValue("id"))
                        .andTypeEqualTo(json.getInteger("type")).example();
                delete(upmsUserPermissionExample);
            }
        }
        return datas.size();
    }
}
