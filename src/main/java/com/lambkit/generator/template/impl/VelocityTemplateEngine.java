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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.lambkit.common.util.PathUtils;
import com.lambkit.generator.template.TemplateEngine;

public class VelocityTemplateEngine extends TemplateEngine {
	
	/**
	 * 核心函数处理，自动生成代码
	 * @param templateModel
	 * @param filepathModel
	 */
	public void generate(Map<String, Object> templateModel, Map<String, Object> filepathModel, String outRootDir) {
		//System.out.println("--------Velocity模板引擎处理开始-----------");
		@SuppressWarnings("unchecked")
		List<String> fileList = (List<String>) filepathModel.get("filelist");
		String folderpath = (String) filepathModel.get("folderpath");
		String templatePath = (String) filepathModel.get("templatePath");
		for (String path : fileList) {
			// 处理文件地址模板
			String newPath = PathUtils.processDir(templateModel, path);
			String newdir = newPath.replaceAll(PathUtils.replacePath(folderpath), outRootDir);
			//println("转换地址" + path + " 为：", newdir);
			// 读取文件
			File filetmp = new File(newdir);
			try {
				FileWriter fw = new FileWriter(filetmp);
				String fwpath = PathUtils.replacePath(path).replace(folderpath, "");
				//println("所处理模板的地址为: ", templatePath + fwpath);
				// 模板转换并写文件
				fw.write(createCode(fwpath, templateModel, templatePath));
				fw.flush();
				fw.close();
				showInfo(newdir);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//System.out.println("--------Velocity模板引擎处理完毕over!-----------");
	}
	
	@Override
	public Object execute(Map<String, Object> templateModel, String templateFilePath) {
		// TODO Auto-generated method stub
		//System.out.println("--------Velocity模板引擎处理开始-----------");
		String folderpath = getFolderPath(templateFilePath);
		String newcode = createCode(templateFilePath, templateModel, folderpath);
		//System.out.println("--------Velocity模板引擎处理完毕over!-----------");
		return newcode;
	}
	/**
	 * 根据模板生成代码
	 * 
	 * @param fileVMPath
	 *            模板路径
	 * @return
	 */
	private String createCode(String fileVMPath, Map<String, Object> templateModel, String templatePath) {
		// 实例化一个VelocityEngine对象
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		velocityEngine.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
		// 设置velocity资源加载方式为file
		velocityEngine.setProperty("resource.loader", "file");
		// 设置velocity资源加载方式为file时的处理类
		velocityEngine.setProperty("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		boolean flag = templatePath.length() > 2 && templatePath.substring(1, 2).equals(":") ? false : true;
		if(flag) fileVMPath = templatePath + fileVMPath;
		else velocityEngine.setProperty("file.resource.loader.path", templatePath);
		velocityEngine.init();
		Template template = velocityEngine.getTemplate(fileVMPath);//(fileVMPath.replace("D:\\web\\workspace\\psms", ""));
		// 实例化一个VelocityContext
		VelocityContext velocityContext = new VelocityContext();
		//velocityContext.put("bean", bean);
		//velocityContext.put("classname", bean.getName());
		//velocityContext.put("date", getDate());
		Iterator<String> iter = templateModel.keySet().iterator();
		while(iter.hasNext()) {
			String skey = iter.next().toString();
			velocityContext.put(skey, templateModel.get(skey));
		}
		StringWriter stringWriter = new StringWriter();
		// 从vm目录下加载hello.vm模板,在eclipse工程中该vm目录与src目录平级
		// velocityEngine.mergeTemplate("vm/hello.vm", "gbk", velocityContext,
		// stringWriter);
		template.merge(velocityContext, stringWriter);
		return stringWriter.toString();
	}
}
