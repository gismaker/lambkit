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
import com.lambkit.common.model.BaseModel;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class BaseMetaApi<M extends BaseMetaApi<M>> extends BaseModel<M> implements IBean {

	public String getTableName() {
		return "meta_api";
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
	public java.lang.String getName() {
		return this.get("name");
	}

	public void setName(java.lang.String name) {
		this.set("name", name);
	}
	public java.lang.String getUrl() {
		return this.get("url");
	}

	public void setUrl(java.lang.String url) {
		this.set("url", url);
	}
	public java.lang.String getAction() {
		return this.get("action");
	}

	public void setAction(java.lang.String action) {
		this.set("action", action);
	}
	public java.lang.String getFormat() {
		return this.get("format");
	}

	public void setFormat(java.lang.String format) {
		this.set("format", format);
	}
	public java.lang.String getStatus() {
		return this.get("status");
	}

	public void setStatus(java.lang.String status) {
		this.set("status", status);
	}
	public java.lang.Integer getViewCount() {
		return this.get("view_count");
	}

	public void setViewCount(java.lang.Integer viewCount) {
		this.set("view_count", viewCount);
	}
}
