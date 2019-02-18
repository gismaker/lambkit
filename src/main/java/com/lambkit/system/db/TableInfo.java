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

import com.jfinal.plugin.activerecord.Table;
import com.lambkit.db.meta.TableMeta;
import com.lambkit.system.info.TableMappingInfo;

public class TableInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1322188496941556188L;

	private String name;
	private TableMeta meta;
	private TableMappingInfo mapping;
	private Table table;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TableMeta getMeta() {
		return meta;
	}
	public void setMeta(TableMeta meta) {
		this.meta = meta;
	}
	public TableMappingInfo getMapping() {
		return mapping;
	}
	public void setMapping(TableMappingInfo mapping) {
		this.mapping = mapping;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	
	
}
