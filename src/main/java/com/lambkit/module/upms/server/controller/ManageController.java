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
package com.lambkit.module.upms.server.controller;

import com.jfinal.kit.StrKit;
import com.lambkit.web.controller.BaseController;
import com.lambkit.component.swagger.annotation.Api;
import com.lambkit.component.swagger.annotation.ApiOperation;
import com.lambkit.core.aop.AopKit;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.rpc.model.*;
import com.lambkit.module.upms.rpc.model.sql.UpmsSystemCriteria;
import com.lambkit.module.upms.rpc.api.UpmsApiService;
import com.lambkit.module.upms.rpc.service.impl.UpmsApiServiceImpl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * 后台controller
 */
//@RequestMapping("/upms")
@Api(tag = "upms", description = "后台管理")
@RequiresAuthentication
public class ManageController extends BaseController {

	//private static Logger _log = LoggerFactory.getLogger(ManageController.class);
	private UpmsApiService upmsApiService = AopKit.get(UpmsApiServiceImpl.class);

	@ApiOperation(url = "/upms/index", tag = "upms", httpMethod = "get", description = "后台首页")
	public void index() {
		// 已注册系统
		Example upmsSystemExample = UpmsSystemCriteria.create()
				.andStatusEqualTo(1).example();
		List<UpmsSystem> upmsSystems = UpmsSystem.service().find(upmsSystemExample);
		setAttr("upmsSystems", upmsSystems);
		// 当前登录用户权限
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		System.out.println("auth:"+subject.isAuthenticated()+", name:"+username);
		if(StrKit.isBlank(username)) {
			// shiro退出登录
	        SecurityUtils.getSubject().logout();
			redirect("sso/login");
			return;
		}
		UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
		if(upmsUser==null) {
			// shiro退出登录
	        SecurityUtils.getSubject().logout();
			redirect("sso/login");
			return;
		}
		List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
		setAttr("upmsPermissions", upmsPermissions);
		renderTemplate("index.html");
	}

}