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
package com.lambkit.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

public class CommonInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		// 参数CTL_PATH设置
		Controller controller = inv.getController();
		String ctl = inv.getControllerKey();
		// ctl = ctl.endsWith("/") ? ctl : ctl + "/";
		controller.setAttr("ckey", ctl);
		controller.setAttr("akey", inv.getActionKey());
		
		/// 非浏览器请求返回失败
		String userAgent = controller.getRequest().getHeader("user-agent");
		if (!StrKit.notBlank(userAgent)) {
			controller.renderError(404);
		} else {
			inv.invoke();
		}
	}

}
