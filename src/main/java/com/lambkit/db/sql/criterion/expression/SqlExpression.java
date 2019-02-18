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
package com.lambkit.db.sql.criterion.expression;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.lambkit.db.sql.criterion.Criteria;
import com.lambkit.db.sql.criterion.Criterion;
import com.lambkit.db.sql.criterion.SqlParas;

public class SqlExpression implements Criterion {

	private String sql;
	private List<Object> paras;
	
	public SqlExpression() {
		// TODO Auto-generated constructor stub
		this.sql = null;
		this.paras = null;
	}
	
	public SqlExpression(String sql) {
		// TODO Auto-generated constructor stub
		this.sql = sql;
		this.paras = null;
	}
	
	public SqlExpression(String sql, List<Object> paras) {
		// TODO Auto-generated constructor stub
		this.sql = sql;
		this.paras = paras;
	}
	
	public SqlExpression(String sql, Object[] paras) {
		// TODO Auto-generated constructor stub
		this.sql = sql;
		this.paras = java.util.Arrays.asList(paras);
	}
	
	public SqlExpression(String sql, Object value) {
		// TODO Auto-generated constructor stub
		this.sql = sql;
		this.paras = new ArrayList<>();
		add(value);
	}
	
	public void add(Object value) {
		if(paras==null){
			paras = new ArrayList<>();
		}
		paras.add(value);
	}
	
	public void put(List<Object> paras) {
		if(paras!=null) {
			paras.addAll(paras);
		} else {
			this.paras = paras;
		}
	}
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<Object> getParas() {
		return paras;
	}
	public void setParas(List<Object> paras) {
		this.paras = paras;
	}

	@Override
	public SqlParas getSqlParas(Criteria criteria) {
		// TODO Auto-generated method stub
		if(StrKit.notBlank(sql)) {
			if(StrKit.notBlank(criteria.getAlias())) {
				sql = sql.replace("{alias}", criteria.getAlias());
			} else {
				sql = sql.replace("{alias}.", "");
			}
		}
		return new SqlParas(sql, paras);
	}

}
