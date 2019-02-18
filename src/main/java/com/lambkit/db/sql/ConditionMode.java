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
package com.lambkit.db.sql;


public enum ConditionMode {
	EQUAL // 相等
	,NOT_EQUAL // 不相等
	,LESS_THEN // 小于
	,LESS_EQUAL // 小于等于
	,GREATER_EQUAL // 大于等于
	,GREATER_THEN // 大于
	
	,NOT_NULL // 不为null值的情况
	,ISNULL // null值的情况
	,NOT_EMPTY // 不为空值的情况
	,EMPTY // 空值的情况
	,IN // 在范围内
	,NOT_IN // 不在范围内
	,BETWEEN // 介于
	,NOT_BETWEEN // 非介于
	
	,IFUZZY // 模糊匹配 %xxx%
	,IFUZZY_LEFT // 左模糊 %xxx
	,IFUZZY_RIGHT // 右模糊 xxx%
	,NOT_IFUZZY // 模糊匹配 %xxx%
	,NOT_IFUZZY_LEFT // 左模糊 %xxx
	,NOT_IFUZZY_RIGHT // 右模糊 xxx%
	
	,FUZZY // 模糊匹配-用户自定义
	,NOT_FUZZY // 模糊匹配-用户自定义
}