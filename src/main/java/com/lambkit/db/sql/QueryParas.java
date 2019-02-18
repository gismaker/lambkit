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

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.SqlPara;

public class QueryParas {
	
	private List<Object> paraList;
	private String select = null;
	private String sqlExceptSelect = null;
	private Integer pageNumber = 1;
	private Integer pageSize = 15;
	
	public QueryParas() {
		// TODO Auto-generated constructor stub
	}
	
	public static QueryParas create(SqlPara para) {
		QueryParas queryParas = new QueryParas();
		if(para!=null) {
			queryParas.setSql(para.getSql());
			queryParas.put(para.getPara());
		}
		return queryParas;
	}
	
	public static QueryParas create(String sql) {
		QueryParas queryParas = new QueryParas();
		queryParas.setSql(sql);
		return queryParas;
	}
	
	public static QueryParas create(String sql, List<Object> paras) {
		QueryParas queryParas = new QueryParas();
		queryParas.setSql(sql);
		queryParas.setParaList(paras);
		return queryParas;
	}
	
	public SqlPara toSqlPara() {
		SqlPara sqlPara = new SqlPara();
		sqlPara.setSql(getSql());
		if(paraList!=null) {
			for (int i = 0; i < paraList.size(); i++) {
				sqlPara.addPara(paraList.get(i));
			}
		}
		return sqlPara;
	}
	
	public QueryParas add(Object paras) {
		if(paraList!=null) {
			paraList.add(paras);
		}
		return this;
	}
	
	public QueryParas put(List<Object> paras) {
		if(paraList!=null) {
			paraList.addAll(paras);
		}
		return this;
	}
	
	public QueryParas put(Object[] paras) {
		if(paraList!=null) {
			for (Object obj : paras) {
				paraList.add(obj);
			}
		}
		return this;
	}
	
	public QueryParas setSql(String sql) {
		if(StrKit.isBlank(sql)) return this;
		String usql = sql.toUpperCase();
		int i = usql.indexOf("FROM");
		if(i>0) {
			select = sql.substring(0, i);
			sqlExceptSelect = sql.substring(i);
		}
		return this;
	}
	
	public String getSql() {
		if(StrKit.isBlank(getSelect())) return null;
		if(StrKit.isBlank(getSqlExceptSelect())) return null;
		return getSelect() + getSqlExceptSelect();
	}
	
	public Object[] getParas() {
		//System.out.println(paraList);
		if(paraList!=null) {
			return paraList.toArray();
		}
		return null;
	}
	
	public String getSelect() {
		return select;
	}
	public QueryParas setSelect(String select) {
		this.select = select;
		return this;
	}
	public String getSqlExceptSelect() {
		return sqlExceptSelect;
	}
	public QueryParas setSqlExceptSelect(String sqlExceptSelect) {
		this.sqlExceptSelect = sqlExceptSelect;
		return this;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public QueryParas setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		return this;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public QueryParas setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}
	public List<Object> getParaList() {
		return paraList;
	}
	public QueryParas setParaList(List<Object> paraList) {
		this.paraList = paraList;
		return this;
	}
}
