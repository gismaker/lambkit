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
public abstract class BaseMetaField<M extends BaseMetaField<M>> extends BaseModel<M> implements IBean {

	public String getTableName() {
		return "meta_field";
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
	public java.lang.String getDatatype() {
		return this.get("datatype");
	}

	public void setDatatype(java.lang.String datatype) {
		this.set("datatype", datatype);
	}
	public java.lang.String getClasstype() {
		return this.get("classtype");
	}

	public void setClasstype(java.lang.String classtype) {
		this.set("classtype", classtype);
	}
	public java.lang.String getIskey() {
		return this.get("iskey");
	}

	public void setIskey(java.lang.String iskey) {
		this.set("iskey", iskey);
	}
	public java.lang.String getIsunsigned() {
		return this.get("isunsigned");
	}

	public void setIsunsigned(java.lang.String isunsigned) {
		this.set("isunsigned", isunsigned);
	}
	public java.lang.String getIsnullable() {
		return this.get("isnullable");
	}

	public void setIsnullable(java.lang.String isnullable) {
		this.set("isnullable", isnullable);
	}
	public java.lang.String getIsai() {
		return this.get("isai");
	}

	public void setIsai(java.lang.String isai) {
		this.set("isai", isai);
	}
	public java.lang.String getFlddefault() {
		return this.get("flddefault");
	}

	public void setFlddefault(java.lang.String flddefault) {
		this.set("flddefault", flddefault);
	}
	public java.lang.String getDescript() {
		return this.get("descript");
	}

	public void setDescript(java.lang.String descript) {
		this.set("descript", descript);
	}
	public java.lang.String getIsfk() {
		return this.get("isfk");
	}

	public void setIsfk(java.lang.String isfk) {
		this.set("isfk", isfk);
	}
	public java.lang.Long getFktbid() {
		return this.get("fktbid");
	}

	public void setFktbid(java.lang.Long fktbid) {
		this.set("fktbid", fktbid);
	}
	public java.lang.String getIsview() {
		return this.get("isview");
	}

	public void setIsview(java.lang.String isview) {
		this.set("isview", isview);
	}
	public java.lang.String getIsselect() {
		return this.get("isselect");
	}

	public void setIsselect(java.lang.String isselect) {
		this.set("isselect", isselect);
	}
	public java.lang.String getIsedit() {
		return this.get("isedit");
	}

	public void setIsedit(java.lang.String isedit) {
		this.set("isedit", isedit);
	}
	public java.lang.String getIsmustfld() {
		return this.get("ismustfld");
	}

	public void setIsmustfld(java.lang.String ismustfld) {
		this.set("ismustfld", ismustfld);
	}
	public java.lang.String getIsmap() {
		return this.get("ismap");
	}

	public void setIsmap(java.lang.String ismap) {
		this.set("ismap", ismap);
	}
	public java.lang.String getOlap() {
		return this.get("olap");
	}

	public void setOlap(java.lang.String olap) {
		this.set("olap", olap);
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
