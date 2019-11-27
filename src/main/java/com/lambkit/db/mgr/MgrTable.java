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
package com.lambkit.db.mgr;

import java.util.List;

import com.lambkit.db.dialect.LambkitDialect;
import com.lambkit.db.meta.TableMeta;

public class MgrTable {

	private String name;
	private ITable model;
	private TableMeta meta;
	private List<? extends IField> fieldList;
	private LambkitDialect dialect;

	public ITable getModel() {
		return model;
	}

	public void setModel(ITable model) {
		this.model = model;
	}

	public TableMeta getMeta() {
		return meta;
	}

	public void setMeta(TableMeta meta) {
		this.meta = meta;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<? extends IField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<? extends IField> list) {
		this.fieldList = list;
	}

	/******/
	public String getPrimaryKey() {
		if(meta!=null) {
			return meta.getPrimaryKey();
		} else {
			return null;
		}
	}
	
	public Object getId() {
		if(model!=null) {
			return model.getId();
		} else {
			return null;
		}
	}
	
	public String getTitle() {
		if(model!=null) {
			return model.getTitle();
		} else {
			return null;
		}
	}

	public LambkitDialect getDialect() {
		return dialect;
	}

	public void setDialect(LambkitDialect dialect) {
		this.dialect = dialect;
	}
}
