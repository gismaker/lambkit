package com.lambkit.core.api.json;

import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.StrKit;
import com.lambkit.core.api.json.query.ApiQuery;
import com.lambkit.db.sql.condition.ConditionGroup;

/**
 * JSON 解析
 * @author yangyong
 *
 */
public class ApiJson {
	
	/**
	 * SQL相关的关键字
	 */
	public static final String PREFIX_SQL = "$";
	/**
	 * 用户自定义返回数据的key
	 */
	public static final String PREFIX_KEY = "#";
	/**
	 * 关联的SQL或其他关联内容
	 */
	public static final String PREFIX_AT = "@";

	public ApiQuery parse(String json) {
		JSONObject jso = JSONObject.parseObject(json);
		return parse(jso);
	}
	
	public ApiQuery parse(JSONObject jso) {
		if(jso==null) return null;
		Set<String> keys = jso.keySet();
		for (String key : keys) {
			if(StrKit.isBlank(key)) continue;
			if(key.startsWith(PREFIX_SQL)) {
				//SQL语句查询
			} else if(key.startsWith(PREFIX_KEY)) {
				//自定义数据查询
			} else if(key.startsWith(PREFIX_AT)) {
				//后台现有SQL或服务查询
			} else {
				//数据查询
				ApiQuery query = new ApiQuery();
				query.setName(key);
				query.parse(jso.getJSONObject(key));
			}
		}
		return null;
	}
	
	/**
	 * 分页查询
	 */
	protected ApiQuery page() {
		return null;
	}
	
	protected ApiQuery list() {
		return null;
	}
	
	
	protected ApiQuery join() {
		return null;
	}
	
	protected ConditionGroup or() {
		ConditionGroup group = new ConditionGroup();
		
		return group;
	}
	
	protected ConditionGroup and() {
		ConditionGroup group = new ConditionGroup();
		
		return group;
	}
	
	
}
