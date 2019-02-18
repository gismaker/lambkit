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

import com.lambkit.Lambkit;
import com.lambkit.common.util.ClassNewer;
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
			manager = ClassNewer.singleton(GeneratorManager.class);
		}
		return manager;
	}
	
	public void init() {
		GeneratorConfig config = Lambkit.config(GeneratorConfig.class);
		String mgrdb = config.getMgrdb();
		switch (mgrdb) {
        case "normal":
        	Lambkit.me().addModule(new SysconfigModule());
        	break;
        case "meta":
        	Lambkit.me().addModule(new MetaMgrModule());
        	break;
        default:
        	Lambkit.me().addModule(new SysconfigModule());
        	break;
		}
	}
	
	private GeneratorContext generator;
	
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
	
	public GeneratorContext createGeneratorContext(GeneratorConfig config) {
		return new GeneratorContext(config);
	}
	
	public IGenerator createGenerator(GeneratorType type) {
		switch (type) {
        case COMMON:
        	return new CommonGenerator();
        case DB:
        	return new DatabaseGenerator();
        case MGRDB:
        	return new MgrdbGenerator();
        default:
            return new CommonGenerator();
		}
	}
	
	public IGenerator getDefaultGenerator() {
		GeneratorConfig config = Lambkit.config(GeneratorConfig.class);
		return createGenerator(GeneratorType.valueOf(config.getProcesser()));
	}

	public GeneratorContext getGeneratorContext() {
		if(generator==null) {
			GeneratorConfig config = Lambkit.config(GeneratorConfig.class);
			generator = new GeneratorContext(config);
		}
		return generator;
	}
	
	public void setGeneratorContext(GeneratorContext generator) {
		this.generator = generator;
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
}
