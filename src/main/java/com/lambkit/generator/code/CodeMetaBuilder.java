package com.lambkit.generator.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.DbKit;
import com.lambkit.db.meta.MetaBuilder;
import com.lambkit.db.meta.TableMeta;

public class CodeMetaBuilder {

	public List<CodeMeta> getCodeMeta(Kv options) {
		Map<String, TableMeta> tmap = getTableMetas(options);
		List<CodeMeta> codeMetas = new ArrayList<>();
		for (TableMeta tableMeta : tmap.values()) {
			CodeMeta codeMeta = new CodeMeta();
			codeMeta.setTable(tableMeta);
			codeMetas.add(codeMeta);
		}
		return codeMetas;
	}

	public Map<String, TableMeta> getTableMetas(Kv options) {
		Config config = DbKit.getConfig();// StrKit.notBlank(configName) ?
											// DbKit.getConfig(configName) :
											// DbKit.getConfig();
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
