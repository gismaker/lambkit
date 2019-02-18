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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.lambkit.db.meta.MetaBuilder;
import com.lambkit.db.meta.TableMeta;
import com.lambkit.system.SystemInfo;
import com.lambkit.system.info.ActiveRecordInfo;
import com.lambkit.system.info.TableMappingInfo;

public class DataSourceInfoBuilder {

	public List<DataSourceInfo> build(SystemInfo info) {
		List<DataSourceInfo> dsiList = new ArrayList<>();
		List<ActiveRecordInfo> ars = info.getActiveRecords();
		if (ars.size() < 1)
			return dsiList;
		List<TableMappingInfo> mappings = info.getMappings();
		for (ActiveRecordInfo activeRecordInfo : ars) {
			dsiList.add(createDataSourceInfo(activeRecordInfo, mappings));
		}
		return dsiList;
	}
	
	public Map<String, DataSourceInfo> buildMap(SystemInfo info) {
		Map<String, DataSourceInfo> dsiList = new HashMap<>();
		List<ActiveRecordInfo> ars = info.getActiveRecords();
		if (ars.size() < 1)
			return dsiList;
		List<TableMappingInfo> mappings = info.getMappings();
		for (ActiveRecordInfo activeRecordInfo : ars) {
			dsiList.put(activeRecordInfo.getName(), createDataSourceInfo(activeRecordInfo, mappings));
		}
		return dsiList;
	}

	public DataSourceInfo createDataSourceInfo(ActiveRecordInfo activeRecordInfo, List<TableMappingInfo> mappings) {
		DataSourceInfo ds = new DataSourceInfo();
		ds.setConfigName(activeRecordInfo.getName());
		ds.setActiveRecord(activeRecordInfo);
		String configName = activeRecordInfo.getName();
		Config config = StrKit.notBlank(configName) ? DbKit.getConfig(configName) : DbKit.getConfig();
		MetaBuilder metaBuilder = new MetaBuilder(config.getDataSource());
		metaBuilder.setDialect(config.getDialect());
		Map<String, TableMeta> tableMetas = metaBuilder.build();
		for (TableMeta tableMeta : tableMetas.values()) {
			TableInfo tb = new TableInfo();
			tb.setName(tableMeta.getName());
			tb.setMeta(tableMeta);
			for (TableMappingInfo tmi : mappings) {
				if (tableMeta.getName() == tmi.getTableName()) {
					tb.setMapping(tmi);
					Table table;
					try {
						table = TableMapping.me().getTable((Class<? extends Model>) Class.forName(tmi.getClassName()));
						tb.setTable(table);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
			ds.addTable(tb);
		}
		return ds;
	}
}
