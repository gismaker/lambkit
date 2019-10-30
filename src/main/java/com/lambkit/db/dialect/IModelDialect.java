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
package com.lambkit.db.dialect;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Example;

public interface IModelDialect {

	public String forFindBySql(String sql, String orderBy, Object limit);
    public String forFindByColumns(String table, String loadColumns, List<Column> columns, String orderBy, Object limit);
    public String forPaginateSelect(String loadColumns);
    public String forPaginateFrom(String table, List<Column> columns, String orderBy);
    
    public SqlPara forFindBySqlPara(SqlPara sqlPara, String orderBy, Object limit);
    public SqlPara forFindByExample(Example example, Object limit);
    public SqlPara forPaginateByExample(Example example);
    public SqlPara forPaginateFormByExample(Example example);
    
    public SqlPara forDeleteByExample(Example example);
    
    public SqlPara forUpdate(Record record, String tableName, QueryParas queryParas);
    public SqlPara forUpdateByExample(Record record, Example example);

    //public String buildSQL(String queryType, String fieldName, Object fieldValue, String alias, List<Object> params);
}
