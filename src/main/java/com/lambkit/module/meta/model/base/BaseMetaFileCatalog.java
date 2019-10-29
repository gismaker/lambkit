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
import com.lambkit.common.model.LambkitModel;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class BaseMetaFileCatalog<M extends BaseMetaFileCatalog<M>> extends LambkitModel<M> implements IBean {

	public String getTableName() {
		return "meta_file_catalog";
	}
    
	public java.lang.Long getId() {
		return this.get("id");
	}

	public void setId(java.lang.Long id) {
		this.set("id", id);
	}
	public java.lang.Integer getSid() {
		return this.get("sid");
	}

	public void setSid(java.lang.Integer sid) {
		this.set("sid", sid);
	}
	public java.lang.Long getUserId() {
		return this.get("user_id");
	}

	public void setUserId(java.lang.Long userId) {
		this.set("user_id", userId);
	}
	public java.lang.Long getPid() {
		return this.get("pid");
	}

	public void setPid(java.lang.Long pid) {
		this.set("pid", pid);
	}
	public java.lang.String getTitle() {
		return this.get("title");
	}

	public void setTitle(java.lang.String title) {
		this.set("title", title);
	}
	public java.lang.Long getFilesize() {
		return this.get("filesize");
	}

	public void setFilesize(java.lang.Long filesize) {
		this.set("filesize", filesize);
	}
	public java.lang.String getType() {
		return this.get("type");
	}

	public void setType(java.lang.String type) {
		this.set("type", type);
	}
	public java.lang.String getFlag() {
		return this.get("flag");
	}

	public void setFlag(java.lang.String flag) {
		this.set("flag", flag);
	}
	public java.lang.Long getOrders() {
		return this.get("orders");
	}

	public void setOrders(java.lang.Long orders) {
		this.set("orders", orders);
	}
	public java.lang.Boolean getStatus() {
		return this.get("status");
	}

	public void setStatus(java.lang.Boolean status) {
		this.set("status", status);
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
	public java.lang.String getPermission() {
		return this.get("permission");
	}

	public void setPermission(java.lang.String permission) {
		this.set("permission", permission);
	}
}
