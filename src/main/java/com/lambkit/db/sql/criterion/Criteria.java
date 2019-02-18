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
import com.lambkit.db.sql.criterion.junction.ConJunction;
import com.lambkit.db.sql.criterion.junction.Junction;
import com.lambkit.db.sql.criterion.order.Order;
import com.lambkit.db.sql.criterion.order.OrderBy;
import com.lambkit.db.sql.criterion.projection.Projection;

/**
 * 使用 Criterion 设置查询条件。
 * 使用Projection 设置Group查询条件。
 * 可以设置 FetchMode( 联合查询抓取的模式 ) 。
 * 可以设置排序方式。
 * 可以设置 FlushModel （冲刷 Session 的方式）和 LockMode （数据库锁模式）。
 * @author 孤竹行
 *
 */
public class Criteria {
	
	//表名称
	private String tableName;
	//别名
	private String alias;
	//表格模式，main or join
	private FetchMode fetchMode = FetchMode.MAIN;
	//查询条件
	private Junction junction;
	//排序
	private OrderBy orderby = null;
	//是否使用参数列表
	private boolean useParas = true;
	//join列表
	private List<Criteria> criteriaList;
	//
	private int firstResult = 0;
	//
	private int maxResult = 0;

	public Criteria(String tableName) {
		// TODO Auto-generated constructor stub
		setJunction(new ConJunction());
		this.tableName = tableName;
	}
	
	public Criteria(String tableName, String alias) {
		// TODO Auto-generated constructor stub
		setJunction(new ConJunction());
		this.tableName = tableName;
		this.alias = alias;
	}
	
	public void clear() {
		setAlias(null);
		junction = null;
		setJunction(new ConJunction());
		orderby = null;
	}
	
	public Criteria add(Criterion criterion) {
		junction.add(criterion);
		return this;
	}
	
	public Criteria join(Criteria criteria) {
		if(criteriaList==null) criteriaList = new ArrayList<>();
		criteria.setUseParas(isUseParas());
		criteriaList.add(criteria);
		alias = StrKit.isBlank(alias) ? tableName : alias;
		return this;
	}
	
	public Criteria setProjection(Projection prj) {
		return this;
	}
	
	public Criteria setProjection(List<Projection> prjList) {
		return this;
	}
	
	public Criteria addOrder(Order order) {
		if(orderby==null) orderby = new OrderBy();
		orderby.add(order);
		return this;
	}
	
	public SqlParas getSqlParas() {
		SqlParas sqlparas = new SqlParas();
		StringBuilder sb = new StringBuilder();
		switch (fetchMode) {
		case MAIN:
			sb.append("from ");
			break;
		case INNTER_JOIN:
			sb.append(" inner join ");
			break;
		case LEFT_JOIN:
			sb.append(" left join ");
			break;
		case RIGHT_JOIN:
			sb.append(" right join ");
			break;
		case FULL_JOIN:
			sb.append(" full join ");
			break;
		case CROSS_JOIN:
			sb.append(" cross join ");
			break;
		default:
			break;
		}
		sb.append(tableName);
		if(StrKit.notBlank(alias)) {
			sb.append(" as ").append(alias);
		}
		if(fetchMode==FetchMode.MAIN) {
			if(criteriaList != null) {
				for (Criteria criteria : criteriaList) {
					sb.append(criteria.getSqlParas().getSql());
					sqlparas.put(criteria.getSqlParas().getParaList());
				}
			}
			sb.append(" where ");
		} else {
			sb.append(" on ");
		}
		SqlParas juncSql = junction.getSqlParas(this);
		sb.append(juncSql.getSql());
		sqlparas.put(juncSql.getParaList());
		if(fetchMode==FetchMode.MAIN) if(orderby != null) sb.append(orderby.toSqlString(this));
		sqlparas.setSql(sb.toString());
		sqlparas.selectAll(this);
		return sqlparas;
	}
	
	public int joinSize() {
		return criteriaList!=null ? criteriaList.size() : 0;
	}
	/*******************************************************/

	public Junction getJunction() {
		return junction;
	}

	public Criteria setJunction(Junction junction) {
		this.junction = junction;
		return this;
	}
	
	public OrderBy getOrderby() {
		return orderby;
	}

	public Criteria setOrderby(OrderBy orderby) {
		this.orderby = orderby;
		return this;
	}

	public String getAlias() {
		return alias;
	}

	public Criteria setAlias(String alias) {
		this.alias = alias;
		return this;
	}

	public boolean isUseParas() {
		return useParas;
	}

	public Criteria setUseParas(boolean useParas) {
		this.useParas = useParas;
		if(criteriaList != null) {
			for (Criteria criteria : criteriaList) {
				criteria.setUseParas(useParas);
			}
		}
		return this;
	}

	public String getTableName() {
		return tableName;
	}

	public Criteria setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	public FetchMode getFetchMode() {
		return fetchMode;
	}

	public Criteria setFetchMode(FetchMode fetchMode) {
		this.fetchMode = fetchMode;
		return this;
	}
	
	public List<Criteria> getCriteriaList() {
		return criteriaList;
	}

	public Criteria setCriteriaList(List<Criteria> criteriaList) {
		this.criteriaList = criteriaList;
		return this;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public Criteria setFirstResult(int firstResult) {
		this.firstResult = firstResult;
		return this;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public Criteria setMaxResult(int maxResult) {
		this.maxResult = maxResult;
		return this;
	}

}
