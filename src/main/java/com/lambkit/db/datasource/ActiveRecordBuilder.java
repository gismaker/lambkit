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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.jfinal.config.Plugins;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.lambkit.common.util.ClassNewer;
import com.lambkit.common.util.StringUtils;
import com.lambkit.db.TableMapping;
import com.lambkit.db.TableMappingManager;
import com.lambkit.exception.LambkitException;

import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingsphere.core.keygen.KeyGenerator;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

public class ActiveRecordBuilder {

    private DataSourceConfig datasourceConfig;

    public ActiveRecordBuilder(DataSourceConfig datasourceConfig) {
        this.datasourceConfig = datasourceConfig;
    }

    public ActiveRecordPlugin build(Plugins plugin) {
    	if (datasourceConfig.isShardingEnable()) {
           DataSource dataSource = getShardingDataSource(plugin);
           return createRecordPlugin(dataSource, datasourceConfig.getName());
        } else {
        	return createRecordPlugin(plugin, datasourceConfig);
        }
    }
    
    private DataSource getShardingDataSource(Plugins plugin) {
    	 Map<String, DataSource> dataSourceMap = new HashMap<>();

         if (datasourceConfig.getChildDatasourceConfigs() != null) {
             for (DataSourceConfig childConfig : datasourceConfig.getChildDatasourceConfigs()) {
                 dataSourceMap.put(childConfig.getName(), createDataSource(plugin, childConfig));
             }
         }
         /**
          * 可能只是分表，不分库
          */
         else {
             dataSourceMap.put(datasourceConfig.getName(), createDataSource(plugin, datasourceConfig));
         }


         ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();

         List<TableMapping> tableInfos = TableMappingManager.me().getTablesInfos(datasourceConfig.getTable(), datasourceConfig.getExcludeTable());
         StringBuilder bindTableGroups = new StringBuilder();
         for (TableMapping ti : tableInfos) {
             TableRuleConfiguration tableRuleConfiguration = getTableRuleConfiguration(ti);
             shardingRuleConfiguration.getTableRuleConfigs().add(tableRuleConfiguration);
             bindTableGroups.append(ti.getTableName()).append(",");
         }

         if (bindTableGroups.length() > 0) {
             bindTableGroups.deleteCharAt(bindTableGroups.length() - 1); //delete last char
             shardingRuleConfiguration.getBindingTableGroups().add(bindTableGroups.toString());
         }


         try {
             return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, new HashMap<String, Object>(), new Properties());
         } catch (SQLException e) {
             throw new LambkitException(e);
         }
    }
    
    private TableRuleConfiguration getTableRuleConfiguration(TableMapping tableInfo) {
        TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration();
        tableRuleConfig.setLogicTable(tableInfo.getTableName());

        if (StrKit.notBlank(tableInfo.getActualDataNodes())) {
            tableRuleConfig.setActualDataNodes(tableInfo.getActualDataNodes());
        }

        if (tableInfo.getKeyGeneratorClass() != KeyGenerator.class) {
            tableRuleConfig.setKeyGenerator(ClassNewer.newInstance(tableInfo.getKeyGeneratorClass()));
        }

        if (StrKit.notBlank(tableInfo.getKeyGeneratorColumnName())) {
            tableRuleConfig.setKeyGeneratorColumnName(tableInfo.getKeyGeneratorColumnName());
        }

        if (tableInfo.getDatabaseShardingStrategyConfig() != ShardingStrategyConfiguration.class) {
            tableRuleConfig.setDatabaseShardingStrategyConfig(ClassNewer.newInstance(tableInfo.getDatabaseShardingStrategyConfig()));
        }

        if (tableInfo.getTableShardingStrategyConfig() != ShardingStrategyConfiguration.class) {
            tableRuleConfig.setTableShardingStrategyConfig(ClassNewer.newInstance(tableInfo.getTableShardingStrategyConfig()));
        }

        return tableRuleConfig;
    }
    
    private DataSource createDataSource(Plugins plugin, DataSourceConfig dataSourceConfig) {
        DataSourceFactory factory = ClassNewer.newInstance(dataSourceConfig.getFactory());
        if (factory == null) {
            factory = new DruidDataSourceFactory();
        }

        return factory.createDataSource(plugin, dataSourceConfig);
    }

    private ActiveRecordPlugin createRecordPlugin(Plugins plugin, DataSourceConfig dataSourceConfig) {
        DataSourceFactory factory = null;
        if(StrKit.notBlank(dataSourceConfig.getFactory())) {
        	ClassNewer.newInstance(dataSourceConfig.getFactory());
        }
        if (factory == null) {
            factory = new DruidDataSourceFactory();
        }

        return factory.createRecordPlugin(plugin, dataSourceConfig);
    }
    
    private ActiveRecordPlugin createRecordPlugin(DataSource dataSource, String configName) {
        ActiveRecordPlugin activeRecordPlugin = StringUtils.isNotBlank(configName)
                ? new ActiveRecordPlugin(configName, dataSource)
                : new ActiveRecordPlugin(dataSource);
		return activeRecordPlugin;
    }
}
