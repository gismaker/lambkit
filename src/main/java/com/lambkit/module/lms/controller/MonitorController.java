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
package com.lambkit.module.lms.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.lambkit.common.ResultKit;
import com.lambkit.common.monitor.SysInfoMonitorManager;
import com.lambkit.common.monitor.SysPerformInfo;
import com.lambkit.common.util.LimitQueue;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.web.controller.LambkitController;

@RequiresPermissions("lms:dev")
public class MonitorController extends LambkitController {


	public void index() {
		renderTemplate("index.html");
	}
	
	public void list() {
		renderTemplate("list.html");
	}
	
	public void controller() {
		renderTemplate("controller.html");
	}
	
	public void api() {
		renderTemplate("api.html");
	}
	
	//------------------
	// API for json result
	//------------------
	
	public void data() {
		renderJson(ResultKit.json(getPara(0, getPara("jt")), 0, "", NodeManager.me().getNode()));
	}
	
	/**
	 * 根据layui的规定设置输出table数据
	 */
	public void table() {
		LimitQueue<SysPerformInfo> queue = SysInfoMonitorManager.me().getService().getSysInfoQueue();
		renderJson(ResultKit.page(getPara(0, getPara("jt")), 1, "monitor", queue.size(), queue));
	}
}
