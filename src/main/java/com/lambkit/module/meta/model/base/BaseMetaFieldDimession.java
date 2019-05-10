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
public abstract class BaseMetaFieldDimession<M extends BaseMetaFieldDimession<M>> extends BaseModel<M> implements IBean {

	public String getTableName() {
		return "meta_field_dimession";
	}
    
	public java.lang.Long getId() {
		return this.get("id");
	}

	public void setId(java.lang.Long id) {
		this.set("id", id);
	}
	public java.lang.Long getTbid() {
		return this.get("tbid");
	}

	public void setTbid(java.lang.Long tbid) {
		this.set("tbid", tbid);
	}
	public java.lang.Long getFldid() {
		return this.get("fldid");
	}

	public void setFldid(java.lang.Long fldid) {
		this.set("fldid", fldid);
	}
	public java.lang.Long getRtbid() {
		return this.get("rtbid");
	}

	public void setRtbid(java.lang.Long rtbid) {
		this.set("rtbid", rtbid);
	}
	public java.lang.String getRtype() {
		return this.get("rtype");
	}

	public void setRtype(java.lang.String rtype) {
		this.set("rtype", rtype);
	}
	public java.lang.Long getRfldid() {
		return this.get("rfldid");
	}

	public void setRfldid(java.lang.Long rfldid) {
		this.set("rfldid", rfldid);
	}
	public java.lang.String getDtype() {
		return this.get("dtype");
	}

	public void setDtype(java.lang.String dtype) {
		this.set("dtype", dtype);
	}
	public java.lang.String getLevelType() {
		return this.get("level_type");
	}

	public void setLevelType(java.lang.String levelType) {
		this.set("level_type", levelType);
	}
	public java.lang.Long getParentfldid() {
		return this.get("parentfldid");
	}

	public void setParentfldid(java.lang.Long parentfldid) {
		this.set("parentfldid", parentfldid);
	}
	public java.lang.Long getChildfldid() {
		return this.get("childfldid");
	}

	public void setChildfldid(java.lang.Long childfldid) {
		this.set("childfldid", childfldid);
	}
}
