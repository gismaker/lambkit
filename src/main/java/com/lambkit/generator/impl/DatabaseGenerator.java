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
package com.lambkit.generator.impl;

import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.DbKit;
import com.lambkit.db.meta.MetaBuilder;
import com.lambkit.db.meta.TableMeta;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.generator.GeneratorContext;
import com.lambkit.generator.IGenerator;

public class DatabaseGenerator implements IGenerator {

	@Override
	public void generate(GeneratorContext g, String templatePath, Map<String, Object> options) {
		// TODO Auto-generated method stub
		Map<String, TableMeta> tableMetas = getTableMetas(options);
		Map<String, Object> templateModel = g.createTemplateModel();
		templateModel.put("tables", tableMetas);
		templateModel.putAll(options);
		boolean hasMgrTable = false;
		boolean genMgrTable = true;
		if(options.containsKey("hasMgrTable") && options.get("hasMgrTable").equals("true")) hasMgrTable = true;
		if(options.containsKey("genMgrTable") && options.get("genMgrTable").equals("false")) genMgrTable = false;
		for (TableMeta tableMeta : tableMetas.values()) {
			String tableName = tableMeta.getName();
			if(!genMgrTable) {
				if(tableName.startsWith("meta_") ||
						tableName.equals("sys_tableconfig") || 
						tableName.equals("sys_fieldconfig")) return; 
			}
			templateModel.put("table", tableMeta); 
			templateModel.put("modelName", tableMeta.getModelName());
			templateModel.put("classname", tableMeta.getModelName());
			templateModel.put("attrName", tableMeta.getAttrName());
			templateModel.put("columns", tableMeta.getColumnMetas());
			templateModel.put("tableName", tableMeta.getName());
			templateModel.put("tablename", tableMeta.getName());
			templateModel.put("primaryKey", tableMeta.getPrimaryKey());
			templateModel.put("title", tableMeta.getTitle());
			if(hasMgrTable) {
				MgrTable mgrtb = MgrdbManager.me().getService().createTableWithoutMeta(tableName, MgrConstants.ALL, null);
				templateModel.put("model", mgrtb);
				templateModel.put("title", mgrtb.getModel().getTitle());
			}
			g.generate(templateModel, templatePath);
		}
	}
	
	@Override
	public Object execute(GeneratorContext g, String templateFilePath, Map<String, Object> options) {
		// TODO Auto-generated method stub
		Map<String, TableMeta> tableMetas = getTableMetas(options);
		Map<String, Object> templateModel = g.createTemplateModel();
		templateModel.put("tables", tableMetas.values());
		templateModel.putAll(options);
		return g.execute(options, templateFilePath);
	}
	
	public Map<String, TableMeta> getTableMetas(Map<String, Object> options) {
		Config config = DbKit.getConfig();//StrKit.notBlank(configName) ? DbKit.getConfig(configName) : DbKit.getConfig();
		MetaBuilder metaBuilder = new MetaBuilder(config.getDataSource());
		metaBuilder.setDialect(config.getDialect());
		if(options.containsKey("tableRemovePrefixes")) {
			Object trp = options.get("tableRemovePrefixes");
			if(trp instanceof List) {
				List<String> tableRemovePrefixes = (List<String>) trp;
				metaBuilder.setRemovedTableNamePrefixes((String[]) tableRemovePrefixes.toArray());
			} else {
				String tableRemovePrefixes = trp.toString();
				metaBuilder.setRemovedTableNamePrefixes(tableRemovePrefixes.split(","));
			}
		}
		if(options.containsKey("excludedTables")) {
			Object eto = options.get("excludedTables");
			if(eto instanceof List) {
				List<String> excludedTables = (List<String>) eto;
				metaBuilder.addExcludedTable((String[]) excludedTables.toArray());
			} else {
				String excludedTables = eto.toString();
				metaBuilder.addExcludedTable(excludedTables.split(","));
			}
		}
		if(options.containsKey("includedTables")) {
			Object eto = options.get("includedTables");
			if(eto instanceof List) {
				List<String> includedTables = (List<String>) eto;
				metaBuilder.addIncludedTable((String[]) includedTables.toArray());
			} else {
				String includedTables = eto.toString();
				metaBuilder.addIncludedTable(includedTables.split(","));
			}
		}
		return metaBuilder.build();
	}
}
