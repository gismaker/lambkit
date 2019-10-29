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
public abstract class BaseMetaFieldMap<M extends BaseMetaFieldMap<M>> extends LambkitModel<M> implements IBean {

	public String getTableName() {
		return "meta_field_map";
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
	public java.lang.String getMtype() {
		return this.get("mtype");
	}

	public void setMtype(java.lang.String mtype) {
		this.set("mtype", mtype);
	}
	public java.lang.String getSrid() {
		return this.get("srid");
	}

	public void setSrid(java.lang.String srid) {
		this.set("srid", srid);
	}
	public java.lang.String getGeotype() {
		return this.get("geotype");
	}

	public void setGeotype(java.lang.String geotype) {
		this.set("geotype", geotype);
	}
}
