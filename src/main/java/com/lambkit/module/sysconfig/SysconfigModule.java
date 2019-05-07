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
package com.lambkit.module.sysconfig;

import com.jfinal.config.Routes;
import com.lambkit.Lambkit;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.db.mgr.MgrdbConfig;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.module.LambkitModule;
import com.lambkit.module.sysconfig.web.controller.SysconfigDataApiController;
import com.lambkit.module.sysconfig.web.controller.SysconfigViewController;

public class SysconfigModule extends LambkitModule {
	@Override
	public void configMapping(ActiveRecordPluginWrapper arp) {
		// TODO Auto-generated method stub
		arp.addMapping("sys_tableconfig", "tbid", TableconfigModel.class);
		arp.addMapping("sys_fieldconfig", "fldid", FieldconfigModel.class);
	}
	
	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		MgrdbConfig config = Lambkit.config(MgrdbConfig.class);
		if(config.isUseController()) {
			me.add("/lambkit/mgrdb/sysconfig", SysconfigViewController.class, "/lambkit/mgrdb/sysconfig");
			me.add("/lambkit/mgrdb/sysconfig/api", SysconfigDataApiController.class);
		}
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		MgrdbManager.me().init(MgrdbSysconfigServiceImpl.class);
	}
}
