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
package com.lambkit.module.upms.rpc.model.base;

import com.jfinal.plugin.activerecord.IBean;

import com.lambkit.common.dao.BaseModel;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-12-26
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class BaseUpmsUserRole<M extends BaseUpmsUserRole<M>> extends BaseModel<M> implements IBean {

	public String tableName() {
		return "upms_user_role";
	}
    
	public java.lang.Long getUserRoleId() {
		return this.get("user_role_id");
	}

	public void setUserRoleId(java.lang.Long userRoleId) {
		this.set("user_role_id", userRoleId);
	}
	public java.lang.Long getUserId() {
		return this.get("user_id");
	}

	public void setUserId(java.lang.Long userId) {
		this.set("user_id", userId);
	}
	public java.lang.Integer getRoleId() {
		return this.get("role_id");
	}

	public void setRoleId(java.lang.Integer roleId) {
		this.set("role_id", roleId);
	}
}
