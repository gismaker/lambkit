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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambkit.common.aop.AopKit;
import com.lambkit.common.service.BaseModelServiceImpl;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.rpc.model.UpmsRolePermission;
import com.lambkit.module.upms.rpc.model.UpmsSystem;
import com.lambkit.module.upms.rpc.model.UpmsUserPermission;
import com.lambkit.module.upms.rpc.model.sql.UpmsPermissionCriteria;
import com.lambkit.module.upms.rpc.model.sql.UpmsSystemCriteria;
import com.lambkit.module.upms.rpc.model.sql.UpmsUserPermissionCriteria;
import com.lambkit.module.upms.rpc.api.UpmsApiService;
import com.lambkit.module.upms.rpc.api.UpmsPermissionService;
import com.lambkit.module.upms.rpc.model.UpmsPermission;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-10-25
 * @version 1.0
 * @since 1.0
 * ${tbcnn}
 */
public class UpmsPermissionServiceImpl extends BaseModelServiceImpl<UpmsPermission> implements UpmsPermissionService {
	
	//protected final UpmsPermission DAO = new UpmsPermission();
	
	UpmsApiService upmsApiService = AopKit.newInstance(UpmsApiServiceImpl.class);

	private UpmsPermission DAO = null;
	
	public UpmsPermission dao() {
		if(DAO==null) {
			DAO = AopKit.singleton(UpmsPermission.class);
		}
		return DAO;
	}
	
