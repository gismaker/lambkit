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
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.sql.ConditionMode;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;
import com.lambkit.common.exception.LambkitException;
import com.lambkit.common.util.ArrayUtils;

import java.util.List;


public class LambkitOracleDialect extends OracleDialect implements IModelDialect, IManagerDialect {
	
	@Override
	public SqlPara forDeleteByExample(Example example) {
		SqlPara sqlPara = new SqlPara();
		StringBuilder sqlBuilder = new StringBuilder("DELETE FROM ");
		sqlBuilder.append(example.getTableName()).append(" ");
		appConditions(example.getColumnsList(), sqlBuilder);
		sqlPara.setSql(sqlBuilder.toString());
        if (ArrayUtils.isNotEmpty(example.getColumnsList())) {
            for (Columns columns : example.getColumnsList()) {
            	if (ArrayUtils.isNotEmpty(columns.getList())) {
                    for (Column column : columns.getList()) {
                        column.addValueToParam(sqlPara);
                    }
                }
            }
        }
		return sqlPara;
	}
	
	@Override
	public SqlPara forUpdate(Record record, String tableName, QueryParas queryParas) {
		SqlPara sqlPara = new SqlPara();
		String[] columnNames = record.getColumnNames();
        if (columnNames.length > 0) {
    		StringBuilder sqlBuilder = new StringBuilder("UPDATE ");
    		sqlBuilder.append(tableName).append(" SET ");
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
    		StringBuilder sqlBuilder = new StringBuilder("UPDATE ");
    		sqlBuilder.append(example.getTableName()).append(" SET ");
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
    		appConditions(example.getColumnsList(), sqlBuilder);
    		sqlPara.setSql(sqlBuilder.toString());
            if (ArrayUtils.isNotEmpty(example.getColumnsList())) {
                for (Columns columns : example.getColumnsList()) {
                	if (ArrayUtils.isNotEmpty(columns.getList())) {
                        for (Column column : columns.getList()) {
                            column.addValueToParam(sqlPara);
                        }
                    }
                }
            }
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

		appConditions(example.getColumnsList(), sqlBuilder);

		if (example.getOrderBy() != null) {
			sqlBuilder.append(" ORDER BY ").append(example.getOrderBy());
		}
		
		if (limit instanceof Number) {
            StringBuilder ret = new StringBuilder();
            ret.append("select * from ( select row_.*, rownum rownum_ from (  ");
            ret.append(sqlBuilder);
            ret.append(" ) row_ where rownum <= ").append(limit).append(") table_alias");
            sqlPara.setSql(ret.toString());
        } else if (limit instanceof String && limit.toString().contains(",")) {
            String[] startAndEnd = limit.toString().split(",");
            String start = startAndEnd[0];
            String end = startAndEnd[1];

            StringBuilder ret = new StringBuilder();
            ret.append("select * from ( select row_.*, rownum rownum_ from (  ");
            ret.append(sqlBuilder);
            ret.append(" ) row_ where rownum <= ").append(end).append(") table_alias");
            ret.append(" where table_alias.rownum_ > ").append(start);
            sqlPara.setSql(ret.toString());
        } else {
        	sqlPara.setSql(sqlBuilder.toString());
        }

		if (ArrayUtils.isNotEmpty(example.getColumnsList())) {
            for (Columns columns : example.getColumnsList()) {
            	if (ArrayUtils.isNotEmpty(columns.getList())) {
                    for (Column column : columns.getList()) {
                        column.addValueToParam(sqlPara);
                    }
                }
            }
        }
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

		appConditions(example.getColumnsList(), sqlBuilder);

		if (example.getOrderBy() != null) {
			sqlBuilder.append(" ORDER BY ").append(example.getOrderBy());
		}
		
		sqlPara.setSql(sqlBuilder.toString());

		if (ArrayUtils.isNotEmpty(example.getColumnsList())) {
            for (Columns columns : example.getColumnsList()) {
            	if (ArrayUtils.isNotEmpty(columns.getList())) {
                    for (Column column : columns.getList()) {
                        column.addValueToParam(sqlPara);
                    }
                }
            }
        }
		return sqlPara;
	}

    @Override
    public String forFindByColumns(String table, String loadColumns, List<Column> columns, String orderBy, Object limit) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT ");
        sqlBuilder.append(loadColumns)
                .append(" FROM ")
                .append(table).append(" ");

        appIfNotEmpty(columns, sqlBuilder, true);


        if (orderBy != null) {
            sqlBuilder.append(" ORDER BY ").append(orderBy);
        }

        if (limit == null) {
            return sqlBuilder.toString();
        }

        if (limit instanceof Number) {
            StringBuilder ret = new StringBuilder();
            ret.append("select * from ( select row_.*, rownum rownum_ from (  ");
            ret.append(sqlBuilder);
            ret.append(" ) row_ where rownum <= ").append(limit).append(") table_alias");
            return ret.toString();
        } else if (limit instanceof String && limit.toString().contains(",")) {
            String[] startAndEnd = limit.toString().split(",");
            String start = startAndEnd[0];
            String end = startAndEnd[1];

            StringBuilder ret = new StringBuilder();
            ret.append("select * from ( select row_.*, rownum rownum_ from (  ");
            ret.append(sqlBuilder);
            ret.append(" ) row_ where rownum <= ").append(end).append(") table_alias");
            ret.append(" where table_alias.rownum_ > ").append(start);
            return ret.toString();
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
        StringBuilder sqlBuilder = new StringBuilder(" FROM ").append(table);

        appIfNotEmpty(columns, sqlBuilder, true);

        if (orderBy != null) {
            sqlBuilder.append(" ORDER BY ").append(orderBy);
        }

        return sqlBuilder.toString();
    }

    private void appConditions(List<Columns> columns, StringBuilder sqlBuilder) {
    	StringBuilder wsb = new StringBuilder();
        if (ArrayUtils.isNotEmpty(columns)) {
            int index = 0;
            for (Columns column : columns) {
            	if (ArrayUtils.isNotEmpty(column.getList())) {
            		wsb.append("(");
                    appIfNotEmpty(column.getList(), wsb, false);
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
        }
    }

    private void appIfNotEmpty(List<Column> columns, StringBuilder sqlBuilder, boolean bAppendWhere) {
        if (ArrayUtils.isNotEmpty(columns)) {
            if(bAppendWhere) sqlBuilder.append(" WHERE ");

            int index = 0;
            for (Column column : columns) {
                sqlBuilder.append(String.format(" %s %s ", column.getName(), getCondition(column)));
                if (index != columns.size() - 1) {
                    sqlBuilder.append(" AND ");
                }
                index++;
            }
        }
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

    /////////////////////////////////////////////
    

	@Override
	public String getCreateTableSQL(String tbname, List<? extends IField> tflv) {
		// TODO Auto-generated method stub
		if(tflv==null) return null;
		String field = getFieldListSQL(tflv);
		if(!StrKit.notBlank(field)) return null;
		//System.out.println("CREATE TABLE IF NOT EXISTS " + tbname + " ( " + field + ")");
		return "CREATE TABLE " + tbname + " ( " + field + ")";
	}
	
	@Override
	public String getDropAndCreateTableSQL(String tbname, List<? extends IField> tflv) {
		// TODO Auto-generated method stub
		if(tflv==null) return null;
		String field = getFieldListSQL(tflv);
		if(!StrKit.notBlank(field)) return null;
		//System.out.println("CREATE TABLE IF NOT EXISTS " + tbname + " ( " + field + ")");
		return "DROP TABLE " + tbname + "; CREATE TABLE " + tbname + " ( " + field + ")";
	}

	@Override
	public String getDropTableSQL(String tbname) {
		// TODO Auto-generated method stub
		return "DROP TABLE " + tbname;
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
		return "select table_name from user_tables and table_name='" + tbname + "'";
	}

	@Override
	public String getTableListSQL(String dbname) {
		// TODO Auto-generated method stub
		return "select table_name from user_tables";
	}
	
	@Override
	public String getTableNameKey() {
		// TODO Auto-generated method stub
		return "TABLE_NAME";
	}
	
	@Override
	public String getColumnSQL(String dbname, String tbname, String colname) {
		// TODO Auto-generated method stub
		return "select * from user_tab_columns where Table_Name='"+tbname+"' and column_name='"+colname+"'";
	}

	@Override
	public String getColumnListSQL(String dbname, String tbname) {
		// TODO Auto-generated method stub
		return "select * from user_tab_columns where Table_Name='"+tbname+"' order by column_name";
	}
	
	@Override
	public String getColumnNameKey() {
		// TODO Auto-generated method stub
		return "COLUMN_NAME";
	}


	@Override
	public String getSelectTopOne(String tbname) {
		// TODO Auto-generated method stub
		return "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM (SELECT * FROM " + tbname + " ) A WHERE ROWNUM <= 1 ) WHERE RN >= 0";
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
			//fsql += " AUTO_INCREMENT ";
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
			//fsql += " COMMENT '" + cmt + "'";
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
	public String getUpdateTableSQL(String tbname, List<? extends IField> tflv,
			String type) {
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

}
