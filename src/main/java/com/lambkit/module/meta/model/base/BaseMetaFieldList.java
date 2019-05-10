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
public abstract class BaseMetaFieldList<M extends BaseMetaFieldList<M>> extends BaseModel<M> implements IBean {

	public String getTableName() {
		return "meta_field_list";
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
	public java.lang.String getViewname() {
		return this.get("viewname");
	}

	public void setViewname(java.lang.String viewname) {
		this.set("viewname", viewname);
	}
	public java.lang.String getIsview() {
		return this.get("isview");
	}

	public void setIsview(java.lang.String isview) {
		this.set("isview", isview);
	}
	public java.lang.String getIsorder() {
		return this.get("isorder");
	}

	public void setIsorder(java.lang.String isorder) {
		this.set("isorder", isorder);
	}
	public java.lang.Long getViewmaxlen() {
		return this.get("viewmaxlen");
	}

	public void setViewmaxlen(java.lang.Long viewmaxlen) {
		this.set("viewmaxlen", viewmaxlen);
	}
	public java.lang.String getViewtype() {
		return this.get("viewtype");
	}

	public void setViewtype(java.lang.String viewtype) {
		this.set("viewtype", viewtype);
	}
	public java.lang.Long getVieworder() {
		return this.get("vieworder");
	}

	public void setVieworder(java.lang.Long vieworder) {
		this.set("vieworder", vieworder);
	}
	public java.lang.String getIssearch() {
		return this.get("issearch");
	}

	public void setIssearch(java.lang.String issearch) {
		this.set("issearch", issearch);
	}
	public java.lang.String getSearchtype() {
		return this.get("searchtype");
	}

	public void setSearchtype(java.lang.String searchtype) {
		this.set("searchtype", searchtype);
	}
	public java.lang.String getSearchinfo() {
		return this.get("searchinfo");
	}

	public void setSearchinfo(java.lang.String searchinfo) {
		this.set("searchinfo", searchinfo);
	}
}
