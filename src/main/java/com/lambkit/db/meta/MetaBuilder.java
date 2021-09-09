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
package com.lambkit.db.meta;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.sql.DataSource;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.dialect.Dialect;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.activerecord.generator.TypeMapping;
import com.lambkit.db.datasource.DataSourceConfig;
import com.lambkit.db.datasource.DataSourceConfigManager;

/**
 * MetaBuilder
 */
public class MetaBuilder {
	
	protected DataSource dataSource;
	protected Dialect dialect = new MysqlDialect();
	protected Set<String> excludedTables = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
	protected Set<String> includedTables = null;
	
	protected Connection conn = null;
	protected DatabaseMetaData dbMeta = null;
	
	protected String[] removedTableNamePrefixes = null;
	
	protected TypeMapping typeMapping = new TypeMapping();
	
	protected String configName;
	
	private String[] tableTypes = new String[] {"TABLE"};
	
	public MetaBuilder(DataSource dataSource) {
		if (dataSource == null) {
			throw new IllegalArgumentException("dataSource can not be null.");
		}
		this.dataSource = dataSource;
		this.configName = DataSourceConfig.NAME_DEFAULT;
	}
	
	public void setDialect(Dialect dialect) {
		if (dialect != null) {
			this.dialect = dialect;
		}
	}
	
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	
	public void addExcludedTable(String... excludedTables) {
		if (excludedTables != null) {
			for (String table : excludedTables) {
				this.excludedTables.add(table.trim());
			}
		}
	}
	
	public void addIncludedTable(String... includedTables) {
		if (includedTables != null) {
			for (String table : includedTables) {
				if(this.includedTables==null) {
					this.includedTables = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
				}
				this.includedTables.add(table.trim());
			}
		}
	}
	
	/**
	 * 设置需要被移除的表名前缀，仅用于生成 modelName 与  baseModelName
	 * 例如表名  "osc_account"，移除前缀 "osc_" 后变为 "account"
	 */
	public void setRemovedTableNamePrefixes(String... removedTableNamePrefixes) {
		this.removedTableNamePrefixes = removedTableNamePrefixes;
	}
	
	public void setTypeMapping(TypeMapping typeMapping) {
		if (typeMapping != null) {
			this.typeMapping = typeMapping;
		}
	}
	
	class MapKeyComparator implements Comparator<String>{
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
	
	public Map<String, TableMeta> build() {
		//System.out.println("Build TableMeta ...");
		try {
			conn = dataSource.getConnection();
			dbMeta = conn.getMetaData();
			
			Map<String, TableMeta> ret = new TreeMap<>(new MapKeyComparator());
			buildTableNames(ret);
			for (TableMeta tableMeta : ret.values()) {
				buildPrimaryKey(tableMeta);
				buildColumnMetas(tableMeta);
			}
			return ret;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (conn != null) {
				try {conn.close();} catch (SQLException e) {throw new RuntimeException(e);}
			}
		}
	}
	
	/**
	 * 通过继承并覆盖此方法，跳过一些不希望处理的 table，定制更加灵活的 table 过滤规则
	 * @return 返回 true 时将跳过当前 tableName 的处理
	 */
	protected boolean isSkipTable(String tableName) {
		return false;
	}
	
	/**
	 * 构造 modelName，mysql 的 tableName 建议使用小写字母，多单词表名使用下划线分隔，不建议使用驼峰命名
	 * oracle 之下的 tableName 建议使用下划线分隔多单词名，无论 mysql还是 oralce，tableName 都不建议使用驼峰命名
	 */
	protected String buildModelName(String tableName) {
		// 移除表名前缀仅用于生成 modelName、baseModelName，而 tableMeta.name 表名自身不能受影响
		if (removedTableNamePrefixes != null) {
			for (String prefix : removedTableNamePrefixes) {
				if(StrKit.isBlank(prefix)) continue;
				if (tableName.startsWith(prefix)) {
					tableName = tableName.replaceFirst(prefix, "");
					break;
				}
			}
		}
		
		// 将 oralce 大写的 tableName 转成小写，再生成 modelName
		if (dialect instanceof OracleDialect || dialect instanceof PostgreSqlDialect) {
			tableName = tableName.toLowerCase();
		}
		
		return StrKit.firstCharToUpperCase(StrKit.toCamelCase(tableName));
	}
	
	/**
	 * 使用 modelName 构建 baseModelName
	 */
	protected String buildTableAttrName(String modelName) {
		return StrKit.firstCharToLowerCase(modelName);
	}
	
