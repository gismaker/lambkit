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
package com.lambkit.db.datasource;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.Model;
import com.lambkit.common.LambkitManager;
import com.lambkit.common.bean.TableMappingBean;
import com.lambkit.db.DbManager;
import com.lambkit.db.TableManager;
import com.lambkit.db.TableWrapper;

public class ActiveRecordPluginWrapper {

	private ActiveRecordPlugin arp;
	
	public ActiveRecordPluginWrapper(ActiveRecordPlugin arp) {
		// TODO Auto-generated constructor stub
		this.arp = arp;
	}
	
	public ActiveRecordPlugin getPlugin() {
		return this.arp;
	}
	
	public Config getConfig() {
		return this.arp.getConfig();
	}
	
	public ActiveRecordPluginWrapper addMapping(String tableName, String primaryKey, Class<? extends Model<?>> modelClass) {
		arp.addMapping(tableName, primaryKey, modelClass);
		String configName = arp.getConfig().getName();
		LambkitManager.me().addMapping(new TableMappingBean(configName, tableName, primaryKey, modelClass.getName()));
		TableWrapper tableWrapper = TableManager.me().addMapping(configName, tableName, primaryKey, modelClass);
		DbManager.me().addTable(configName, tableWrapper);
		return this;
	}
	
	public ActiveRecordPluginWrapper addMapping(String tableName, Class<? extends Model<?>> modelClass) {
		arp.addMapping(tableName, modelClass);
		String configName = arp.getConfig().getName();
		LambkitManager.me().addMapping(new TableMappingBean(configName, tableName, "", modelClass.getName()));
		TableWrapper tableWrapper = TableManager.me().addMapping(configName, tableName, "", modelClass);
		DbManager.me().addTable(configName, tableWrapper);
		return this;
	}
	
	public ActiveRecordPluginWrapper addSqlTemplate(String sqlTemplate) {
		arp.addSqlTemplate(sqlTemplate);
		return this;
	}
	
	public ActiveRecordPluginWrapper addSqlTemplate(com.jfinal.template.source.ISource sqlTemplate) {
		arp.addSqlTemplate(sqlTemplate);
		return this;
	}
	
	public ActiveRecordPluginWrapper setBaseSqlTemplatePath(String baseSqlTemplatePath) {
		arp.setBaseSqlTemplatePath(baseSqlTemplatePath);
		return this;
	}

}
