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
package com.lambkit.db.mgr;

import com.lambkit.core.config.annotation.PropertieConfig;

@PropertieConfig(prefix = "lambkit.mgrdb")
public class MgrdbConfig {
	
	public static final String SYSCONFIG = "sysconfig";
	public static final String META = "meta";
	
	private String type;
	private String tableRemovePrefixes;
	private boolean isUseController = false;

	public String getTableRemovePrefixes() {
		return tableRemovePrefixes;
	}

	public void setTableRemovePrefixes(String tableRemovePrefixes) {
		this.tableRemovePrefixes = tableRemovePrefixes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isUseController() {
		return isUseController;
	}

	public void setUseController(boolean isUseController) {
		this.isUseController = isUseController;
	}
}
