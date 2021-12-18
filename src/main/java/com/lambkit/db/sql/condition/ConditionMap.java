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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.kit.StrKit;
import com.lambkit.db.dialect.LambkitDialect;
import com.lambkit.db.meta.TableMeta;
import com.lambkit.db.sql.condition.Condition;
import com.lambkit.db.sql.condition.Conditions;

public class ConditionMap {
	
	private Conditions conditions;
	private Map<String, Condition> condition;
	
	/*
	public ConditonsParam() {
		this.params = new HashMap<String, SqlParam>();
	}
	*/
	
	public ConditionMap(Conditions conditions) {
		this.conditions = conditions;
		this.condition = new HashMap<String, Condition>();
	}
	
	public void put(String fieldName, String queryType, Object value) {
		condition.put(fieldName, new Condition(fieldName, queryType, value));
		//System.out.println(fieldName+","+queryType+","+value);
	}
	
	public void build(String alias) {
		if(conditions==null) {
			return;
		}
		//conditions.setExcludeField("id");
		// 所有的字段
        //String[] fieldNames = (String[]) params.keySet().toArray();
		List<String> fieldNames = new ArrayList<String>();
		// 字段名和值的map集合
        Map<String, Object> valueMap = new HashMap<String, Object>();
		for (Entry<String, Condition> param : condition.entrySet()) {
			Condition sp = param.getValue();
			String field = param.getKey();
			conditions.setFiledQuery(sp.getQueryType(), field);
			valueMap.put(field, sp.getValue());
			fieldNames.add(field);
			//System.out.println(field+","+sp.getQueryType()+","+sp.getValue());
		}
        // 构建查询条件
        conditions.buildCondition(alias, fieldNames, valueMap);
        //System.out.println(conditions.getSql());
        //System.out.println(conditions.getParamList());
	}
	
	public void setDialect(LambkitDialect dialect) {
		conditions.setDialect(dialect);
	}
	
	public void setTableMeta(TableMeta tableMeta) {
		conditions.setTableMeta(tableMeta);
	}
	
	public boolean hasSql() {
		if(conditions!=null) return conditions.hasSql();
		return false;
	}
	
	public String getSql() {
		if(conditions!=null) return conditions.getSql();
		return null;
	}
	
	public List<Object> getParamList() {
		if(conditions!=null) return conditions.getParamList();
		return null;
	}
	
	public Object[] getSqlParas() {
		if(conditions!=null) {
			List<Object> pl = getParamList();
			if(pl!=null) return pl.toArray();
		}
		return null;
	}
	
	/***
	 * 根据字段拼接order by sql
	 * @param fid 字段名称
	 * @return
	 */
	public String getOrderBy(String fid, String orderby)
	{
		if(StrKit.notBlank(orderby)) {
			return " ORDER BY " + fid +" "+orderby +" ";
		}
		return " ORDER BY " + fid +" ";
	}
	
	//--------------------------------------------

	public Map<String, Condition> getParam() {
		return condition;
	}

	public void setParams(Map<String, Condition> params) {
		this.condition = params;
	}

	public Conditions getConditions() {
		return conditions;
	}

	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}
}
