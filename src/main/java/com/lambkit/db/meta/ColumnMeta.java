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

import com.mysql.jdbc.ResultSetMetaData;

public class ColumnMeta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8652833602172084778L;
	
	private String name;				// 字段名，field_name
	private String title;				// 字段的标题，field name
	private String attrName;			// 字段对应的属性名, fieldName
	private String upperName;			// FieldName
	private String catalogName;			// 所在的Catalog名字
	private String javaType;			// 字段对应的 java 类型,如java.lang.String
	/*
	-----------+---------+------+-----+---------+----------------
	 Field     | Type    | Null | Key | Default | Remarks
	-----------+---------+------+-----+---------+----------------
	 id		   | int(11) | NO	| PRI | NULL	| remarks here	
	*/
	private String type;				// 字段类型(附带字段长度与小数点)，例如：decimal(11,2)
	private String isNullable;			// 是否允许空值
	private String isPrimaryKey;		// 是否主键
	private String defaultValue;		// 默认值
	private String remarks;				// 字段备注
	
	private int displaySize;			// 在数据库中类型的最大字符个数
	private int precision;				// 某列类型的精确度(类型的长度)
	private int scale;					// 小数点后的位数
	private int isNullableType; 		// 是否为空
	private boolean primaryKey;			// 是否主键
	private boolean isAutoInctement;	// 是否自动递增
	private boolean isCurrency;			// 在数据库中是否为货币型
	private boolean isReadOnly;			// 是否为只读
	private boolean isSigned;
	
	public String getName() {
		return name;
	}

	public void setName(String columnName) {
		this.name = columnName;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}


	public int getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public boolean isAutoInctement() {
		return isAutoInctement;
	}

	public void setAutoInctement(boolean isAutoInctement) {
		this.isAutoInctement = isAutoInctement;
	}

	public boolean isCurrency() {
		return isCurrency;
	}

	public void setCurrency(boolean isCurrency) {
		this.isCurrency = isCurrency;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public String getUpperName() {
		return upperName;
	}

	public void setUpperName(String upperName) {
		this.upperName = upperName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getIsPrimaryKey() {
		return isPrimaryKey;
	}
	public void setIsPrimaryKey(String isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public int getIsNullableType() {
		return isNullableType;
	}

	public void setIsNullableType(int isNullableType) {
		this.isNullableType = isNullableType;
		if(isNullableType==ResultSetMetaData.columnNoNulls) {
			this.isNullable = "NO";
		} else if(isNullableType==ResultSetMetaData.columnNullable) {
			this.isNullable = "YES";
		}
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isSigned() {
		return isSigned;
	}

	public void setSigned(boolean isSigned) {
		this.isSigned = isSigned;
	}
}
