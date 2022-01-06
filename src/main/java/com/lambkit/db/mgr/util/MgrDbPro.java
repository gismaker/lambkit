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
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbPro;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.Lambkit;
import com.lambkit.db.datasource.DataSourceConfig;
import com.lambkit.db.datasource.DataSourceConfigManager;
import com.lambkit.db.dialect.IManagerDialect;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.ITable;
import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrdbManager;

/**
 * 数据库操作
 * @author lamb
 *
 */
public class MgrDbPro {
	
	private DbPro dbtool = null;
	
	public MgrDbPro() {
		this.dbtool = Db.use();
	}
	
	public MgrDbPro(String configName) {
		this.dbtool = Db.use(configName);
	}
	
	/*
	public void initDbName() {
		if(!StrKit.notBlank(getDbName())) {
			initDbName(PropKit.get("jdbcUrl"));
		}
	}
	
	public void initDbName(String jdbcurl) {
		if(!StrKit.notBlank(getDbName()) &&
				StrKit.notBlank(jdbcurl)) {
			if(jdbcurl.contains("sqlserver")) {
				String[] temp = jdbcurl.split("DatabaseName=");
				String[] temp2 = temp[1].split(";");
				setDbName(temp2[0]);
			} else {
				jdbcurl = jdbcurl.substring(jdbcurl.indexOf("//")+2, jdbcurl.length());
				String[] temp = jdbcurl.split("/");
				if(temp.length > 1) {
					int nwh = temp[1].indexOf("?");
					if(nwh > -1) {
						setDbName(temp[1].substring(0, nwh));
					} else {
						setDbName(temp[1]);
					}
				}
			}
		}
	}
	*/
	
	//------------------------------------
	// 数据库信息
	//------------------------------------	
	public List<Record> version() {
		if(dbtool==null) return null;
		String sql = getDialect() == null ? null : getDialect().version();
		List<Record> records  = sql == null ? null : dbtool.find(sql);
		return records ;
	}
	
	public List<Object> query(String sql) {
		if(dbtool==null) return null;
		return dbtool.query(sql);
	}
	
	public List<Record> find(String sql) {
		if(dbtool==null) return null;
		return dbtool.find(sql);
	}
	
	public Page<Record> paginate(int pageNumber, int pageSize, String select, String sqlExceptSelect) {
		if(dbtool==null) return null;
		return dbtool.paginate(pageNumber, pageSize, select, sqlExceptSelect);
	}
	
	public int update(String sql) {
		if(dbtool==null) return -1;
		return dbtool.update(sql);
	}
	
	public int delete(String sql) {
		if(dbtool==null) return -1;
		return dbtool.delete(sql);
	}
		
	//------------------------------------
	// 查询数据库
	//------------------------------------	
	/**
	 * 查询所有表名
	 * @return
	 */
	public List<Object> getTableList(String dbname) {
		String sql = getDialect() == null ? null : getDialect().getTableListSQL(dbname);
		//查询表中所有列名
		List<Object> records  = sql == null ? null : dbtool.query(sql);
		return records ;
	}
	
	public List<Record> getTableListRecord(String dbname) {
		String sql = getDialect() == null ? null : getDialect().getTableListSQL(dbname);
		//查询表中所有列名
		List<Record> records  = sql == null ? null : dbtool.find(sql);
		return records ;
	}
	
	public Map<String, Record> getTableListMap(String dbname) {
		String sql = getDialect() == null ? null : getDialect().getTableListSQL(dbname);
		String tbnamekey = getDialect() == null ? null : getDialect().getTableNameKey();
		if(sql==null || tbnamekey==null) return null;
		List<Record> records  = sql == null ? null : dbtool.find(sql);
		Map<String, Record> map = new HashMap<String, Record>();
		for (Record record : records) {
			map.put(record.getStr(tbnamekey), record);
		}
		return map ;
	}
	
	public List<Object> getTableList() {
		return getTableList(getDbName());
	}
	