	protected String buildTableTitleName(String tableName) {
		// 移除表名前缀仅用于生成 modelName、baseModelName，而 tableMeta.name 表名自身不能受影响
		if (removedTableNamePrefixes != null) {
			for (String prefix : removedTableNamePrefixes) {
				if(StrKit.isBlank(prefix)) continue;
				if (tableName.startsWith(prefix)) {
					tableName = tableName.replaceFirst(prefix, "");
					break;
				}
			}
		}
		
		// 将 oralce 大写的 tableName 转成小写，再生成 modelName
		if (dialect instanceof OracleDialect || dialect instanceof PostgreSqlDialect) {
			tableName = tableName.toLowerCase();
		}
		String[] tbns = tableName.toLowerCase().split("_");
		String title = "";
		for (String tbn : tbns) {
			title += " " + StrKit.firstCharToUpperCase(tbn);
		}
		return title.substring(1);
	}
	
	/**
	 * 不同数据库 dbMeta.getTables(...) 的 schemaPattern 参数意义不同
	 * 1：oracle 数据库这个参数代表 dbMeta.getUserName()
	 * 2：postgresql 数据库中需要在 jdbcUrl中配置 schemaPatter，例如：
	 *   jdbc:postgresql://localhost:15432/djpt?currentSchema=public,sys,app
	 *   最后的参数就是搜索schema的顺序，DruidPlugin 下测试成功
	 * 3：开发者若在其它库中发现工作不正常，可通过继承 MetaBuilder并覆盖此方法来实现功能
	 */
	protected ResultSet getTablesResultSet() throws SQLException {
		String schemaPattern = dialect instanceof OracleDialect ? dbMeta.getUserName() : null;
		schemaPattern = dialect instanceof PostgreSqlDialect ? "public" : schemaPattern;
		if(StrKit.notBlank(configName)) {
			DataSourceConfig dsconfig = DataSourceConfigManager.me().getDatasourceConfig(configName);
			if(dsconfig!=null && StrKit.notBlank(dsconfig.getSchema())) {
				schemaPattern = dsconfig.getSchema();
			}
		}
		// return dbMeta.getTables(conn.getCatalog(), schemaPattern, null, new String[]{"TABLE", "VIEW"});
		return dbMeta.getTables(conn.getCatalog(), schemaPattern, null, tableTypes);
		//new String[]{"TABLE"});	// 不支持 view 生成
	}
	
	protected void buildTableNames(Map<String, TableMeta> ret) throws SQLException {
		ResultSet rs = getTablesResultSet();
		while (rs.next()) {
			String tableName = rs.getString("TABLE_NAME");
			
			if (excludedTables.contains(tableName)) {
				//System.out.println("Skip table :" + tableName);
				continue ;
			}
			if (includedTables!=null && !includedTables.contains(tableName)) {
				//System.out.println("Skip table :" + tableName);
				continue ;
			}
			if (isSkipTable(tableName)) {
				//System.out.println("Skip table :" + tableName);
				continue ;
			}
			
			TableMeta tableMeta = new TableMeta();
			tableMeta.setName(tableName);
			tableMeta.setRemarks(rs.getString("REMARKS"));
			tableMeta.setModelName(buildModelName(tableName));
			tableMeta.setAttrName(buildTableAttrName(tableMeta.getModelName()));
			tableMeta.setTitle(buildTableTitleName(tableName));
			ret.put(tableName, tableMeta);
		}
		rs.close();
	}
	
	protected void buildPrimaryKey(TableMeta tableMeta) throws SQLException {
		ResultSet rs = dbMeta.getPrimaryKeys(conn.getCatalog(), null, tableMeta.getName());
		
		String primaryKey = "";
		int index = 0;
		while (rs.next()) {
			if (index++ > 0) {
				primaryKey += ",";
			}
			primaryKey += rs.getString("COLUMN_NAME");
		}
		/*
		if (StrKit.isBlank(primaryKey)) {
			throw new RuntimeException("primaryKey of table \"" + tableMeta.name + "\" required by active record pattern");
		}
		*/
		tableMeta.setPrimaryKey(primaryKey);
		rs.close();
	}
	
