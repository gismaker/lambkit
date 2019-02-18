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
package com.lambkit.db.sql.condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jfinal.kit.StrKit;

public class SqlJoinBuilder {
	
	public static final String INNER_JOIN = " inner join ";
	public static final String LEFT_JOIN = " left join ";
	public static final String RIGHT_JOIN = " right join ";
	
	private String joinType = INNER_JOIN;

	private String tableName = null;
	
	// on sql builder
	private List<SqlJoinOn> joinOn = new ArrayList<SqlJoinOn>();
	
	//where sql builder
	private ConditionBuilder conditions;
	
	public void addJoinOn(String tb1, String fld1, String tb2, String fld2) {
		joinOn.add(new SqlJoinOn(tb1, fld1, tb2, fld2));
	}
	
	public void addJoinOn(String tb1, String fld1, String fld2) {
		joinOn.add(new SqlJoinOn(tb1, fld1, tableName, fld2));
	}
	
	public String build() {
		if(!StrKit.notBlank(joinType)) return null;
		if(!StrKit.notBlank(tableName)) return null;
		if(joinOn==null || joinOn.size() < 1) return null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(joinType).append(tableName).append(" on ");
		int i=0;
		for (SqlJoinOn sjo : joinOn) {
			if(sjo!=null) {
				if(i>0) sb.append(" and ");
				sb.append(sjo.getTb1()).append(".").append(sjo.getFld1()).append("=");
				sb.append(sjo.getTb2()).append(".").append(sjo.getFld2()).append(" ");
				i++;
			}
		}
		if(conditions!=null) conditions.build(tableName+".");
		return sb.toString();
	}
	
	public String getWhereSql() {
		if(conditions!=null) return conditions.getSql();
		return null;
	}
	
	public List<Object> getParamList() {
		if(conditions!=null) return conditions.getParamList();
		return null;
	}
	
	public Object[] getSqlParas() {
		if(conditions!=null) return conditions.getSqlParas();
		return null;
	}
	
	public Object[] getSqlParas(Object[] sqlparas) {
		return concat(getSqlParas(), sqlparas);
	}
	
	private Object[] concat(Object[] first, Object[] second) {
		Object[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
	//----------------------------------
	
	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tbName) {
		this.tableName = tbName;
	}

	public List<SqlJoinOn> getJoinOn() {
		return joinOn;
	}

	public void setJoinOn(List<SqlJoinOn> joinOn) {
		this.joinOn = joinOn;
	}

	public ConditionBuilder getConditions() {
		return conditions;
	}

	public void setConditions(ConditionBuilder conditions) {
		this.conditions = conditions;
	}
}

class SqlJoinOn {
	private String tb1;
	private String fld1;
	private String tb2;
	private String fld2;
	
	public SqlJoinOn(String tb1, String fld1, String tb2, String fld2) {
		setTb1(tb1);
		setFld1(fld1);
		setTb2(tb2);
		setFld2(fld2);
	}
	
	public String getTb1() {
		return tb1;
	}
	public void setTb1(String tb1) {
		this.tb1 = tb1;
	}
	public String getFld1() {
		return fld1;
	}
	public void setFld1(String fld1) {
		this.fld1 = fld1;
	}
	public String getTb2() {
		return tb2;
	}
	public void setTb2(String tb2) {
		this.tb2 = tb2;
	}
	public String getFld2() {
		return fld2;
	}
	public void setFld2(String fld2) {
		this.fld2 = fld2;
	}
}
