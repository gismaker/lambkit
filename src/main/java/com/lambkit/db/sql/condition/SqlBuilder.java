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

import java.util.List;

import com.jfinal.kit.StrKit;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.db.dialect.LambkitDialect;
import com.lambkit.db.mgr.MgrTable;

public class SqlBuilder {

	protected StringBuilder strBuilder;
	
	public SqlBuilder() {
		this.strBuilder = new StringBuilder();
	}
	
	public String build() {
		return strBuilder.toString();
	}
	
	public SqlBuilder append(String str) {
		strBuilder.append(str);
		return this;
	} 
	
	public SqlBuilder appendSelectAll() {
		strBuilder.append("select * ");
		return this;
	}
	
	public SqlBuilder appendSelect(MgrTable tbc) {
		strBuilder.append("select ");
		strBuilder.append(MgrdbManager.me().getService().getSelectNamesOfView(tbc, ""));
		return this;
	}
	
//	public SqlBuilder appendSelect(MgrTable tbc, LambkitDialect dialect) {
//		strBuilder.append("select ");
//		tbc.setDialect(dialect);
//		strBuilder.append(MgrdbManager.me().getService().getSelectNamesOfView(tbc, ""));
//		return this;
//	}
//	
//	public SqlBuilder appendSelect(MgrTable tbc, String alis, LambkitDialect dialect) {
//		strBuilder.append("select ");
//		tbc.setDialect(dialect);
//		strBuilder.append(MgrdbManager.me().getService().getSelectNamesOfView(tbc, alis));
//		return this;
//	}
	
	public SqlBuilder appendSelect(MgrTable tbc, String alis) {
		strBuilder.append("select ");
		strBuilder.append(MgrdbManager.me().getService().getSelectNamesOfView(tbc, alis));
		return this;
	}
	
	public SqlBuilder appendForSelect(MgrTable tbc, String alis) {
		strBuilder.append(MgrdbManager.me().getService().getSelectNamesOfView(tbc, alis));
		return this;
	}
	
	public SqlBuilder appendFrom(MgrTable tbc) {
		strBuilder.append(" from ");
		strBuilder.append(tbc.getName());
		strBuilder.append(" where 1=1 ");
		return this;
	}
	
	public SqlBuilder appendFromOnly(MgrTable tbc) {
		strBuilder.append(" from ");
		strBuilder.append(tbc.getName());
		return this;
	}
	
	public SqlBuilder appendFromTable(MgrTable tbc, String alis) {
		strBuilder.append(tbc.getName());
		strBuilder.append(" ");
		strBuilder.append(alis);
		return this;
	}
	
	public SqlBuilder appendFrom(String tbname) {
		strBuilder.append(" from ");
		strBuilder.append(tbname);
		strBuilder.append(" where 1=1 ");
		return this;
	}
	
	public SqlBuilder appendConditions(ConditionBuilder cb) {
		if(cb!=null && cb.hasSql()) strBuilder.append(cb.getSql());
		return this;
	}
	
	public SqlBuilder appendOrderBy(String orderby) { 
		if(StrKit.notBlank(orderby)) {
			strBuilder.append(" ORDER BY ");
			strBuilder.append(orderby);
		}
		return this;
	}
	
	public SqlBuilder clear() {
		strBuilder.delete(0, strBuilder.length());
		return this;
	}
	
	public boolean appendWhereOrAnd(boolean needWhere) {
		if (needWhere) {
			strBuilder.append(" WHERE ");
		} else {
			strBuilder.append(" AND ");
		}
		return false;
	}

	public boolean appendIfNotEmpty(String colName, String value, List<Object> params,
			boolean needWhere) {
		if (value != null) {
			needWhere = appendWhereOrAnd(needWhere);
			strBuilder.append(" ").append(colName).append(" = ? ");
			params.add(value);
		}
		return needWhere;
	}

	public boolean appendIfNotEmpty(String colName, Long value,
			List<Object> params, boolean needWhere) {
		if (value != null) {
			needWhere = appendWhereOrAnd(needWhere);
			strBuilder.append(" ").append(colName).append(" = ? ");
			params.add(value);
		}
		return needWhere;
	}

	public boolean appendIfNotEmpty(String colName, Object[] array,
			List<Object> params, boolean needWhere) {
		if (null != array && array.length > 0) {
			needWhere = appendWhereOrAnd(needWhere);
			strBuilder.append(" (");
			for (int i = 0; i < array.length; i++) {
				if (i == 0) {
					strBuilder.append(" ").append(colName).append(" = ? ");
				} else {
					strBuilder.append(" OR ").append(colName).append(" = ? ");
				}
				params.add(array[i]);
			}
			strBuilder.append(" ) ");
		}
		return needWhere;
	}

	public boolean appendIfNotEmptyWithLike(String colName, String value,
			List<Object> params, boolean needWhere) {
		if (StrKit.notBlank(value)) {
			needWhere = appendWhereOrAnd(needWhere);
			strBuilder.append(" ").append(colName).append(" like ? ");
			if (value.contains("%")) {
				params.add(value);
			} else {
				params.add("%" + value + "%");
			}

		}
		return needWhere;
	}

	public boolean appendIfNotEmptyWithLike(String colName, String[] array,
			List<Object> params, boolean needWhere) {
		if (null != array && array.length > 0) {
			needWhere = appendWhereOrAnd(needWhere);
			strBuilder.append(" (");
			for (int i = 0; i < array.length; i++) {
				if (i == 0) {
					strBuilder.append(" ").append(colName).append(" like ? ");
				} else {
					strBuilder.append(" OR ").append(colName).append(" like ? ");
				}
				String value = array[i];
				if (value.contains("%")) {
					params.add(value);
				} else {
					params.add("%" + value + "%");
				}
			}
			strBuilder.append(" ) ");
		}
		return needWhere;
	}
	
}
