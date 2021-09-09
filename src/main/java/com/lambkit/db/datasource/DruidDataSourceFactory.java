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

import javax.sql.DataSource;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Plugins;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.lambkit.common.util.StringUtils;

public class DruidDataSourceFactory implements DataSourceFactory {

	@Override
	public DataSource createDataSource(Plugins plugin, DataSourceConfig dataSourceConfig) {
		// TODO Auto-generated method stub
		//System.out.println("db_config: " + dataSourceConfig.getName() + ", " + dataSourceConfig.getDbname());
    	DruidPlugin druidPlugin = new DruidPlugin(dataSourceConfig.getUrl(),
    			dataSourceConfig.getUser(),dataSourceConfig.getPassword(), dataSourceConfig.getDriverClassName());
    	// 1.统计信息插件
    	StatFilter statFilter = new StatFilter();
    	statFilter.setMergeSql(dataSourceConfig.isDruidMergeSql());
    	statFilter.setLogSlowSql(dataSourceConfig.isDruidLogSlowSql());
    	// 慢查询目前设置为1s,随着优化一步步进行慢慢更改
    	statFilter.setSlowSqlMillis(dataSourceConfig.getDruidSlowSqlMillis());
    	druidPlugin.addFilter(statFilter);
    	String dbtype = dataSourceConfig.getType();
    	if(!"sqlite".equals(dbtype)) {
    		WallFilter wall = new WallFilter();
    		wall.setDbType(dbtype);
    		druidPlugin.addFilter(wall);
    	}
		
		druidPlugin.setInitialSize(dataSourceConfig.getInitialSize());
		druidPlugin.setMinIdle(dataSourceConfig.getMinIdle());
		druidPlugin.setMaxActive(dataSourceConfig.getMaxActive());
		druidPlugin.setMaxWait(dataSourceConfig.getMaxWait());
		if(plugin!=null) {
			plugin.add(druidPlugin);
		}
		druidPlugin.start();
		return druidPlugin.getDataSource();
	}
	
    @Override
    public ActiveRecordPlugin createRecordPlugin(Plugins plugin, DataSourceConfig dataSourceConfig) {
    	//System.out.println("db_config: " + dataSourceConfig.getName() + ", " + dataSourceConfig.getDbname());
    	DruidPlugin druidPlugin = new DruidPlugin(dataSourceConfig.getUrl(),
    			dataSourceConfig.getUser(),dataSourceConfig.getPassword(), dataSourceConfig.getDriverClassName());
    	// 1.统计信息插件
    	StatFilter statFilter = new StatFilter();
    	statFilter.setMergeSql(dataSourceConfig.isDruidMergeSql());
    	statFilter.setLogSlowSql(dataSourceConfig.isDruidLogSlowSql());
    	// 慢查询目前设置为1s,随着优化一步步进行慢慢更改
    	statFilter.setSlowSqlMillis(dataSourceConfig.getDruidSlowSqlMillis());
    	druidPlugin.addFilter(statFilter);
    	String dbtype = dataSourceConfig.getType();
    	if(!"sqlite".equals(dbtype)) {
    		WallFilter wall = new WallFilter();
    		wall.setDbType(dbtype);
    		druidPlugin.addFilter(wall);
    	}
		
		druidPlugin.setInitialSize(dataSourceConfig.getInitialSize());
		druidPlugin.setMinIdle(dataSourceConfig.getMinIdle());
		druidPlugin.setMaxActive(dataSourceConfig.getMaxActive());
		druidPlugin.setMaxWait(dataSourceConfig.getMaxWait());
		if(plugin!=null) {
			plugin.add(druidPlugin);
		}
		String configName = dataSourceConfig.getName();
        ActiveRecordPlugin activeRecordPlugin = StringUtils.isNotBlank(configName)
                ? new ActiveRecordPlugin(configName, druidPlugin)
                : new ActiveRecordPlugin(druidPlugin);
		return activeRecordPlugin;
    }
}
