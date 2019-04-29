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
package com.lambkit.generator.template;

import java.util.Map;

public abstract class TemplateEngine {
	
	/**
	 * 核心函数处理，自动生成代码, 批量处理，并生成文件
	 * @param templateModel
	 * @param filepathModel
	 */
	public abstract void generate(Map<String, Object> templateModel, Map<String, Object> filepathModel, String outRootDir);
	
	/**
	 * 核心函数处理，自动生成代码, 单文件，并返回内容
	 * @param templateModel
	 * @param path
	 * @param folderpath
	 * @param templatePath
	 * @return
	 */
	public abstract Object execute(Map<String, Object> templateModel, String templateFilePath);
	
	public String getFolderPath(String templateFilePath) {
		return templateFilePath.substring(0, templateFilePath.lastIndexOf("/"));
	}
	
	public String getFileName(String templateFilePath) {
		return templateFilePath.substring(templateFilePath.lastIndexOf("/"));
	}
	/**
	 * 显示信息
	 * 
	 * @param info
	 */
	public void showInfo(String info) {
		System.out.println("生成文件：" + info);
	}
	
	public void println(String title, String content) {
		System.out.println(title + content);
	}
}
