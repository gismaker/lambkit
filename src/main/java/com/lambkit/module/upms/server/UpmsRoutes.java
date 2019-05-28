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
package com.lambkit.module.upms.server;

import com.jfinal.config.Routes;
import com.lambkit.module.upms.server.controller.ManageController;
import com.lambkit.module.upms.server.controller.SSOController;
import com.lambkit.module.upms.server.controller.UpmsLogController;
import com.lambkit.module.upms.server.controller.UpmsOrganizationController;
import com.lambkit.module.upms.server.controller.UpmsPermissionController;
import com.lambkit.module.upms.server.controller.UpmsRoleController;
import com.lambkit.module.upms.server.controller.UpmsSessionController;
import com.lambkit.module.upms.server.controller.UpmsSystemController;
import com.lambkit.module.upms.server.controller.UpmsUserController;

public class UpmsRoutes extends Routes {

	
	public void config() {
		setBaseViewPath("/lambkit/upms");
		add("/upms", ManageController.class);
		add("/sso", SSOController.class);
		
		add("/upms/log", UpmsLogController.class);
		add("/upms/organization", UpmsOrganizationController.class);
		add("/upms/permission", UpmsPermissionController.class);
		add("/upms/role", UpmsRoleController.class);
		add("/upms/session", UpmsSessionController.class);
		add("/upms/system", UpmsSystemController.class);
		add("/upms/user", UpmsUserController.class);
	}

}
