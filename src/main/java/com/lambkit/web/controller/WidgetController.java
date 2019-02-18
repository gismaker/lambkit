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

import com.jfinal.kit.StrKit;

public class WidgetController extends BaseController {

	public void index() {
		String akey = getPara(0)==null ? getPara("request") : getPara(0);
		boolean kp = getParaToBoolean("kp", true);
		if(kp) keepPara();
		WidgetResult result = null;
		if(StrKit.notBlank(akey)) {
			Widget action = WidgetManager.me().getWidget(akey);
			if(action!=null) {
				result = action.execute(this);
				if(result!=null) {
					switch (result.getResultType()) {
					case OVER:
						return;
					case ERROR:
						renderError(result.getErrorCode());
						return;
					case REDIRECT:
						redirect(result.getUrl());
						return;
					case VIEW:
						break;
					default:
						break;
					}
				}
			}
		}
		String html = getPara("p");
		if(StrKit.notBlank(html)) {
			if(html.endsWith(".html")) render(html);
			else render(html + ".html");
		} else if(result!=null && StrKit.notBlank(result.getView())) {
			RenderType rt = result.getRenderType();
			if(rt!=null) {
				//渲染器
				if(rt==RenderType.JSON) {
					renderJson();
				} else if(rt==RenderType.JFINAL_TEMPLATE) {
					renderTemplate(result.getView());
				} else if(rt==RenderType.FREE_MARKER) {
					renderFreeMarker(result.getView());
				} else if(rt==RenderType.JSP) {
					renderJsp(result.getView());
				} else if(rt==RenderType.VELOCITY) {
					renderVelocity(result.getView());
				} else if(rt==RenderType.XML) {
					renderXml(result.getView());
				} else {
					render(result.getView());
				}
			} else {
				render(result.getView());
			}
		} else {
			renderJson();
		}
	}
}
