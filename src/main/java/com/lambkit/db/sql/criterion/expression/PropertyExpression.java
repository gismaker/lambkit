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

import java.io.Serializable;

import com.jfinal.kit.StrKit;
import com.lambkit.db.sql.ConditionMode;
import com.lambkit.db.sql.criterion.Criteria;
import com.lambkit.db.sql.criterion.Criterion;
import com.lambkit.db.sql.criterion.SqlParas;

public class PropertyExpression implements Criterion, Serializable {

	private static final long serialVersionUID = 1L;
	private ConditionMode type;
	private String propertyName;
	private String otherPropertyName;
	
	public PropertyExpression(String propertyName, String otherPropertyName, ConditionMode type) {
		// TODO Auto-generated constructor stub
		setPropertyName(propertyName);
		setOtherPropertyName(otherPropertyName);
		setType(type);
	}
	
	@Override
	public SqlParas getSqlParas(Criteria criteria) {
		// TODO Auto-generated method stub
		SqlParas csql = new SqlParas();
		StringBuilder sb = new StringBuilder();
		String alias = StrKit.notBlank(criteria.getAlias()) ? criteria.getAlias() + "." : "";
		if(!propertyName.contains(".")) sb.append(alias);
		sb.append(propertyName);
		switch (type) {
		case EQUAL:
			sb.append(" = ");
			break;
		case NOT_EQUAL:
			sb.append(" <> ");
			break;
		case GREATER_EQUAL:
			sb.append(" >= ");
			break;
		case GREATER_THEN:
			sb.append(" > ");
			break;
		case LESS_EQUAL:
			sb.append(" <= ");
			break;
		case LESS_THEN:
			sb.append(" < ");
			break;
		default:
			sb.append(" = ");
			break;
		}
		//sb.append(alias);
		sb.append(otherPropertyName);
		csql.setSql(sb.toString());
		return csql;
	}

	public ConditionMode getType() {
		return type;
	}

	public void setType(ConditionMode type) {
		this.type = type;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getOtherPropertyName() {
		return otherPropertyName;
	}

	public void setOtherPropertyName(String otherPropertyName) {
		this.otherPropertyName = otherPropertyName;
	}
}
