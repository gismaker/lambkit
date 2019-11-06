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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

public class ModelUtils {

	/**
	 * 将Model类转换为Map modelToMap
	 * 
	 * @param 参数说明
	 * @return 返回对象
	 * @Exception 异常对象
	 */
	public static Map<String, Object> modelToMap(Model<?> model) {
		/*
		Map<String, Object> map = new HashMap<String, Object>();
		String[] names = model.getAttrNames();
		for (String str : names) {
			map.put(str, model.get(str));
		}
		*/
		Map<String, Object> map = new HashMap<String, Object>(); 
		for(Entry<String, Object> en : model._getAttrsEntrySet())
		{ 
			map.put(en.getKey(), en.getValue()); 
		} 
		return map;
	}

	/**
	 * 将Record转换成Map recordToMap
	 * 
	 * @param 参数说明
	 * @return 返回对象
	 * @Exception 异常对象
	 */
	public static Map<String, Object> recordToMap(Record record) {
		/*
		Map<String, Object> map = new HashMap<String, Object>();
		if (record != null) {
			String[] columns = record.getColumnNames();
			for (String col : columns) {
				map.put(col, record.get(col));
			}
		}
		return map;
		*/
		return record.getColumns();
	}
}
