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

import com.beust.jcommander.internal.Maps;
import com.jfinal.config.Plugins;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Model;
import com.lambkit.Lambkit;
import com.lambkit.common.LambkitManager;
import com.lambkit.common.bean.ActiveRecordBean;
import com.lambkit.common.exception.LambkitIllegalConfigException;
import com.lambkit.common.util.ArrayUtils;
import com.lambkit.common.util.StringUtils;
import com.lambkit.core.aop.AopKit;
import com.lambkit.core.cache.CacheManager;
import com.lambkit.db.datasource.ActiveRecordBuilder;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.db.datasource.DataSourceConfig;
import com.lambkit.db.datasource.DataSourceConfigManager;
import com.lambkit.db.dialect.LambkitAnsiSqlDialect;
import com.lambkit.db.dialect.LambkitMysqlDialect;
import com.lambkit.db.dialect.LambkitOracleDialect;
import com.lambkit.db.dialect.LambkitPostgreSqlDialect;
import com.lambkit.db.dialect.LambkitSqlServerDialect;
import com.lambkit.db.dialect.LambkitSqlite3Dialect;

import java.util.*;


/**
 * 数据库 管理
 */
public class DbManager {
    private static DbManager manager;

    private Map<String, DbWrapper> dbWrappers = null;

    public static DbManager me() {
        if (manager == null) {
            manager = AopKit.singleton(DbManager.class);
        }
        return manager;
    }
    
    public void addArp(Plugins plugin, DataSourceConfig datasourceConfig) {
    	if (datasourceConfig.isConfigOk()) {
            ActiveRecordPlugin activeRecordPlugin = createRecordPlugin(plugin, datasourceConfig);
            activeRecordPlugin.setShowSql(Lambkit.isDevMode());
            activeRecordPlugin.setCache(CacheManager.me().getCache());
            configSqlTemplate(datasourceConfig, activeRecordPlugin);
            configDialect(activeRecordPlugin, datasourceConfig);
            addArp(activeRecordPlugin);
        }
    }
    
    public void addArp(ActiveRecordPlugin arp) {
    	addArp(new ActiveRecordPluginWrapper(arp));
    }
    
    public void addArp(ActiveRecordPluginWrapper arp) {
    	DbWrapper db = new DbWrapper();
    	db.setArp(arp);
    	db.setConfigName(arp.getConfig().getName());
    	getDbWrappers().put(arp.getConfig().getName(), db);
    }
    
    public void addTable(String configName, TableWrapper table) {
    	DbWrapper db = getDbWrappers().get(configName);
    	if(db!=null) {
    		db.addTable(table);
    	}
    }
    
    public DbWrapper getDbWrapper(String configName) {
    	return getDbWrappers().get(configName);
    }
    
    public void removeArp(String configName) {
    	//
    	
    }

    public Map<String, DbWrapper> init(Plugins plugin) {
        // 所有的数据源
        Map<String, DataSourceConfig> datasourceConfigs = DataSourceConfigManager.me().getDatasourceConfigs();
        // 分库的数据源，一个数据源包含了多个数据源。
        Map<String, DataSourceConfig> shardingDatasourceConfigs = DataSourceConfigManager.me().getShardingDatasourceConfigs();
        if (shardingDatasourceConfigs != null && shardingDatasourceConfigs.size() > 0) {
            for (Map.Entry<String, DataSourceConfig> entry : shardingDatasourceConfigs.entrySet()) {
                String databaseConfig = entry.getValue().getShardingDatabase();
                if (StringUtils.isBlank(databaseConfig)) {
                    continue;
                }
                Set<String> databases = StringUtils.splitToSet(databaseConfig, ",");
                for (String database : databases) {
                    DataSourceConfig datasourceConfig = datasourceConfigs.remove(database);
                    if (datasourceConfig == null) {
                        throw new NullPointerException("has no datasource config named " + database + ",plase check your sharding database config");
                    }
                    entry.getValue().addChildDatasourceConfig(datasourceConfig);
                }
            }
        }
        //所有数据源，包含了分库的和未分库的
        Map<String, DataSourceConfig> allDatasourceConfigs = new HashMap<>();
        if (datasourceConfigs != null) {
            allDatasourceConfigs.putAll(datasourceConfigs);
        }
        if (shardingDatasourceConfigs != null) {
            allDatasourceConfigs.putAll(shardingDatasourceConfigs);
        }
        for (Map.Entry<String, DataSourceConfig> entry : allDatasourceConfigs.entrySet()) {
            DataSourceConfig datasourceConfig = entry.getValue();
            addArp(plugin, datasourceConfig);
        }
        return getDbWrappers();
    }

