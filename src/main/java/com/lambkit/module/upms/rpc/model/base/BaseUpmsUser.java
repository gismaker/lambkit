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
public abstract class BaseUpmsUser<M extends BaseUpmsUser<M>> extends BaseModel<M> implements IBean {

	public String tableName() {
		return "upms_user";
	}
    
	public java.lang.Long getUserId() {
		return this.get("user_id");
	}

	public void setUserId(java.lang.Long userId) {
		this.set("user_id", userId);
	}
	public java.lang.String getUsername() {
		return this.get("username");
	}

	public void setUsername(java.lang.String username) {
		this.set("username", username);
	}
	public java.lang.String getPassword() {
		return this.get("password");
	}

	public void setPassword(java.lang.String password) {
		this.set("password", password);
	}
	public java.lang.String getSalt() {
		return this.get("salt");
	}

	public void setSalt(java.lang.String salt) {
		this.set("salt", salt);
	}
	public java.lang.String getRealname() {
		return this.get("realname");
	}

	public void setRealname(java.lang.String realname) {
		this.set("realname", realname);
	}
	public java.lang.String getAvatar() {
		return this.get("avatar");
	}

	public void setAvatar(java.lang.String avatar) {
		this.set("avatar", avatar);
	}
	public java.lang.String getPhone() {
		return this.get("phone");
	}

	public void setPhone(java.lang.String phone) {
		this.set("phone", phone);
	}
	public java.lang.String getEmail() {
		return this.get("email");
	}

	public void setEmail(java.lang.String email) {
		this.set("email", email);
	}
	public java.lang.Integer getSex() {
		return this.get("sex");
	}

	public void setSex(java.lang.Integer sex) {
		this.set("sex", sex);
	}
	public java.lang.Integer getLocked() {
		return this.get("locked");
	}

	public void setLocked(java.lang.Integer locked) {
		this.set("locked", locked);
	}
	public java.lang.Long getCtime() {
		return this.get("ctime");
	}

	public void setCtime(java.lang.Long ctime) {
		this.set("ctime", ctime);
	}
}
