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
package com.lambkit.db.sql.criterion.expression;

import java.io.Serializable;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.lambkit.db.sql.ConditionMode;
import com.lambkit.db.sql.criterion.Criteria;
import com.lambkit.db.sql.criterion.Criterion;
import com.lambkit.db.sql.criterion.SqlParas;

public class QueryExpression implements Criterion, Serializable {

	private static final long serialVersionUID = 1L;
	
	private ConditionMode type;
	
	private String column;
	
	private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private String typeHandler;
    
    private String valueType;
	
	public QueryExpression(ConditionMode type, String column) {
        this.type = type;
        this.column = column;
        this.typeHandler = null;
        this.noValue = true;
    }

    public QueryExpression(ConditionMode type, String column, Object value, String typeHandler, String valueType) {
        this.type = type;
        this.value = value;
        this.column = column;
        this.typeHandler = typeHandler;
        if (value instanceof List<?>) {
            this.listValue = true;
        } else {
            this.singleValue = true;
        }
        this.valueType = valueType;
    }
    
    public QueryExpression(ConditionMode type, String column, Object value, String typeHandler) {
        this(type, column, value, typeHandler, null);
    }

    public QueryExpression(ConditionMode type, String column, Object value) {
        this(type, column, value, null);
    }

    public QueryExpression(ConditionMode type, String column, Object value, Object secondValue, String typeHandler, String valueType) {
        this.type = type;
        this.column = column;
        this.value = value;
        this.secondValue = secondValue;
        this.typeHandler = typeHandler;
        this.betweenValue = true;
        this.valueType = valueType;
        if(this.valueType == null && value instanceof String) {
        	this.valueType = "String";
        }
    }
    
    public QueryExpression(ConditionMode type, String column, Object value, Object secondValue, String typeHandler) {
    	this(type, column, value, secondValue, typeHandler, null);
    }

    public QueryExpression(ConditionMode type, String column, Object value, Object secondValue) {
        this(type, column, value, secondValue, null);
    }
    
    /***********************************************/
    
    @Override
	public SqlParas getSqlParas(Criteria criteria) {
		// TODO Auto-generated method stub
		return build(criteria, criteria.isUseParas());
	}

	/***********************************************/
	
    private SqlParas build(Criteria criteria, boolean needParas) {
    	SqlParas csql = new SqlParas();
    	StringBuilder sb = new StringBuilder();
    	sb.append(StrKit.notBlank(criteria.getAlias()) ? criteria.getAlias() + "." : "");
    	sb.append(column);
    	int ptype = 0;
    	switch (type) {
		case ISNULL:
			sb.append(" is null");
			break;
		case NOT_NULL:
			sb.append(" is not null");
			break;
		case EMPTY:
			sb.append(" = ''");
			break;
		case NOT_EMPTY:
			sb.append(" <> ''");
			break;
		case BETWEEN:
			sb.append(" between");
			ptype = 2;
			//if(needParas) sb.append(" ? and ? ");
			break;
		case NOT_BETWEEN:
			sb.append(" not between");
			ptype = 2;
			//if(needParas) sb.append(" ? and ?");
			break;
		case IN:
			sb.append(" in");
			ptype = 3;
			break;
		case NOT_IN:
			sb.append(" not in");
			ptype = 3;
			break;
		case EQUAL:
			sb.append(" = ");
			ptype = 1;
			break;
		case NOT_EQUAL:
			sb.append(" <>");
			ptype = 1;
			break;
		case GREATER_THEN:
			sb.append(" >");
			ptype = 1;
			break;
		case GREATER_EQUAL:
			sb.append(" >=");
			ptype = 1;
			break;
		case LESS_THEN:
			sb.append(" <");
			ptype = 1;
			break;
		case LESS_EQUAL:
			sb.append(" <=");
			ptype = 1;
			break;
		case IFUZZY:
			sb.append(" like ");
			if(needParas) {
				sb.append("?");
				csql.add("%" + value + "%");
			} else {
				sb.append("'%" + value + "%'");
			}
			break;
		case NOT_IFUZZY:
			sb.append(" not like ");
			if(needParas) {
				sb.append("?");
				csql.add("%" + value + "%");
			} else {
				sb.append("'%" + value + "%'");
			}
			break;
		case IFUZZY_LEFT:
			sb.append(" like ");
			if(needParas) {
				sb.append("?");
				csql.add("%" + value);
			} else {
				sb.append("'%" + value + "'");
			}
			break;
		case NOT_IFUZZY_LEFT:
			sb.append(" not like ");
			if(needParas) {
				sb.append("?");
				csql.add("%" + value);
			} else {
				sb.append("'%" + value + "'");
			}
			break;
		case IFUZZY_RIGHT:
			sb.append(" like ");
			if(needParas) {
				sb.append("?");
				csql.add(value + "%");
			} else {
				sb.append("'" + value + "%'");
			}
			break;
		case NOT_IFUZZY_RIGHT:
			sb.append(" not like ");
			if(needParas) {
				sb.append("?");
				csql.add(value + "%");
			} else {
				sb.append("'" + value + "%'");
			}
			break;
		case FUZZY:
			sb.append(" like ");
			ptype = 1;
			break;
		case NOT_FUZZY:
			sb.append(" not like ");
			ptype = 1;
			break;
		default:
			sb.append(" like ");
			ptype = 1;
			break;
		}
    	
    	boolean bStr = "String".equals(getValueType());
    	switch (ptype) {
		case 1:
			if(needParas) {
				sb.append("?");
				csql.add(value);
			} else {
				if(bStr) sb.append("'");
				sb.append(value);
				if(bStr) sb.append("'");
			}
			break;
		case 2:
			sb.append(" ");
			if(bStr) sb.append("'");
			sb.append(value);
			if(bStr) sb.append("'");
			sb.append(" and ");
			if(bStr) sb.append("'");
			sb.append(secondValue);
			if(bStr) sb.append("'");
			break;
		case 3:
			int j=0;
			if(value instanceof List) {
				for (Object val : (List<?>)value) {
					if(j==0) sb.append(" (");
					else sb.append(",");
					if(needParas) {
						sb.append("?");
						csql.add(val);
					} else {
						if(bStr) sb.append("'");
						sb.append(val);
						if(bStr) sb.append("'");
					}
					j++;
				}
			} else {
				for (Object val : (Object[]) value) {
					if(j==0) sb.append(" (");
					else sb.append(",");
					if(needParas) {
						sb.append("?");
						csql.add(val);
					} else {
						if(bStr) sb.append("'");
						sb.append(val);
						if(bStr) sb.append("'");
					}
					j++;
				}
			}
			sb.append(")");
			break;
		default:
			break;
		}
    	csql.setSql(sb.toString());
    	return csql;
    }
    
    /***********************************************/
	
	public ConditionMode getType() {
        return type;
    }
	
	public String getColumn() {
		return column;
	}
	
	public Object getValue() {
        return value;
    }

    public Object getSecondValue() {
        return secondValue;
    }

    public boolean isNoValue() {
        return noValue;
    }

    public boolean isSingleValue() {
        return singleValue;
    }

    public boolean isBetweenValue() {
        return betweenValue;
    }

    public boolean isListValue() {
        return listValue;
    }

    public String getTypeHandler() {
        return typeHandler;
    }
    
    public String getValueType() {
    	return valueType;
    }

	
}