	/**
	 * 文档参考：
	 * http://dev.mysql.com/doc/connector-j/en/connector-j-reference-type-conversions.html
	 * 
	 * JDBC 与时间有关类型转换规则，mysql 类型到 java 类型如下对应关系：
	 * DATE				java.sql.Date
	 * DATETIME			java.sql.Timestamp
	 * TIMESTAMP[(M)]	java.sql.Timestamp
	 * TIME				java.sql.Time
	 * 
	 * 对数据库的 DATE、DATETIME、TIMESTAMP、TIME 四种类型注入 new java.util.Date()对象保存到库以后可以达到“秒精度”
	 * 为了便捷性，getter、setter 方法中对上述四种字段类型采用 java.util.Date，可通过定制 TypeMapping 改变此映射规则
	 */
	protected void buildColumnMetas(TableMeta tableMeta) throws SQLException {
		String sql = dialect.forTableBuilderDoBuild(tableMeta.getName());
		Statement stm = conn.createStatement();
		//System.out.println(sql);
		ResultSet rs = stm.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		for (int i=1; i<=rsmd.getColumnCount(); i++) {
			ColumnMeta cm = new ColumnMeta();
			String columnName = rsmd.getColumnName(i);
			cm.setName(columnName);
			// 所在的Catalog名字
			cm.setCatalogName(rsmd.getCatalogName(i));
			// 获得指定列的数据类型名
			cm.setType(rsmd.getColumnTypeName(i).toLowerCase());
			// 是否为空
			cm.setIsNullableType(rsmd.isNullable(i));
			// 是否主键
			cm.setPrimaryKey(tableMeta.isPrimaryKey(columnName));
			// 在数据库中类型的最大字符个数
			cm.setDisplaySize(rsmd.getColumnDisplaySize(i));
			// 某列类型的精确度(类型的长度)
			cm.setPrecision(rsmd.getPrecision(i));
			// 小数点后的位数
			cm.setScale(rsmd.getScale(i));
			// 获取某列对应的表名
			//cm.setTableName(rsmd.getTableName(i));
			// 是否自动递增
			cm.setAutoInctement(rsmd.isAutoIncrement(i));
			// 在数据库中是否为货币型
			cm.setCurrency(rsmd.isCurrency(i));
			// 是否为只读
			cm.setReadOnly(rsmd.isReadOnly(i));
			cm.setSigned(rsmd.isSigned(i));
			
			String typeStr = null;
			if (dialect.isKeepByteAndShort()) {
				int type = rsmd.getColumnType(i);
				if (type == Types.TINYINT) {
					typeStr = "java.lang.Byte";
				} else if (type == Types.SMALLINT) {
					typeStr = "java.lang.Short";
				}
			}
			
			if (typeStr == null) {
				String colClassName = rsmd.getColumnClassName(i);
				typeStr = typeMapping.getType(colClassName);
			}
			
			if (typeStr == null) {
				int type = rsmd.getColumnType(i);
				//System.out.println(columnName + " columnType: " + type);
				if (type == Types.BINARY || type == Types.VARBINARY || type == Types.LONGVARBINARY || type == Types.BLOB) {
					typeStr = "byte[]";
				} else if (type == Types.CLOB || type == Types.NCLOB) {
					typeStr = "java.lang.String";
				} else if (type == Types.INTEGER) {
					typeStr = "java.lang.Integer";
				} else if (type == Types.NUMERIC || type == Types.REAL) {
					typeStr = "java.lang.Double";
				} else {
					typeStr = "java.lang.String";
				}
			}
			cm.setJavaType(typeStr);
			
			// 构造字段对应的属性名 attrName
			cm.setAttrName(buildColumnAttrName(columnName));
			cm.setUpperName(StrKit.firstCharToUpperCase(cm.getAttrName()));
			cm.setTitle(buildColumnTitleName(columnName));
			
			tableMeta.addColumnMeta(cm);
		}
		
		rs.close();
		stm.close();
	}
	
	/**
	 * 构造 colName 所对应的 attrName，mysql 数据库建议使用小写字段名或者驼峰字段名
	 * Oralce 反射将得到大写字段名，所以不建议使用驼峰命名，建议使用下划线分隔单词命名法
	 */
	protected String buildColumnAttrName(String colName) {
		if (dialect instanceof OracleDialect || dialect instanceof PostgreSqlDialect) {
			colName = colName.toLowerCase();
		}
		return StrKit.toCamelCase(colName);
	}
	
	protected String buildColumnTitleName(String colName) {
		if (dialect instanceof OracleDialect || dialect instanceof PostgreSqlDialect) {
			colName = colName.toLowerCase();
		}
		String[] tbns = colName.toLowerCase().split("_");
		String title = "";
		for (String tbn : tbns) {
			title += " " + StrKit.firstCharToUpperCase(tbn);
		}
		return title.substring(1);
	}

	public String[] getTableTypes() {
		return tableTypes;
	}

	public void setTableTypes(String[] tableTypes) {
		this.tableTypes = tableTypes;
	}
}
