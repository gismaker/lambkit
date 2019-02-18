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
package com.lambkit.db.sql.criterion;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.criterion.junction.Junction;

public class SqlParas extends QueryParas {

	public SqlParas() {
	}
	
	public SqlParas(String sql) {
		// TODO Auto-generated constructor stub
		setSql(sql);
	}
	
	public SqlParas(String sql, List<Object> paraList) {
		// TODO Auto-generated constructor stub
		setSql(sql);
		setParaList(paraList);
	}
	
	public SqlParas(String sql, Object[] paraList) {
		// TODO Auto-generated constructor stub
		setSql(sql);
		setParaList(java.util.Arrays.asList(paraList));
	}
	
	public SqlParas(String sql, Object value) {
		// TODO Auto-generated constructor stub
		setSql(sql);
		setParaList(new ArrayList<>());
		add(value);
	}
	
	/********************************************************/
	
	public SqlParas and(Criteria criteria, Criterion criterion) {
		if(criterion==null) return this;
		StringBuilder sb = new StringBuilder();
		if(StrKit.notBlank(getSql())) sb.append(getSql());
		SqlParas csql = criterion.getSqlParas(criteria);
		if(StrKit.notBlank(csql.getSql())) {
			if(criterion instanceof Junction) {
				if(StrKit.notBlank(getSql())) sb.append(" and (");
				sb.append(csql.getSql());
				sb.append(") ");
			} else {
				if(StrKit.notBlank(getSql())) sb.append(" and ");
				sb.append(csql.getSql());
			}
			setSql(sb.toString());
			put(csql.getParas());
		}
		return this;
	}
	
	
	public SqlParas or(Criteria criteria, Criterion criterion) {
		if(criterion==null) return this;
		StringBuilder sb = new StringBuilder();
		SqlParas csql = criterion.getSqlParas(criteria);
		if(StrKit.notBlank(csql.getSql())) {
			if(criterion instanceof Junction) {
				if(StrKit.notBlank(getSql())) {
					sb.append(" (");
					sb.append(getSql());
					sb.append(")");
				}
				sb.append(" or (");
				sb.append(csql.getSql());
				sb.append(") ");
			} else {
				if(StrKit.notBlank(getSql())) {
					sb.append(getSql());
					sb.append(" or ");
				}
				sb.append(csql.getSql());
			}
			setSql(sb.toString());
			put(csql.getParas());
		}
		return this;
	}
	
	/*
	public CriterionSql and(CriterionSql criterion) {
		if(StrKit.notBlank(sql)) setSql(sql + " and " + criterion.getSql());
		else setSql(criterion.getSql());
		put(criterion.getParamList());
		return this;
	}
	
	public CriterionSql and(List<CriterionSql> criterionList) {
		for (CriterionSql criterion : criterionList) {
			and(criterion);
		}
		return this;
	}
	
	public CriterionSql or(CriterionSql criterion) {
		setSql("(" + sql + ") or " + criterion.getSql());
		put(criterion.getParamList());
		return this;
	}
	
	public CriterionSql or(List<CriterionSql> criterionList) {
		CriterionSql orCri = new CriterionSql();
		for (CriterionSql criterion : criterionList) {
			orCri.and(criterion);
		}
		setSql("(" + sql + ") or (" + orCri.getSql() + ")");
		put(orCri.getParamList());
		return this;
	}
	
	public void put(List<Object> paraList) {
		if(paramList!=null) {
			paramList.addAll(paraList);
		}
	}
	*/
	
	public SqlParas getSqlParas(Criteria criteria) {
		if(StrKit.notBlank(getSqlExceptSelect())) {
			if(StrKit.notBlank(criteria.getAlias())) {
				setSql(getSql().replace("{alias}", criteria.getAlias()));
			} else {
				setSql(getSql().replace("{alias}.", ""));
			}
		}
		return this;
	}
	
	public SqlParas selectAll(Criteria criteria) {
		int joinSize = criteria.joinSize();
		if(joinSize==0) {
			setSelect("select * ");
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("select").append(criteria.getAlias()).append(".*");
			for (Criteria crt : criteria.getCriteriaList()) {
				String _alias = crt.getAlias();
				_alias = StrKit.isBlank(_alias) ? crt.getTableName() : _alias;
				sb.append(", ").append(_alias).append(".*");
			}
			sb.append(" ");
			setSelect(sb.toString());
		}
		return this;
	}
	/********************************************************/
	
	public Integer getPageSize() {
		if(getParaList()==null) return 0;
		return getParaList().size();
	}
}
