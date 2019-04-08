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
package com.lambkit.module.meta.model.base;

import com.jfinal.plugin.activerecord.IBean;

import com.lambkit.common.dao.BaseModel;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class BaseMetaTable<M extends BaseMetaTable<M>> extends BaseModel<M> implements IBean {

	public String getTableName() {
		return "meta_table";
	}
    
	public java.lang.Long getId() {
		return this.get("id");
	}

	public void setId(java.lang.Long id) {
		this.set("id", id);
	}
	public java.lang.Long getSid() {
		return this.get("sid");
	}

	public void setSid(java.lang.Long sid) {
		this.set("sid", sid);
	}
	public java.lang.Long getUserId() {
		return this.get("user_id");
	}

	public void setUserId(java.lang.Long userId) {
		this.set("user_id", userId);
	}
	public java.lang.String getName() {
		return this.get("name");
	}

	public void setName(java.lang.String name) {
		this.set("name", name);
	}
	public java.lang.String getTitle() {
		return this.get("title");
	}

	public void setTitle(java.lang.String title) {
		this.set("title", title);
	}
	public java.lang.String getKeyname() {
		return this.get("keyname");
	}

	public void setKeyname(java.lang.String keyname) {
		this.set("keyname", keyname);
	}
	public java.lang.String getNamefld() {
		return this.get("namefld");
	}

	public void setNamefld(java.lang.String namefld) {
		this.set("namefld", namefld);
	}
	public java.lang.String getOlapType() {
		return this.get("olap_type");
	}

	public void setOlapType(java.lang.String olapType) {
		this.set("olap_type", olapType);
	}
	public java.lang.String getType() {
		return this.get("type");
	}

	public void setType(java.lang.String type) {
		this.set("type", type);
	}
	public java.util.Date getCreated() {
		return this.get("created");
	}

	public void setCreated(java.util.Date created) {
		this.set("created", created);
	}
	public java.util.Date getModified() {
		return this.get("modified");
	}

	public void setModified(java.util.Date modified) {
		this.set("modified", modified);
	}
	public java.lang.Integer getStatus() {
		return this.get("status");
	}

	public void setStatus(java.lang.Integer status) {
		this.set("status", status);
	}
	public java.lang.Long getOrders() {
		return this.get("orders");
	}

	public void setOrders(java.lang.Long orders) {
		this.set("orders", orders);
	}
	public java.lang.String getPermission() {
		return this.get("permission");
	}

	public void setPermission(java.lang.String permission) {
		this.set("permission", permission);
	}
}
