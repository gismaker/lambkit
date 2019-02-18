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

import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.lambkit.db.sql.IQuery;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.condition.ConditionBuilder;

public class ConditionQuery implements IQuery {
	
	private String baseTable = null;
	private String select = "select * ";
	private ConditionBuilder conditions = null;
	private List<SqlJoinBuilder> joins = new ArrayList<SqlJoinBuilder>();
	private Integer pageNumber = 1;
	private Integer pageSize = 15;
	private String orderBy = null;
	
	@Override
	public QueryParas toQueryParas() {
		SqlBuilder sb = new SqlBuilder();
		if(!StrKit.notBlank(select)) select = sb.appendSelectAll().build();
		sb.clear().append(" from ").append(baseTable);
		conditions.build(baseTable+".");
		SqlBuilder sbwhere = new SqlBuilder();
		sbwhere.append(" where 1=1 ").appendConditions(conditions);
		List<Object> paras = conditions.getParamList();
		//System.out.println(paras);
		for (SqlJoinBuilder sjb : joins) {
			sb.append(sjb.build());
			sbwhere.appendConditions(sjb.getConditions());
			List<Object> sjbParas = sjb.getConditions().getParamList();
			if(sjbParas!=null) paras.addAll(sjbParas);
		}
		String sql = sb.append(sbwhere.build()).appendOrderBy(orderBy).build();
		QueryParas qp = new QueryParas();
		qp.setSelect(select);
		qp.setSqlExceptSelect(sql);
		qp.setParaList(paras);
		qp.setPageNumber(pageNumber);
		qp.setPageSize(pageSize);
		
		return qp;
	}
	
	/*
	public Page<Record> paginate() {
		SqlBuilder sb = new SqlBuilder();
		if(!StrKit.notBlank(select)) select = sb.appendSelectAll().build();
		sb.clear().append(" from ").append(baseTable);
		conditions.build(baseTable+".");
		SqlBuilder sbwhere = new SqlBuilder();
		sbwhere.append(" where 1=1 ").appendConditions(conditions);
		Object[] paras = conditions.getSqlParas();
		for (SqlJoinBuilder sjb : joins) {
			sb.append(sjb.build());
			sbwhere.appendConditions(sjb.getConditions());
			paras = CommonUtils.concat(paras, sjb.getSqlParas());
		}
		String sql = sb.append(sbwhere.build()).build();
		return Db.paginate(pageNumber, pageSize, select, sql, paras);
	}
	
	public List<Record> list() {
		SqlBuilder sb = new SqlBuilder();
		if(!StrKit.notBlank(select)) select = sb.appendSelectAll().build();
		sb.clear().append(" from ").append(baseTable);
		conditions.build(baseTable+".");
		SqlBuilder sbwhere = new SqlBuilder();
		sbwhere.append(" where 1=1 ").appendConditions(conditions);
		Object[] paras = conditions.getSqlParas();
		for (SqlJoinBuilder sjb : joins) {
			sb.append(sjb.build());
			sbwhere.appendConditions(sjb.getConditions());
			paras = CommonUtils.concat(paras, sjb.getSqlParas());
		}
		String sql = sb.append(sbwhere.build()).build();
		return Db.find(select + sql, paras);
	}
	
	public Page<Record> joinlist(String basetb, ConditionsBuilder cb, String select, List<SqlJoinBuilder> joins, Integer pNumber, Integer pSize) {
		SqlBuilder sb = new SqlBuilder();
		if(!StrKit.notBlank(select)) select = sb.appendSelectAll().build();
		sb.clear().append(" from ").append(basetb);
		cb.build(basetb+".");
		SqlBuilder sbwhere = new SqlBuilder();
		sbwhere.append(" where 1=1 ").appendConditions(cb);
		Object[] paras = cb.getSqlParas();
		for (SqlJoinBuilder sjb : joins) {
			sb.append(sjb.build());
			sbwhere.appendConditions(sjb.getConditions());
			paras = CommonUtils.concat(paras, sjb.getSqlParas());
		}
		String sql = sb.append(sbwhere.build()).build();
		return Db.paginate(pNumber, pSize, select, sql, paras);
	}
	*/
	
	public void addSqlJoin(SqlJoinBuilder sqljoin) {
		joins.add(sqljoin);
	}
	
	//---------------------------------------------

	public String getBaseTable() {
		return baseTable;
	}
	public void setBaseTable(String baseTable) {
		this.baseTable = baseTable;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public ConditionBuilder getConditions() {
		return conditions;
	}
	public void setConditions(ConditionBuilder conditions) {
		this.conditions = conditions;
	}
	public List<SqlJoinBuilder> getJoins() {
		return joins;
	}
	public void setJoins(List<SqlJoinBuilder> joins) {
		this.joins = joins;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
