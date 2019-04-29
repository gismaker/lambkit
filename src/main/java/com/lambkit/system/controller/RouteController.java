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


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import com.jfinal.config.Routes.Route;
import com.jfinal.json.FastJson;
import com.jfinal.kit.StrKit;
import com.lambkit.common.base.ResultKit;
import com.lambkit.system.SystemManager;
import com.lambkit.system.info.ActionInfo;
import com.lambkit.system.info.ActionMapping;
import com.lambkit.web.controller.BaseController;

public class RouteController extends BaseController {

	public void index() {
		String name = getPara("name");
		if(StrKit.notBlank(name)) {
			ActionMapping am = SystemManager.me().getInfo().getActionMapping();
			Route route = null;
			for (Route rt : am.getRoutes()) {
				if(name.equals(rt.getControllerKey())) {
					route = rt;
				}
			}
			setAttr("flag", true);
			setAttr("route", route);
			Map<String, ActionInfo> resultMap = sortMapByKey(am.getMapping()); 
			setAttr("actions", resultMap.values());
		} else {
			setAttr("flag", false);
			setAttr("route", null);
			setAttr("actions", null);
		}
		renderTemplate("index.html");
	}
	
	/**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    private Map<String, ActionInfo> sortMapByKey(Map<String, ActionInfo> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, ActionInfo> sortMap = new TreeMap<String, ActionInfo>(
                new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
    
    class MapKeyComparator implements Comparator<String>{
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
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
	public void actions() {
		ActionMapping am = SystemManager.me().getInfo().getActionMapping();
		renderJson(FastJson.getJson().toJson(ResultKit.page(getPara(0, getPara("jt")), 1, "success", am.getMapping().size(), am.getMapping().values())));
	}
	
	/**
	 * 根据layui的规定设置输出table数据
	 */
	public void table() {
		ActionMapping am = SystemManager.me().getInfo().getActionMapping();
		renderJson(FastJson.getJson().toJson(ResultKit.page(getPara(0, getPara("jt")), 1, "success", am.getRoutes().size(), am.getRoutes())));
	}
}
