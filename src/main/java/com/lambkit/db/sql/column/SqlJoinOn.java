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
	private String tableName;
	private String onMainTableField;
	private String onMyTableField;
	
	public SqlJoinOn(String tableName, Columns cols) {
		this.tableName = tableName;
		this.addAll(cols.getList());
	}
	
	public SqlJoinOn(String tableName) {
		this.tableName = tableName;
	}
	
	public SqlJoinOn on(String mainTableField, String myTableField) {
		this.onMainTableField = mainTableField;
		this.onMyTableField = myTableField;
		return this;
	}
	
	public SqlJoinOn mode(SqlJoinMode type) {
		this.type = type;
		return this;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public SqlJoinOn setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	public SqlJoinMode getType() {
		return type;
	}

	public SqlJoinOn setType(SqlJoinMode type) {
		this.type = type;
		return this;
	}

	public String getOnMainTableField() {
		return onMainTableField;
	}

	public SqlJoinOn setOnMainTableField(String onMainTableField) {
		this.onMainTableField = onMainTableField;
		return this;
	}

	public String getOnMyTableField() {
		return onMyTableField;
	}

	public SqlJoinOn setOnMyTableField(String onMyTableField) {
		this.onMyTableField = onMyTableField;
		return this;
	}
}
