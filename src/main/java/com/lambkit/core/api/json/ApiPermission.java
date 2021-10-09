package com.lambkit.core.api.json;

/**
 * 数据接口权限框架
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.api.json
 */
public interface ApiPermission {

	boolean hasDataRule(String dataName);
	
	boolean hasDataColumnRule(String dataName, String column);
}
