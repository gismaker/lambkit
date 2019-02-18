package com.lambkit.generator.test;

import java.util.Map;

import com.beust.jcommander.internal.Maps;
import com.lambkit.Lambkit;
import com.lambkit.generator.GeneratorType;
import com.lambkit.generator.GeneratorManager;
import com.lambkit.generator.IGenerator;
import com.lambkit.server.LambkitServer;

public class GeneratorTest {

	public static void main(String[] args) {
		Lambkit.setBootArg("lambkit.server.type", "applicaiton");
		Lambkit.setBootArg("lambkit.generator.outRootDir", "D:/lambkit-workspace/workspace/Lambkit-opensource/lambkit/src/main/java");
		Lambkit.setBootArg("lambkit.generator.basepackage", "com.lambkit.module.meta");
		Lambkit.setBootArg("lambkit.generator.webpages", "app");
		Lambkit.setBootArg("lambkit.generator.mgrdb", "normal");
		Lambkit.setBootArg("lambkit.generator.processer", "db");
		LambkitServer server = Lambkit.me().getServer();
		GeneratorManager.me().init();
		server.start();
		
		String templatePath = "/template";
		Map<String,Object> options = Maps.newHashMap();
		//options.put("tableRemovePrefixes", "ucenter_");
		//options.put("includedTables", "upms_favorites, upms_log, upms_organization, upms_permission, upms_role, upms_role_permission, upms_system, upms_tag, upms_user, upms_user_organization, upms_user_permission, upms_user_role");
		//options.put("includedTables", "meta_table, meta_field, meta_api, meta_app, meta_field_dimession, meta_field_edit, meta_field_list, meta_field_map, meta_field_measure, meta_field_relation, meta_file, meta_file_catalog, meta_file_catalog_mapping, meta_image, meta_image_set, meta_store, meta_store_db, meta_store_resource, meta_store_route, meta_theme");
		//options.put("includedTables", "meta_api, meta_app, meta_field_dimession, meta_field_edit, meta_field_list, meta_field_map, meta_field_measure, meta_field_relation, meta_file, meta_file_catalog, meta_file_catalog_mapping, meta_image, meta_image_set, meta_store, meta_store_db, meta_store_resource, meta_store_route, meta_theme");
		options.put("includedTables", "meta_field_dimession, meta_field_measure, meta_field_relation");
		
		//options.put("includedTables", "meta_image, meta_store_image_set, meta_store_route");
		IGenerator g = GeneratorManager.me().createGenerator(GeneratorType.DB);
		//GeneratorConfig config = new GeneratorConfig();
		//Generator g = GeneratorManager.me().createGenerator(config);
		g.generate(GeneratorManager.me().getGeneratorContext(), templatePath, options);
		System.out.println("-------over-------");
		
		server.stop();
	}
}
