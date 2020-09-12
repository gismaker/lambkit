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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.kit.StrKit;
import com.lambkit.LambkitApplicationContext;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.db.meta.MetaKit;
import com.lambkit.db.meta.TableMeta;
import com.lambkit.module.LambkitModule;
import com.lambkit.module.sysconfig.SysconfigModule;

public class MgrdbManager {
	private static MgrdbManager manager = new MgrdbManager();

	private MgrdbService mgrdbService;
	private MgrdbConfig config = ConfigManager.me().get(MgrdbConfig.class);
	private Map<String, MgrdbService> serviceMap;
	private Map<String, Class<? extends LambkitModule>> mgrdbModule;

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
		registModule(MgrdbConfig.SYSCONFIG, SysconfigModule.class);
	}
	
	public void init(String name, Class<? extends MgrdbService> cacheClazz) {
		if(serviceMap==null) {
			serviceMap = new HashMap<String, MgrdbService>();
		}
		try {
			serviceMap.put(name, cacheClazz.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		registModule(MgrdbConfig.SYSCONFIG, SysconfigModule.class);
	}
	
	public void registModule(String name, Class<? extends LambkitModule> moduleClass) {
		if(mgrdbModule==null) {
			mgrdbModule = new HashMap<String, Class<? extends LambkitModule>>();
		}
		mgrdbModule.put(name, moduleClass);
	}
	
	void destroy() {
	}

	public MgrdbService getService() {
		if(mgrdbService==null) {
			setService(new MgrdbServiceImpl());
		}
		return mgrdbService;
	}
	
	public MgrdbService getService(String name) {
		if(serviceMap==null) {
			return null;
		}
		return serviceMap.get(name);
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
		return getLambkitModule(config.getType());
	}
	
	public LambkitModule getLambkitModule(String type) {
		LambkitModule module = null;
		if(StrKit.isBlank(type)) return module;
		if(mgrdbModule!=null) {
			Class<? extends LambkitModule> moduleClass = mgrdbModule.get(type);
			if(moduleClass!=null) {
				try {
					module = moduleClass.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return module;
	}
	
	public void run(Map<String, Object> options, String type) {
		LambkitApplication application = new LambkitApplication(LambkitApplicationContext.class);
		application.setWebEnvironment(false);
		if(MgrdbConfig.ALL.equals(type)) {
			if(mgrdbModule!=null) {
				for (Class<? extends LambkitModule> moduleClass : mgrdbModule.values()) {
					if(moduleClass!=null) {
						try {
							LambkitModule module = moduleClass.newInstance();
							if(module!=null) {
								Lambkit.addModule(module);
							}
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		} else {
			LambkitModule module = getLambkitModule(type);
			if(module!=null) {
				Lambkit.addModule(module);
			}
		}
		application.run();
		MgrdbService service = MgrdbManager.me().getService();
		Map<String, TableMeta> tableMetas = MetaKit.getTableMetas(options);
		for (Entry<String, TableMeta> entry : tableMetas.entrySet()) {
			System.out.println("table: "+entry.getKey());
			service.tableToMgrdb(entry.getValue());
        }
		System.out.println("-------over-------");
		application.stop();
	}

	public Map<String, MgrdbService> getServiceMap() {
		return serviceMap;
	}

	public void setServiceMap(Map<String, MgrdbService> serviceMap) {
		this.serviceMap = serviceMap;
	}
}
