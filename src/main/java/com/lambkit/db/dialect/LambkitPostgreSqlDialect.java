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

import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.sql.ConditionMode;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.SqlJoinMode;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;
import com.lambkit.db.sql.column.SqlJoinOn;
import com.lambkit.common.exception.LambkitException;
import com.lambkit.common.util.ArrayUtils;

import java.util.List;


public class LambkitPostgreSqlDialect extends PostgreSqlDialect implements LambkitDialect, IManagerDialect {

	public String forLimitSql(Object limit) {
		// TODO Auto-generated method stub
		StringBuilder sqlBuilder = new StringBuilder();
		if (limit instanceof Number) {
            sqlBuilder.append(" limit ").append(limit).append(" offset ").append(0);
        } else if (limit instanceof String && limit.toString().contains(",")) {
            String[] startAndEnd = limit.toString().split(",");
            String start = startAndEnd[0];
            String end = startAndEnd[1];
            sqlBuilder.append(" limit ").append(end).append(" offset ").append(start);
        }
		return sqlBuilder.toString();
	}
	
	@Override
	public String forFindBySql(String sql, String orderBy, Object limit) {
    	 StringBuilder sqlBuilder = new StringBuilder(sql);
         if (orderBy != null) {
             sqlBuilder.append(" ORDER BY ").append(orderBy);
         }
         sqlBuilder.append(forLimitSql(limit));
         return sqlBuilder.toString();
	}

	@Override
	public SqlPara forFindBySqlPara(SqlPara sqlPara, String orderBy, Object limit) {
		StringBuilder sqlBuilder = new StringBuilder(sqlPara.getSql());
        if (orderBy != null) {
            sqlBuilder.append(" ORDER BY ").append(orderBy);
        }
        sqlBuilder.append(forLimitSql(limit));
		sqlPara.setSql(sqlBuilder.toString());
        return sqlPara;
	}
	
	@Override
	public SqlPara forDeleteByExample(Example example) {
		SqlPara sqlPara = new SqlPara();
		StringBuilder sqlBuilder = new StringBuilder("DELETE FROM \"");
		sqlBuilder.append(example.getTableName()).append("\" ");
		appIfJoinNotEmpty(example, sqlBuilder);
		appExampleConditions(example, sqlBuilder);
		//appConditions(example.getColumnsList(), sqlBuilder);
		sqlPara.setSql(sqlBuilder.toString());
        example.addValueToParam(sqlPara);
		return sqlPara;
	}
	
	@Override
	public SqlPara forUpdate(Record record, String tableName, QueryParas queryParas) {
		SqlPara sqlPara = new SqlPara();
		String[] columnNames = record.getColumnNames();
        if (columnNames.length > 0) {
    		StringBuilder sqlBuilder = new StringBuilder("UPDATE \"");
    		sqlBuilder.append(tableName).append("\" SET ");
    		int i=0;
    		for (String name : columnNames) {
    			sqlBuilder.append(name).append("=?");
    			if(i>0) {
    				sqlBuilder.append(", ");
    			} else {
    				sqlBuilder.append(" ");
    			}
    			i++;
    			sqlPara.addPara(record.get(name));
			}
    		String sqlExceptSelect = queryParas.getSqlExceptSelect();
    		sqlExceptSelect = sqlExceptSelect.substring(sqlExceptSelect.indexOf("where"));
    		sqlBuilder.append(sqlExceptSelect);
    		List<Object> paraList = queryParas.getParaList();
    		for(int j=0; j<paraList.size(); j++) {
    			sqlPara.addPara(paraList.get(j));
    		}
        }
        return sqlPara;
	}
	