	/**
	 * 查询表中所有列名
	 * @param tableName
	 * @return
	 */
	public List<Object> getColumnList(String dbname, String tbname) {
		String sql = getDialect() == null ? null : getDialect().getColumnListSQL(dbname, tbname);
		//查询表中所有列名
		List<Object> records  = sql == null ? null : dbtool.query(sql);
		//String fieldName = (String) ((Object[]) records.get(j))[3];//数组的第4个元素为列名
		//String fieldType = (String) ((Object[]) records.get(j))[7];//数组的第8个元素为列类型
		return records ;
	}
	
	public List<Record> getColumnListRecord(String dbname, String tbname) {
		String sql = getDialect() == null ? null : getDialect().getColumnListSQL(dbname, tbname);
		//查询表中所有列名
		List<Record> records  = sql == null ? null : dbtool.find(sql);
		return records ;
	}
	
	public Set<String> getColumnNameSet(String dbname, String tbname) {
		//查询表中所有列名
		String sql = getDialect() == null ? null : getDialect().getColumnListSQL(dbname, tbname);
		String colname = getDialect() == null ? null : getDialect().getColumnNameKey();
		if(sql==null || colname==null) return null;
		List<Record> records  = sql == null ? null : dbtool.find(sql);
		Set<String> set = new HashSet<String>();
		for (Record record : records) {
			set.add(record.getStr(colname));
		}
		return set;
	}
	
	public List<Object> getColumnList(String tbname) {
		return getColumnList(getDbName(), tbname);
	}
	
	public Object getColumn(String dbname, String tbname, String colname) {
		String sql = getDialect() == null ? null : getDialect().getColumnSQL(dbname, tbname, colname);
		//查询表中所有列名
		Object records  = sql == null ? null : dbtool.queryFirst(sql);
		//String fieldName = (String) ((Object[]) records.get(j))[3];//数组的第4个元素为列名
		//String fieldType = (String) ((Object[]) records.get(j))[7];//数组的第8个元素为列类型
		return records ;
	}
	
	public Object getColumn(String tbname, String colname) {
		return getColumn(getDbName(), tbname, colname);
	}
	
