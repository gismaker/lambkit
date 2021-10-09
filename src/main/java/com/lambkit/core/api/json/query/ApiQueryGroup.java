package com.lambkit.core.api.json.query;

import java.util.Map;

import com.beust.jcommander.internal.Maps;

public class ApiQueryGroup {

	private Map<String, ApiQuery> queryMap;
	
	public ApiQueryGroup() {
		// TODO Auto-generated constructor stub
		setQueryMap(Maps.newHashMap());
	}
	
	public void put(String name, ApiQuery query) {
		queryMap.put(name, query);
	}
	
	public ApiQuery getQuery(String name) {
		return queryMap.get(name);
	}

	public Map<String, ApiQuery> getQueryMap() {
		return queryMap;
	}

	public void setQueryMap(Map<String, ApiQuery> queryMap) {
		this.queryMap = queryMap;
	}
}