	@Override
	public SqlPara forUpdateByExample(Record record, Example example) {
		SqlPara sqlPara = new SqlPara();
		String[] columnNames = record.getColumnNames();
        if (columnNames.length > 0) {
    		StringBuilder sqlBuilder = new StringBuilder("UPDATE \"");
    		sqlBuilder.append(example.getTableName()).append("\" ");
    		appIfJoinNotEmpty(example, sqlBuilder);
    		sqlBuilder.append(" SET ");
    		int i=0;
    		for (String name : columnNames) {
    			sqlBuilder.append(name).append("=?");
    			if(i>0) {
    				sqlBuilder.append(", ");
    			} else {
    				sqlBuilder.append(" ");
    			}
    			i++;
    			sqlPara.addPara(record.get(name));
			}
    		appExampleConditions(example, sqlBuilder);
    		//appConditions(example.getColumnsList(), sqlBuilder);
    		sqlPara.setSql(sqlBuilder.toString());
    		example.addValueToParam(sqlPara);
        }
		return sqlPara;
	}
	
	@Override
	public SqlPara forFindByExample(Example example, Object limit) {
		SqlPara sqlPara = new SqlPara();
		StringBuilder sqlBuilder = new StringBuilder("SELECT ");
		sqlBuilder.append(example.getSelectSql());
		sqlBuilder.append(" FROM ");
		sqlBuilder.append(example.getTableName()).append(" ");

		appIfJoinNotEmpty(example, sqlBuilder);
		appExampleConditions(example, sqlBuilder);
		//appConditions(example.getColumnsList(), sqlBuilder);

		if (example.getOrderBy() != null) {
			sqlBuilder.append(" ORDER BY ").append(getOrderby(example.getTableName(),example.getOrderBy()));
		}
		
		sqlBuilder.append(forLimitSql(limit));
		sqlPara.setSql(sqlBuilder.toString());
		
		example.addValueToParam(sqlPara);
		return sqlPara;
	}
	
	@Override
	public SqlPara forPaginateByExample(Example example) {
		// TODO Auto-generated method stub
		SqlPara sqlPara = forPaginateFormByExample(example);
		StringBuilder sqlBuilder = new StringBuilder("SELECT ");
		sqlBuilder.append(example.getSelectSql());
		sqlBuilder.append(sqlPara.getSql());
		sqlPara.setSql(sqlBuilder.toString());
		return sqlPara;
	}
	
	@Override
	public SqlPara forPaginateFormByExample(Example example) {
		SqlPara sqlPara = new SqlPara();
		StringBuilder sqlBuilder = new StringBuilder(" FROM ");
		sqlBuilder.append(example.getTableName()).append(" ");

		appIfJoinNotEmpty(example, sqlBuilder);
		appExampleConditions(example, sqlBuilder);
		//appConditions(example.getColumnsList(), sqlBuilder);

		if (example.getOrderBy() != null) {
			sqlBuilder.append(" ORDER BY ").append(getOrderby(example.getTableName(),example.getOrderBy()));
		}
		sqlPara.setSql(sqlBuilder.toString());
		/*
        if (ArrayUtils.isNotEmpty(example.getColumnsList())) {
            for (Columns columns : example.getColumnsList()) {
            	if (ArrayUtils.isNotEmpty(columns.getList())) {
                    for (Column column : columns.getList()) {
                        column.addValueToParam(sqlPara);
                    }
                }
            }
        }*/
		example.addValueToParam(sqlPara);
		return sqlPara;
	}
	
    @Override
    public String forFindByColumns(String table, String loadColumns, List<Column> columns, String orderBy, Object limit) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT ");
        sqlBuilder.append(loadColumns)
                .append(" FROM  \"")
                .append(table).append("\" ");

        appIfNotEmpty(table, columns, sqlBuilder, true);


        if (orderBy != null) {
            sqlBuilder.append(" ORDER BY ").append(getOrderby(table,orderBy));
        }

        if (limit == null) {
            return sqlBuilder.toString();
        }

