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
public abstract class BaseMetaStoreDb<M extends BaseMetaStoreDb<M>> extends BaseModel<M> implements IBean {

	public String getTableName() {
		return "meta_store_db";
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
	public java.lang.String getDbname() {
		return this.get("dbname");
	}

	public void setDbname(java.lang.String dbname) {
		this.set("dbname", dbname);
	}
	public java.lang.String getDbtype() {
		return this.get("dbtype");
	}

	public void setDbtype(java.lang.String dbtype) {
		this.set("dbtype", dbtype);
	}
	public java.lang.String getDburl() {
		return this.get("dburl");
	}

	public void setDburl(java.lang.String dburl) {
		this.set("dburl", dburl);
	}
	public java.lang.String getUser() {
		return this.get("user");
	}

	public void setUser(java.lang.String user) {
		this.set("user", user);
	}
	public java.lang.String getPassword() {
		return this.get("password");
	}

	public void setPassword(java.lang.String password) {
		this.set("password", password);
	}
}
