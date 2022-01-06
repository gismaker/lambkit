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
package com.lambkit.db.dialect;

import java.util.List;

import com.lambkit.db.mgr.IField;

public interface IManagerDialect {
	
	/**
	 * 数据库version
	 * @return
	 */
	String version();

	String getCreateTableSQL(String tbname, List<? extends IField> tflv);

	String getUpdateTableSQL(String tbname, List<? extends IField> tflv, String type);

	String getDropAndCreateTableSQL(String tbname, List<? extends IField> tflv);

	String getDropTableSQL(String tbname);

	String getAlterAddSQL(String tbname, String fldinfo, String after);

	String getAlterChangeSQL(String tbname, String fldinfo, String after);

	String getAlterModifySQL(String tbname, String fldinfo, String after);

	String getAlterDropSQL(String tbname, String fldname);

	String getTableSQL(String dbname, String tbname);

	String getTableListSQL(String dbname);

	String getTableNameKey();

	String getColumnListSQL(String dbname, String tbname);

	String getColumnSQL(String dbname, String tbname, String colname);

	String getColumnNameKey();

	String getSelectTopOne(String tbname);

	/**
	 * 数据库字段类型 转 Java数据类型
	 * @param fieldType
	 * @return
	 */
	String convertTo(String fieldType);

	String convertForm(String valueType);

	/**
	 * 转换IField为SQL语句
	 * @param fldmodel
	 * @return
	 */
	String getFieldSQL(IField fldmodel);
	/**
	 * 转换 List<? extends IField>为SQL语句
	 * @param fldmodel
	 * @return
	 */
	String getFieldListSQL(List<? extends IField> tflv);
}
