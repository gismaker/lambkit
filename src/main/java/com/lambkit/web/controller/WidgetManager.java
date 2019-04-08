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
package com.lambkit.web.controller;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.lambkit.common.aop.AopKit;

public class WidgetManager {

	private static WidgetManager manager;
	
	public static WidgetManager me() {
		if (manager == null) {
            manager = AopKit.singleton(WidgetManager.class);
		}
		return manager;
	}
	
	private Map<String, Widget> actionMap = new HashMap<String, Widget>();
	
	public Widget getWidget(String key) {
		return actionMap.get(key);
	}
	
	public Map<String, Widget> getWidgetMap() {
		return actionMap;
	}

	public void setWidgetMap(Map<String, Widget> actionMap) {
		this.actionMap = actionMap;
	}
	
	public void addWidget(String key, Widget w) {
		w.setName(key);
		addWidget(w);
	}
	
	public void addWidget(Widget w) {
		actionMap.put(w.getName(), w);
	}	
	
	public void render(BaseController c) {
		render(c, null);
	}
	
	public void render(BaseController c, String templatePath) {
		String akey = c.getPara(0)==null ? c.getPara("request") : c.getPara(0);
		boolean kp = c.getParaToBoolean("kp", true);
		if(kp) c.keepPara();
		WidgetResult result = null;
		if(StrKit.notBlank(akey)) {
			Widget action = WidgetManager.me().getWidget(akey);
			if(action!=null) {
				result = action.execute(c);
				if(result!=null) {
					switch (result.getResultType()) {
					case OVER:
						return;
					case ERROR:
						c.renderError(result.getErrorCode());
						return;
					case REDIRECT:
						c.redirect(result.getUrl());
						return;
					case VIEW:
						break;
					default:
						break;
					}
				}
			}
		}
		String html = c.getPara("p");
		if(StrKit.notBlank(html)) {
			html = templatePath + "/" + html;
			if(html.endsWith(".html")) c.render(html);
			else c.render(html + ".html");
		} else if(result!=null && StrKit.notBlank(result.getView())) {
			String view = result.getView();
			view = templatePath + "/" + view;
			RenderType rt = result.getRenderType();
			if(rt!=null) {
				//渲染器
				if(rt==RenderType.JSON) {
					c.renderJson();
				} else if(rt==RenderType.JFINAL_TEMPLATE) {
					c.renderTemplate(view);
				} else if(rt==RenderType.FREE_MARKER) {
					c.renderFreeMarker(view);
				} else if(rt==RenderType.JSP) {
					c.renderJsp(view);
				} else if(rt==RenderType.VELOCITY) {
					c.renderVelocity(view);
				} else if(rt==RenderType.XML) {
					c.renderXml(view);
				} else if(rt==RenderType.FILE) {
					c.renderFile(view);
				} else {
					c.render(view);
				}
			} else {
				c.render(view);
			}
		} else {
			c.renderJson();
		}
	}
}
