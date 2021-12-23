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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lambkit.db.meta.TableMeta;
import com.lambkit.db.mgr.ITable;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.generator.GeneratorContext;

public class MgrdbGenerator extends DatabaseGenerator {

	public MgrdbGenerator(GeneratorContext context) {
		super(context);
	}

	@Override
	public void generate(String templatePath, Map<String, Object> options) {
		// TODO Auto-generated method stub
		if(context==null) return;
		//List<TableconfigModel> tables = TableconfigModel.dao.find("select * from sys_tableconfig " + options.get("where"));
		String where = options.get("where")!=null ? options.get("where").toString() : null;
		List<? extends ITable> tables =  MgrdbManager.me().getService().getTableDao().findByWhere(where);
		if(tables==null) return;
		Map<String, Object> templateModel = context.createTemplateModel();
		templateModel.putAll(options);
		boolean genMgrTable = true;
		if(options.containsKey("genMgrTable") && options.get("genMgrTable").equals("false")) genMgrTable = false;
		boolean eachTable = options.containsKey("eachTable") && options.get("eachTable").toString().equals("false") ? false : true;
		if(eachTable) {
			templateModel.put("tables", tables);
			for(ITable tb : tables) {
				String tableName = tb.getName();
				if(!genMgrTable) {
					if(tableName.startsWith("meta_") ||
							tableName.equals("sys_tableconfig") || 
							tableName.equals("sys_fieldconfig")) return; 
				}
				MgrTable mgrtb = MgrdbManager.me().getService().createTable(tb.getName(), MgrConstants.ALL);
				templateModel.put("model", tb); 
				templateModel.put("table", mgrtb.getMeta());
				templateModel.put("modelName", mgrtb.getMeta().getModelName());
				templateModel.put("classname", mgrtb.getMeta().getModelName());
				templateModel.put("attrName", mgrtb.getMeta().getAttrName());
				templateModel.put("columns", mgrtb.getMeta().getColumnMetas());
				templateModel.put("tableName", mgrtb.getMeta().getName());
				templateModel.put("tablename", mgrtb.getMeta().getName());
				templateModel.put("primaryKey", mgrtb.getMeta().getPrimaryKey());
				templateModel.put("title", tb.getTitle());
				context.generate(templateModel, templatePath);
			}
		} else {
			List<Map<String, Object>> tablesList = new ArrayList<Map<String, Object>>();
			for(ITable tb : tables) {
				Map<String, Object> tableItem = new HashMap<String, Object>();
				String tableName = tb.getName();
				if(!genMgrTable) {
					if(tableName.startsWith("meta_") ||
							tableName.equals("sys_tableconfig") || 
							tableName.equals("sys_fieldconfig")) return; 
				}
				MgrTable mgrtb = MgrdbManager.me().getService().createTable(tb.getName(), MgrConstants.ALL);
				tableItem.put("model", tb); 
				tableItem.put("table", mgrtb.getMeta());
				tableItem.put("modelName", mgrtb.getMeta().getModelName());
				tableItem.put("classname", mgrtb.getMeta().getModelName());
				tableItem.put("attrName", mgrtb.getMeta().getAttrName());
				tableItem.put("columns", mgrtb.getMeta().getColumnMetas());
				tableItem.put("tableName", mgrtb.getMeta().getName());
				tableItem.put("tablename", mgrtb.getMeta().getName());
				tableItem.put("primaryKey", mgrtb.getMeta().getPrimaryKey());
				tableItem.put("title", tb.getTitle());
				tablesList.add(tableItem);
			}
			templateModel.put("tables", tablesList);
			context.generate(templateModel, templatePath);
		}
		
	}
	
	@Override
	public Object execute(String templateFilePath, Map<String, Object> options) {
		// TODO Auto-generated method stub
		if(context==null) return null;
		Map<String, TableMeta> tableMetas = getTableMetas(options);
		Map<String, Object> templateModel = context.createTemplateModel();
		templateModel.put("tables", tableMetas.values());
		templateModel.putAll(options);
		return context.execute(options, templateFilePath);
	}
}
