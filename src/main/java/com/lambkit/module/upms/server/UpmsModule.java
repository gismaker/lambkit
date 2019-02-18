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

import com.jfinal.config.Interceptors;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.StrKit;
import com.lambkit.Lambkit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.module.LambkitModule;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.plugin.auth.cache.UserCache;
import com.lambkit.module.upms.client.auth.UpmsAuthServiceImpl;
import com.lambkit.module.upms.client.shiro.ShiroSsoInterceptor;
import com.lambkit.module.upms.common.UpmsConfig;
import com.lambkit.module.upms.common.UpmsManager;
import com.lambkit.module.upms.server.interceptor.UpmsInterceptor;

public class UpmsModule extends LambkitModule {

	public UpmsModule() {
		AuthManager.me().init(UserCache.class, UpmsAuthServiceImpl.class);
	}
	
	public void configRoute(Routes me) {
		super.configRoute(me);
		me.add(new UpmsRoutes());
	}
	
	
	public void configPlugin(Plugins me) {
	}
	
	public void configMapping(ActiveRecordPluginWrapper arp) {
		UpmsConfig config = Lambkit.config(UpmsConfig.class);
		if(StrKit.isBlank(config.getDbconfig())) {
			UpmsManager.me().mapping(arp);
		}
	}
	
	public void configMapping(String name, ActiveRecordPluginWrapper arp) {
		super.configMapping(name, arp);
		UpmsConfig config = Lambkit.config(UpmsConfig.class);
		System.out.println("mapping name: " + name + ", this config is " + config.getDbconfig());
		if(StrKit.notBlank(name) && name.equals(config.getDbconfig())) {
			UpmsManager.me().mapping(arp);
		}
	}
	
	
	public void configInterceptor(Interceptors me) {
		super.configInterceptor(me);
		//加入shiro验证
		me.add(new ShiroSsoInterceptor());
		me.add(new UpmsInterceptor());
	}

	@Override
	public void afterJFinalStart() {
		UpmsConfig config = ConfigManager.me().get(UpmsConfig.class);
		UpmsManager.me().registerService(config.getRpcGroup(), config.getRpcVersion(), config.getRpcPort());
	}
}
