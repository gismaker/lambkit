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
package com.lambkit.db.mgr;

import java.util.Map;

public interface ITable {

	Object getId();
	
	String getName();
	
	String getTitle();
	
	String getPrimaryKey();
	
	void setPrimaryKey(String primaryKey);
	/**
	 * 表格类型
	 * @return
	 */
	int getTypeId();
	
	Object getAttr(String key);
	
	String getStr(String key);
	
	void setAttr(String key, Object value);
	
	void putAttr(String key, Object value);
	
	Map<String, Object> getAttrs();
	
	/**
	 * for table type of map
	 */
	void configMap();
	
	boolean update();
	
	boolean save();
}
