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
package com.lambkit.system.controller;

import com.jfinal.kit.StrKit;
import com.lambkit.common.base.ResultKit;
import com.lambkit.system.SystemManager;
import com.lambkit.system.info.ActionInfo;
import com.lambkit.system.info.ActionMapping;
import com.lambkit.web.controller.BaseController;

public class InterceptorController extends BaseController {

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
		renderJson(ResultKit.json(getPara(0, getPara("jt")), 1, "success", "data"));
	}
	
	/**
	 * 根据layui的规定设置输出table数据
	 */
	public void table() {
		//?page=1&limit=10
		String akey = getPara("akey");
		boolean flag = false;
		if(StrKit.notBlank(akey)) {
			ActionMapping am = SystemManager.me().getInfo().getActionMapping();
			ActionInfo ainfo = am.getMapping().get(akey);
			if(ainfo!=null) {
				flag = true;
				renderJson(ResultKit.page(getPara(0, getPara("jt")), 0, "", ainfo.getInterceptors().length, ainfo.getInterceptors()));
			}
		}
		if(!flag) renderJson(ResultKit.page(getPara(0, getPara("jt")), 1, "success", 0, null));
	}
}
