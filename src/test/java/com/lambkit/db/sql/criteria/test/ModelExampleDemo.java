package com.lambkit.db.sql.criteria.test;

import com.lambkit.db.sql.criterion.BaseExample;
import com.lambkit.db.sql.criterion.Criteria;
import com.lambkit.db.sql.criterion.Restrictions;
import com.lambkit.db.sql.criterion.SqlParas;
import com.lambkit.db.sql.criterion.junction.Junction;

public class ModelExampleDemo extends BaseExample {
	
	private Junction junction;
	
	public ModelExampleDemo nameEq(Object value) {
		junction.add(Restrictions.eq("name", value));
		return this;
	}
	
	public ModelExampleDemo nameLike(Object value) {
		junction.add(Restrictions.like("name", value));
		return this;
	}

	@Override
	public SqlParas getSqlParas(Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
