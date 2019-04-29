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
 */package com.lambkit.generator.template.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.JavaKeyword;
import com.jfinal.kit.StrKit;
import com.jfinal.template.Engine;
import com.jfinal.template.source.FileSourceFactory;
import com.lambkit.common.util.PathUtils;
import com.lambkit.generator.template.TemplateEngine;

public class JFinalTemplateEngine extends TemplateEngine {

	private Engine engine;
	/**
	 * 针对 Model 中七种可以自动转换类型的 getter 方法，调用其具有确定类型返回值的 getter 方法
	 * 享用自动类型转换的便利性，例如 getInt(String)、getStr(String)
	 * 其它方法使用泛型返回值方法： get(String)
	 * 注意：jfinal 3.2 及以上版本 Model 中的六种 getter 方法才具有类型转换功能
	 */
	@SuppressWarnings("serial")
	protected Map<String, String> getterTypeMap = new HashMap<String, String>() {{
		put("java.lang.String", "getStr");
		put("java.lang.Integer", "getInt");
		put("java.lang.Long", "getLong");
		put("java.lang.Double", "getDouble");
		put("java.lang.Float", "getFloat");
		put("java.lang.Short", "getShort");
		put("java.lang.Byte", "getByte");
	}};
	
	public JFinalTemplateEngine() {
		engine = Engine.create("forGenerator");
        engine.setSourceFactory(new FileSourceFactory());
        engine.addSharedMethod(new StrKit());
        engine.addSharedObject("getterTypeMap", getterTypeMap);
        engine.addSharedObject("javaKeyword", JavaKeyword.me);
	}
	
	@Override
	public void generate(Map<String, Object> templateModel, Map<String, Object> filepathModel, String outRootDir) {
		//System.out.println("--------JFinal Class Template模板引擎处理开始-----------");
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
		//System.out.println("--------JFinal Class Template模板引擎处理完毕over!-----------");
	}
	
	@Override
	public Object execute(Map<String, Object> templateModel, String templateFilePath) {
		Engine engine = Engine.use("forGenerator");
		return engine.getTemplate(templateFilePath).renderToString(templateModel);
	}
	
	/**
	 * 根据模板生成代码
	 * 
	 * @param fileVMPath
	 *            模板路径
	 * @return
	 */
	private String createCode(String fileVMPath, Map<String, Object> templateModel, String templatePath) {
		Engine engine = Engine.use("forGenerator");
		boolean flag = templatePath.length() > 2 && templatePath.substring(1, 2).equals(":") ? false : true;
		if(flag) fileVMPath = templatePath + fileVMPath;
		return engine.getTemplate(templatePath + fileVMPath).renderToString(templateModel);
	}

}
