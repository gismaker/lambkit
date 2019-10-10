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
 */package com.lambkit.module.lms;

import com.jfinal.config.Handlers;
import com.jfinal.config.Routes;
import com.lambkit.Lambkit;
import com.lambkit.component.swagger.SwaggerController;
import com.lambkit.db.mgr.MgrdbConfig;
import com.lambkit.db.mgr.controller.MgrdbApiController;
import com.lambkit.db.mgr.controller.MgrdbController;
import com.lambkit.db.mgr.controller.MgrdbViewController;
import com.lambkit.module.LambkitModule;
import com.lambkit.module.lms.controller.FileController;
import com.lambkit.module.lms.controller.HandlerController;
import com.lambkit.module.lms.controller.IndexLmsController;
import com.lambkit.module.lms.controller.InterceptorController;
import com.lambkit.module.lms.controller.MappingController;
import com.lambkit.module.lms.controller.IndexMgrdbController;
import com.lambkit.module.lms.controller.MonitorController;
import com.lambkit.module.lms.controller.PluginController;
import com.lambkit.module.lms.controller.PropController;
import com.lambkit.module.lms.controller.RouteController;
import com.lambkit.module.lms.controller.IndexDevController;
import com.lambkit.module.lms.controller.TagController;
import com.lambkit.module.meta.web.controller.manage.ManageController;
import com.lambkit.module.meta.web.controller.manage.MetaAppController;
import com.lambkit.module.meta.web.controller.manage.MetaFieldController;
import com.lambkit.module.meta.web.controller.manage.MetaStoreController;
import com.lambkit.module.meta.web.controller.manage.MetaStoreDbController;
import com.lambkit.module.meta.web.controller.manage.MetaTableController;
import com.lambkit.web.handler.LambkitDruidStatViewHandler;

public class LmsModule extends LambkitModule {

	@Override
	public void configRoute(Routes routes) {
		LmsConfig lmsconfig = Lambkit.config(LmsConfig.class);
		routes.add(lmsconfig.getControllerKey(), IndexLmsController.class, lmsconfig.getViewPath());
		
		routes.add(lmsconfig.getControllerKey() + "/dev", IndexDevController.class, lmsconfig.getViewPath() + "/dev");
		routes.add(lmsconfig.getControllerKey() + "/dev/route", RouteController.class, lmsconfig.getViewPath() + "/dev/route");
		routes.add(lmsconfig.getControllerKey() + "/dev/handler", HandlerController.class, lmsconfig.getViewPath() + "/dev/handler");
		routes.add(lmsconfig.getControllerKey() + "/dev/plugin", PluginController.class, lmsconfig.getViewPath() + "/dev/plugin");
		routes.add(lmsconfig.getControllerKey() + "/dev/tag", TagController.class, lmsconfig.getViewPath() + "/dev/tag");
		routes.add(lmsconfig.getControllerKey() + "/dev/interceptor", InterceptorController.class, lmsconfig.getViewPath() + "/dev/interceptor");
		routes.add(lmsconfig.getControllerKey() + "/dev/mapping", MappingController.class, lmsconfig.getViewPath() + "/dev/mapping");
		routes.add(lmsconfig.getControllerKey() + "/dev/prop", PropController.class, lmsconfig.getViewPath() + "/dev/prop");
		routes.add(lmsconfig.getControllerKey() + "/dev/monitor", MonitorController.class, lmsconfig.getViewPath() + "/dev/monitor");
		routes.add(lmsconfig.getControllerKey() + "/dev/file", FileController.class, lmsconfig.getViewPath() + "/dev/file");
		routes.add(lmsconfig.getControllerKey() + "/dev/swagger", SwaggerController.class, lmsconfig.getViewPath() + "/dev/swagger");
		
		MgrdbConfig config = Lambkit.config(MgrdbConfig.class);
		if(MgrdbConfig.META.equals(config.getType())) {
			routes.add(lmsconfig.getControllerKey() + "/mgrdb/api", MgrdbApiController.class);
			routes.add(lmsconfig.getControllerKey() + "/mgrdb", MgrdbViewController.class, lmsconfig.getViewPath() + "/dev/mgrdb/enjoy");

			routes.add(lmsconfig.getControllerKey() + "/mgrdb/meta", ManageController.class, lmsconfig.getViewPath() + "/dev/mgrdb/meta");
			routes.add(lmsconfig.getControllerKey() + "/mgrdb/meta/app", MetaAppController.class, lmsconfig.getViewPath() + "/dev/mgrdb/meta/app");
			routes.add(lmsconfig.getControllerKey() + "/mgrdb/meta/store", MetaStoreController.class, lmsconfig.getViewPath() + "/dev/mgrdb/meta/store");
			routes.add(lmsconfig.getControllerKey() + "/mgrdb/meta/store/db", MetaStoreDbController.class, lmsconfig.getViewPath() + "/dev/mgrdb/meta/store/db");
			routes.add(lmsconfig.getControllerKey() + "/mgrdb/meta/store/db/table", MetaTableController.class, lmsconfig.getViewPath() + "/dev/mgrdb/meta/store/db/table");
			routes.add(lmsconfig.getControllerKey() + "/mgrdb/meta/store/db/field", MetaFieldController.class, lmsconfig.getViewPath() + "/dev/mgrdb/meta/store/db/field");
		} else if(MgrdbConfig.SYSCONFIG.equals(config.getType())) {
			routes.add(lmsconfig.getControllerKey() + "/mgrdb", MgrdbController.class, lmsconfig.getViewPath() + "/dev/mgrdb/freemarker");
		} 
		routes.add(lmsconfig.getControllerKey() + "/dev/mgrdb", IndexMgrdbController.class, lmsconfig.getViewPath() + "/dev/mgrdb");
	}
	
	@Override
	public void configHandler(Handlers me) {
		LmsConfig lmsconfig = Lambkit.config(LmsConfig.class);
		LambkitDruidStatViewHandler druidViewHandler = new LambkitDruidStatViewHandler(lmsconfig.getControllerKey() + "/dev/druid");
		me.add(druidViewHandler);
	}
}
