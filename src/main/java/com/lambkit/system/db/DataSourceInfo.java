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
package com.lambkit.system.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lambkit.system.info.ActiveRecordInfo;

public class DataSourceInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1940692012105254900L;

	private String configName;
	private ActiveRecordInfo activeRecord;
	private List<TableInfo> tables;
	
	public void addTable(TableInfo table) {
		if(tables==null) {
			tables = new ArrayList<>();
		}
		tables.add(table);
	}
	
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public List<TableInfo> getTables() {
		return tables;
	}
	public void setTables(List<TableInfo> tables) {
		this.tables = tables;
	}

	public ActiveRecordInfo getActiveRecord() {
		return activeRecord;
	}

	public void setActiveRecord(ActiveRecordInfo activeRecord) {
		this.activeRecord = activeRecord;
	}
	
}
