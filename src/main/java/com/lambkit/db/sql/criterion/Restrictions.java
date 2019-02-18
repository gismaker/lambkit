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

import java.util.List;

import com.lambkit.db.sql.ConditionMode;
import com.lambkit.db.sql.criterion.expression.LogicalExpression;
import com.lambkit.db.sql.criterion.expression.NotExpression;
import com.lambkit.db.sql.criterion.expression.PropertyExpression;
import com.lambkit.db.sql.criterion.expression.QueryExpression;
import com.lambkit.db.sql.criterion.expression.SqlExpression;
import com.lambkit.db.sql.criterion.junction.ConJunction;
import com.lambkit.db.sql.criterion.junction.DisJunction;

public class Restrictions {

	public static Criteria createCriteria(String tableName) {
		Criteria criteria = new Criteria(tableName);
		return criteria;
	}
	
	public static Criteria createCriteria(String tableName, FetchMode fetchMode) {
		Criteria criteria = new Criteria(tableName);
		criteria.setFetchMode(fetchMode);
		if(fetchMode!=FetchMode.MAIN) criteria.setAlias(tableName);
		return criteria;
	}
	
	public static Criteria createCriteria(String tableName, String alias, FetchMode fetchMode) {
		Criteria criteria = new Criteria(tableName, alias);
		return criteria;
	}
	
	public static ConJunction conjunction() {
		return new ConJunction();
	}

	public static DisJunction disjunction() {
		return new DisJunction();
	}
	
	public static LogicalExpression and(Criterion lhs, Criterion rhs) {
		return new LogicalExpression(lhs, rhs, "and");
	}

	public static LogicalExpression or(Criterion lhs, Criterion rhs) {
		return new LogicalExpression(lhs, rhs, "or");
	}

	public static Criterion between(String propertyName, Object lo, Object hi) {
		return new QueryExpression(ConditionMode.BETWEEN, propertyName, lo, hi);
	}

	public static Criterion eq(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.EQUAL, propertyName, value);
	}

	public static PropertyExpression eqProperty(String propertyName, String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.EQUAL);
	}

	public static Criterion ge(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.GREATER_EQUAL, propertyName, value);
	}

	public static PropertyExpression geProperty(String propertyName, String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.GREATER_EQUAL);
	}

	public static Criterion gt(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.GREATER_THEN, propertyName, value);
	}

	public static PropertyExpression gtProperty(String propertyName, String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.GREATER_THEN);
	}

	public static Criterion ilike(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.IFUZZY, propertyName, value);
	}
	
	public static Criterion ilikeLeft(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.IFUZZY_LEFT, propertyName, value);
	}
	
	public static Criterion ilikeRight(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.IFUZZY_RIGHT, propertyName, value);
	}
	
	public static Criterion inotlike(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.NOT_IFUZZY, propertyName, value);
	}
	
	public static Criterion inotlikeLeft(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.NOT_IFUZZY_LEFT, propertyName, value);
	}
	
	public static Criterion inotlikeRight(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.NOT_IFUZZY_RIGHT, propertyName, value);
	}

	public static Criterion query(String propertyName, String value, ConditionMode type) {
		return new QueryExpression(type, propertyName, value);
	}

	public static Criterion in(String propertyName, List<?> values) {
		return new QueryExpression(ConditionMode.IN, propertyName, values);
	}
	
	public static Criterion in(String propertyName, Object[] values) {
		return new QueryExpression(ConditionMode.IN, propertyName, values);
	}

	public static Criterion isEmpty(String propertyName) {
		return new QueryExpression(ConditionMode.EMPTY, propertyName);
	}

	public static Criterion isNotEmpty(String propertyName) {
		return new QueryExpression(ConditionMode.NOT_EMPTY, propertyName);
	}

	public static Criterion isNotNull(String propertyName) {
		return new QueryExpression(ConditionMode.NOT_NULL, propertyName);
	}

	public static Criterion isNull(String propertyName) {
		return new QueryExpression(ConditionMode.ISNULL, propertyName);
	}

	public static Criterion le(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.LESS_EQUAL, propertyName, value);
	}

	public static PropertyExpression leProperty(String propertyName, String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.LESS_EQUAL);
	}

	public static Criterion like(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.FUZZY, propertyName, value);
	}
	
	public static Criterion notlike(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.NOT_FUZZY, propertyName, value);
	}

	public static Criterion lt(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.LESS_THEN, propertyName, value);
	}

	public static PropertyExpression ltProperty(String propertyName, String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.LESS_THEN);
	}

	public static Criterion ne(String propertyName, Object value) {
		return new QueryExpression(ConditionMode.NOT_EMPTY, propertyName, value);
	}

	public static PropertyExpression neProperty(String propertyName, String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.NOT_EQUAL);
	}

	public static Criterion not(Criterion expression) {
		return new NotExpression(expression);
	}

	public static Criterion sql(String sql) {
		return new SqlExpression(sql);
	}

	public static Criterion sql(String sql, Object[] values) {
		return new SqlExpression(sql, values);
	}

	public static Criterion sql(String sql, Object value) {
		return new SqlExpression(sql, value);
	}
}
