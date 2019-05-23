package com.lambkit.generator;

import java.util.Map;

import com.jfinal.kit.PathKit;
import com.lambkit.Application;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;

public class Msch {

	public static void generator(Map<String,Object> options) {
		//模板地址，根目录是项目文件夹
		String templatePath = options.get("templatePath")==null ? "/template" : options.get("templatePath").toString();
		GeneratorConfig config = new GeneratorConfig();
		//生成java代码的存放地址
		config.setOutRootDir(PathKit.getWebRootPath());
		//生成java代码的包地址
		String basePackage = options.get("basePackage")==null ? "com.lambkit.msch" : options.get("basePackage").toString();
		config.setBasepackage(basePackage);
		//生成前端文件文件夹
		config.setWebpages("app");
		//表格配置方式
		config.setMgrdb("normal");
		//选择一种模板语言
		config.setEngine(GeneratorConfig.TYPE_VELOCITY);
		//选择一种处理引擎
		config.setType(GeneratorType.DB.getValue());
		generator(templatePath, options, config);
	}
	
	public static void generator(String templatePath, Map<String,Object> options) {
		generator(templatePath, options, null);
	}
	
	public static void generator(String templatePath, Map<String,Object> options, GeneratorConfig config) {
		if(config==null) {
			config = Lambkit.config(GeneratorConfig.class);
		}
		//初始化生成工具
		GeneratorManager.me().init(config);
		//创建应用
		LambkitApplication application = new LambkitApplication(Application.class);
		application.setWebEnvironment(false);
		//启动应用
		application.run(null);
		//创建生成器
		Generator g = GeneratorManager.me().getDefaultGenerator();
		//执行
		if(g!=null) g.generate(templatePath, options);
		else System.out.println("生成器创建失败，请先初始化GeneratorManager!");
		
		System.out.println("-------over-------");
		//结束应用
		application.stop();
	}
}
