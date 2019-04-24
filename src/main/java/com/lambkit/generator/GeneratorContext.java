/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.generator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lambkit.common.util.PathUtils;
import com.lambkit.generator.template.TemplateEngine;
import com.lambkit.generator.template.impl.BeetleTemplateEngine;
import com.lambkit.generator.template.impl.FreemarkerTemplateEngine;
import com.lambkit.generator.template.impl.VelocityTemplateEngine;

public class GeneratorContext {

	private TemplateEngine template;
	private GeneratorConfig config;
	
	public GeneratorContext(GeneratorConfig config) {
		this.config = config;
		this.template = buildTemplateEngine(config.getEngine());
	}
	
	public TemplateEngine buildTemplateEngine(String type) {
		switch (type) {
        case GeneratorConfig.TYPE_BEETLE:
        	return new BeetleTemplateEngine();
        case GeneratorConfig.TYPE_FREEMARKER:
        	return new FreemarkerTemplateEngine();
        case GeneratorConfig.TYPE_VELOCITY:
        	return new VelocityTemplateEngine();
        default:
            return new VelocityTemplateEngine();
		}
	}
	
	/**
	 * 删除输出目录
	 * @throws IOException 
	 */
	public void deleteOutRootDir() throws IOException {
		PathUtils.deleteOutRootDir(config.getOutRootDir());
	}
	
	/**
	 * 核心函数处理，自动生成代码，根据templatePath生成filepathModel文件列表模型
	 * @param templateModel
	 * @param templatePath
	 */
	public void generate(Map<String, Object> templateModel, String templatePath) {
		if(template==null) return;
		Map<String, Object> filepathModel = PathUtils.createFilepathModel(templatePath);
		templateModel = templateModelAddConfig(templateModel, config);
		PathUtils.createOutRootDir(templateModel, filepathModel);
		template.generate(templateModel, filepathModel, config.getOutRootDir());
	}
	
	/**
	 * 核心函数处理，自动生成代码，并生成结果文件
	 * @param templateModel
	 * @param filepathModel
	 */
	public void generate(Map<String, Object> templateModel, Map<String, Object> filepathModel) {
		if(template==null) return;
		templateModel = templateModelAddConfig(templateModel, config);
		PathUtils.createOutRootDir(templateModel, filepathModel);
		template.generate(templateModel, filepathModel, config.getOutRootDir());
	}
	
	/**
	 * 核心函数处理，自动生成代码，并返回生成的结果
	 * @param templateModel
	 * @param templateFilePath
	 * @return
	 */
	public Object execute(Map<String, Object> templateModel, String templateFilePath) {
		if(template==null) return null;
		templateModel = templateModelAddConfig(templateModel, config);
		return template.execute(templateModel, templateFilePath);
	}
	
	/**
	 * 创建模板内容模型
	 * @return
	 */
	public Map<String, Object> createTemplateModel() {
		Map<String, Object> templateModel = new HashMap<String, Object>();
		templateModel.put("outRootDir", config.getOutRootDir());
		templateModel.put("basepackage", config.getBasepackage());
		String basepackage_dir = config.getBasepackage().replaceAll("\\.", "/");
		templateModel.put("basepackage_dir", basepackage_dir);
		templateModel.put("webpages", config.getWebpages());
		templateModel.put("date", getDate());
		return templateModel;
	}
	
	/**
	 * 检查模板中是否加入config的内容
	 * @param templateModel
	 * @param config
	 * @return
	 */
	public Map<String, Object> templateModelAddConfig(Map<String, Object> templateModel, GeneratorConfig config) {
		if(!templateModel.containsKey("outRootDir")) {
			templateModel.put("outRootDir", config.getOutRootDir());
		}
		if(!templateModel.containsKey("basepackage")) {
			templateModel.put("basepackage", config.getBasepackage());
		}
		if(!templateModel.containsKey("basepackage_dir")) {
			String basepackage_dir = config.getBasepackage().replaceAll("\\.", "/");
			templateModel.put("basepackage_dir", basepackage_dir);
		}
		if(!templateModel.containsKey("webpages")) {
			templateModel.put("webpages", config.getWebpages());
		}
		return templateModel;
	}
	
	/**
	 * 创建文件地址模型
	 * @param templatePath
	 * @return
	 */
	public Map<String, Object> createFilePathModel(String templatePath) {
		return PathUtils.createFilepathModel(templatePath);
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
	private String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * 获取路径的最后面字符串<br>
	 * 如：<br>
	 * <code>str = "com.app.base.bean.User"</code><br>
	 * <code> return "User";<code>
	 * 
	 * @param str
	 * @return
	 */
	public String getLastChar(String str) {
		if ((str != null) && (str.length() > 0)) {
			int dot = str.lastIndexOf('.');
			if ((dot > -1) && (dot < (str.length() - 1))) {
				return str.substring(dot + 1);
			}
		}
		return str;
	}
	
	/**
	 * 字符串转换unicode
	 */
	public String string2Unicode(String string) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			// 取出每一个字符
			char c = string.charAt(i);
			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}
		return unicode.toString();
	}
	
	public TemplateEngine getTemplate() {
		return template;
	}

	public void setTemplate(TemplateEngine template) {
		this.template = template;
	}

	public GeneratorConfig getConfig() {
		return config;
	}

	public void setConfig(GeneratorConfig config) {
		this.config = config;
	}
}
