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
package com.lambkit.db.mgr;

import java.io.OutputStream;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;
import com.lambkit.db.meta.TableMeta;
import com.lambkit.db.sql.condition.ConditionBuilder;
import com.lambkit.db.sql.condition.SqlBuilder;
import com.lambkit.web.controller.BaseController;

public interface MgrdbService {

	ITableDao getTableDao();

	IFieldDao getFieldDao();

	MgrTable createTable(Object tbid, int type);

	MgrTable createTable(String tableName, int type);
	
	MgrTable createTable(Object tbid, int type, String orderby);

	MgrTable createTable(String tableName, int type, String orderby);

	MgrTable createTable(ITable tb, int type, String orderby);
	
	MgrTable createTableWithoutMeta(String tableName, int type, String orderby);
	
	MgrTable createTableWithoutModel(String tableName, int type);
	
	/**
	 * 将对象的日期，Long数据类型转化成date
	 * @param rod
	 * @param tbc
	 */
	void recordLongToDate(Record rod, MgrTable tbc);
	
	/**
	 * 将对象的日期,date数据类型转化成Long 
	 * @param rod
	 * @param tbc
	 */
	void recordDateToLong(Record rod, MgrTable tbc);
	
	List<String> getColumnNames(MgrTable tbc);
	
	String getSelectNames(MgrTable tbc, String alis);
	
	String getSelectNamesOfView(MgrTable tbc, String alis);
	
	String getSelectNamesOfViewInJoin(MgrTable tbc, String alis);
	
	String getSelectNamesOfView(MgrTable tbc, String fld, Object val);
	
	String getColumnType(Object tbid, String field);
	
	ConditionBuilder getConditionsSQL(BaseController c, MgrTable tbc);
	
	SqlBuilder getSelectNamesOfView(SqlBuilder sb, MgrTable tbc, String fld, Object val);
	
	SqlBuilder getSelectSQLOfView(SqlBuilder sb, MgrTable tbc, String alis);
	
	void exportExcel(MgrTable tbc, OutputStream os, String sql);
	void exportExcel(MgrTable tbc, OutputStream os, String sql, Object[] paras);
	/**
	 * 输出Excel
	 */
	void toExcel(MgrTable tbc, OutputStream os, List<Record> mlist, String selectSQL);
	
	/**
	 * 
	 * @param tb
	 */
	void tableToMgrdb(TableMeta tbc);
}
