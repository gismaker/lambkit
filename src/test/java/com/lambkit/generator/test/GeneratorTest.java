package com.lambkit.generator.test;

import java.util.Map;

import com.beust.jcommander.internal.Maps;
import com.lambkit.Lambkit;
import com.lambkit.common.app.DefaultApplication;
import com.lambkit.common.app.LambkitApplication;
import com.lambkit.generator.GeneratorType;
import com.lambkit.generator.GeneratorManager;
import com.lambkit.generator.IGenerator;

public class GeneratorTest {

	public static void main(String[] args) {
		Lambkit.setBootArg("lambkit.server.type", "applicaiton");
		//生成java代码的存放地址
		Lambkit.setBootArg("lambkit.generator.outRootDir", "D:/lambkit-workspace/workspace/Lambkit-opensource/lambkit/src/main/java");
		//生成java代码的包地址
		Lambkit.setBootArg("lambkit.generator.basepackage", "com.lambkit.module.meta");
		//生成前端文件文件夹
		Lambkit.setBootArg("lambkit.generator.webpages", "app");
		//表格配置方式
		Lambkit.setBootArg("lambkit.generator.mgrdb", "normal");
		//处理引擎
		Lambkit.setBootArg("lambkit.generator.processer", "db");
		LambkitApplication server = new DefaultApplication();
		//初始化生成工具
		GeneratorManager.me().init();
		server.start();
		
		//模板地址，根目录是项目文件夹
		String templatePath = "/template";
		Map<String,Object> options = Maps.newHashMap();
		//需要去掉的前缀
		//options.put("tableRemovePrefixes", "ucenter_");
		//仅包含如下表格
		//options.put("includedTables", "upms_favorites, upms_log, upms_organization, upms_permission, upms_role, upms_role_permission, upms_system, upms_tag, upms_user, upms_user_organization, upms_user_permission, upms_user_role");
		//options.put("includedTables", "meta_table, meta_field, meta_api, meta_app, meta_field_dimession, meta_field_edit, meta_field_list, meta_field_map, meta_field_measure, meta_field_relation, meta_file, meta_file_catalog, meta_file_catalog_mapping, meta_image, meta_image_set, meta_store, meta_store_db, meta_store_resource, meta_store_route, meta_theme");
		//options.put("includedTables", "meta_api, meta_app, meta_field_dimession, meta_field_edit, meta_field_list, meta_field_map, meta_field_measure, meta_field_relation, meta_file, meta_file_catalog, meta_file_catalog_mapping, meta_image, meta_image_set, meta_store, meta_store_db, meta_store_resource, meta_store_route, meta_theme");
		options.put("includedTables", "meta_field_dimession, meta_field_measure, meta_field_relation");
		
		//options.put("includedTables", "meta_image, meta_store_image_set, meta_store_route");
		//选择一种处理引擎
		IGenerator g = GeneratorManager.me().createGenerator(GeneratorType.DB);
		//GeneratorConfig config = new GeneratorConfig();
		//Generator g = GeneratorManager.me().createGenerator(config);
		//执行
		g.generate(GeneratorManager.me().getGeneratorContext(), templatePath, options);
		System.out.println("-------over-------");
		
		server.stop();
	}
}