	public JSONArray getTreeByRoleId(Long roleId) {
        // 角色已有权限
        List<UpmsRolePermission> rolePermissions = upmsApiService.selectUpmsRolePermisstionByUpmsRoleId(roleId);

        JSONArray systems = new JSONArray();
        // 系统
        Example upmsSystemExample = UpmsSystemCriteria.create().andStatusEqualTo(1).example();
        upmsSystemExample.setOrderBy("orders asc");
        List<UpmsSystem> upmsSystems = UpmsSystem.service().find(upmsSystemExample);
        for (UpmsSystem upmsSystem : upmsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", upmsSystem.getSystemId());
            node.put("name", upmsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system: systems) {
            	Example upmsPermissionExample = UpmsPermissionCriteria.create().andStatusEqualTo(1)
                        .andSystemIdEqualTo(((JSONObject) system).getLongValue("id")).example();
                upmsPermissionExample.setOrderBy("orders asc");
                List<UpmsPermission> upmsPermissions = find(upmsPermissionExample);
                if (upmsPermissions.size() == 0) continue;
                // 目录
                JSONArray folders = new JSONArray();
                for (UpmsPermission upmsPermission: upmsPermissions) {
                    if (upmsPermission.getPid().intValue() != 0 || upmsPermission.getType() != 1) continue;
                    JSONObject node = new JSONObject();
                    node.put("id", upmsPermission.getPermissionId());
                    node.put("name", upmsPermission.getName());
                    node.put("open", true);
                    for (UpmsRolePermission rolePermission : rolePermissions) {
                        if (rolePermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (UpmsPermission upmsPermission2: upmsPermissions) {
                            if (upmsPermission2.getPid().intValue() != ((JSONObject) folder).getIntValue("id") || upmsPermission2.getType() != 2) continue;
                            JSONObject node2 = new JSONObject();
                            node2.put("id", upmsPermission2.getPermissionId());
                            node2.put("name", upmsPermission2.getName());
                            node2.put("open", true);
                            for (UpmsRolePermission rolePermission : rolePermissions) {
                                if (rolePermission.getPermissionId().intValue() == upmsPermission2.getPermissionId().intValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (UpmsPermission upmsPermission3: upmsPermissions) {
                                    if (upmsPermission3.getPid().intValue() != ((JSONObject) menu).getIntValue("id") || upmsPermission3.getType() != 3) continue;
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", upmsPermission3.getPermissionId());
                                    node3.put("name", upmsPermission3.getName());
                                    node3.put("open", true);
                                    for (UpmsRolePermission rolePermission : rolePermissions) {
                                        if (rolePermission.getPermissionId().intValue() == upmsPermission3.getPermissionId().intValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
    }

    
    public JSONArray getTreeByUserId(Long usereId, Integer type) {
        // 角色权限
        Example upmsUserPermissionExample = UpmsUserPermissionCriteria.create()
                .andUserIdEqualTo(usereId)
                .andTypeEqualTo(type).example();
        List<UpmsUserPermission> upmsUserPermissions = UpmsUserPermission.service().find(upmsUserPermissionExample);

        JSONArray systems = new JSONArray();
        // 系统
        Example upmsSystemExample = UpmsSystemCriteria.create().andStatusEqualTo(1).example();
        upmsSystemExample.setOrderBy("orders asc");
        List<UpmsSystem> upmsSystems = UpmsSystem.service().find(upmsSystemExample);
        for (UpmsSystem upmsSystem : upmsSystems) {
            JSONObject node = new JSONObject();
            node.put("id", upmsSystem.getSystemId());
            node.put("name", upmsSystem.getTitle());
            node.put("nocheck", true);
            node.put("open", true);
            systems.add(node);
        }

        if (systems.size() > 0) {
            for (Object system: systems) {
				Example upmsPermissionExample = UpmsPermissionCriteria.create().andStatusEqualTo(1)
                        .andSystemIdEqualTo(((JSONObject) system).getLongValue("id")).example();
                upmsPermissionExample.setOrderBy("orders asc");
                List<UpmsPermission> upmsPermissions = UpmsPermission.service().find(upmsPermissionExample);
                if (upmsPermissions.size() == 0) continue;
                // 目录
                JSONArray folders = new JSONArray();
                for (UpmsPermission upmsPermission: upmsPermissions) {
                    if (upmsPermission.getPid().intValue() != 0 || upmsPermission.getType() != 1) continue;
                    JSONObject node = new JSONObject();
                    node.put("id", upmsPermission.getPermissionId());
                    node.put("name", upmsPermission.getName());
                    node.put("open", true);
                    for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                        if (upmsUserPermission.getPermissionId().intValue() == upmsPermission.getPermissionId().intValue()) {
                            node.put("checked", true);
                        }
                    }
                    folders.add(node);
                    // 菜单
                    JSONArray menus = new JSONArray();
                    for (Object folder : folders) {
                        for (UpmsPermission upmsPermission2: upmsPermissions) {
                            if (upmsPermission2.getPid().intValue() != ((JSONObject) folder).getIntValue("id") || upmsPermission2.getType() != 2) continue;
                            JSONObject node2 = new JSONObject();
                            node2.put("id", upmsPermission2.getPermissionId());
                            node2.put("name", upmsPermission2.getName());
                            node2.put("open", true);
                            for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                                if (upmsUserPermission.getPermissionId().intValue() == upmsPermission2.getPermissionId().intValue()) {
                                    node2.put("checked", true);
                                }
                            }
                            menus.add(node2);
                            // 按钮
                            JSONArray buttons = new JSONArray();
                            for (Object menu : menus) {
                                for (UpmsPermission upmsPermission3: upmsPermissions) {
                                    if (upmsPermission3.getPid().intValue() != ((JSONObject) menu).getIntValue("id") || upmsPermission3.getType() != 3) continue;
                                    JSONObject node3 = new JSONObject();
                                    node3.put("id", upmsPermission3.getPermissionId());
                                    node3.put("name", upmsPermission3.getName());
                                    node3.put("open", true);
                                    for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                                        if (upmsUserPermission.getPermissionId().intValue() == upmsPermission3.getPermissionId().intValue()) {
                                            node3.put("checked", true);
                                        }
                                    }
                                    buttons.add(node3);
                                }
                                if (buttons.size() > 0) {
                                    ((JSONObject) menu).put("children", buttons);
                                    buttons = new JSONArray();
                                }
                            }
                        }
                        if (menus.size() > 0) {
                            ((JSONObject) folder).put("children", menus);
                            menus = new JSONArray();
                        }
                    }
                }
                if (folders.size() > 0) {
                    ((JSONObject) system).put("children", folders);
                }
            }
        }
        return systems;
    }
}
