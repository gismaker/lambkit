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
package com.lambkit.common.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * 1:将JavaBean转换成Map、JSONObject 2:将Map转换成Javabean 3:将JSONObject转换成Map、Javabean
 */
public class JsonUtils {

	public static final ObjectMapper JSON_MAPPER = newObjectMapper(), JSON_MAPPER_WEB = newObjectMapper();

	private static ObjectMapper newObjectMapper() {
		ObjectMapper result = new ObjectMapper();
		result.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		result.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		result.setSerializationInclusion(Include.NON_NULL);
		result.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false); // 不输出value=null的属性
		result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		result.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		return result;
	}

	public static ObjectMapper getObjectMapper() {
		return JSON_MAPPER;
	}

	public static String writeValueAsString(Object value) {
		try {
			return value == null ? null : JSON_MAPPER.writeValueAsString(value);
		} catch (IOException e) {
			throw new IllegalArgumentException(e); // TIP: 原则上，不对异常包装，这里为什么要包装？因为正常情况不会发生IOException
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMapValue(Object value) throws IllegalArgumentException {
		return convertValue(value, Map.class);
	}

	public static <T> T convertValue(Object value, Class<T> clazz) throws IllegalArgumentException {
		if (StringUtils.isNullOrEmpty(value))
			return null;
		try {
			if (value instanceof String)
				value = JSON_MAPPER.readTree((String) value);
			return JSON_MAPPER.convertValue(value, clazz);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * 获取节点值
	 * @author mengfeiyang
	 * @param jsonContent
	 * @param jsonPath
	 * @return
	 * @throws Exception
	 */
	public static synchronized String getNodeValue(String jsonContent, String jsonPath) throws Exception {
		String[] nodes = jsonPath.split("\\.");
		JSONObject obj = new JSONObject(jsonContent);

		for (int i = 0; i < nodes.length; i++) {
			if (obj != null) {
				obj = getObj(obj, nodes[i]);
			}

			if ((i + 1) == nodes.length) {
				try{
					return obj.getString(nodes[i]);
				}catch(Exception e){
					return "JSONException:"+e.getMessage()+",NodeString:"+obj.toString();
				}
			}
		}
		return null;
	}
	
	/**
	 * 对节点进行解析
	 * 
	 * @author mengfeiyang
	 * @param obj
	 * @param node
	 * @return
	 */
	private static JSONObject getObj(JSONObject obj, String node) {
		try {
			if (node.contains("[")) {
				JSONArray arr = obj.getJSONArray(node.substring(0,node.indexOf("[")));
				for (int i = 0; i < arr.length(); i++) {
					if ((i + "").equals(node.substring(node.indexOf("["),node.indexOf("]")).replace("[", ""))) {
						return arr.getJSONObject(i);
					}
				}
			} else {
				return obj.getJSONObject(node);
			}
		} catch (Exception e) {
			return obj;
		}
		return null;
	}

	/**
	 * 将Javabean转换为Map
	 * 
	 * @param javaBean javaBean
	 * @return Map对象
	 */
	public static Map toMap(Object javaBean) {
		Map result = new HashMap();
		Method[] methods = javaBean.getClass().getDeclaredMethods();
		for (Method method : methods) {
			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					Object value = method.invoke(javaBean, (Object[]) null);
					result.put(field, null == value ? "" : value.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 将Json对象转换成Map
	 * 
	 * @param jsonObject json对象
	 * @return Map对象
	 * @throws JSONException
	 */
	public static Map toMap(String jsonString) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);
		Map result = new HashMap();
		Iterator iterator = jsonObject.keys();
		String key = null;
		String value = null;
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			value = jsonObject.getString(key);
			result.put(key, value);
		}
		return result;
	}

	/**
	 * 将JavaBean转换成JSONObject（通过Map中转）
	 * 
	 * @param bean javaBean
	 * @return json对象
	 */
	public static JSONObject toJSON(Object bean) {
		return new JSONObject(toMap(bean));
	}

	public static String toJSON(Map map) {
		return new JSONObject(map).toString();
	}

	/**
	 * 将Map转换成Javabean
	 * 
	 * @param javabean javaBean
	 * @param data     Map数据
	 */
	public static Object toJavaBean(Object javabean, Map data) {
		Method[] methods = javabean.getClass().getDeclaredMethods();
		for (Method method : methods) {
			try {
				if (method.getName().startsWith("set")) {

					String field = method.getName();
					field = field.substring(field.indexOf("set") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					method.invoke(javabean, new Object[] {
							data.get(field)
					});
				}
			} catch (Exception e) {
			}
		}
		return javabean;
	}

	/**
	 * JSONObject到JavaBean
	 * 
	 * @param bean javaBean
	 * @return json对象
	 * @throws ParseException json解析异常
	 * @throws JSONException
	 */
	public static Object toJavaBean(Object javabean, String jsonString) throws ParseException, JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);
		Map map = toMap(jsonObject.toString());
		return toJavaBean(javabean, map);
	}
}
