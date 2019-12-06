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
 */package com.lambkit.db.sql.column;

import com.lambkit.db.sql.SqlJoinMode;

public class SqlJoinOn extends Columns {
	private static final long serialVersionUID = -8250834881745500656L;
	
	private SqlJoinMode type = SqlJoinMode.INNER_JOIN;
	private String mainTableName;
	private String joinTableName;
	private String mainField;
	private String joinField;
	
	public SqlJoinOn(String mainTableName, String mainTableField, String joinTableName, String joinTableField, SqlJoinMode type, Columns cols) {
		this.type = type;
		this.mainTableName = mainTableName;
		this.joinTableName = joinTableName;
		this.mainField = mainTableField;
		this.joinField = joinTableField;
		this.addAll(cols.getList());
	}
	
	public SqlJoinOn(String mainTableName, String mainTableField, String joinTableName, String joinTableField, Columns cols) {
		this.mainTableName = mainTableName;
		this.joinTableName = joinTableName;
		this.mainField = mainTableField;
		this.joinField = joinTableField;
		this.addAll(cols.getList());
	}
	
	public SqlJoinOn(String mainTableName, String mainTableField, String joinTableName, String joinTableField, SqlJoinMode type) {
		this.type = type;
		this.mainTableName = mainTableName;
		this.joinTableName = joinTableName;
		this.mainField = mainTableField;
		this.joinField = joinTableField;
	}
	
	public SqlJoinOn(String mainTableName, String mainTableField, String joinTableName, String joinTableField) {
		this.mainTableName = mainTableName;
		this.joinTableName = joinTableName;
		this.mainField = mainTableField;
		this.joinField = joinTableField;
	}

	public SqlJoinOn mode(SqlJoinMode type) {
		this.type = type;
		return this;
	}
	
	public String getJoinTableName() {
		return joinTableName;
	}
	
	public SqlJoinOn setJoinTableName(String joinTableName) {
		this.joinTableName = joinTableName;
		return this;
	}

	public SqlJoinMode getType() {
		return type;
	}

	public SqlJoinOn setType(SqlJoinMode type) {
		this.type = type;
		return this;
	}

	public String getMainField() {
		return mainField;
	}

	public SqlJoinOn setMainField(String mainField) {
		this.mainField = mainField;
		return this;
	}

	public String getJoinField() {
		return joinField;
	}

	public void setJoinField(String joinField) {
		this.joinField = joinField;
	}

	public String getMainTableName() {
		return mainTableName;
	}

	public void setMainTableName(String mainTableName) {
		this.mainTableName = mainTableName;
	}
}
