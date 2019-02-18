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
import com.lambkit.db.sql.criterion.expression.PropertyExpression;
import com.lambkit.db.sql.criterion.expression.QueryExpression;

/**
 * Property实例是获得一个条件的另外一种途径。你可以通过调用Property.forName() 创建一个
Property。
Property age = Property.forName("age");
List cats = sess.createCriteria(Cat.class)
.add( Restrictions.disjunction()
.add( age.isNull() )
.add( age.eq( new Integer(0) ) )
.add( age.eq( new Integer(1) ) )
.add( age.eq( new Integer(2) ) )
) )
.add( Property.forName("name").in( new String[] { "Fritz", "Izi", "Pk" } ) )
.list();
 * @author 孤竹行
 *
 */
public class Property {
	
	private String propertyName;
	
	public Property(String name) {
		// TODO Auto-generated constructor stub
		this.propertyName = name;
	}
	
	public static Property forName(String name) {
		return new Property(name);
	}
	
	public Criterion between(Object lo, Object hi) {
		return new QueryExpression(ConditionMode.BETWEEN, propertyName, lo, hi);
	}

	public Criterion eq(Object value) {
		return new QueryExpression(ConditionMode.EQUAL, propertyName, value);
	}

	public PropertyExpression eqProperty(String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.EQUAL);
	}

	public Criterion ge(Object value) {
		return new QueryExpression(ConditionMode.GREATER_EQUAL, propertyName, value);
	}

	public PropertyExpression geProperty(String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.GREATER_EQUAL);
	}

	public Criterion gt(Object value) {
		return new QueryExpression(ConditionMode.GREATER_THEN, propertyName, value);
	}

	public PropertyExpression gtProperty(String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.GREATER_THEN);
	}

	public Criterion ilike(Object value) {
		return new QueryExpression(ConditionMode.IFUZZY, propertyName, value);
	}
	
	public Criterion ilikeLeft(Object value) {
		return new QueryExpression(ConditionMode.IFUZZY_LEFT, propertyName, value);
	}
	
	public Criterion ilikeRight(Object value) {
		return new QueryExpression(ConditionMode.IFUZZY_RIGHT, propertyName, value);
	}
	
	public Criterion inotlike(Object value) {
		return new QueryExpression(ConditionMode.NOT_IFUZZY, propertyName, value);
	}
	
	public Criterion inotlikeLeft(Object value) {
		return new QueryExpression(ConditionMode.NOT_IFUZZY_LEFT, propertyName, value);
	}
	
	public Criterion inotlikeRight(Object value) {
		return new QueryExpression(ConditionMode.NOT_IFUZZY_RIGHT, propertyName, value);
	}

	public Criterion query(String value, ConditionMode type) {
		return new QueryExpression(type, propertyName, value);
	}

	public Criterion in(List<?> values) {
		return new QueryExpression(ConditionMode.IN, propertyName, values);
	}
	
	public Criterion in(Object[] values) {
		return new QueryExpression(ConditionMode.IN, propertyName, values);
	}

	public Criterion isEmpty() {
		return new QueryExpression(ConditionMode.EMPTY, propertyName);
	}

	public Criterion isNotEmpty() {
		return new QueryExpression(ConditionMode.NOT_EMPTY, propertyName);
	}

	public Criterion isNotNull() {
		return new QueryExpression(ConditionMode.NOT_NULL, propertyName);
	}

	public Criterion isNull() {
		return new QueryExpression(ConditionMode.ISNULL, propertyName);
	}

	public Criterion le(Object value) {
		return new QueryExpression(ConditionMode.LESS_EQUAL, propertyName, value);
	}

	public PropertyExpression leProperty(String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.LESS_EQUAL);
	}

	public Criterion like(Object value) {
		return new QueryExpression(ConditionMode.FUZZY, propertyName, value);
	}
	
	public Criterion notlike(Object value) {
		return new QueryExpression(ConditionMode.NOT_FUZZY, propertyName, value);
	}

	public Criterion lt(Object value) {
		return new QueryExpression(ConditionMode.LESS_THEN, propertyName, value);
	}

	public PropertyExpression ltProperty(String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.LESS_THEN);
	}

	public Criterion ne(Object value) {
		return new QueryExpression(ConditionMode.NOT_EMPTY, propertyName, value);
	}

	public PropertyExpression neProperty(String otherPropertyName) {
		return new PropertyExpression(propertyName, otherPropertyName, ConditionMode.NOT_EQUAL);
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

}
