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

import java.util.Map;

import com.lambkit.LambkitApplicationContext;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;
import com.lambkit.core.aop.AopKit;
import com.lambkit.generator.impl.CommonGenerator;
import com.lambkit.generator.impl.DatabaseGenerator;
import com.lambkit.generator.impl.MgrdbGenerator;
import com.lambkit.generator.template.TemplateEngine;
import com.lambkit.generator.template.impl.BeetleTemplateEngine;
import com.lambkit.generator.template.impl.FreemarkerTemplateEngine;
import com.lambkit.generator.template.impl.JFinalTemplateEngine;
import com.lambkit.generator.template.impl.VelocityTemplateEngine;
import com.lambkit.module.meta.MetaMgrModule;
import com.lambkit.module.sysconfig.SysconfigModule;

public class GeneratorManager {

	private static GeneratorManager manager;
	
	public static GeneratorManager me() {
		if(manager==null) {
			manager = AopKit.singleton(GeneratorManager.class);
		}
		return manager;
	}
	
	private GeneratorContext context;
	private boolean hasInit = false;
	
	/**
	 * 初始化，应用启动之前
	 */
	public void init() {
		GeneratorConfig config = Lambkit.config(GeneratorConfig.class);
		init(config);
	}
	
	/**
	 * 初始化，应用启动之前
	 * @param config
	 */
	public void init(GeneratorConfig config) {
		if(hasInit) return;
		if(context==null) {
			context = new GeneratorContext(config);
		}
		String mgrdb = config.getMgrdb();
		switch (mgrdb) {
        case "normal":
        	Lambkit.addModule(new SysconfigModule());
        	break;
        case "meta":
        	Lambkit.addModule(new MetaMgrModule());
        	break;
        default:
        	break;
		}
		hasInit = true;
	}
	
	/**
	 * 获取默认配置的生成器
	 * @return
	 */
	public Generator getDefaultGenerator() {
		if(!hasInit) return null;
		if(context==null) return null;
		GeneratorConfig config = context.getConfig();
		return createGenerator(GeneratorType.valueOf(config.getType().toUpperCase()), context);
	}
	
	public Generator getDefaultGenerator(GeneratorType type) {
		GeneratorConfig config = context.getConfig();
		if(!config.isHasMgrdb() && type==GeneratorType.MGRDB) {
			return null;
		}
		return createGenerator(type, context);
	}
	
	/**
	 * 获取指定配置的生成器
	 * @param config
	 * @return
	 */
	public Generator createGenerator(GeneratorConfig config) {
		if(config.isHasMgrdb() && context!=null && context.getConfig().isHasMgrdb()) {
			return null;
		}
		GeneratorContext gc = new GeneratorContext(config);
		return createGenerator(GeneratorType.valueOf(config.getType().toUpperCase()), gc);
	}
	
	public Generator createGenerator(GeneratorType type, GeneratorContext context) {
		switch (type) {
        case COMMON:
        	return new CommonGenerator(context);
        case DB:
        	return new DatabaseGenerator(context);
        case MGRDB:
        	return new MgrdbGenerator(context);
        default:
            return new CommonGenerator(context);
		}
	}
	
	public TemplateEngine buildTemplateEngine(String type) {
		switch (type) {
        case GeneratorConfig.TYPE_BEETLE:
        	return new BeetleTemplateEngine();
        case GeneratorConfig.TYPE_FREEMARKER:
        	return new FreemarkerTemplateEngine();
        case GeneratorConfig.TYPE_VELOCITY:
        	return new VelocityTemplateEngine();
        case GeneratorConfig.TYPE_JFINAL:
        	return new JFinalTemplateEngine();
        default:
            return new VelocityTemplateEngine();
		}
	}
	
	
	public GeneratorContext getDefaultContext() {
		return context;
	}
	
	public void setDefaultContext(GeneratorContext generator) {
		this.context = generator;
	}
	
	/*
	private TemplateEngine tempEngine;
	
	public TemplateEngine getTempEngine() {
		if(tempEngine==null) {
			GeneratorConfig config = Lambkit.config(GeneratorConfig.class);
			tempEngine = buildTemplateEngine(config.getEngine());
		}
		return tempEngine;
	}
	*/
	
	public void run(String templatePath, Map<String,Object> options) {
		run(templatePath, options, null);
	}
	
	public void run(String templatePath, Map<String,Object> options, GeneratorConfig config) {
		if(config==null) {
			config = Lambkit.config(GeneratorConfig.class);
		}
		//初始化生成工具
		GeneratorManager.me().init(config);
		//创建应用
		LambkitApplication application = new LambkitApplication(LambkitApplicationContext.class);
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
