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
public abstract class BaseMetaFieldMeasure<M extends BaseMetaFieldMeasure<M>> extends LambkitModel<M> implements IBean {

	public String getTableName() {
		return "meta_field_measure";
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
	public java.lang.Long getName() {
		return this.get("name");
	}

	public void setName(java.lang.Long name) {
		this.set("name", name);
	}
	public java.lang.String getType() {
		return this.get("type");
	}

	public void setType(java.lang.String type) {
		this.set("type", type);
	}
	public java.lang.String getAgg() {
		return this.get("agg");
	}

	public void setAgg(java.lang.String agg) {
		this.set("agg", agg);
	}
	public java.lang.String getFormula() {
		return this.get("formula");
	}

	public void setFormula(java.lang.String formula) {
		this.set("formula", formula);
	}
}
