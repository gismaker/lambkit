package com.lambkit.core.api.json.query;

import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.StrKit;
import com.lambkit.core.api.json.ApiJson;

/**
 * API查询条件
 * @author yangyong
 *
 */
public class ApiQuery {

	/**
	 * 本数据的id
	 */
	private String id;
	
	/**
	 * 数据名称
	 */
	private String name;
	
	/**
	 * 
	 */
	private ApiQueryGroup queryGroup;
	
	private int page = 0;
	
	private int count = 0;
	
	private List<ApiQueryColumn> columns;
	
	private List<ApiQueryOrder> orders;
	
	
	public void addQuery(ApiQuery query) {
		if(query==null || StrKit.isBlank(query.getName())) return;
		if(queryGroup==null) {
			queryGroup = new ApiQueryGroup();
		}
		queryGroup.put(query.getName(), query);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ApiQueryGroup getQueryGroup() {
		return queryGroup;
	}

	public void setQueryGroup(ApiQueryGroup queryGroup) {
		this.queryGroup = queryGroup;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * 加入字段配置
	 * @param columns
	 */
	public void addColumns(String columns) {
		
	}

	public List<ApiQueryColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<ApiQueryColumn> columns) {
		this.columns = columns;
	}
	
	/**
	 * 加入排序配置
	 * @param string
	 */
	public void addOrders(String orders) {
		// TODO Auto-generated method stub
		
	}

	public List<ApiQueryOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ApiQueryOrder> orders) {
		this.orders = orders;
	}

	public ApiQuery parse(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		if(jsonObject==null) return this;
		Set<String> keys = jsonObject.keySet();
		for (String key : keys) {
			if(StrKit.isBlank(key)) continue;
			if(key.startsWith(ApiJson.PREFIX_SQL)) {
				parseSQL(key, jsonObject);
			} else if(key.startsWith(ApiJson.PREFIX_KEY)) {
				//子查询
				ApiQuery query = new ApiQuery();
				query.setName(key);
				query.parse(jsonObject.getJSONObject(key));
				addQuery(query);
			} else if(key.startsWith(ApiJson.PREFIX_AT)) {
				//关联SQL
			} else {
				//字段条件
			}
		}
		return this;
	}

	/**
	 * 处理SQL配置
	 * @param key
	 * @param jsonObject
	 */
	private void parseSQL(String key, JSONObject jsonObject) {
		// TODO Auto-generated method stub
		switch (key) {
		case "count":
			this.count = jsonObject.getIntValue(key);
			break;
		case "page":
			this.page = jsonObject.getIntValue(key);
			break;
		case "$column":
			addColumns(jsonObject.getString(key));
			break;
		case "$order":
			addOrders(jsonObject.getString(key));
			break;
		case "$filter":
			parseFilter(jsonObject.getJSONObject(key));
			break;
		default:
			break;
		}
	}

	private void parseFilter(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
	}

}
