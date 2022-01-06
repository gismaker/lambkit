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
package com.lambkit.db.mgr.util;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jfinal.kit.SyncWriteMap;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.db.dialect.IManagerDialect;
import com.lambkit.db.mgr.IField;

/**
 * 数据库操作
 * @author lamb
 *
 */
public class MgrDb {
	
	private static MgrDbPro pro = null;
	private static final Map<String, MgrDbPro> map = new SyncWriteMap<String, MgrDbPro>(32, 0.25F);
	
	
	public static MgrDbPro use() {
		if(pro==null) {
			pro = new MgrDbPro();
		}
		return pro;
	}
	
	public static MgrDbPro use(String configName) {
		MgrDbPro result = map.get(configName);
		if(result==null) {
			result = new MgrDbPro(configName);
			map.put(configName, result);
		}
		return result;
	}
	
	public IManagerDialect getDialect() {
		return pro.getDialect();
	}
	
	//------------------------------------
	// 数据库信息
	//------------------------------------	
	public List<Record> version() {
		return pro.version();
	}
	
	public List<Object> query(String sql) {
		return pro.query(sql);
	}
	
	public List<Record> find(String sql) {
		return pro.find(sql);
	}
	
	public Page<Record> paginate(int pageNumber, int pageSize, String select, String sqlExceptSelect) {
		return pro.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	}
	
	public int update(String sql) {
		return pro.update(sql);
	}
	
	public int delete(String sql) {
		return pro.delete(sql);
	}
	//------------------------------------
	// 查询数据库
	//------------------------------------	
	/**
	 * 查询所有表名
	 * @return
	 */
	public List<Object> getTableList(String dbname) {
		return pro.getTableList(dbname);
	}
	
	public List<Record> getTableListRecord(String dbname) {
		return pro.getTableListRecord(dbname);
	}
	
	public Map<String, Record> getTableListMap(String dbname) {
		return pro.getTableListMap(dbname);
	}
	
	public List<Object> getTableList() {
		return pro.getTableList();
	}
	
	/**
	 * 查询表中所有列名
	 * @param tableName
	 * @return
	 */
	public List<Object> getColumnList(String dbname, String tbname) {
		return pro.getColumnList(dbname, tbname);
	}
	
	public List<Record> getColumnListRecord(String dbname, String tbname) {
		return pro.getColumnListRecord(dbname, tbname);
	}
	
	public Set<String> getColumnNameSet(String dbname, String tbname) {
		return pro.getColumnNameSet(dbname, tbname);
	}
	
	public List<Object> getColumnList(String tbname) {
		return pro.getColumnList(tbname);
	}
	
	public Object getColumn(String dbname, String tbname, String colname) {
		return pro.getColumn(dbname, tbname, colname);
	}
	
	public Object getColumn(String tbname, String colname) {
		return pro.getColumn(tbname, colname);
	}
	
	public String getPrimaryKey(String schema, String tbname) {
		return pro.getPrimaryKey(schema, tbname);
	}
	
	//------------------------------------
	// 数据库操作
	//------------------------------------	
	/**
	 * 执行SQL语句
	 * @param conn
	 * @param sql
	 * @return
	 */
	public boolean execute(Connection conn, String sql) {
		return pro.execute(conn, sql);
	}
	
	public boolean execute(String sql) {
		return pro.execute(sql);
	}
	
	/**
	 * 执行SQL语句,
	 * @param tbname
	 * @param sql
	 * @return
	 */
	public boolean executeHasTable(String tbname, String sql, boolean flag) {
		return pro.executeHasTable(tbname, sql, flag);
	}
	
	/**
	 * 是否存在数据表
	 * @param dbname
	 * @param tbname
	 * @return
	 */
	public boolean hasTable(String tbname) {
		return pro.hasTable(tbname);
	}
	
	public boolean hasTable(String dbname, String tbname) {
		return pro.hasTable(dbname, tbname);
	}
	
	public boolean hasTable(Connection conn, String tbname) {
		return pro.hasTable(conn, tbname);
	}
	
	public boolean hasTable(Connection conn, String dbname, String tbname) {
		return pro.hasTable(conn, dbname, tbname);
	}

	/**
	 * 删除数据表
	 * @param tbname
	 * @return
	 */
	public boolean dropTable(String tbname) {
		return pro.dropTable(tbname);
	}
	
	//------------------------------------
	// 数据库字段操作
	//------------------------------------
	
	/**
	 * 添加字段
	 * ALTER TABLE 表名称 ADD id int unsigned not Null auto_increment primary key
	 * 
	 * @param tbname
	 * @param fldinfo
	 */
	public boolean addField(String tbname, String fldinfo, String after) {
		return pro.addField(tbname, fldinfo, after);
	}

