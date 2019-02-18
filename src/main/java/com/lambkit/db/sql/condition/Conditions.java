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

import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.common.util.CommonUtils;
import com.lambkit.common.util.ModelUtils;
import com.lambkit.common.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于生成JFinal的SQL查询语句<br>
 * 工具说明： 1.setFiledQuery 指定字段查询的TYPE，通过record或者model对象将查询需要的值传入，并生成sql。<br>
 * 例如：<br>
 * setFiledQuery(FUZZY_LEFT,"name","phone");<br>
 * modelToCondition(new Model().set("name","admin");<br>
 * 此时生成sql为：and name like "%admin" //因为只传了name的值没有phone的，故不生成phone<br>
 *
 * 2.当setValueQuery中字段和BizUser对象中的字段重叠时，以setValueQuery为准.<br>
 * 例如：<br>
 * conditions.setValueQuery(Conditions.FUZZY_LEFT,BizUser.COL_NAME,"admin");<br>
 * //传入name和phone的值<br>
 * modelToCondition(new Model().set("name","admin").set("phone","13012341234"));
 * <br>
 * 此时生成sql为：and name like "%admin" //因为setValueQuery中只有name的查询类型；<br>
 *
 * 3.如果需要以modal中的参数和值为准，请将modelToCondition方法的isAll设为false.
 *
 * http://my.oschina.net/helloyangxp/blog/294231
 */
public class Conditions {
	static Log log = Log.getLog(Conditions.class);

	public static final String EQUAL = "EQUAL"; // 相等

	public static final String NOT_EQUAL = "NOT_EQUAL"; // 不相等

	public static final String LESS_THEN = "LESS_THEN"; // 小于

	public static final String LESS_EQUAL = "LESS_EQUAL"; // 小于等于

	public static final String GREATER_EQUAL = "GREATER_EQUAL"; // 大于等于

	public static final String GREATER_THEN = "GREATER_THEN"; // 大于

	public static final String FUZZY_MID = "FUZZY_MID"; // 模糊匹配 xxx%xx

	public static final String FUZZY = "FUZZY"; // 模糊匹配 %xxx%

	public static final String FUZZY_LEFT = "FUZZY_LEFT"; // 左模糊 %xxx

	public static final String FUZZY_RIGHT = "FUZZY_RIGHT"; // 右模糊 xxx%

	public static final String NOT_FUZZY_MID = "NOT_FUZZY_MID"; // 模糊匹配 xxx%xx

	public static final String NOT_FUZZY = "NOT_FUZZY"; // 模糊匹配 %xxx%

	public static final String NOT_FUZZY_LEFT = "NOT_FUZZY_LEFT"; // 左模糊 %xxx

	public static final String NOT_FUZZY_RIGHT = "NOT_FUZZY_RIGHT"; // 右模糊 xxx%

	public static final String NOT_NULL = "NOT_NULL"; // 不为null值的情况

	public static final String ISNULL = "ISNULL"; // null值的情况

	public static final String NOT_EMPTY = "NOT_EMPTY"; // 不为空值的情况

	public static final String EMPTY = "EMPTY"; // 空值的情况

	public static final String IN = "IN"; // 在范围内

	public static final String NOT_IN = "NOT_IN"; // 不在范围内

	public static final String BETWEEN = "BETWEEN"; // 介于

	public static final String NOT_BETWEEN = "NOT_BETWEEN"; // 非介于

	// 用于接收SQL语句
	private ThreadLocal<String> sql = new ThreadLocal<String>();
	// select * from
	private ThreadLocal<String> selectorsql = new ThreadLocal<String>();

	// 用于接收参数数组
	private ThreadLocal<ArrayList<Object>> paramList = new ThreadLocal<ArrayList<Object>>();

	// String-column；object-value
	private ThreadLocal<Map<String, Object>> colvaluesMap = new ThreadLocal<Map<String, Object>>();

	// 用于存放设置的条件
	private ThreadLocal<Map<String, Object[]>> conditionMap = new ThreadLocal<Map<String, Object[]>>();

	// 用于存放需要排除的字段
	private ThreadLocal<Map<String, String>> excludeFieldMap = new ThreadLocal<Map<String, String>>();

	// 构造方法(表示没有设置查询类型的字段全部按照等于来处理)
	public Conditions() {
		colvaluesMap.set(new HashMap<String, Object>());
		conditionMap.set(new HashMap<String, Object[]>());
		excludeFieldMap.set(new HashMap<String, String>());
	}

	// 构造方法(设置后表示字段所有的查询方式按照设置类型来处理，除非后面针对字段的重新设置)
	public Conditions(String type) {
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		map.put("GLOBALTYPE", new String[] { type });
		conditionMap.set(map);
		excludeFieldMap.set(new HashMap<String, String>());
	}

	public Conditions setGlobeType(String type) {
		Map<String, Object[]> map = conditionMap.get();
		map.put("GLOBALTYPE", new String[] { type });
		conditionMap.set(map);
		return this;
	}

	/***************************************************************************
	 * 设置字段的查询类型
	 *
	 * @param QueryType
	 *            查询类型
	 * @param filedName
	 *            字段名称数组
	 */
	public Conditions setFiledQuery(String QueryType, String... filedName) {
		// if (StringUtils.isNotBlank(QueryType) &&
		// StringUtils.notNull(filedName)) {
		if (StringUtils.isNotBlank(QueryType) && CommonUtils.notNullorEmpty(filedName)) {
			Map<String, Object[]> map = conditionMap.get();
			Object[] objs = map.get(QueryType);
			if (objs != null) {
				List<Object> list = new ArrayList<Object>(Arrays.asList(filedName));
				list.addAll(Arrays.asList(objs));
				Object[] c = list.toArray();
				map.put(QueryType, c);
			} else {
				map.put(QueryType, filedName);
			}
			conditionMap.set(map);
		}
		return this;
	}

	/***************************************************************************
	 * 设置需要排除的字段
	 *
	 * setexcludeField<br>
	 *
	 * @param filedName
	 * @return 返回对象
	 * @Exception 异常对象
	 *
	 */
	public Conditions setExcludeField(String... filedName) {
		if (CommonUtils.notNullorEmpty(filedName)) {
			Map<String, String> map = excludeFieldMap.get();
			for (String str : filedName) {
				map.put(str, str);
			}
			excludeFieldMap.set(map);
		}
		return this;
	}

	/***************************************************************************
	 * 查询空值或者不为空值的情况 setNullFieldQuery
	 *
	 * @param QueryType
	 * @param filedName
	 * @return 返回对象
	 * @Exception 异常对象
	 */
	public void setNullOrNotNullFieldQuery(String QueryType, String... filedName) {
		if (StringUtils.notBlank(QueryType) && CommonUtils.notNullorEmpty(filedName)) {
			if (!NOT_EMPTY.equals(QueryType) && !EMPTY.equals(QueryType)) {
				log.error("空值或者非空查询的类型只能为：EMPTY、NOT_EMPTY");
				throw new RuntimeException("空值或者非空查询的类型只能为：EMPTY、NOT_EMPTY");
			}
			Map<String, Object[]> map = conditionMap.get();
			map.put(QueryType, filedName);
			conditionMap.set(map);
		}
	}

	/***************************************************************************
	 * <b>传值查询</b><br>
	 * 注：如果QueryType为<b>in</b>或者<b>not in</b>那么filedValue必须为一个list对象
	 *
	 * @param QueryType
	 *            查询类型
	 * @param fieldName
	 *            字段名称
	 * @param filedValue
	 *            字段值
	 */
	public Conditions setValueQuery(String QueryType, String fieldName, Object filedValue) {
		if (StringUtils.notBlank(QueryType) && StringUtils.notBlank(fieldName)
				&& CommonUtils.notNullorEmpty(filedValue)) {
			Object[] param = new Object[2];
			param[0] = fieldName; // 字段名
			param[1] = filedValue;// 字段值
			Map<String, Object[]> map = conditionMap.get();
			map.put(QueryType + "#" + fieldName, param);// 避免类型重复被覆盖掉就加上字段名
			conditionMap.set(map);
		}
		return this;
	}

	/**
	 * 当非主动调用modelToCondition，recordToCondition方法时 需要此方法触发sql拼接方法
	 * 
	 * @param val
	 * @TODO
	 * @return
	 * 
	 * 		public Conditions buildSQL(HashMap<String,Object> val){ //@TODO
	 *         // buildCondition("", val.keySet().toArray(new
	 *         String[val.size()]), val); return this; }
	 */
	/***************************************************************************
	 * 用于生成SQL条件语句不带别名
	 *
	 * @param modelClass
	 *            必须继承于Model
	 */
	public void modelToCondition(Model<?> modelClass) {
		modelToCondition(modelClass, null, true);
	}

	/***************************************************************************
	 * 用于生成SQL条件语句不带别名
	 *
	 * @param recordClass
	 *            必须是一个Record类
	 */
	public void recordToCondition(Record recordClass) {
		recordToCondition(recordClass, null, true);
	}

	/***************************************************************************
	 * 用于生成SQL条件语句带别名 生成时以modelClass对象中的非空非null字段为准
	 * 
	 * @param modelClass
	 *            必须继承于Model
	 * @param alias
	 *            别名 默认可以为[null or ""]
	 * @param isAll
	 *            是否需要以Modelclass中的属性和值为准，默认为true <br>
	 *            [true 使用conditionMap过滤，false 只用excludeFieldMap 过滤]
	 */
	public void modelToCondition(Model<?> modelClass, String alias, boolean isAll) {
		alias = StringUtils.notBlank(alias) ? alias + "." : "";
		if (modelClass != null) {
			// 所有的字段
			String[] fieldNames = modelClass._getAttrNames();
			// System.out.println("fieldNames length = " + fieldNames.length);
			// 字段名和值的map集合
			Map<String, Object> valueMap = ModelUtils.modelToMap(modelClass);
			// System.out.println("valueMap length = " + valueMap.size());
			// 构建查询条件
			buildCondition(alias, fieldNames, valueMap);
		} else {
			if (!conditionMap.get().isEmpty()) {
				buildCondition(alias, new String[] {}, new HashMap<String, Object>());
			} else {
				sql.set("");
				paramList.set(new ArrayList<Object>(0));
			}
		}
	}

	/***************************************************************************
	 * 用于生成SQL条件语句不带别名
	 *
	 * @param recordClass
	 *            必须是一个Record类
	 * @param alias
	 *            别名 默认可以为[null or ""]
	 * @param isAll
	 *            是否需要以Modelclass中的属性和值为准，默认为true <br>
	 *            [true 使用conditionMap过滤，false 只用excludeFieldMap 过滤]
	 */
	public void recordToCondition(Record recordClass, String alias, boolean isAll) {
		// 别名
		alias = StringUtils.notBlank(alias) ? alias + "." : "";
		if (recordClass != null) {
			// 所有的字段
			String[] fieldNames = recordClass.getColumnNames();
			// 字段名和值的map集合
			Map<String, Object> valueMap = ModelUtils.recordToMap(recordClass);
			// 构建查询条件
			buildCondition(alias, fieldNames, valueMap);
		} else {
			if (!conditionMap.get().isEmpty()) {
				buildCondition(alias, new String[] {}, new HashMap<String, Object>());
			} else {
				sql.set("");
				paramList.set(new ArrayList<Object>(0));
			}
		}
	}

	public void buildCondition(String alias, String[] fieldNames, Map<String, Object> valueMap) {
		buildCondition(alias, java.util.Arrays.asList(fieldNames), valueMap);
	}

	/***************************************************************************
	 * 构建条件语句
	 *
	 * @param alias
	 *            别名
	 * @param fieldNames
	 *            所有查询的字段名称
	 * @param valueMap
	 *            所有的值的map
	 */
	public void buildCondition(String alias, List<String> fieldNames, Map<String, Object> valueMap) {
		try {
			// 构建条件前先清空变量
			sql.set("");
			paramList.set(new ArrayList<Object>());
			// 用于存放参数列表
			ArrayList<Object> paramArrayList = new ArrayList<Object>();
			StringBuilder sb = new StringBuilder();
			// 所有的字段名称
			Map<String, String> usedFieldMap = new HashMap<String, String>();
			if (!conditionMap.get().isEmpty()) {
				for (Map.Entry<String, Object[]> map : conditionMap.get().entrySet()) {
					String queryType = map.getKey();
					// System.out.println("for conditionMap key = " +
					// queryType);
					Object[] array = map.getValue();
					if (queryType.indexOf("#") > 0) {// 传值查询
						String fieldQueryType = queryType.split("#")[0];
						String fieldName = array[0] != null ? array[0].toString() : "";
						Object fieldValue = array[1];
						// 将设置过的字段保存到数组中
						usedFieldMap.put(fieldName, fieldName);
						// 构建SQL语句
						buildSQL(sb, fieldQueryType, fieldName, fieldValue, alias, paramArrayList);
					} else {// 字段查询
						if (!"GLOBALTYPE".equals(queryType)) {
							for (Object field : array) {
								String filedName = field != null ? field.toString() : "";
								if (!excludeFieldMap.get().containsKey(filedName)) {
									Object fieldValue = valueMap.get(filedName);
									// 将设置过的字段保存到数组中
									usedFieldMap.put(filedName, filedName);
									// 构建查询语句
									buildSQL(sb, queryType, filedName, fieldValue, alias, paramArrayList);
								}
							}
						}
					}
				}
			}

			// 对没有设置条件的字段进行查询类型设置
			String queryType = EQUAL;
			if (conditionMap.get().containsKey("GLOBALTYPE")) {
				String[] typeArray = (String[]) conditionMap.get().get("GLOBALTYPE");
				queryType = typeArray[0];
				// System.out.println("for conditionMap GLOBALTYPE = " +
				// queryType);
				// 对未使用过的字段进行build
				for (String field : fieldNames) {
					// System.out.println("fieldNames field = " + field);
					if (!usedFieldMap.containsKey(field)) {
						// System.out.println("usedFieldMap uncontains field = "
						// + field);
						Object fieldValue = valueMap.get(field);
						// 构建查询语句
						buildSQL(sb, queryType, field, fieldValue, alias, paramArrayList);
					}
				}
			}
			// 合并传入的参数到参数对象中
			sql.set(sb.toString());
			paramList.set(paramArrayList);
			conditionMap.set(new HashMap<String, Object[]>(0));// 清空本次的条件map
			excludeFieldMap.set(new HashMap<String, String>(0));// 清空本次的排除字段
		} catch (Exception e) {
			log.error("Conditions构建SQL语句出现错误,请仔细检查!", e);
			e.printStackTrace();
		}
	}

	/***************************************************************************
	 * 构建SQL语句
	 *
	 * @param sb
	 *            用于拼接SQL语句
	 * @param queryType
	 *            查询类型
	 * @param fieldName
	 *            字段名称
	 * @param fieldValue
	 *            字段值
	 * @param alias
	 *            别名
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private void buildSQL(StringBuilder sb, String queryType, String fieldName, Object fieldValue, String alias,
			ArrayList<Object> params) {
		// 非空的时候进行设置
		if (CommonUtils.notNullorEmpty(fieldValue) && CommonUtils.notNullorEmpty(fieldName)) {
			if (EQUAL.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " = ? ");
				params.add(fieldValue);
			} else if (NOT_EQUAL.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " <> ? ");
				params.add(fieldValue);
			} else if (LESS_THEN.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " < ? ");
				params.add(fieldValue);
			} else if (LESS_EQUAL.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " <= ? ");
				params.add(fieldValue);
			} else if (GREATER_THEN.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " > ? ");
				params.add(fieldValue);
			} else if (GREATER_EQUAL.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " >= ? ");
				params.add(fieldValue);
			} else if (FUZZY.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " like ? ");
				params.add("%" + fieldValue + "%");
			} else if (FUZZY_MID.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " like ? ");
				params.add(fieldValue);
			} else if (FUZZY_LEFT.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " like ? ");
				params.add("%" + fieldValue);
			} else if (FUZZY_RIGHT.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " like ? ");
				params.add(fieldValue + "%");
			} else if (NOT_FUZZY.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " not like ? ");
				params.add("%" + fieldValue + "%");
			} else if (NOT_FUZZY_MID.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " not like ? ");
				params.add(fieldValue);
			} else if (NOT_FUZZY_LEFT.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " not like ? ");
				params.add("%" + fieldValue);
			} else if (NOT_FUZZY_RIGHT.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " not like ? ");
				params.add(fieldValue + "%");
			} else if (IN.equals(queryType)) {
				try {
					StringBuffer instr = new StringBuffer();
					sb.append(" and " + alias + fieldName + " in (");
					if (fieldValue instanceof List) {
						List list = (List) fieldValue;
						for (Object obj : list) {
							instr.append(StringUtils.notBlank(instr) ? ",?" : "?");
							params.add(obj);
						}
					} else if (fieldValue.getClass().isArray()) {
						// 如果是数组那么获取数组的长度
						int length = Array.getLength(fieldValue);
						for (int i = 0; i < length; i++) {
							instr.append(StringUtils.notBlank(instr) ? ",?" : "?");
							// 通过Array类静态方法根据指定的索引输出
							params.add(Array.get(fieldValue, i));
						}
					}
					sb.append(instr + ") ");
				} catch (Exception e) {
					throw new RuntimeException(
							"使用IN条件的时候传入的值必须是个List对象，否则将会转换出错!例如将 in('1','2','3')中的'1','2','3'分为三个分别添加到List中做为值传入.", e);
				}
			} else if (NOT_IN.equals(queryType)) {
				try {
					List list = (List) fieldValue;
					StringBuffer instr = new StringBuffer();
					sb.append(" and " + alias + fieldName + " not in (");
					for (Object obj : list) {
						instr.append(StringUtils.notBlank(instr) ? ",?" : "?");
						params.add(obj);
					}
					sb.append(instr + ") ");
				} catch (Exception e) {
					throw new RuntimeException(
							"使用NOT IN条件的时候传入的值必须是个List对象，否则将会转换出错!例如将 not in('1','2','3')中的'1','2','3'分为三个分别添加到List中做为值传入.",
							e);
				}
			} else if (BETWEEN.equals(queryType)) {
				String[] infos = ((String) fieldValue).split("-");
				if (infos.length > 1) {
					sb.append(" and " + alias + fieldName + " between '" + infos[0] + "' and '" + infos[1] + "' ");
				}
			} else if (NOT_BETWEEN.equals(queryType)) {
				String[] infos = ((String) fieldValue).split("-");
				if (infos.length > 1) {
					sb.append(" and " + alias + fieldName + " not between '" + infos[0] + "' and '" + infos[1] + "' ");
				}
			}
		} else {
			if (EMPTY.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " = '' ");
			} else if (NOT_EMPTY.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " <> '' ");
			} else if (ISNULL.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " is null ");
			} else if (NOT_NULL.equals(queryType)) {
				sb.append(" and " + alias + fieldName + " is not null ");
			}
		}
	}

	public boolean hasSql() {
		if (StrKit.notBlank(getSql()) && getParamList().size() > 0) {
			return true;
		}
		return false;
	}

	public String getSql() {
		return sql.get();
	}

	public List<Object> getParamList() {
		return paramList.get();
	}

	public Map<String, Object> getParamMap() {
		return colvaluesMap.get();
	}

	public String getSelector() {
		return selectorsql.get();
	}

	public void setSelector(String selector) {
		selectorsql.set(selector);
	}
}