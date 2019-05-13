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
package com.lambkit.common.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.config.Routes;
import com.jfinal.config.Routes.Route;
import com.jfinal.core.Action;
import com.jfinal.core.JFinal;

public class ActionMapping {

	protected List<Route> routes;
	protected Map<String, ActionBean> mapping = new HashMap<String, ActionBean>();
	
	public ActionMapping(Routes routes) {
		if(this.routes==null) {
			this.routes = new ArrayList<>();
		}
		this.routes.addAll(routes.getRouteItemList());
		for (Routes rts : routes.getRoutesList()) {
			this.routes.addAll(rts.getRouteItemList());
		}
	}
	
	public void initMapping() {
		List<String> actionkeys = JFinal.me().getAllActionKeys();
		for (String key : actionkeys) {
			String[] urlPara = {null};
			Action action = JFinal.me().getAction(key, urlPara);
			mapping.put(key, new ActionBean(action));
		}
	}

	/**
	 * Support four types of url
	 * 1: http://abc.com/controllerKey                 ---> 00
	 * 2: http://abc.com/controllerKey/para            ---> 01
	 * 3: http://abc.com/controllerKey/method          ---> 10
	 * 4: http://abc.com/controllerKey/method/para     ---> 11
	 * The controllerKey can also contains "/"
	 * Example: http://abc.com/uvw/xyz/method/para
	 */
	public ActionBean getAction(String url, String[] urlPara) {
		ActionBean action = mapping.get(url);
		if (action != null) {
			return action;
		}
		
		// --------
		int i = url.lastIndexOf('/');
		if (i != -1) {
			action = mapping.get(url.substring(0, i));
			urlPara[0] = url.substring(i + 1);
		}
		
		return action;
	}
	
	public List<String> getAllActionKeys() {
		List<String> allActionKeys = new ArrayList<String>(mapping.keySet());
		Collections.sort(allActionKeys);
		return allActionKeys;
	}
	
	public Map<String, ActionBean> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, ActionBean> mapping) {
		this.mapping = mapping;
	}
	
	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
}
