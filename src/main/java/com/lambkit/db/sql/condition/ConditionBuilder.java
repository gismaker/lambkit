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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.lambkit.db.sql.condition.Conditions;
import com.lambkit.db.sql.condition.ConditionBuilder;
import com.lambkit.db.sql.condition.ConditionMap;

public class ConditionBuilder {
	
	//private static final Log log = Log.getLog(ConditionsBuilder.class);
	
	protected ConditionMap conditons;
	
	public ConditionBuilder() {
		this.conditons = new ConditionMap(new Conditions());
	}
	
	public ConditionBuilder build(String alias) {
		this.conditons.build(alias);
		return this;
	}
	
	public boolean hasSql() {
		return this.conditons.hasSql();
	}
	
	public String getSql() {
		return this.conditons.getSql();
	}
	
	public ConditionMap getConditons() {
		return this.conditons;
	}
	
	public List<Object> getParamList() {
		return this.conditons.getParamList();
	}
	
	public Object[] getSqlParas() {
		return this.conditons.getSqlParas();
	}
	
	public Object[] getSqlParas(Object[] sqlparas) {
		return concat(getSqlParas(), sqlparas);
	}
	
	public Object[] concat(Object[] first, Object[] second) {
		Object[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
	//-------------------------------------------------
	
	public ConditionBuilder appendQuery(String fieldName, String queryType, Object value) {
		this.conditons.put(fieldName, queryType, value);
		return this;
	}
	
	public ConditionBuilder appendEqual(String fieldName, Object value) {
		this.conditons.put(fieldName, Conditions.EQUAL, value);
		return this;
	}
	
	public ConditionBuilder appendFuzzy(String fieldName, Object value) {
		this.conditons.put(fieldName, Conditions.FUZZY, value);
		return this;
	}
	
	public ConditionBuilder appendCql(String cql) {
		if(!StrKit.notBlank(cql)) return this;
		String[] cqls = cql.split("and");
		for (String filter : cqls) {
			filter = filter.trim();
			if(filter.contains(" = ")) {
				
			} else if(filter.contains(" <> ")) {
				
			} else if(filter.contains(" > ")) {
				 
			} else if(filter.contains(" >= ")) {
				
			} else if(filter.contains(" < ")) {
				
			} else if(filter.contains(" <= ")) {
				
			} else if(filter.contains(" like ") || filter.contains(" LIKE ")) {
				
			} else if(filter.contains(" in ")) {
				
			}
		}
		return this;
	}
	
 	public ConditionBuilder append(String field, String value, String type) {
		if(StrKit.notBlank(value)) {
			String info = value.trim();
			//System.out.println("Field: " + field + ", info: " + info);
			if(info.startsWith("{") && info.endsWith("}")) {
				info = info.substring(1, info.length()-1);
				if(info.equalsIgnoreCase("empty")) {
					this.conditons.put(field, Conditions.EMPTY, "");
				} else if(info.equalsIgnoreCase("not_empty")) {
					this.conditons.put(field, Conditions.NOT_EMPTY, null);
				} else if(info.equalsIgnoreCase("isnull")) {
					this.conditons.put(field, Conditions.ISNULL, null);
				} else if(info.equalsIgnoreCase("not_null")) {
					this.conditons.put(field, Conditions.NOT_NULL, null);
				} 
			} else if(info.startsWith("@")) {
				//包含
				info = info.substring(1).trim();
				if(info.startsWith("-") || info.startsWith(",")) info = info.substring(1);
				if(info.endsWith("-") || info.endsWith(",")) info = info.substring(0, info.length()-1);
				if(info.contains("%")) {
					if(info.startsWith("%") && info.endsWith("%")) { 
						this.conditons.put(field, Conditions.FUZZY_MID, info);
					} else if(info.startsWith("%")) { 
						this.conditons.put(field, Conditions.FUZZY_LEFT, info.substring(1));
					} else if(info.endsWith("%")) { 
						this.conditons.put(field, Conditions.FUZZY_RIGHT, info.substring(0, info.length()-1));
					} else {
						this.conditons.put(field, Conditions.FUZZY_MID, info);
					}
				} else if(info.startsWith("=")) { 
					this.conditons.put(field, Conditions.EQUAL, getValue(type, info.substring(1)));
				} else if(info.contains("-")) { 
					this.conditons.put(field, Conditions.BETWEEN, info);
				} else if(info.contains(",") && !info.endsWith(",") && !info.endsWith(",%")) {
					String[] infos = info.split(",");
					if(infos.length > 0) {
						this.conditons.put(field, Conditions.IN, transArrayValue(type, infos));
					}
				} else {
					this.conditons.put(field, Conditions.FUZZY, info);
				}
			} else if(info.startsWith("#")) {
				//不包含
				info = info.substring(1).trim();
				if(info.startsWith("-") || info.startsWith(",")) info = info.substring(1);
				if(info.endsWith("-") || info.endsWith(",")) info = info.substring(0, info.length()-1);
				if(info.contains("%")) {
					if(info.startsWith("%") && info.endsWith("%")) { 
						this.conditons.put(field, Conditions.FUZZY_MID, info);
					} else if(info.startsWith("%")) { 
						this.conditons.put(field, Conditions.NOT_FUZZY_LEFT, info.substring(1));
					} else if(info.endsWith("%")) { 
						this.conditons.put(field, Conditions.NOT_FUZZY_RIGHT, info.substring(0, info.length()-1));
					} else {
						this.conditons.put(field, Conditions.NOT_FUZZY_MID, info);
					}
				} else if(info.startsWith("=")) { 
					this.conditons.put(field, Conditions.NOT_EQUAL, getValue(type, info.substring(1)));
				} else if(info.contains("-")) {
					this.conditons.put(field, Conditions.NOT_BETWEEN, info);
				} else if(info.contains(",") && !info.endsWith(",") && !info.endsWith(",%")) {
					String[] infos = info.split(",");
					if(infos.length > 0) {
						this.conditons.put(field, Conditions.NOT_IN, transArrayValue(type, infos));
					}
				} else {
					this.conditons.put(field, Conditions.NOT_FUZZY, info);
				}
			} else if(info.startsWith("!=")) { 
				//info = info.substring(2).trim();
				//if(info.startsWith("$") && info.endsWith("$")) info = info.substring(1, info.length()-1); 
				this.conditons.put(field, Conditions.NOT_EQUAL, getValue(type, info.substring(2)));
			} else if(info.startsWith("<")) { 
				this.conditons.put(field, Conditions.LESS_THEN, getValue(type, info.substring(1)));
			} else if(info.startsWith("<=")) { 
				this.conditons.put(field, Conditions.LESS_EQUAL, getValue(type, info.substring(2)));
			} else if(info.startsWith(">")) { 
				this.conditons.put(field, Conditions.GREATER_THEN, getValue(type, info.substring(1)));
			} else if(info.startsWith(">=")) { 
				this.conditons.put(field, Conditions.GREATER_EQUAL, getValue(type, info.substring(2)));
			} else if(info.contains("%")) {
				if(info.startsWith("%") && info.endsWith("%")) { 
					this.conditons.put(field, Conditions.FUZZY_MID, info);
				} else if(info.startsWith("%")) { 
					this.conditons.put(field, Conditions.FUZZY_LEFT, info.substring(1));
				} else if(info.endsWith("%")) { 
					this.conditons.put(field, Conditions.FUZZY_RIGHT, info.substring(0, info.length()-1));
				} else {
					this.conditons.put(field, Conditions.FUZZY_MID, info);
				}
			} else {
				this.conditons.put(field, Conditions.EQUAL, getValue(type, info));
			}
		}
		return this;
	}
 	
 	private Object getValue(String type, String value) {
 		type = type.toLowerCase();
 		if(type.startsWith("int"))return Integer.parseInt(value);
 		else if(type.startsWith("float"))return Float.parseFloat(value);
 		else if(type.startsWith("double"))return Double.parseDouble(value);
 		else if(type.startsWith("num"))return Double.parseDouble(value);//numeric,number
 		else if(type.startsWith("date")) return Date.valueOf(value);
 		else if(type.startsWith("datetime")) return Timestamp.valueOf(value);
 		else return value;
 	}
 	
 	private Object transArrayValue(String type, String[] value) {
 		//判断字段类型
 		type = type.toLowerCase();
 		if(type.startsWith("int")) return StringToInt(value);
 		else if(type.startsWith("float")) return StringToFloat(value);
 		else if(type.startsWith("double")) return StringToDouble(value);
 		else if(type.startsWith("num")) return StringToDouble(value);//numeric,number
 		else if(type.startsWith("date")) return StringToDate(value);
 		else if(type.startsWith("datetime")) return StringToTimestamp(value);
 		return java.util.Arrays.asList(value);
 	}
 	
 	/*
 	private List<Integer> StringToIntList(String[] arrs){
 	    List<Integer> ints = new ArrayList<Integer>();
 	    for(int i=0;i<arrs.length;i++){
 	    	ints.add(Integer.parseInt(arrs[i]));
 	    }
 	    return ints;
 	}
 	*/
 	
 	private int[] StringToInt(String[] arrs){
 	    int[] ints = new int[arrs.length];
 	    for(int i=0;i<arrs.length;i++){
 	        ints[i] = Integer.parseInt(arrs[i]);
 	    }
 	    return ints;
 	}
 	
 	private float[] StringToFloat(String[] arrs){
 		float[] ints = new float[arrs.length];
 	    for(int i=0;i<arrs.length;i++){
 	        ints[i] = Float.parseFloat(arrs[i]);
 	    }
 	    return ints;
 	}
 	
 	private double[] StringToDouble(String[] arrs){
 		double[] ints = new double[arrs.length];
 	    for(int i=0;i<arrs.length;i++){
 	        ints[i] = Double.parseDouble(arrs[i]);
 	    }
 	    return ints;
 	}

 	private Date[] StringToDate(String[] arrs){
 		Date[] ints = new Date[arrs.length];
 	    for(int i=0;i<arrs.length;i++){
 	        ints[i] = Date.valueOf(arrs[i]);
 	    }
 	    return ints;
 	}
 	
 	private Timestamp[] StringToTimestamp(String[] arrs){
 		Timestamp[] ints = new Timestamp[arrs.length];
 	    for(int i=0;i<arrs.length;i++){
 	        ints[i] = Timestamp.valueOf(arrs[i]);
 	    }
 	    return ints;
 	}
}
