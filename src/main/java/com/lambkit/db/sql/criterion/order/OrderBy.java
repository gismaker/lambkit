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
package com.lambkit.db.sql.criterion.order;

import java.util.ArrayList;
import java.util.List;

import com.lambkit.db.sql.criterion.Criteria;

public class OrderBy {

	private List<Order> orderList;
	
	public OrderBy() {
		// TODO Auto-generated constructor stub
		orderList = new ArrayList<>();
	}
	
	public String toSqlString(Criteria criteria) {
		StringBuilder sb = new StringBuilder();
		sb.append(" order by ");
		int i=0;
		for (Order orderItem : orderList) {
			if(i>0) sb.append(",");
			sb.append(orderItem.toSqlString(criteria));
			i++;
		}
		return sb.toString();
	}
	
	public void add(Order order) {
		orderList.add(order);
	}
	
	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
}
