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

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.Properties;

import com.beust.jcommander.internal.Lists;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.common.ResultKit;
import com.lambkit.common.util.FileUtils;
import com.lambkit.web.controller.BaseController;

@RequiresPermissions("lms:dev")
public class PropController extends BaseController {

	public void index() {
		String name = getPara("name", "lambkit");
		name += ".properties";
		renderJson(ResultKit.json(getPara(0, getPara("jt")), 1, "success", PropKit.use(name)));
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
		renderJson(ResultKit.json(getPara(0, getPara("jt")), 1, "success", PropKit.getProp()));
	}

	public void table() {
		String name = getPara("name", "lambkit");
		name += ".properties";
		Properties prop = PropKit.use(name).getProperties();
		List<Record> rcdList = Lists.newArrayList();
		Iterator<Entry<Object, Object>> it = prop.entrySet().iterator();
		while (it.hasNext()) {
			Record data = new Record();
			Entry<Object, Object> entry = it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			data.set("key", key);
			data.set("value", value);
			rcdList.add(data);
		}
		renderJson(ResultKit.page(getPara(0, getPara("jt")), 1, "success", rcdList.size(), rcdList));
	}

	public void read() {
		String wrpath = PathKit.getRootClassPath();
		String path = getPara("path");
		setAttr("info", FileUtils.readFile(wrpath + path, "GBK"));
		setAttr("path", path);
		renderTemplate("info.html");
	}

	public void update() {
		String info = getPara("model.info", "");
		String path = getPara("model.path", "");
		boolean flag = false;
		if (StrKit.notBlank(path)) {
			String wrpath = PathKit.getRootClassPath();
			flag = FileUtils.writeFile(wrpath + path, info, "GBK");
		}
		renderJson(flag);
	}
}
