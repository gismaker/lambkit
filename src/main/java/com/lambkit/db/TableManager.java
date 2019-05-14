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
import com.lambkit.common.util.StringUtils;
import com.lambkit.db.annotation.Table;
import com.lambkit.db.meta.MetaKit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TableManager {

    private List<TableWrapper> tableMappings;

    private static TableManager instance = new TableManager();

    public static TableManager me() {
        return instance;
    }

    public List<TableWrapper> getAllTableMappings() {
        if (tableMappings == null) {
            tableMappings = new ArrayList<>();
            //initTableMappings(tableMappings);
        }
        return tableMappings;
    }


    public List<TableWrapper> getTablesInfos(String includeTables, String excludeTables) {
        List<TableWrapper> tableMappings = new ArrayList<>();

        Set<String> includeTableSet = includeTables == null ? null : StringUtils.splitToSet(includeTables, ",");
        Set<String> excludeTableSet = excludeTables == null ? null : StringUtils.splitToSet(excludeTables, ",");

        for (TableWrapper TableMapping : getAllTableMappings()) {
            boolean isAdd = false;
            if (includeTableSet == null || includeTableSet.isEmpty()) {
                isAdd = true;
            } else if (includeTableSet.contains(TableMapping.getTableName())) {
                isAdd = true;
            }

            if (isAdd == true && excludeTableSet != null && excludeTableSet.contains(TableMapping.getTableName())) {
                isAdd = false;
            }

            if (isAdd) {
                tableMappings.add(TableMapping);
            }
        }

        return tableMappings;
    }

    /*
    private void initTableMappings(List<TableMapping> tableMappings) {
        Set<Class<?>> modelClassList = ClassUtils.scanPackageBySuper("", true, Model.class);
        if (modelClassList.size()==0) {
            return;
        }

        for (Class<?> clazz : modelClassList) {
            Table tb = clazz.getAnnotation(Table.class);
            if (tb == null)
                continue;


            TableMapping TableMapping = new TableMapping();
            TableMapping.setModelClass((Class<Model>)clazz);
            TableMapping.setPrimaryKey(tb.primaryKey());
            TableMapping.setTableName(tb.tableName());

            TableMapping.setActualDataNodes(tb.actualDataNodes());
            //TableMapping.setDatabaseShardingStrategyConfig(tb.databaseShardingStrategyConfig());
            //TableMapping.setTableShardingStrategyConfig(tb.tableShardingStrategyConfig());

            TableMapping.setKeyGeneratorClass(tb.keyGeneratorClass());
            TableMapping.setKeyGeneratorColumnName(tb.keyGeneratorColumnName());

            tableMappings.add(TableMapping);
        }

    }*/
    
    public TableWrapper addMapping(String configName, String tableName, String primaryKey, Class<? extends Model> modelClass) {
        TableWrapper tableMapping = new TableWrapper();
        tableMapping.setModelClass(modelClass);
        tableMapping.setPrimaryKey(primaryKey);
        tableMapping.setTableName(tableName);
        tableMapping.setConfigName(configName);
        //com.jfinal.plugin.activerecord.Table table = com.jfinal.plugin.activerecord.TableMapping.me().getTable(modelClass);
        //tableMapping.setColumnTypeMap(table.getColumnTypeMap());
        //tableMapping.setMeta(MetaKit.createTable(configName, tableName, tableName.split("_")[0]+"_"));
        Table tb = modelClass.getAnnotation(Table.class);
        if (tb != null) {
        	 tableMapping.setActualDataNodes(tb.actualDataNodes());
             //tableMapping.setDatabaseShardingStrategyConfig(tb.databaseShardingStrategyConfig());
             //tableMapping.setTableShardingStrategyConfig(tb.tableShardingStrategyConfig());
             tableMapping.setKeyGeneratorClass(tb.keyGeneratorClass());
             tableMapping.setKeyGeneratorColumnName(tb.keyGeneratorColumnName());
        }
        getAllTableMappings().add(tableMapping);
        return tableMapping;
    }
}
