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
public abstract class BaseMetaTheme<M extends BaseMetaTheme<M>> extends BaseModel<M> implements IBean {

	public String getTableName() {
		return "meta_theme";
	}
    
	public java.lang.Long getId() {
		return this.get("id");
	}

	public void setId(java.lang.Long id) {
		this.set("id", id);
	}
	public java.lang.String getName() {
		return this.get("name");
	}

	public void setName(java.lang.String name) {
		this.set("name", name);
	}
	public java.lang.Long getUserid() {
		return this.get("userid");
	}

	public void setUserid(java.lang.Long userid) {
		this.set("userid", userid);
	}
	public java.lang.String getTmtype() {
		return this.get("tmtype");
	}

	public void setTmtype(java.lang.String tmtype) {
		this.set("tmtype", tmtype);
	}
	public java.lang.Integer getActive() {
		return this.get("active");
	}

	public void setActive(java.lang.Integer active) {
		this.set("active", active);
	}
}
