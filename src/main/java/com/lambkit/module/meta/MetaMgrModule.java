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
package com.lambkit.module.meta;

import com.jfinal.config.Routes;
import com.jfinal.kit.StrKit;
import com.lambkit.Lambkit;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.db.mgr.MgrdbConfig;
import com.lambkit.db.mgr.MgrdbManager;
import com.lambkit.module.LambkitModule;
import com.lambkit.module.meta.web.controller.MetaDataApiController;
import com.lambkit.module.meta.web.controller.MetaViewController;

public class MetaMgrModule extends LambkitModule {
	
	@Override
	public void configMapping(ActiveRecordPluginWrapper arp) {
		if(StrKit.isBlank(MetaMgrManager.me().getConfig().getDbconfig())) {
			MetaMgrManager.me().mapping(arp);
		}
	}
	
	@Override
	public void configMapping(String name, ActiveRecordPluginWrapper arp) {
		super.configMapping(name, arp);
		if(StrKit.notBlank(name) && name.equals(MetaMgrManager.me().getConfig().getDbconfig())) {
			MetaMgrManager.me().mapping(arp);
		}
	}
	
	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		MgrdbConfig config = Lambkit.config(MgrdbConfig.class);
		if(config.isUseController()) {
			me.add("/lambkit/mgrdb/meta", MetaViewController.class, "/lambkit/mgrdb/meta");
			me.add("/lambkit/mgrdb/meta/api", MetaDataApiController.class);
		}
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		MetaMgrManager.me().addTag(this);
		MetaMgrManager.me().registerLocalService();
		MgrdbManager.me().init(MgrdbMetaServiceImpl.class);
	}
	
}
