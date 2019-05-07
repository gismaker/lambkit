package com.lambkit.test.db.sql;

import java.util.HashMap;
import java.util.Map;

import com.lambkit.db.sql.condition.Conditions;

public class ConditionsTest {
	public static void main(String[] args) {
		Conditions conditions = new Conditions();
		conditions.setFiledQuery(Conditions.FUZZY, "test");
		conditions.setFiledQuery(Conditions.EMPTY, "id");
		conditions.setFiledQuery(Conditions.FUZZY_RIGHT, "name");

		// conditions.setExcludeField("id");
		// 所有的字段
		String[] fieldNames = { "id", "test", "name" };
		// 字段名和值的map集合
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("id", "");
		valueMap.put("test", "yangyong");
		valueMap.put("name", "lamb");
		// 构建查询条件
		conditions.buildCondition("a.", fieldNames, valueMap);

		// conditions.setFiledQuery(Conditions.FUZZY,
		// BizUser.COL_NAME,BizUser.COL_PHONE);
		// System.out.println(conditions.getSql());
		// conditions=new Conditions();
		// conditions.modelToCondition(BizUser.dao.searchFirst(BizUser.COL_LIVEFLAG,
		// 1));
		// conditions.modelToCondition(new BizUser());
		// System.out.println(conditions.getSql());
		// System.out.println(conditions.getParamList());

		// Conditions conditions=new Conditions().setExcludeField("id");
		// /*当setValueQuery中字段和BizUser对象中的字段重叠时，以setValueQuery为准*/
		// conditions.setFiledQuery(Conditions.FUZZY_LEFT, "year","wissue");
		// conditions.modelToCondition(new
		// WatersectionModel().set("year","2014").set("wissue", "17"));
		//
		System.out.println(conditions.getSql());
		System.out.println(conditions.getParamList());
	}
}