        if (limit instanceof Number) {
            sqlBuilder.append(" limit ").append(limit).append(" offset ").append(0);
            return sqlBuilder.toString();
        } else if (limit instanceof String && limit.toString().contains(",")) {
            String[] startAndEnd = limit.toString().split(",");
            String start = startAndEnd[0];
            String end = startAndEnd[1];

            sqlBuilder.append(" limit ").append(end).append(" offset ").append(start);
            return sqlBuilder.toString();
        } else {
            throw new LambkitException("sql limit is error!,limit must is Number of String like \"0,10\"");
        }
    }


    @Override
    public String forPaginateSelect(String loadColumns) {
        return "SELECT " + loadColumns;
    }


    @Override
    public String forPaginateFrom(String table, List<Column> columns, String orderBy) {
        StringBuilder sqlBuilder = new StringBuilder(" FROM \"").append(table).append("\"");

        appIfNotEmpty(table, columns, sqlBuilder, true);

        if (orderBy != null) {
            sqlBuilder.append(" ORDER BY ").append(getOrderby(table,orderBy));
        }

        return sqlBuilder.toString();
    }
    
    private boolean appConditions(String alias, List<Columns> columns, StringBuilder sqlBuilder) {
    	StringBuilder wsb = new StringBuilder();
        if (ArrayUtils.isNotEmpty(columns)) {
            int index = 0;
            for (Columns column : columns) {
            	if (ArrayUtils.isNotEmpty(column.getList())) {
            		wsb.append("(");
                    appIfNotEmpty(alias, column.getList(), wsb, false);
                    wsb.append(")");
                    if (index != columns.size() - 1) {
                    	wsb.append(" OR ");
                    }
            	}
                index++;
            }
        }
        if(wsb.length() > 3) {
        	sqlBuilder.append(" WHERE ");
            sqlBuilder.append(wsb);
            return true;
        }
        return false;
    }

    private void appIfNotEmpty(String alias, List<Column> columns, StringBuilder sqlBuilder, boolean bAppendWhere) {
        if (ArrayUtils.isNotEmpty(columns)) {
            if(bAppendWhere) sqlBuilder.append(" WHERE ");

            int index = 0;
            for (Column column : columns) {
                sqlBuilder.append(String.format(" \"%s\".\"%s\" %s ", alias, column.getName(), getCondition(column)));
                if (index != columns.size() - 1) {
                    sqlBuilder.append(" AND ");
                }
                index++;
            }
        }
    }
    
    private void appExampleConditions(Example example, StringBuilder sqlBuilder) {
    	boolean flag = appConditions(example.getTableName(), example.getColumnsList(), sqlBuilder);
    	if (ArrayUtils.isNotEmpty(example.getJoinOnList())) {
        	int i=0;
        	for (SqlJoinOn join : example.getJoinOnList()) {
        		if(i==0) appIfNotEmpty(join.getJoinTableName(), join.getList(), sqlBuilder, !flag);
        		else  appIfNotEmpty(join.getJoinTableName(), join.getList(), sqlBuilder, false);
        		i++;
        	}
    	}
    }
    
    private void appIfJoinNotEmpty(Example example, StringBuilder sqlBuilder) {
    	if (ArrayUtils.isNotEmpty(example.getJoinOnList())) {
        	for (SqlJoinOn join : example.getJoinOnList()) {
        		if(join.getType()==SqlJoinMode.LEFT_JOIN) {
            		sqlBuilder.append(" LEFT JOIN ");
            	} else if(join.getType()==SqlJoinMode.RIGHT_JOIN) {
            		sqlBuilder.append(" RIGHT JOIN ");
            	} else {
            		sqlBuilder.append(" INNER JOIN ");
            	}
            	sqlBuilder.append(" \"").append(join.getJoinTableName()).append("\" ON ");
            	String mainAlias = join.getMainTableName();
            	sqlBuilder.append(" \"").append(mainAlias).append("\".\"").append(join.getMainField()).append("\" ");
            	String alias = join.getJoinTableName();
            	sqlBuilder.append("=");
            	sqlBuilder.append(" \"").append(alias).append("\".\"").append(join.getJoinField()).append("\" ");
        	}
        }
    }
    
    private String getOrderby(String table, String orderby) {
    	StringBuilder sqlBuilder = new StringBuilder();
    	sqlBuilder.append("\"").append(table).append("\".");
    	orderby = orderby.replaceAll(",", ",\""+table+"\".");
    	sqlBuilder.append(orderby);
    	return sqlBuilder.toString();
    }
    
    private String getCondition(Column column) {
    	if(column.isNoValue()) {
    		if (ConditionMode.EMPTY.equals(column.getLogic())) {
				return" = '' ";
			} else if (ConditionMode.NOT_EMPTY.equals(column.getLogic())) {
				return" <> '' ";
			} else if (ConditionMode.ISNULL.equals(column.getLogic())) {
				return" is null ";
			} else if (ConditionMode.NOT_NULL.equals(column.getLogic())) {
				return" is not null ";
			}
    	} else if(column.isSingleValue()) {
    		if (ConditionMode.EQUAL.equals(column.getLogic())) {
    			return " = ? ";
    		} else if (ConditionMode.NOT_EQUAL.equals(column.getLogic())) {
    			return " <> ? ";
    		} else if (ConditionMode.LESS_THEN.equals(column.getLogic())) {
    			return " < ? ";
    		} else if (ConditionMode.LESS_EQUAL.equals(column.getLogic())) {
    			return " <= ? ";
    		} else if (ConditionMode.GREATER_THEN.equals(column.getLogic())) {
    			return " > ? ";
    		} else if (ConditionMode.GREATER_EQUAL.equals(column.getLogic())) {
    			return " >= ? ";
    		} else if (ConditionMode.FUZZY.equals(column.getLogic())) {
    			return " like ? ";
    		} else if (ConditionMode.NOT_FUZZY.equals(column.getLogic())) {
    			return " not like ? ";
    		}
    	} else if(column.isBetweenValue()) {
    		if (ConditionMode.BETWEEN.equals(column.getLogic())) {
    			return " between ? and ? ";
    		} else if (ConditionMode.NOT_BETWEEN.equals(column.getLogic())) {
    			return " not between ? and ? ";
    		}
    	} else if(column.isListValue()) {
    		StringBuilder str = new StringBuilder();
    		if (ConditionMode.IN.equals(column.getLogic())) {
    			str.append(" in ");
    		} else if (ConditionMode.NOT_IN.equals(column.getLogic())) {
    			str.append(" not in ");
    		}
    		int j=0;
    		for (Object val : (List<?>) column.getValue()) {
				if(j==0) str.append(" (?");
				else str.append(",?");
				j++;
			}
			str.append(")");
			return str.toString();
    	}
    	return "";
    }
    
    //////////////////////////////////
    
    @Override
	public String getCreateTableSQL(String tbname, List<? extends IField> tflv) {
		// TODO Auto-generated method stub
		if(tflv==null) return null;
		System.out.println("CREATE TABLE " + getTableSQL(tbname, tflv));
		return "CREATE TABLE " + getTableSQL(tbname, tflv);
	}
	
	@Override
	public String getDropAndCreateTableSQL(String tbname, List<? extends IField> tflv) {
		// TODO Auto-generated method stub
		if(tflv==null) return null;
		System.out.println("DROP TABLE IF EXISTS " + tbname + "; CREATE TABLE " + getTableSQL(tbname, tflv));
		return "DROP TABLE IF EXISTS " + tbname + "; CREATE TABLE " + getTableSQL(tbname, tflv);
	}

	@Override
	public String getDropTableSQL(String tbname) {
		// TODO Auto-generated method stub
		return "drop table IF EXISTS " + tbname;
	}
	
	@Override
	public String getAlterAddSQL(String tbname, String fldinfo, String after) {
		// TODO Auto-generated method stub
		//System.out.println("alter table " + tbname + " add " + fldinfo + after + ";");
		return "alter table " + tbname + " ADD COLUMN " + fldinfo + after + ";";
	}
	
	@Override
	public String getAlterChangeSQL(String tbname, String fldinfo, String after) {
		// TODO Auto-generated method stub
		//System.out.println("alter table " + tbname + " RENAME " + fldinfo + " TO " + after + ";");
		return "alter table " + tbname + " RENAME " + fldinfo + " TO " + after + ";";
	}
	
	@Override
	public String getAlterModifySQL(String tbname, String fldinfo, String after) {
		// TODO Auto-generated method stub
		//System.out.println("alter table " + tbname + " ALTER " + fldinfo + after + ";");
		return "alter table " + tbname + " ALTER COLUMN " + fldinfo + after + ";";
	}
	
	@Override
	public String getAlterDropSQL(String tbname, String fldname) {
		// TODO Auto-generated method stub
		//System.out.println("alter table " + tbname + " drop " + fldname + ";");
		return "alter table " + tbname + " DROP COLUMN " + fldname + ";";
	}
	
	@Override
	public String getTableSQL(String dbname, String tbname) {
		// TODO Auto-generated method stub
		return "select tablename from pg_tables where schemaname='public' and tablename='" + tbname + "'";
	}

	@Override
	public String getTableListSQL(String dbname) {
		// TODO Auto-generated method stub
		return "select tablename from pg_tables where schemaname='public'";
	}
	
	@Override
	public String getTableNameKey() {
		// TODO Auto-generated method stub
		return "tablename";
	}

	@Override
	public String getColumnSQL(String dbname, String tbname, String colname) {
		// TODO Auto-generated method stub
		return "SELECT a.attnum,a.attname AS field,t.typname AS type,a.attlen AS length,a.atttypmod AS lengthvar,a.attnotnull AS notnull " +
		"FROM pg_class c,pg_attribute a,pg_type t WHERE c.relname = '" + tbname + "' and a.attname = '"+ colname +
		"' and a.attnum > 0 and a.attrelid = c.oid and a.atttypid = t.oid ORDER BY a.attnum";
	}
	
	@Override
	public String getColumnListSQL(String dbname, String tbname) {
		// TODO Auto-generated method stub
		return "SELECT a.attnum,a.attname AS field,t.typname AS type,a.attlen AS length,a.atttypmod AS lengthvar,a.attnotnull AS notnull " +
				"FROM pg_class c,pg_attribute a,pg_type t WHERE c.relname = '" + tbname +
				"' and a.attnum > 0 and a.attrelid = c.oid and a.atttypid = t.oid ORDER BY a.attnum";
		//and a.attname = 'passport'
	}
	
	@Override
	public String getColumnNameKey() {
		// TODO Auto-generated method stub
		return "field";
	}

	@Override
	public String getSelectTopOne(String tbname) {
		// TODO Auto-generated method stub
		return "select * from \"" + tbname + "\" limit 1";
	}
	
	/**
	 * 数据库字段类型 转 Java数据类型
	 * @param fieldType
	 * @return
	 */
	public String convertTo(String fieldType) {
        if (fieldType.equalsIgnoreCase("varchar") || fieldType.equalsIgnoreCase("char")
                || fieldType.equalsIgnoreCase("blob") || fieldType.equalsIgnoreCase("text")) {
            return "String";
        } else if (fieldType.equalsIgnoreCase("int") || fieldType.equalsIgnoreCase("smallint")) {
            return "Integer";
        } else if (fieldType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (fieldType.equalsIgnoreCase("float") || fieldType.equalsIgnoreCase("double")) {
            return "Double";
        } else if (fieldType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (fieldType.equalsIgnoreCase("datetime")) {
            return "java.sql.TimeStamp";
        } else {
            return "String";
        }
    }
	
	@Override
	public String convertForm(String valueType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 转换FieldConfigModel为SQL语句
	 * 
	 * @param tflv
	 * @return
	 */
	@Override
	public String getFieldSQL(IField fldmodel) {
		String flddt = fldmodel.getDatatype();
		String fsql = fldmodel.getName();
		//boolean bunsign = fldmodel.getIsunsigned().equals("Y");
		if (fldmodel.getIsai().equals("Y")) {
			if(flddt.equalsIgnoreCase("int")) fsql += " serial4";
			else if(flddt.equalsIgnoreCase("bigint")) fsql += " serial8";
			else if(flddt.equalsIgnoreCase("smallint")) fsql += " serial2";
			else if(flddt.equalsIgnoreCase("datetime")) fsql += " timestamp";
			else fsql += flddt;
		}
		if (fldmodel.getIsnullable().equals("Y")) {
			fsql += " NULL";
		} else {
			fsql += " NOT NULL";
		}
		String dft = fldmodel.getDefault();
		if (dft != null && !dft.isEmpty()) {
			fsql += " DEFAULT " + dft;
		}
		
		// 主键不能修改
		//if (fldmodel.getIskey().equals("Y")) {
		//	fsql += ",PRIMARY KEY (\"id\"),";
		//}
		return fsql;
	}
	
	/**
	 * 转换 List<? extends IField>为SQL语句
	 * 
	 * @param tflv
	 * @return
	 */
	public String getTableSQL(String tbname, List<? extends IField> tflv) {
		String createSQL = tbname + " (";
		String inSQL = "";
		String addSQL = "";
		for (int i = 0; i < tflv.size(); i++) {
			IField fcv = tflv.get(i);
			if(i>0) {
				createSQL += ",";
			}
			createSQL += getFieldCreateSQL(fcv);
			inSQL += getFieldInSQL(fcv);
			addSQL += getFieldCommentSQL(tbname, fcv);
		}
		createSQL += inSQL + ") WITH (OIDS=FALSE) ;";
		return createSQL + addSQL;
	}
	
	public String getFieldCreateSQL(IField fldmodel) {
		String flddt = fldmodel.getDatatype();
		String fsql = fldmodel.getName();
		//boolean bunsign = fldmodel.getIsunsigned().equals("Y");
		if (fldmodel.getIsai().equals("Y")) {
			if(flddt.contains("int")) fsql += " serial4";
			else if(flddt.contains("bigint")) fsql += " serial8";
			else if(flddt.contains("smallint")) fsql += " serial2";
			else if(flddt.contains("datetime")) fsql += " timestamp";
			else fsql += " " + flddt;
		} else {
			if(flddt.contains("int")) fsql += " integer";
			else if(flddt.contains("bigint")) fsql += " bigint";
			else if(flddt.contains("smallint")) fsql += " smallint";
			else if(flddt.contains("datetime")) fsql += " timestamp";
			else if(flddt.contains("float")) fsql += " real";
			else if(flddt.contains("float(")) fsql += " " + flddt.replaceAll("float", "numeric");
			else if(flddt.contains("double(")) fsql += " " + flddt.replaceAll("double", "numeric");
			else fsql += " " + flddt;
		}
		if (fldmodel.getIsnullable().equals("Y")) {
			fsql += " NULL";
		} else {
			fsql += " NOT NULL";
		}
		String dft = fldmodel.getDefault();
		if (dft != null && !dft.isEmpty()) {
			fsql += " DEFAULT " + dft;
		}
		
		// 主键不能修改
		//if (fldmodel.getIskey().equals("Y")) {
		//	fsql += ",PRIMARY KEY (\"id\"),";
		//}
		return fsql;
	}
	
	private String getFieldCommentSQL(String tbname, IField fldmodel) {
		String fname = fldmodel.getName();
		String cmt = fldmodel.getDescript();
		if(cmt !=null && !cmt.isEmpty()) {
			//return "COMMENT ON COLUMN \"public\".\""+tbname+"\".\""+fname+"\" IS '"+ cmt+"';";
			return "COMMENT ON COLUMN "+tbname+"."+fname+" IS '"+ cmt+"';";
		}
		return null;
	}
	
	private String getFieldInSQL(IField fldmodel) {
		String fldname = fldmodel.getName();
		String fsql = "";
		if (fldmodel.getIskey().equals("Y")) {
			fsql += ",PRIMARY KEY ("+ fldname +")";
		}
		return fsql;
	}

	@Override
	public String getUpdateTableSQL(String tbname, List<? extends IField> tflv,
			String type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getFieldListSQL(List<? extends IField> tflv) {
		// TODO Auto-generated method stub
		String fsql = null;
		for (int i = 0; i < tflv.size(); i++) {
			IField fcv = tflv.get(i);
			if(i>0) {
				fsql += ",";
			}
			if (fsql == null) {
				fsql = getFieldSQL(fcv);
			} else {
				fsql += getFieldSQL(fcv);
			}
		}
		return fsql;
	}

}