	/**
	 * 修改某个表的字段名称及指定为空或非空 alter table 表名称 change 字段原名称 字段新名称 字段类型 [是否允许非空];
	 * 修改某个表的字段类型及指定为空或非空 alter table 表名称 change 字段名称 字段名称 字段类型 [是否允许非空];
	 * @param tbname : 表名称
	 * @param fldinfo : 字段名称 字段类型 [是否允许非空]
	 * @param after
	 * @return
	 */
	public boolean changeField(String tbname, String fldinfo, String after) {
		return pro.changeField(tbname, fldinfo, after);
	}

	/**
	 * 修改某个表的字段类型及指定为空或非空 alter table 表名称 modify 字段名称 字段类型 [是否允许非空];
	 * 
	 * @param tbname
	 *            : 表名称
	 * @param fldinfo
	 *            : 字段名称 字段类型 [是否允许非空]
	 */
	public boolean modifyFieldType(String tbname, String fldinfo, String after) {
		return pro.modifyFieldType(tbname, fldinfo, after);
	}

	/**
	 * 删除某一字段，可用命令：ALTER TABLE mytable DROP 字段名;
	 * 
	 * @param tbname
	 *            : mytable
	 * @param fldname
	 *            : 字段名
	 */
	public boolean dropField(String tbname, String fldname) {
		return pro.dropField(tbname, fldname);
	}
	
	//------------------------------------
	// Mgrdb数据库操作
	//------------------------------------	
	
	/**
	 * 删除数据表
	 * 
	 * @param tbid
	 * @param tbname
	 *            默认为NULL，通过tbid获得
	 * @return
	 */
	public boolean dropTableByMgrdb(Long tbid, String tbname) {
		return pro.dropTableByMgrdb(tbid, tbname);
	}

	/**
	 * 创建数据表
	 * 
	 * @param tbid
	 * @param tbname
	 * @return
	 */
	public boolean createTableByMgrdb(Object tbid, String tbname) {
		return pro.createTableByMgrdb(tbid, tbname);
	}
	
	/**
	 * 创建数据表
	 * 
	 * @param tbid
	 * @param tbname
	 * @return
	 */
	public boolean updateTableByMgrdb(Object tbid, String tbname, String sqlfrom, String type) {
		return pro.updateTableByMgrdb(tbid, tbname, sqlfrom, type);
	}
	
	//------------------------------------
	// Mgrdb数据库字段操作
	//------------------------------------	
		
	/**
	 * 删除字段
	 * @param tbid
	 * @param tbname
	 * @param fldname
	 * @return
	 */
	public boolean dropFieldByMgrdb(Long tbid, String tbname, String fldname) {
		return pro.dropFieldByMgrdb(tbid, tbname, fldname);
	}
	/**
	 * 删除字段
	 * @param tbid
	 * @param tbname
	 * @param fldid
	 * @return
	 */
	public boolean dropFieldByMgrdb(Long tbid, String tbname, Long fldid) {
		return pro.dropFieldByMgrdb(tbid, tbname, fldid);
	}
	
	/**
	 * 添加字段
	 * @param tbid
	 * @param tbname
	 * @param fldid
	 * @param after
	 * @return
	 */
	public boolean addFieldByMgrdb(Long tbid, String tbname, Long fldid, String after) {
		return pro.addFieldByMgrdb(tbid, tbname, fldid, after);
	}
	
	/**
	 * 添加字段
	 * @param tbid
	 * @param tbname
	 * @param resfcv
	 * @param after
	 * @return
	 */
	public boolean addFieldByMgrdb(Long tbid, String tbname, IField resfcv, String after) {
		return pro.addFieldByMgrdb(tbid, tbname, resfcv, after);
	}
	
	/**
	 * 修改字段
	 * @param tbid
	 * @param tbname
	 * @param fldid
	 * @param after
	 * @return
	 */
	public boolean changeFieldByMgrdb(Long tbid, String tbname, String resname, Long fldid, String after) {
		return pro.changeFieldByMgrdb(tbid, tbname, resname, fldid, after);
	}
	/**
	 * 修改字段
	 * @param tbid
	 * @param tbname
	 * @param resname
	 * @param resfcv
	 * @param after
	 * @return
	 */
	public boolean changeFieldByMgrdb(Long tbid, String tbname, String resname, IField resfcv, String after) {
		return pro.changeFieldByMgrdb(tbid, tbname, resname, resfcv, after);
	}
	
	/**
	 * 修改字段类型
	 * @param tbid
	 * @param tbname
	 * @param fldid
	 * @param after
	 * @return
	 */
	public boolean modifyFieldTypeByMgrdb(Long tbid, String tbname, Long fldid, String after) {
		return pro.modifyFieldTypeByMgrdb(tbid, tbname, fldid, after);
	}
	
	////////////////////////////////////////////////////////////////////
	/**
	 * 获取表格名称
	 * @param tbid
	 * @param tbname
	 * @return
	 */
	public String getTbNameByMgrdb(Object tbid, String tbname) {
		return pro.getTbNameByMgrdb(tbid, tbname);
	}
	
	/**
	 * 获取没个字段的sql
	 * @param fcv
	 * @return
	 */
	public String getFieldSQL(IField fcv) {
		return pro.getFieldSQL(fcv);
	}
	
}
