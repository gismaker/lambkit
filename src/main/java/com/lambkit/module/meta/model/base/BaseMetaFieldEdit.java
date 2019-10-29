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
public abstract class BaseMetaFieldEdit<M extends BaseMetaFieldEdit<M>> extends LambkitModel<M> implements IBean {

	public String getTableName() {
		return "meta_field_edit";
	}
    
	public java.lang.Long getId() {
		return this.get("id");
	}

	public void setId(java.lang.Long id) {
		this.set("id", id);
	}
	public java.lang.Long getFldid() {
		return this.get("fldid");
	}

	public void setFldid(java.lang.Long fldid) {
		this.set("fldid", fldid);
	}
	public java.lang.Long getTmid() {
		return this.get("tmid");
	}

	public void setTmid(java.lang.Long tmid) {
		this.set("tmid", tmid);
	}
	public java.lang.Long getChecktype() {
		return this.get("checktype");
	}

	public void setChecktype(java.lang.Long checktype) {
		this.set("checktype", checktype);
	}
	public java.lang.Long getEdittype() {
		return this.get("edittype");
	}

	public void setEdittype(java.lang.Long edittype) {
		this.set("edittype", edittype);
	}
	public java.lang.Long getEditid() {
		return this.get("editid");
	}

	public void setEditid(java.lang.Long editid) {
		this.set("editid", editid);
	}
	public java.lang.Long getEditminlen() {
		return this.get("editminlen");
	}

	public void setEditminlen(java.lang.Long editminlen) {
		this.set("editminlen", editminlen);
	}
	public java.lang.Long getEditmaxlen() {
		return this.get("editmaxlen");
	}

	public void setEditmaxlen(java.lang.Long editmaxlen) {
		this.set("editmaxlen", editmaxlen);
	}
	public java.lang.Long getEditorder() {
		return this.get("editorder");
	}

	public void setEditorder(java.lang.Long editorder) {
		this.set("editorder", editorder);
	}
}
