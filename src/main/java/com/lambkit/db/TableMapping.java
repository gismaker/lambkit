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

import com.jfinal.plugin.activerecord.Model;

import io.shardingsphere.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingsphere.core.keygen.KeyGenerator;

public class TableMapping {
    
    private String tableName;
    private String primaryKey;
    private Class<? extends Model> modelClass;
    private Class<? extends ShardingStrategyConfiguration> databaseShardingStrategyConfig;
    private Class<? extends ShardingStrategyConfiguration> tableShardingStrategyConfig;
    private String actualDataNodes;
    private String keyGeneratorColumnName;
    private Class<? extends KeyGenerator> keyGeneratorClass;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Class<? extends Model> getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
    }

    public Class<? extends ShardingStrategyConfiguration> getDatabaseShardingStrategyConfig() {
        return databaseShardingStrategyConfig;
    }

    public void setDatabaseShardingStrategyConfig(Class<? extends ShardingStrategyConfiguration> databaseShardingStrategyConfig) {
        this.databaseShardingStrategyConfig = databaseShardingStrategyConfig;
    }

    public Class<? extends ShardingStrategyConfiguration> getTableShardingStrategyConfig() {
        return tableShardingStrategyConfig;
    }

    public void setTableShardingStrategyConfig(Class<? extends ShardingStrategyConfiguration> tableShardingStrategyConfig) {
        this.tableShardingStrategyConfig = tableShardingStrategyConfig;
    }
    
    public String getActualDataNodes() {
        return actualDataNodes;
    }

    public void setActualDataNodes(String actualDataNodes) {
        this.actualDataNodes = actualDataNodes;
    }

    public String getKeyGeneratorColumnName() {
        return keyGeneratorColumnName;
    }

    public void setKeyGeneratorColumnName(String keyGeneratorColumnName) {
        this.keyGeneratorColumnName = keyGeneratorColumnName;
    }

	public Class<? extends KeyGenerator> getKeyGeneratorClass() {
		return keyGeneratorClass;
	}

	public void setKeyGeneratorClass(Class<? extends KeyGenerator> keyGeneratorClass) {
		this.keyGeneratorClass = keyGeneratorClass;
	}
}
