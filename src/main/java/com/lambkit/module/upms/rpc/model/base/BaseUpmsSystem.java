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
import com.lambkit.common.model.LambkitModel;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-12-26
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class BaseUpmsSystem<M extends BaseUpmsSystem<M>> extends LambkitModel<M> implements IBean {

	public String tableName() {
		return "upms_system";
	}
    
	public java.lang.Long getSystemId() {
		return this.get("system_id");
	}

	public void setSystemId(java.lang.Long systemId) {
		this.set("system_id", systemId);
	}
	public java.lang.String getIcon() {
		return this.get("icon");
	}

	public void setIcon(java.lang.String icon) {
		this.set("icon", icon);
	}
	public java.lang.String getBanner() {
		return this.get("banner");
	}

	public void setBanner(java.lang.String banner) {
		this.set("banner", banner);
	}
	public java.lang.String getTheme() {
		return this.get("theme");
	}

	public void setTheme(java.lang.String theme) {
		this.set("theme", theme);
	}
	public java.lang.String getBasepath() {
		return this.get("basepath");
	}

	public void setBasepath(java.lang.String basepath) {
		this.set("basepath", basepath);
	}
	public java.lang.Integer getStatus() {
		return this.get("status");
	}

	public void setStatus(java.lang.Integer status) {
		this.set("status", status);
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
	public java.lang.String getDescription() {
		return this.get("description");
	}

	public void setDescription(java.lang.String description) {
		this.set("description", description);
	}
	public java.lang.Long getCtime() {
		return this.get("ctime");
	}

	public void setCtime(java.lang.Long ctime) {
		this.set("ctime", ctime);
	}
	public java.lang.Long getOrders() {
		return this.get("orders");
	}

	public void setOrders(java.lang.Long orders) {
		this.set("orders", orders);
	}
}
