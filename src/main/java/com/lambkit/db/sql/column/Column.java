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
package com.lambkit.db.sql.column;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.jfinal.plugin.activerecord.SqlPara;
import com.lambkit.db.sql.ConditionMode;

public class Column implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9012991621949620093L;

	private String name;

	private ConditionMode logic = ConditionMode.EQUAL;

	private Object value;

	private Object secondValue;

	private boolean noValue = true;

	private boolean singleValue;

	private boolean betweenValue;

	private boolean listValue;

	private String typeHandler;

	private String valueType;

	public Column() {
		// TODO Auto-generated constructor stub
		this.typeHandler = null;
		this.noValue = true;
	}

	public static Column create(String name, Object value) {
		Column column = new Column();
		column.setName(name);
		column.setValue(value);
		column.setSingleValue(true);
		return column;
	}

	public static Column create(String name, Object value, ConditionMode logic) {
		Column column = new Column();
		column.setName(name);
		column.setValue(value);
		column.setLogic(logic);
		column.setSingleValue(true);
		return column;
	}
	
	public static Column create(String name, ConditionMode logic) {
		Column column = new Column();
		column.setName(name);
		column.setLogic(logic);
		return column;
	}

	public static Column create(String name, Object value, ConditionMode logic, String typeHandler) {
		return create(name, value, logic, typeHandler, "String");
	}

	public static Column create(String name, Object value, ConditionMode logic, String typeHandler, String valueType) {
		Column column = new Column();
		column.setName(name);
		column.setValue(value);
		column.setLogic(logic);
		column.setTypeHandler(typeHandler);
		if (value instanceof List<?>) {
			column.setListValue(true);
		} else {
			column.setSingleValue(true);
		}
		column.setValueType(valueType);
		return column;
	}

	public static Column create(String name, Object value, Object secondValue, ConditionMode logic) {
		return create(name, value, secondValue, logic, null);
	}

	public static Column create(String name, Object value, Object secondValue, ConditionMode logic,
			String typeHandler) {
		return create(name, value, secondValue, logic, typeHandler, "String");
	}

	public static Column create(String name, Object value, Object secondValue, ConditionMode logic, String typeHandler,
			String valueType) {
		Column column = new Column();
		column.setName(name);
		column.setValue(value);
		column.setLogic(logic);
		column.setSecondValue(secondValue);
		column.setTypeHandler(typeHandler);
		column.setBetweenValue(true);
		column.setValueType(valueType);
		return column;
	}
	
	public void addValueToParam(LinkedList<Object> params) {
		if(isNoValue()) {
			return;
		} else if(isSingleValue()) {
			params.add(value);
		} else if(isBetweenValue()) {
			params.add(value);
			params.add(secondValue);
		} else if(isListValue()) {
			for (Object val : (List<?>) value) {
				params.add(val);
			}
		}
	}
	
	public void addValueToParam(SqlPara sqlPara) {
		if(isNoValue()) {
			return;
		} else if(isSingleValue()) {
			sqlPara.addPara(value);
		} else if(isBetweenValue()) {
			sqlPara.addPara(value);
			sqlPara.addPara(secondValue);
		} else if(isListValue()) {
			for (Object val : (List<?>) value) {
				sqlPara.addPara(val);
			}
		}
	}

	public Column logic(ConditionMode logic) {
		this.setLogic(logic);
		return this;
	}

	public Column name(String name) {
		this.setName(name);
		return this;
	}

	public Column value(Object value) {
		this.setValue(value);
		return this;
	}

	public Column secondValue(Object value) {
		this.setSecondValue(value);
		setBetweenValue(true);
		return this;
	}

	public Column typeHandler(String typeHandler) {
		this.setTypeHandler(typeHandler);
		return this;
	}

	public Column valueType(String valueType) {
		this.setValueType(valueType);
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
		this.noValue = false;
	}

	public ConditionMode getLogic() {
		return logic;
	}
	
	public void setLogic(ConditionMode logic) {
		this.logic = logic;
		if (logic == ConditionMode.BETWEEN || logic == ConditionMode.NOT_BETWEEN) {
			this.setBetweenValue(true);
		} else if (logic == ConditionMode.IN || logic == ConditionMode.NOT_IN) {
			this.setListValue(true);
		} else if (logic == ConditionMode.EMPTY || logic == ConditionMode.NOT_EMPTY || logic == ConditionMode.ISNULL
				|| logic == ConditionMode.NOT_NULL) {
			this.setNoValue(true);
		} else {
			this.setSingleValue(true);
		}
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

	public void setSecondValue(Object secondValue) {
		this.secondValue = secondValue;
		setBetweenValue(true);
	}

	public void setNoValue(boolean noValue) {
		this.noValue = noValue;
		this.singleValue = false;
		this.betweenValue=false;
		this.listValue=false;
	}

	public void setSingleValue(boolean singleValue) {
		this.singleValue = singleValue;
		this.noValue = false;
		this.betweenValue=false;
		this.listValue=false;
	}

	public void setBetweenValue(boolean betweenValue) {
		this.betweenValue = betweenValue;
		this.singleValue = false;
		this.noValue = false;
		this.listValue=false;
	}

	public void setListValue(boolean listValue) {
		this.listValue = listValue;
		this.singleValue = false;
		this.noValue = false;
		this.betweenValue=false;
	}

	public void setTypeHandler(String typeHandler) {
		this.typeHandler = typeHandler;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String toSql() {
		return String.format(" `%s` %s ? ", name, logic);
	}
	
	public boolean isMustNeedValue() {
        return !ConditionMode.ISNULL.equals(getLogic())
                && !ConditionMode.NOT_NULL.equals(getLogic());
    }
}
