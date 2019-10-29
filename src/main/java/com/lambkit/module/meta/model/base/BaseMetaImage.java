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
public abstract class BaseMetaImage<M extends BaseMetaImage<M>> extends LambkitModel<M> implements IBean {

	public String getTableName() {
		return "meta_image";
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
	public java.lang.String getDescription() {
		return this.get("description");
	}

	public void setDescription(java.lang.String description) {
		this.set("description", description);
	}
	public java.lang.String getPath() {
		return this.get("path");
	}

	public void setPath(java.lang.String path) {
		this.set("path", path);
	}
	public java.lang.String getMimeType() {
		return this.get("mime_type");
	}

	public void setMimeType(java.lang.String mimeType) {
		this.set("mime_type", mimeType);
	}
	public java.lang.String getSuffix() {
		return this.get("suffix");
	}

	public void setSuffix(java.lang.String suffix) {
		this.set("suffix", suffix);
	}
	public java.lang.Long getFilesize() {
		return this.get("filesize");
	}

	public void setFilesize(java.lang.Long filesize) {
		this.set("filesize", filesize);
	}
	public java.lang.Integer getWidth() {
		return this.get("width");
	}

	public void setWidth(java.lang.Integer width) {
		this.set("width", width);
	}
	public java.lang.Integer getHeight() {
		return this.get("height");
	}

	public void setHeight(java.lang.Integer height) {
		this.set("height", height);
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
}
