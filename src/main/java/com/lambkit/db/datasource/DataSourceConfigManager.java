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

import com.google.common.collect.Maps;
import com.jfinal.kit.StrKit;
import com.lambkit.core.config.ConfigManager;

import java.util.*;

public class DataSourceConfigManager {

    private static final String DATASOURCE_PREFIX = "lambkit.db.";


    private static DataSourceConfigManager manager = new DataSourceConfigManager();

    public static DataSourceConfigManager me() {
        return manager;
    }

    private Map<String, DataSourceConfig> datasourceConfigs = Maps.newHashMap();
    private Map<String, DataSourceConfig> shardingDatasourceConfigs = Maps.newHashMap();

    private DataSourceConfigManager() {

        DataSourceConfig datasourceConfig = ConfigManager.me().get(DataSourceConfig.class, "lambkit.db");
        datasourceConfig.setName(DataSourceConfig.NAME_DEFAULT);
        if (datasourceConfig.isConfigOk()) {
            datasourceConfigs.put(datasourceConfig.getName(), datasourceConfig);
        }
        if (datasourceConfig.isShardingEnable()) {
            shardingDatasourceConfigs.put(datasourceConfig.getName(), datasourceConfig);
        }


        Properties prop = ConfigManager.me().getProperties();
        Set<String> datasourceNames = new HashSet<>();
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            String key = entry.getKey().toString();
            if (key.startsWith(DATASOURCE_PREFIX) && entry.getValue() != null) {
                String[] keySplits = key.split("\\.");
                if (keySplits.length == 4) {
                    datasourceNames.add(keySplits[2]);
                }
            }
        }


        for (String name : datasourceNames) {
            DataSourceConfig dsc = ConfigManager.me().get(DataSourceConfig.class, DATASOURCE_PREFIX + name);
            if (StrKit.isBlank(dsc.getName())) {
                dsc.setName(name);
            }
            if (dsc.isConfigOk()) {
                datasourceConfigs.put(name, dsc);
            }
            if (dsc.isShardingEnable()) {
                shardingDatasourceConfigs.put(name, dsc);
            }
        }
    }
    
    public DataSourceConfig getDatasourceConfig(String configName) {
    	return datasourceConfigs.get(configName);
    }

    public Map<String, DataSourceConfig> getDatasourceConfigs() {
        return datasourceConfigs;
    }

    public Map<String, DataSourceConfig> getShardingDatasourceConfigs() {
        return shardingDatasourceConfigs;
    }

    public DataSourceConfig getDefaultDatasourceConfigs() {
    	return datasourceConfigs.get(DataSourceConfig.NAME_DEFAULT);
    }
}
