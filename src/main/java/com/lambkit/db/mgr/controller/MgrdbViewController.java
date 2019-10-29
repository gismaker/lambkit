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
package com.lambkit.db.mgr.controller;

import com.lambkit.db.mgr.MgrConstants;
import com.lambkit.db.mgr.MgrTable;
import com.lambkit.web.controller.LambkitController;

public class MgrdbViewController extends LambkitController {

	/**
	 * 主题页
	 */
	//@RequiresPermissions("mgr:view:index")
	public void index() {
		MgrTable tbc = getBase(MgrConstants.VIEW);
		if (tbc == null) {
			tbc = getTable("meta_table", MgrConstants.NONE, false);
			setAttr("mgrdb", tbc);
			renderTemplate("tables.html");
			return;
		}
		keepTable(tbc);
		keepPara();
		renderTemplate("index.html");
	}
	
	/**
	 * 表格列表页
	 */
	//@RequiresPermissions("mgr:view:list")
	public void list() {
		MgrTable tbc = getBase(MgrConstants.VIEW);
		if (tbc == null) {
			renderError(404);
			return;
		}
		keepTable(tbc);
		keepPara();
		renderTemplate("list.html");
	}

	/**
	 * 图表页
	 */
	//@RequiresPermissions("mgr:view:chart")
	public void chart() {
		MgrTable tbc = getBase(MgrConstants.OLAP);
		if (tbc == null) {
			renderError(404);
			return;
		}
		keepTable(tbc);
		keepPara();
		renderTemplate("chart.html");
	}
	
	/**
	 * 分析页
	 */
	//@RequiresPermissions("mgr:view:privot")
	public void pivot() {
		MgrTable tbc = getBase(MgrConstants.OLAP);
		if (tbc == null) {
			renderError(404);
			return;
		}
		keepTable(tbc);
		keepPara();
		renderTemplate("pivot.html");
	}
	
	/**
	 * 地图页
	 */
	//@RequiresPermissions("mgr:view:map")
	public void map() {
		MgrTable tbc = getBase(MgrConstants.MAP);
		if (tbc == null) {
			renderError(404);
			return;
		}
		keepTable(tbc);
		keepPara();
		renderTemplate("map.html");
	}
}
