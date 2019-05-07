package com.lambkit.test.generator;

import java.util.Map;

import com.beust.jcommander.internal.Maps;
import com.jfinal.kit.PathKit;
import com.lambkit.Lambkit;
import com.lambkit.common.app.DefaultApplication;
import com.lambkit.common.app.LambkitApplication;
import com.lambkit.generator.GeneratorType;
import com.lambkit.generator.Generator;
import com.lambkit.generator.GeneratorConfig;
import com.lambkit.generator.GeneratorManager;

public class GeneratorTest {

	public static void main(String[] args) {
		GeneratorConfig config = new GeneratorConfig();
		//生成java代码的存放地址
		config.setOutRootDir(PathKit.getWebRootPath());
		//生成java代码的包地址
		config.setBasepackage("com.lambkit.module.meta");
		//生成前端文件文件夹
		config.setWebpages("app");
		//表格配置方式
		config.setMgrdb("normal");
		//选择一种模板语言
		config.setEngine(GeneratorConfig.TYPE_VELOCITY);
		//选择一种处理引擎
		config.setType(GeneratorType.DB.getValue());
		//模板地址，根目录是项目文件夹
		String templatePath = "/template";
		Map<String,Object> options = Maps.newHashMap();
		//需要去掉的前缀
		//options.put("tableRemovePrefixes", "data_");
		//仅包含如下表格
		options.put("includedTables", "meta_api, ");
		options.put("hasMgrTable", true);
		GeneratorManager.me().run(templatePath, options, config);
	}
	/*
	public static void main(String[] args) {
		LambkitApplication server = new DefaultApplication();
				
		GeneratorConfig config = new GeneratorConfig();
		//生成java代码的存放地址
		config.setOutRootDir(PathKit.getWebRootPath());
		//生成java代码的包地址
		config.setBasepackage("com.lambkit.module.meta");
		//生成前端文件文件夹
		config.setWebpages("app");
		//表格配置方式
		config.setMgrdb("normal");
		//选择一种模板语言
		config.setEngine(GeneratorConfig.TYPE_VELOCITY);
		//选择一种处理引擎
		config.setType(GeneratorType.DB.getValue());
		//初始化生成工具
		GeneratorManager.me().init(config);
		
		//启动应用
		server.run();
		
		//模板地址，根目录是项目文件夹
		String templatePath = "/template";
		Map<String,Object> options = Maps.newHashMap();
		//需要去掉的前缀
		//options.put("tableRemovePrefixes", "data_");
		//仅包含如下表格
		options.put("includedTables", "meta_api, ");
		options.put("hasMgrTable", true);
		//创建生成器
		Generator g = GeneratorManager.me().getDefaultGenerator();
		//执行
		g.generate(templatePath, options);
		
		System.out.println("-------over-------");
		//结束应用
		server.stop();
	}
	*/
	/*
	public static void main(String[] args) {
		Lambkit.setBootArg("lambkit.server.type", "applicaiton");
		//生成java代码的存放地址
		Lambkit.setBootArg("lambkit.generator.outRootDir", PathKit.getWebRootPath() + "/src/main/java");
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
		server.run();
		
		//模板地址，根目录是项目文件夹
		String templatePath = "/template";
		Map<String,Object> options = Maps.newHashMap();
		//需要去掉的前缀
		//options.put("tableRemovePrefixes", "data_");
		//仅包含如下表格
		options.put("includedTables", "meta_api, ");
		//options.put("hasMgrTable", true);
		//选择一种处理引擎
		Generator g = GeneratorManager.me().getDefaultGenerator(GeneratorType.DB);
		//执行
		g.generate(templatePath, options);
		System.out.println("-------over-------");
		
		server.stop();
	}
	*/
}
