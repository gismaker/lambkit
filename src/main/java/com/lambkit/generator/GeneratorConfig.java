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

import com.lambkit.core.config.annotation.PropertieConfig;

@PropertieConfig(prefix = "lambkit.generator")
public class GeneratorConfig {

	public static final String TYPE_BEETLE = "beetle";
	public static final String TYPE_FREEMARKER = "freemarker";
	public static final String TYPE_VELOCITY = "velocity";
	public static final String TYPE_JFINAL = "jfinal";

	public String engine = TYPE_VELOCITY;
	
	private String outRootDir = "D:/lambkit";
	private String basepackage = "com.lambkit.demo";
	private String webpages = "pages";
	private String mgrdb = "none";
	private String processer = "common";

	public String getOutRootDir() {
		return outRootDir;
	}

	public void setOutRootDir(String outRootDir) {
		this.outRootDir = outRootDir;
	}

	public String getBasepackage() {
		return basepackage;
	}

	public void setBasepackage(String basepackage) {
		this.basepackage = basepackage;
	}

	public String getWebpages() {
		return webpages;
	}

	public void setWebpages(String webpages) {
		this.webpages = webpages;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getMgrdb() {
		return mgrdb;
	}

	public void setMgrdb(String mgrdb) {
		this.mgrdb = mgrdb;
	}

	public String getProcesser() {
		return processer;
	}

	public void setProcesser(String processer) {
		this.processer = processer;
	}
}
