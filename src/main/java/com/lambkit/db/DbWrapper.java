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
package com.lambkit.db;

import java.util.List;

import com.google.common.collect.Lists;
import com.jfinal.config.Plugins;
import com.jfinal.plugin.IPlugin;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.db.datasource.DataSourceConfig;
import com.lambkit.db.datasource.DataSourceConfigManager;

public class DbWrapper {

	private String configName; // DataSourceConfig Name from DataSourceConfigManager
	private Plugins plugins;
	private ActiveRecordPluginWrapper arp;
	private List<TableWrapper> tables;
	
	public DataSourceConfig getDatasourceConfig() {
		return DataSourceConfigManager.me().getDatasourceConfig(configName);
	}
	
	public void start() {
		if(plugins!=null) {
			for(IPlugin plugin : plugins.getPluginList()) {
				plugin.start();
			}
		}
		if(arp!=null && arp.getPlugin()!=null) {
			arp.getPlugin().start();
		}
	}
	
	public void stop() {
		if(plugins!=null) {
			for(IPlugin plugin : plugins.getPluginList()) {
				plugin.stop();
			}
		}
		if(arp!=null && arp.getPlugin()!=null) {
			arp.getPlugin().stop();
		}
	}
	
	public void addTable(TableWrapper table) {
		if(tables==null) {
			tables = Lists.newArrayList();
		}
		tables.add(table);
	}
	
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public List<TableWrapper> getTables() {
		return tables;
	}
	public void setTables(List<TableWrapper> tables) {
		this.tables = tables;
	}
	public ActiveRecordPluginWrapper getArp() {
		return arp;
	}
	public void setArp(ActiveRecordPluginWrapper arp) {
		this.arp = arp;
	}

	public Plugins getPlugins() {
		return plugins;
	}

	public void setPlugins(Plugins plugins) {
		this.plugins = plugins;
	}
}
