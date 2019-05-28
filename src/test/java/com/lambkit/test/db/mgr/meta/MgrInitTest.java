package com.lambkit.test.db.mgr.meta;

import java.util.Map;

import com.beust.jcommander.internal.Maps;
import com.lambkit.db.mgr.MgrdbConfig;
import com.lambkit.db.mgr.MgrdbManager;

/**
 * Sysconfig表格配置初始化
 * @author yangyong
 */
public class MgrInitTest {
	public static void main(String[] args) {
		Map<String, Object> options = Maps.newHashMap();
		//标题中需要去掉的前缀
		options.put("tableRemovePrefixes", "cms_");
		//不包含如下数据表
		//options.put("excludedTables", "sys_fieldconfig, sys_tableconfig");
		//仅包含如下数据表
		options.put("includedTables",
				"meta_api, meta_app, meta_field, meta_field_dimession, meta_field_edit, meta_field_list, meta_field_map, meta_field_measure, meta_field_relation, meta_file, meta_file_catalog, meta_file_catalog_mapping, meta_image, meta_image_set, meta_store, meta_store_db, meta_store_resource, meta_store_route, meta_table, meta_theme");
		MgrdbManager.me().run(options, MgrdbConfig.ALL);
		System.exit(0);
	}
}
