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
package com.lambkit.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.lambkit.Lambkit;
import com.lambkit.plugin.auth.AuthManager;

public class LambkitDruidStatViewHandler extends DruidStatViewHandler {
	
	static String visitPath = "/lambkit/dev/druid";

	public LambkitDruidStatViewHandler() {
		super(visitPath);
	}

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		if (target.startsWith(visitPath)) {
			if(Lambkit.me().isDevMode()) {
				super.handle(target, request, response, isHandled);
				return;
			} else if (AuthManager.me().hasUser(request)) {
				if (AuthManager.me().getRoleService().isSuperAdmin()) {
					super.handle(target, request, response, isHandled);
					return;
				}
			}
		} else {
			next.handle(target, request, response, isHandled);
		}
	}
}
