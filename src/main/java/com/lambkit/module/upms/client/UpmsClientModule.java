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

import com.jfinal.config.Interceptors;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.module.LambkitModule;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.plugin.auth.cache.UserCache;
import com.lambkit.module.upms.UpmsAuthServiceImpl;
import com.lambkit.module.upms.UpmsConfig;
import com.lambkit.module.upms.UpmsInterceptor;
import com.lambkit.module.upms.UpmsManager;

public class UpmsClientModule extends LambkitModule {

	public UpmsClientModule() {
		// TODO Auto-generated constructor stub
		AuthManager.me().init(UserCache.class, UpmsAuthServiceImpl.class);
	}
	
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		super.configRoute(me);
		//ShiroAnnotionManager.me().init(me);
	}
	
	
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		super.configPlugin(me);
		
	}
	
	
	public void configMapping(ActiveRecordPluginWrapper arp) {
		// TODO Auto-generated method stub
		super.configMapping(arp);
	}
	
	
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		super.configInterceptor(me);
		//加入shiro验证
		me.add(new ShiroClientInterceptor());
		//me.add(new ShiroInterceptor());
		//me.add(new ShiroSsoInterceptor());
		me.add(new UpmsInterceptor());
		//me.add(new UserAccessInterceptor());
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub\
		UpmsManager.me().addTag(this);
		UpmsConfig config = ConfigManager.me().get(UpmsConfig.class);
		UpmsManager.me().registerService(config.getRpcGroup(), config.getRpcVersion(), config.getRpcPort());
	}
}
