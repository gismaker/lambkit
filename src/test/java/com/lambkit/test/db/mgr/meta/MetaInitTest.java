package com.lambkit.test.db.mgr.meta;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.beust.jcommander.internal.Maps;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.DbKit;
import com.lambkit.Lambkit;
import com.lambkit.common.app.DefaultApplication;
import com.lambkit.common.app.LambkitApplication;
import com.lambkit.db.meta.MetaBuilder;
import com.lambkit.db.meta.TableMeta;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.db.mgr.MgrdbService;
import com.lambkit.module.meta.MetaMgrModule;

/**
 * Meta表格配置初始化
 * @author yangyong
 *
 */
public class MetaInitTest {
	public static void main(String[] args) {
		Lambkit.setBootArg("lambkit.server.type", "applicaiton");
		LambkitApplication server = new DefaultApplication();
		Lambkit.me().addModule(new MetaMgrModule());
		server.run();

		MetaInitTest metaHelp = new MetaInitTest();
		MgrdbService service = MgrdbManager.me().getService();

		Map<String, Object> options = Maps.newHashMap();
		//标题中需要去掉的前缀
		options.put("tableRemovePrefixes", "cms_");
		//不包含的表格
		options.put("excludedTables", "meta_correlation, bak_user,sys_fieldconfig, sys_tableconfig");
		/*
		//仅包含的表格
		options.put("includedTables",
				"meta_app, meta_app_api, meta_app_store, meta_correlation, meta_doc, meta_field, meta_field_edit, meta_field_join, meta_field_list, meta_field_map, meta_field_olap, meta_map, meta_resource_level, meta_store_db, meta_store_resource, meta_table, meta_theme");
		*/
		Map<String, TableMeta> tableMetas = metaHelp.getTableMetas(options);
		for (Entry<String, TableMeta> entry : tableMetas.entrySet()) {
			System.out.println("table: "+entry.getKey());
			service.tableToMgrdb(entry.getValue());
        }
		System.out.println("-------over-------");

		server.stop();
	}

	public Map<String, TableMeta> getTableMetas(Map<String, Object> options) {
		Config config = DbKit.getConfig();
		MetaBuilder metaBuilder = new MetaBuilder(config.getDataSource());
		metaBuilder.setDialect(config.getDialect());
		if (options.containsKey("tableRemovePrefixes")) {
			Object trp = options.get("tableRemovePrefixes");
			if (trp instanceof List) {
				List<String> tableRemovePrefixes = (List<String>) trp;
				metaBuilder.setRemovedTableNamePrefixes((String[]) tableRemovePrefixes.toArray());
			} else {
				String tableRemovePrefixes = trp.toString();
				metaBuilder.setRemovedTableNamePrefixes(tableRemovePrefixes.split(","));
			}
		}
		if (options.containsKey("excludedTables")) {
			Object eto = options.get("excludedTables");
			if (eto instanceof List) {
				List<String> excludedTables = (List<String>) eto;
				metaBuilder.addExcludedTable((String[]) excludedTables.toArray());
			} else {
				String excludedTables = eto.toString();
				metaBuilder.addExcludedTable(excludedTables.split(","));
			}
		}
		if (options.containsKey("includedTables")) {
			Object eto = options.get("includedTables");
			if (eto instanceof List) {
				List<String> includedTables = (List<String>) eto;
				metaBuilder.addIncludedTable((String[]) includedTables.toArray());
			} else {
				String includedTables = eto.toString();
				metaBuilder.addIncludedTable(includedTables.split(","));
			}
		}
		return metaBuilder.build();
	}
	
}
