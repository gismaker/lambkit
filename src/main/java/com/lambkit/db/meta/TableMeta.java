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
package com.lambkit.db.meta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.dialect.Dialect;
import com.lambkit.db.dialect.LambkitDialect;

public class TableMeta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name; 		// 表名,db_user_info
	private String attrName; 	// 属性名,userInfo
	private String modelName; 	// 类名称,UserInfo
	private String primaryKey; 	// 主键，复合主键以逗号分隔
	private String title; 		// 数据表标题
	private String remarks; 	// 表备注
	private List<ColumnMeta> columnMetas = new ArrayList<ColumnMeta>(); // 字段meta

	public int colNameMaxLen = "Field".length(); // 字段名最大宽度，用于辅助生成字典文件样式
	public int colTypeMaxLen = "Type".length(); // 字段类型最大宽度，用于辅助生成字典文件样式
	public int colDefaultValueMaxLen = "Default".length(); // 字段默认值最大宽度，用于辅助生成字典文件样式
	
	private Dialect dialect;
	
	public boolean isPrimaryKey(String name) {
		if (StrKit.isBlank(primaryKey)) {
			return false;
		}
		String pk = primaryKey.trim();
		pk = primaryKey.endsWith(",") ? primaryKey : primaryKey + ",";
		if (pk.indexOf(name + ",") != -1) {
			return true;
		}
		return false;
	}

	public void addColumnMeta(ColumnMeta column) {
		columnMetas.add(column);
	}
	
	public ColumnMeta getColumn(String column) {
		for (ColumnMeta columnMeta : columnMetas) {
			if(columnMeta.getName().equals(column)) {
				return columnMeta;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<ColumnMeta> getColumnMetas() {
		return columnMetas;
	}

	public void setColumnMetas(List<ColumnMeta> columnMetas) {
		this.columnMetas = columnMetas;
	}

	public int getColNameMaxLen() {
		return colNameMaxLen;
	}

	public void setColNameMaxLen(int colNameMaxLen) {
		this.colNameMaxLen = colNameMaxLen;
	}

	public int getColTypeMaxLen() {
		return colTypeMaxLen;
	}

	public void setColTypeMaxLen(int colTypeMaxLen) {
		this.colTypeMaxLen = colTypeMaxLen;
	}

	public int getColDefaultValueMaxLen() {
		return colDefaultValueMaxLen;
	}

	public void setColDefaultValueMaxLen(int colDefaultValueMaxLen) {
		this.colDefaultValueMaxLen = colDefaultValueMaxLen;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Dialect getDialect() {
		return dialect;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}
}
