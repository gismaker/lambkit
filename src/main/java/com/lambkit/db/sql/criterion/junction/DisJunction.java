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
package com.lambkit.db.sql.criterion.junction;

import com.lambkit.db.sql.criterion.Criteria;
import com.lambkit.db.sql.criterion.Criterion;
import com.lambkit.db.sql.criterion.SqlParas;

public class DisJunction extends Junction {

	private static final long serialVersionUID = 1L;

	public DisJunction() {
		// TODO Auto-generated constructor stub
		super("or");
	}
	
	public DisJunction add(Criterion criterion) {
		criterionList.add(criterion);
		return this;
	}

	@Override
	public SqlParas getSqlParas(Criteria criteria) {
		// TODO Auto-generated method stub
		SqlParas csql = new SqlParas();
		for (Criterion criterion : criterionList) {
			csql = csql.or(criteria, criterion);
		}
		return csql;
	}
}
