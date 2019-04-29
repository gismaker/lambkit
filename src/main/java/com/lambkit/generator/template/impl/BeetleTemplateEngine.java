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
import java.util.List;
import java.util.Map;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import com.lambkit.common.util.PathUtils;
import com.lambkit.generator.template.TemplateEngine;

public class BeetleTemplateEngine extends TemplateEngine {
	/**
	 * 核心函数处理，自动生成代码
	 * @param templateModel
	 * @param filepathModel
	 */
	@Override
	public void generate(Map<String, Object> templateModel, Map<String, Object> filepathModel, String outRootDir) {
		// TODO Auto-generated method stub
		//System.out.println("--------Beetle模板引擎处理开始-----------");
		@SuppressWarnings("unchecked")
		List<String> fileList = (List<String>) filepathModel.get("filelist");
		//templatePath的绝对路径
		String folderpath = (String) filepathModel.get("folderpath");
		//String templatePath = (String) filepathModel.get("templatePath");
		//println("所处理模板的地址为: ", folderpath);
		try {
			Configuration cfg = Configuration.defaultConfiguration();
			for (String path : fileList) {
				// 处理文件地址模板
				String newPath = PathUtils.processDir(templateModel, path);
				String newdir = newPath.replaceAll(PathUtils.replacePath(folderpath), outRootDir);
				//println("转换地址" + path + " 为：", newdir);
				// 读取文件
				File filetmp = new File(newdir);
				
				FileWriter fw = new FileWriter(filetmp);
				String fwpath = PathUtils.replacePath(path).replace(folderpath, "");
				//println("所处理模板的地址为: ", folderpath + fwpath);
				
				FileResourceLoader resourceLoader = new FileResourceLoader(folderpath);
				GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
				Template template = gt.getTemplate(fwpath);
				template.binding(templateModel);
				template.renderTo(fw);
				fw.flush();
				fw.close();
				showInfo(newdir);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("--------Beetle模板引擎处理完毕over!-----------");
	}

	@Override
	public Object execute(Map<String, Object> templateModel, String templateFilePath) {
		//System.out.println("--------Beetle模板引擎处理开始-----------");
		String res = null;
		try {
			Configuration cfg = Configuration.defaultConfiguration();
			String folderpath = getFolderPath(templateFilePath);
			FileResourceLoader resourceLoader = new FileResourceLoader(folderpath);
			GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
			Template template = gt.getTemplate(templateFilePath);
			template.binding(templateModel);
			res = template.render();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("--------Beetle模板引擎处理完毕over!-----------");
		return res;
	}
}
