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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbPro;
import com.lambkit.db.dialect.LambkitDialect;
import com.lambkit.db.meta.ColumnMeta;
import com.lambkit.db.meta.TableMeta;
import com.lambkit.db.sql.column.Columns;

public class MgrTable {

	private String name;
	private ITable model;
	private TableMeta meta;
	private List<? extends IField> fieldList;
	private LambkitDialect dialect;
	
	public DbPro db() {
		return Db.use();
	}

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
	
	public IField getField(String fieldName) {
		for (IField iField : fieldList) {
			if(iField.getName().equals(fieldName)) {
				return iField;
			}
		}
		return null;
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
	
	public boolean hasColumn(String fldName) {
		if(StrKit.isBlank(fldName)) return false;
		if(fieldList!=null) {
			for (IField iField : fieldList) {
				if(fldName.equals(iField.getName())) {
					return true;
				}
			}
		} else if(meta!=null && meta.getColumnMetas()!=null) {
			for (ColumnMeta column : meta.getColumnMetas()) {
				if(fldName.equals(column.getName())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Object getValue(String type, String value) {
 		type = type.toLowerCase();
 		if(type.startsWith("int") || type.startsWith("serial"))return Integer.parseInt(value);
 		else if(type.startsWith("float"))return Float.parseFloat(value);
 		else if(type.startsWith("double"))return Double.parseDouble(value);
 		else if(type.startsWith("num"))return Double.parseDouble(value);//numeric,number
 		else if(type.startsWith("date")) return Date.valueOf(value);
 		else if(type.startsWith("datetime")) return Timestamp.valueOf(value);
 		else if(type.startsWith("timestamp")) return Timestamp.valueOf(value);
 		else return value;
 	}
	/////////////////////////////////////////////////
	
	public String getLoadColumns(String alias) {
		return MgrdbManager.me().getService().getSelectNamesOfView(this, alias);
	}
	
	protected String sql4FindById(Object id) {
		String pkname = getPrimaryKey();
		return dialect.forFindByColumns(getName(), getLoadColumns(""), Columns.create(pkname, id).getList(), "", null);
	} 
}
