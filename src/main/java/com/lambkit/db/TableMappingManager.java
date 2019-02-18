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
import com.lambkit.common.util.ClassUtils;
import com.lambkit.common.util.StringUtils;
import com.lambkit.db.annotation.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TableMappingManager {

    private List<TableMapping> TableMappings;


    private static TableMappingManager instance = new TableMappingManager();

    public static TableMappingManager me() {
        return instance;
    }

    public List<TableMapping> getAllTableMappings() {
        if (TableMappings == null) {
            TableMappings = new ArrayList<>();
            initTableMappings(TableMappings);
        }
        return TableMappings;
    }


    public List<TableMapping> getTablesInfos(String includeTables, String excludeTables) {
        List<TableMapping> TableMappings = new ArrayList<>();

        Set<String> includeTableSet = includeTables == null ? null : StringUtils.splitToSet(includeTables, ",");
        Set<String> excludeTableSet = excludeTables == null ? null : StringUtils.splitToSet(excludeTables, ",");

        for (TableMapping TableMapping : getAllTableMappings()) {
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
                TableMappings.add(TableMapping);
            }
        }

        return TableMappings;
    }


    private void initTableMappings(List<TableMapping> TableMappings) {
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

            TableMappings.add(TableMapping);
        }

    }
}
