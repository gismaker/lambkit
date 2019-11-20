package com.lambkit.test.generator;

import java.util.Map;

import com.google.common.collect.Maps;
import com.jfinal.kit.PathKit;
import com.lambkit.generator.GeneratorType;
import com.lambkit.generator.Msch;
import com.lambkit.generator.GeneratorConfig;

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
		config.setType(GeneratorType.DB);
		//模板地址，根目录是项目文件夹
		String templatePath = "/template";
		Map<String,Object> options = Maps.newHashMap();
		//需要去掉的前缀
		//options.put("tableRemovePrefixes", "data_");
		//仅包含如下表格
		options.put("includedTables", "meta_api, ");
		options.put("hasMgrTable", true);
		Msch.generator(templatePath, options, config);
		System.exit(0);
	}
}
