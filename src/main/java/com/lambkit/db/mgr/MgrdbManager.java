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
package com.lambkit.db.mgr;

import com.jfinal.kit.StrKit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.module.LambkitModule;
import com.lambkit.module.meta.MetaMgrModule;
import com.lambkit.module.sysconfig.SysconfigModule;

public class MgrdbManager {
	private static MgrdbManager manager = new MgrdbManager();

	private MgrdbService mgrdbService;
	private MgrdbConfig config = ConfigManager.me().get(MgrdbConfig.class);

	private MgrdbManager() {
	}

	public static MgrdbManager me() {
		return manager;
	}

	public void init(Class<? extends MgrdbService> cacheClazz) {
		try {
			setService(cacheClazz.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	void destroy() {
	}

	public MgrdbService getService() {
		if(mgrdbService==null) {
			setService(new MgrdbServiceImpl());
		}
		return mgrdbService;
	}

	public void setService(MgrdbService mgrdbService) {
		this.mgrdbService = mgrdbService;
	}

	public MgrdbConfig getConfig() {
		return config;
	}

	public void setConfig(MgrdbConfig config) {
		this.config = config;
	}
	
	public LambkitModule getLambkitModule() {
		LambkitModule module = null;
		if(StrKit.isBlank(config.getType())) return module;
		switch (config.getType()) {
		case MgrdbConfig.SYSCONFIG:
			module = new SysconfigModule();
			break;
		case MgrdbConfig.META:
			module = new MetaMgrModule();
			break;
		default:
			break;
		}
		return module;
	}
}
