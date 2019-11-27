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
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.lambkit.common.exception.LambkitException;
import com.lambkit.common.util.ArrayUtils;
import com.lambkit.db.sql.ConditionMode;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;

import java.util.List;


public class LambkitAnsiSqlDialect extends AnsiSqlDialect implements LambkitDialect {

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
		
		if (limit != null) {
            throw new LambkitException("limit param not finished LambkitAnsiSqlDialect.");
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

        if (limit != null) {
            throw new LambkitException("limit param not finished LambkitAnsiSqlDialect.");
        }

        return sqlBuilder.toString();
    }
    
    @Override
	public String forFindBySql(String sql, String orderBy, Object limit) {
    	 StringBuilder sqlBuilder = new StringBuilder(sql);

         if (orderBy != null) {
             sqlBuilder.append(" ORDER BY ").append(orderBy);
         }

         if (limit != null) {
             throw new LambkitException("limit param not finished LambkitAnsiSqlDialect.");
         }

         return sqlBuilder.toString();
	}

	@Override
	public SqlPara forFindBySqlPara(SqlPara sqlPara, String orderBy, Object limit) {
		StringBuilder sqlBuilder = new StringBuilder(sqlPara.getSql());

        if (orderBy != null) {
            sqlBuilder.append(" ORDER BY ").append(orderBy);
        }

        if (limit != null) {
            throw new LambkitException("limit param not finished LambkitAnsiSqlDialect.");
        }
        sqlPara.setSql(sqlBuilder.toString());
        return sqlPara;
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
}
