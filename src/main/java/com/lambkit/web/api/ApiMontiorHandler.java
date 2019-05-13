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
package com.lambkit.web.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.lambkit.common.LambkitManager;
import com.lambkit.common.bean.ActionBean;
import com.lambkit.distributed.node.NodeManager;

public class ApiMontiorHandler extends Handler {

	private static String[] urlPara = {null};
	
	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		next.handle(target, request, response, isHandled);
		long endTime = System.currentTimeMillis();
		
		ActionBean action = LambkitManager.me().getInfo().getActionMapping().getAction(target, urlPara);
		if(action!=null) {
			action.access(startTime, endTime);
			NodeManager.me().updateApiTime(action.getActionKey(), endTime - startTime);
		}
		if(target.indexOf('.')==-1) {
			System.out.println("Api of [" + target + "] use time：" + (endTime - startTime) + " ms");
		}
	}

}
