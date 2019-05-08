package com.lambkit.test.db.mgr.meta;

import java.util.Map;

import com.beust.jcommander.internal.Maps;
import com.lambkit.db.mgr.MgrdbConfig;
import com.lambkit.db.mgr.MgrdbManager;

/**
 * Meta表格配置初始化
 * @author yangyong
 *
 */
public class MetaInitTest {
	public static void main(String[] args) {
		Map<String, Object> options = Maps.newHashMap();
		//标题中需要去掉的前缀
		options.put("tableRemovePrefixes", "meta_");
		//不包含如下数据表
		options.put("excludedTables", "meta_correlation, bak_user,sys_fieldconfig, sys_tableconfig");
		//仅包含如下数据表
		//options.put("includedTables", "meta_app, meta_app_api, meta_app_store, meta_correlation, meta_doc, meta_field, meta_field_edit, meta_field_join, meta_field_list, meta_field_map, meta_field_olap, meta_map, meta_resource_level, meta_store_db, meta_store_resource, meta_table, meta_theme");
		MgrdbManager.me().run(options, MgrdbConfig.META);
	}
}