    /**
     * 配置 本地 sql
     *
     * @param datasourceConfig
     * @param activeRecordPlugin
     */
    private void configSqlTemplate(DataSourceConfig datasourceConfig, ActiveRecordPlugin activeRecordPlugin) {
        String sqlTemplatePath = datasourceConfig.getSqlTemplatePath();
        if (StringUtils.isNotBlank(sqlTemplatePath)) {
            if (sqlTemplatePath.startsWith("/")) {
                activeRecordPlugin.setBaseSqlTemplatePath(datasourceConfig.getSqlTemplatePath());
            } else {
                activeRecordPlugin.setBaseSqlTemplatePath(PathKit.getRootClassPath() + "/" + datasourceConfig.getSqlTemplatePath());
            }
        } else {
            activeRecordPlugin.setBaseSqlTemplatePath(PathKit.getRootClassPath());
        }
        String sqlTemplateString = datasourceConfig.getSqlTemplate();
        if (sqlTemplateString != null) {
            String[] sqlTemplateFiles = sqlTemplateString.split(",");
            for (String sql : sqlTemplateFiles) {
                activeRecordPlugin.addSqlTemplate(sql);
            }
        }
    }

    /**
     * 配置 数据源的 方言
     *
     * @param activeRecordPlugin
     * @param datasourceConfig
     */
    private void configDialect(ActiveRecordPlugin activeRecordPlugin, DataSourceConfig datasourceConfig) {
        switch (datasourceConfig.getType()) {
            case DataSourceConfig.TYPE_MYSQL:
                activeRecordPlugin.setDialect(new LambkitMysqlDialect());
                break;
            case DataSourceConfig.TYPE_ORACLE:
                activeRecordPlugin.setDialect(new LambkitOracleDialect());
                break;
            case DataSourceConfig.TYPE_SQLSERVER:
                activeRecordPlugin.setDialect(new LambkitSqlServerDialect());
                break;
            case DataSourceConfig.TYPE_SQLITE:
                activeRecordPlugin.setDialect(new LambkitSqlite3Dialect());
                break;
            case DataSourceConfig.TYPE_ANSISQL:
                activeRecordPlugin.setDialect(new LambkitAnsiSqlDialect());
                break;
            case DataSourceConfig.TYPE_POSTGRESQL:
                activeRecordPlugin.setDialect(new LambkitPostgreSqlDialect());
                break;
            default:
                throw new LambkitIllegalConfigException("only support datasource type : mysql、orcale、sqlserver、sqlite、ansisql and postgresql, please check your lambkit.properties. ");
        }
    }

    /**
     * 创建 ActiveRecordPlugin 插件，用于数据库读写
     *
     * @param config
     * @return
     */
    private ActiveRecordPlugin createRecordPlugin(Plugins plugin, DataSourceConfig config) {
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordBuilder(config).build(plugin);
        if(activeRecordPlugin!=null) {
        	LambkitManager.me().addActiveRecord(new ActiveRecordBean(config.getName(), config.getDbname(), config));
        }
        /**
         * 不需要添加映射的直接返回
         */
        if (!config.isNeedAddMapping()) {
            return activeRecordPlugin;
        }
        System.out.println("需要添加映射");

        String configTableString = config.getTable();
        String excludeTableString = config.getExcludeTable();
        
        List<TableWrapper> TableMappings = TableManager.me().getTablesInfos(configTableString, excludeTableString);
        if (ArrayUtils.isNullOrEmpty(TableMappings)) {
            return activeRecordPlugin;
        }
        for (TableWrapper ti : TableMappings) {
            if (StringUtils.isNotBlank(ti.getPrimaryKey())) {
                activeRecordPlugin.addMapping(ti.getTableName(), ti.getPrimaryKey(), (Class<? extends Model<?>>) ti.getModelClass());
            } else {
                activeRecordPlugin.addMapping(ti.getTableName(), (Class<? extends Model<?>>) ti.getModelClass());
            }
        }
        return activeRecordPlugin;
    }

	public Map<String, DbWrapper> getDbWrappers() {
		if(dbWrappers==null) {
			dbWrappers = Maps.newHashMap();
		}
		return dbWrappers;
	}
}
