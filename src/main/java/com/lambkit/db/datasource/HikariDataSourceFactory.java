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

import com.jfinal.config.Plugins;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.lambkit.common.util.StringUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class HikariDataSourceFactory implements DataSourceFactory {

	@Override
	public DataSource createDataSource(Plugins plugin, DataSourceConfig dataSourceConfig) {
		// TODO Auto-generated method stub
		HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dataSourceConfig.getUrl());
        hikariConfig.setUsername(dataSourceConfig.getUser());
        hikariConfig.setPassword(dataSourceConfig.getPassword());
        hikariConfig.addDataSourceProperty("cachePrepStmts", dataSourceConfig.isCachePrepStmts());
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", dataSourceConfig.getPrepStmtCacheSize());
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", dataSourceConfig.getPrepStmtCacheSqlLimit());
        hikariConfig.setDriverClassName(dataSourceConfig.getDriverClassName());
        hikariConfig.setPoolName(dataSourceConfig.getPoolName());
        if (hikariConfig.getConnectionInitSql() != null) {
            hikariConfig.setConnectionInitSql(dataSourceConfig.getConnectionInitSql());
        }
        hikariConfig.setMaximumPoolSize(dataSourceConfig.getMaximumPoolSize());
        return new HikariDataSource(hikariConfig);
	}
	
    @Override
    public ActiveRecordPlugin createRecordPlugin(Plugins plugin, DataSourceConfig dataSourceConfig) {
        String configName = dataSourceConfig.getName();
        DataSource dataSource = createDataSource(plugin, dataSourceConfig);
        ActiveRecordPlugin activeRecordPlugin = StringUtils.isNotBlank(configName)
                ? new ActiveRecordPlugin(configName, dataSource)
                : new ActiveRecordPlugin(dataSource);
        return activeRecordPlugin;
    }
}