	public String getPrimaryKey(String schema, String tbname) {
		String pkey = null;
		Connection conn = getConnection();
		try {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet rsid = meta.getPrimaryKeys(conn.getCatalog(), schema, tbname);
			if (rsid.next()) {
				// System.err.println("****** Comment ******");
				// System.err.println("TABLE_CAT : "+rsid.getObject(1));
				// System.err.println("TABLE_SCHEM: "+rsid.getObject(2));
				// System.err.println("TABLE_NAME : "+rsid.getObject(3));
				// System.err.println("COLUMN_NAME: "+rsid.getObject(4));
				// System.err.println("KEY_SEQ : "+rsid.getObject(5));
				// System.err.println("PK_NAME : "+rsid.getObject(6));
				// System.err.println("****** ******* ******");
				pkey = String.valueOf(rsid.getObject(4));
			}
			rsid.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pkey;
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
		boolean res = true;
		if(!StrKit.notBlank(sql)) return false;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if(Lambkit.isDevMode()) System.out.println(sql);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return res;
	}
	
	public boolean execute(String sql) {
		boolean res = true;
		if(!StrKit.notBlank(sql)) return false;
		PreparedStatement pstmt = null;
		try {
			Connection conn = getNewConnection();
			pstmt = conn.prepareStatement(sql);
			if(Lambkit.isDevMode()) System.out.println(sql);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return res;
	}
	
	/**
	 * 执行SQL语句,
	 * @param tbname
	 * @param sql
	 * @return
	 */
	public boolean executeHasTable(String tbname, String sql, boolean flag) {
		boolean res = true;
		Connection conn = getConnection();
		if (hasTable(conn, tbname) == flag) {
			res = execute(conn, sql);
		}
		ConnectionFactory.close(conn);
		return res;
	}
	
	/**
	 * 是否存在数据表
	 * @param dbname
	 * @param tbname
	 * @return
	 */
	public boolean hasTable(String tbname) {
		return hasTable(getDbName(), tbname);
	}
	
	public boolean hasTable(String dbname, String tbname) {
		boolean res = true;
		Connection conn = getConnection();
		res = hasTable(conn, dbname, tbname);
		ConnectionFactory.close(conn);
		return res;
	}
	
	public boolean hasTable(Connection conn, String tbname) {
		return hasTable(conn, getDbName(), tbname);
	}
	
	public boolean hasTable(Connection conn, String dbname, String tbname) {
		if(conn == null) return false;
		boolean res = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 判断数据库中是否存在表
			pstmt = conn.prepareStatement(getDialect().getTableSQL(dbname, tbname));
			rs = pstmt.executeQuery();
			if (rs!=null && rs.next()) {// 如果存在则删除
				res = true;
			} else {
				res = false;
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	/**
	 * 删除数据表
	 * @param tbname
	 * @return
	 */
	public boolean dropTable(String tbname) {
		return executeHasTable(tbname, getDialect().getDropTableSQL(tbname), true);
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
		return executeHasTable(tbname, getDialect().getAlterAddSQL(tbname, fldinfo, after), true);
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
		return executeHasTable(tbname, getDialect().getAlterChangeSQL(tbname, fldinfo, after), true);
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
		return executeHasTable(tbname, getDialect().getAlterModifySQL(tbname, fldinfo, after), true);
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
		return executeHasTable(tbname, getDialect().getAlterDropSQL(tbname, fldname), true);
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
		boolean res = false;
		if (tbid < 1) {
			return res;
		}
		try {
			ITable tcv = MgrdbManager.me().getService().getTableDao().findById(tbid);
			if(tcv.getTypeId() == 16) {
				//系统内部表格不允许删除
				return res;
			}
			if (tbname == null || tbname.isEmpty()) {
				tbname = tcv.getName();
			}
			if (tbname == null || tbname.isEmpty()) {
				return res;
			}
			//删除数据表的表
			res = dropTable(tbname);
			if(res) {
				//删除表配置信息表中的信息
				MgrdbManager.me().getService().getTableDao().deleteByTbId(tbid);
				//删除表字段信息表中的信息
				MgrdbManager.me().getService().getFieldDao().deleteByTbid(tbid);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return res;
		}
		return res;
	}

	/**
	 * 创建数据表
	 * 
	 * @param tbid
	 * @param tbname
	 * @return
	 */
	public boolean createTableByMgrdb(Object tbid, String tbname) {
		boolean res = false;
		String sql = null;
		try {
			tbname = getTbNameByMgrdb(tbid, tbname);
			if(!StrKit.notBlank(tbname)) return res;
			sql = getDialect().getCreateTableSQL(tbname, MgrdbManager.me().getService().getFieldDao().findByTbid(tbid, MgrConstants.NONE));
			if (!StrKit.notBlank(sql)) {
				return res;
			}
			//System.out.println(sql);
			res = executeHasTable(tbname, sql, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return res;
		}
		return res;
	}
	
	/**
	 * 创建数据表
	 * 
	 * @param tbid
	 * @param tbname
	 * @return
	 */
	public boolean updateTableByMgrdb(Object tbid, String tbname, String sqlfrom, String type) {
		boolean res = false;
		if(StrKit.notBlank(sqlfrom)) {
			return res;
		}
		String sql = null;
		try {
			tbname = getTbNameByMgrdb(tbid, tbname);
			if(!StrKit.notBlank(tbname)) return res;
			sql = getDialect().getUpdateTableSQL(tbname, MgrdbManager.me().getService().getFieldDao().findByTbid(tbid, MgrConstants.NONE), type);
			if (!StrKit.notBlank(sql)) {
				return res;
			}
			//System.out.println(sql);
			res = executeHasTable(tbname, sql+sqlfrom, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return res;
		}
		return res;
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
		boolean res = false;
		try {
			if (tbname == null || tbname.isEmpty()) {
				ITable tm = MgrdbManager.me().getService().getTableDao().findById(tbid);
				if(tm==null) return res;
				tbname = tm.getName();
			}
			if (fldname == null || fldname.isEmpty()) {
				return res;
			}
			res = dropField(tbname, fldname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return res;
		}
		return res;
	}
	/**
	 * 删除字段
	 * @param tbid
	 * @param tbname
	 * @param fldid
	 * @return
	 */
	public boolean dropFieldByMgrdb(Long tbid, String tbname, Long fldid) {
		boolean res = false;
		try {
			IField fcv = MgrdbManager.me().getService().getFieldDao().findById(fldid, MgrConstants.NONE);
			if(fcv==null) return res;
			String fldname = fcv.getName();
			if (fldname == null || fldname.isEmpty()) {
				return res;
			}
			res = dropFieldByMgrdb(tbid, tbname, fldname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return res;
		}
		return res;
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
		IField fcv = MgrdbManager.me().getService().getFieldDao().findById(fldid, MgrConstants.NONE);
		return addFieldByMgrdb(tbid, tbname, fcv, after);
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
		boolean res = false;
		String fldinfo = null;
		if(resfcv==null) return res;
		try {
			tbname = getTbNameByMgrdb(tbid, tbname);
			if(tbname==null) return res;
			fldinfo = getFieldSQL(resfcv);
			if (fldinfo == null || fldinfo.isEmpty()) {
				return res;
			}
			res = addField(tbname, fldinfo, after);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return res;
		}
		return res;
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
		IField fcv = MgrdbManager.me().getService().getFieldDao().findById(fldid, MgrConstants.NONE);
		return changeFieldByMgrdb(tbid, tbname, resname, fcv, after);
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
		boolean res = false;
		String fldinfo = null;
		try {
			tbname = getTbNameByMgrdb(tbid, tbname);
			if(tbname==null) return res;
			fldinfo = resname + " ";
			fldinfo += getFieldSQL(resfcv);
			if (fldinfo == null || fldinfo.isEmpty()) {
				return res;
			}
			res = changeField(tbname, fldinfo, after);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return res;
		}
		return res;
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
		boolean res = false;
		String fldinfo = null;
		try {
			tbname = getTbNameByMgrdb(tbid, tbname);
			if(tbname==null) return res;
			IField fcv = MgrdbManager.me().getService().getFieldDao().findById(fldid, MgrConstants.NONE);
			fldinfo = getFieldSQL(fcv);
			if (fldinfo == null || fldinfo.isEmpty()) {
				return res;
			}
			res = modifyFieldType(tbname, fldinfo, after);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return res;
		}
		return res;
	}
	
	////////////////////////////////////////////////////////////////////
	/**
	 * 获取表格名称
	 * @param tbid
	 * @param tbname
	 * @return
	 */
	public String getTbNameByMgrdb(Object tbid, String tbname) {
		if (tbname == null || tbname.isEmpty()) {
			ITable tm = MgrdbManager.me().getService().getTableDao().findById(tbid);
			if(tm==null) return null;
			tbname = tm.getName();
		}
		return tbname;
	}
	
	/**
	 * 获取没个字段的sql
	 * @param fcv
	 * @return
	 */
	public String getFieldSQL(IField fcv) {
		if(getDialect()==null) return null;
		return getDialect().getFieldSQL(fcv);
	}
	
	public Connection getConnection() {
		try {
			return dbtool.getConfig().getConnection();
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Connection getNewConnection() {
		String name = dbtool.getConfig().getName();
		DataSourceConfig dsc = DataSourceConfigManager.me().getDatasourceConfigs().get(name);
		return ConnectionFactory.getConnection(dsc.getDriverClassName(), dsc.getUrl(), dsc.getUser(), dsc.getPassword());
	}
	////////////////////////////////////////////////////////////////////

	public IManagerDialect getDialect() {
		return (IManagerDialect) dbtool.getConfig().getDialect();
	}

	public String getDbName() {
		String name = dbtool.getConfig().getName();
		DataSourceConfig dsc = DataSourceConfigManager.me().getDatasourceConfigs().get(name);
		return dsc.getDbname();
	}

}
