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
package com.lambkit.generator.template.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import com.lambkit.common.util.PathUtils;
import com.lambkit.generator.template.TemplateEngine;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreemarkerTemplateEngine extends TemplateEngine {
	/**
	 * 核心函数处理，自动生成代码
	 * @param templateModel
	 * @param filepathModel
	 */
	@Override
	public void generate(Map<String, Object> templateModel,	Map<String, Object> filepathModel, String outRootDir) {
		// TODO Auto-generated method stub
		//System.out.println("--------Freemarker模板引擎处理开始-----------");
		@SuppressWarnings("unchecked")
		List<String> fileList = (List<String>) filepathModel.get("filelist");
		//templatePath的绝对路径
		String folderpath = (String) filepathModel.get("folderpath");
		//String templatePath = (String) filepathModel.get("templatePath");
		//println("所处理模板的地址为: ", folderpath);
		try {
			// Freemarker配置
			Configuration config = new Configuration();
			// filepath:ftl存放路径（/template/file/static）
			config.setDirectoryForTemplateLoading(new File(folderpath));
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());
			//config.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  

			for (String path : fileList) {
				// 处理文件地址模板
				String newPath = PathUtils.processDir(templateModel, path);
				String newdir = newPath.replaceAll(PathUtils.replacePath(folderpath), outRootDir);
				//println("转换地址" + path + " 为：", newdir);
				// 读取文件
				File filetmp = new File(newdir);

				FileWriter fw = new FileWriter(filetmp);
				String fwpath = PathUtils.replacePath(path).replace(folderpath, "");
				//println("所处理模板的名称为: ", fwpath);
				// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
				// 否则会出现乱码
				// templatePath:ftl文件名称（template.ftl）
				Template template = config.getTemplate(fwpath, "UTF-8");
				// 合并数据模型与模板
				template.process(templateModel, fw);
				fw.flush();
				fw.close();
				showInfo(newdir);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("--------Freemarker模板引擎处理完毕over!-----------");
	}

	@Override
	public Object execute(Map<String, Object> templateModel, String templateFilePath) {
		//System.out.println("--------Freemarker模板引擎处理开始-----------");
		String res = null;
		try {
			// Freemarker配置
			Configuration config = new Configuration();
			// filepath:ftl存放路径（/template/file/static）
			String folderpath = getFolderPath(templateFilePath);
			config.setDirectoryForTemplateLoading(new File(folderpath));
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());
			//config.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
			StringWriter fw = new StringWriter();
			// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			// 否则会出现乱码
			// templatePath:ftl文件名称（template.ftl）
			Template template = config.getTemplate(templateFilePath, "UTF-8");
			// 合并数据模型与模板
			template.process(templateModel, fw);
			res = fw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("--------Freemarker模板引擎处理完毕over!-----------");
		return res;
	}
}
