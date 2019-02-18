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
package com.lambkit.module.upms.client;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.module.upms.rpc.model.UpmsUser;
import com.lambkit.module.upms.rpc.api.UpmsApiService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录信息拦截器 
 */
public class UpmsInterceptor implements Interceptor {

	private static final String LAMBKIT_OSS_ALIYUN_OSS_POLICY = PropKit.get("lambkit.oss.aliyun.oss.policy");

	//// @Autowired
	UpmsApiService upmsApiService = RpcKit.obtain(UpmsApiService.class);

	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		HttpServletRequest request = inv.getController().getRequest();
		request.setAttribute("LAMBKIT_OSS_ALIYUN_OSS_POLICY", LAMBKIT_OSS_ALIYUN_OSS_POLICY);
		// 过滤ajax
		if (null != request.getHeader("X-Requested-With")
				&& request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
			// 不处理
		} else {
			// 登录信息
			Subject subject = SecurityUtils.getSubject();
			String username = (String) subject.getPrincipal();
			if (StrKit.notBlank(username)) {
				UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
				request.setAttribute("upmsUser", upmsUser);
			}

		}
		inv.invoke();
	}
}
