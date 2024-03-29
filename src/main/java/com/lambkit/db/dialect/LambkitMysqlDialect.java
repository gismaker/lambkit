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

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.sql.ConditionMode;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.SqlJoinMode;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;
import com.lambkit.db.sql.column.SqlJoinOn;
import com.lambkit.common.util.ArrayUtils;

import java.util.List;

public class LambkitMysqlDialect extends MysqlDialect implements LambkitDialect, IManagerDialect {

	public String forLimitSql(Object limit) {
		StringBuilder sqlBuilder = new StringBuilder();
		if (limit != null) {
            sqlBuilder.append(" LIMIT " + limit);
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
	public SqlPara forUpdate(Record record, String tableName, QueryParas queryParas) {
		SqlPara sqlPara = new SqlPara();
		String[] columnNames = record.getColumnNames();
        if (columnNames.length > 0) {
    		StringBuilder sqlBuilder = new StringBuilder("UPDATE `");
    		sqlBuilder.append(tableName).append("` SET ");
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
    		StringBuilder sqlBuilder = new StringBuilder("UPDATE `");
    		sqlBuilder.append(example.getTableName()).append("` ");
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
    		//appConditions(example.getTableName(), example.getColumnsList(), sqlBuilder);
    		sqlPara.setSql(sqlBuilder.toString());
            example.addValueToParam(sqlPara);
        }
		return sqlPara;
	}
	
	@Override
	public SqlPara forDeleteByExample(Example example) {
		SqlPara sqlPara = new SqlPara();
		StringBuilder sqlBuilder = new StringBuilder("DELETE FROM `");
		sqlBuilder.append(example.getTableName()).append("` ");
		appIfJoinNotEmpty(example, sqlBuilder);
		appExampleConditions(example, sqlBuilder);
		//appConditions(example.getTableName(), example.getColumnsList(), sqlBuilder);
		sqlPara.setSql(sqlBuilder.toString());
		example.addValueToParam(sqlPara);
		return sqlPara;
	}
	
	@Override
	public SqlPara forFindByExample(Example example, Object limit) {
		SqlPara sqlPara = new SqlPara();
		StringBuilder sqlBuilder = new StringBuilder("SELECT ");
		sqlBuilder.append(example.getSelectSql());
		sqlBuilder.append(" FROM  `");
		sqlBuilder.append(example.getTableName()).append("` ");

		appIfJoinNotEmpty(example, sqlBuilder);
		appExampleConditions(example, sqlBuilder);
		//appConditions(example.getTableName(), example.getColumnsList(), sqlBuilder);

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
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" FROM  `");
		sqlBuilder.append(example.getTableName()).append("` ");
		
		appIfJoinNotEmpty(example, sqlBuilder);
		appExampleConditions(example, sqlBuilder);
		//appConditions(example.getTableName(), example.getColumnsList(), sqlBuilder);

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
        }
        if (example.getJoinOn()!=null) {
			if (ArrayUtils.isNotEmpty(example.getJoinOn().getList())) {
                for (Column column : example.getJoinOn().getList()) {
                    column.addValueToParam(sqlPara);
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
                .append(" FROM  `")
                .append(table).append("` ");
        appIfNotEmpty(table, columns, sqlBuilder, true);
        if (orderBy != null) {
            sqlBuilder.append(" ORDER BY ").append(getOrderby(table,orderBy));
        }
        if (limit != null) {
            sqlBuilder.append(" LIMIT " + limit);
        }
        return sqlBuilder.toString();
    }

    @Override
    public String forPaginateSelect(String loadColumns) {
        return "SELECT " + loadColumns;
    }

    @Override
    public String forPaginateFrom(String table, List<Column> columns, String orderBy) {
        StringBuilder sqlBuilder = new StringBuilder(" FROM `").append(table).append("`");

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
                sqlBuilder.append(String.format(" `%s`.`%s` %s ", alias, column.getName(), getCondition(column)));
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
            	sqlBuilder.append(" `").append(join.getJoinTableName()).append("` ON ");
            	String mainAlias = join.getMainTableName();
            	sqlBuilder.append(" `").append(mainAlias).append("`.`").append(join.getMainField()).append("` ");
            	String alias = join.getJoinTableName();
            	sqlBuilder.append("=");
            	sqlBuilder.append(" `").append(alias).append("`.`").append(join.getJoinField()).append("` ");
        	}
        }
    }
    
    private String getOrderby(String table, String orderby) {
    	StringBuilder sqlBuilder = new StringBuilder();
    	sqlBuilder.append("`").append(table).append("`.");
    	orderby = orderby.replaceAll(",", ",`"+table+"`.");
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
    
    ////////////////////////////////////////
    

	@Override
	public String getCreateTableSQL(String tbname, List<? extends IField> tflv) {
		// TODO Auto-generated method stub
		if(tflv==null) return null;
		String field = getFieldListSQL(tflv);
		if(!StrKit.notBlank(field)) return null;
		//System.out.println("CREATE TABLE IF NOT EXISTS " + tbname + " ( " + field + ")");
		return "CREATE TABLE IF NOT EXISTS " + tbname + " ( " + field + ")";
	}
	
	@Override
	public String getDropAndCreateTableSQL(String tbname, List<? extends IField> tflv) {
		// TODO Auto-generated method stub
		if(tflv==null) return null;
		String field = getFieldListSQL(tflv);
		if(!StrKit.notBlank(field)) return null;
		//System.out.println("CREATE TABLE IF NOT EXISTS " + tbname + " ( " + field + ")");
		return "DROP TABLE IF EXISTS " + tbname + "; CREATE TABLE " + tbname + " ( " + field + ")";
	}

	@Override
	public String getDropTableSQL(String tbname) {
		// TODO Auto-generated method stub
		return "drop table IF EXISTS " + tbname;
	}
	
	@Override
	public String getAlterAddSQL(String tbname, String fldinfo, String after) {
		// TODO Auto-generated method stub
		after = after != null && after.length() > 0 && !after.isEmpty() ? " after " + after : "";
		//System.out.println("alter table " + tbname + " add column " + fldinfo + after + ";");
		return "alter table " + tbname + " add column " + fldinfo + after + ";";
	}
	
	@Override
	public String getAlterChangeSQL(String tbname, String fldinfo, String after) {
		// TODO Auto-generated method stub
		after = after != null && after.length() > 0 && !after.isEmpty() ? " after " + after : "";
		//System.out.println("alter table " + tbname + " change " + fldinfo + after + ";");
		return "alter table " + tbname + " change " + fldinfo + after + ";";
	}
	
	@Override
	public String getAlterModifySQL(String tbname, String fldinfo, String after) {
		// TODO Auto-generated method stub
		after = after != null && after.length() > 0 && !after.isEmpty() ? " after " + after : "";
		//System.out.println("alter table " + tbname + " modify " + fldinfo + after + ";");
		return "alter table " + tbname + " modify " + fldinfo + after + ";";
	}
	
	@Override
	public String getAlterDropSQL(String tbname, String fldname) {
		// TODO Auto-generated method stub
		//System.out.println("alter table " + tbname + " drop " + fldname + ";");
		return "alter table " + tbname + " drop " + fldname + ";";
	}
	
	@Override
	public String getTableSQL(String dbname, String tbname) {
		// TODO Auto-generated method stub
		//System.out.println("SELECT table_name FROM information_schema.TABLES WHERE table_schema='" + dbname + "' and table_name='" + tbname + "'");
		return "SELECT table_name FROM information_schema.TABLES WHERE table_schema='" + dbname + "' and table_name='" + tbname + "'";
	}

	@Override
	public String getTableListSQL(String dbname) {
		// TODO Auto-generated method stub
		return "select table_name from information_schema.tables where table_schema='" + dbname + "'";
	}
	
	@Override
	public String getTableNameKey() {
		// TODO Auto-generated method stub
		return "table_name";
	}
	
	@Override
	public String getColumnSQL(String dbname, String tbname, String colname) {
		// TODO Auto-generated method stub
		return "select * from information_schema.columns where table_schema='" + dbname 
				+ "' and table_name='" + tbname + "' and column_name='" + colname + "'";
	}

	@Override
	public String getColumnListSQL(String dbname, String tbname) {
		// TODO Auto-generated method stub
		return "select * from information_schema.columns where table_schema='" + dbname + "' and table_name='" + tbname + "'";
	}
	
	@Override
	public String getColumnNameKey() {
		// TODO Auto-generated method stub
		return "COLUMN_NAME";
	}

	@Override
	public String getSelectTopOne(String tbname) {
		// TODO Auto-generated method stub
		return "select * from " + tbname + " limit 1";
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
	public String getFieldSQL(IField fldmodel) {
		String fsql = fldmodel.getName();
		fsql += " " + fldmodel.getDatatype() + " ";
		if (fldmodel.getIsunsigned().equals("Y")) {
			fsql += " UNSIGNED ";
		}
		// 主键不能修改
		if (fldmodel.getIskey().equals("Y")) {
			fsql += " PRIMARY KEY ";
		}
		if (fldmodel.getIsnullable().equals("Y")) {
			fsql += " NULL ";
		} else {
			fsql += " NOT NULL ";
		}
		if (fldmodel.getIsai().equals("Y")) {
			fsql += " AUTO_INCREMENT ";
		}
		String dft = fldmodel.getDefault();
		if (dft != null && !dft.isEmpty()) {
			if (isInt(dft) || dft.equalsIgnoreCase("null")) {
				fsql += " DEFAULT " + dft;
			} else {
				fsql += " DEFAULT '" + dft + "'";
			}
		}
		String cmt = fldmodel.getDescript();
		if(cmt !=null && !cmt.isEmpty()) {
			fsql += " COMMENT '" + cmt + "'";
		}
		return fsql;
	}
	
	/**
	 * 转换 List<? extends IField>为SQL语句
	 * 
	 * @param tflv
	 * @return
	 */
	public String getFieldListSQL(List<? extends IField> tflv) {
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

	@Override
	public String getUpdateTableSQL(String tbname, List<? extends IField> tflv, String type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 判断String是否为数字
	 * 
	 * @param str
	 * @return
	 */
	protected boolean isInt(String str) {
		str = str.trim();
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	@Override
	public String version() {
		// TODO Auto-generated method stub
		return "select version() as info;";
	}
}
