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
package com.lambkit.server.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.config.Plugins;
import com.jfinal.json.JsonManager;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.lambkit.component.redis.RedisManager;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.db.DbManager;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.module.LambkitModule;

import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import net.sf.ehcache.config.DiskStoreConfiguration;

public class AppModule extends LambkitModule {

	private List<LambkitModule> modules = null;
	
	@Override
	public void configPlugin(Plugins me) {
		String cache = ConfigManager.me().getValue("lambkit.cache.type");
		if("ehcache".equals(cache)){
			EhCachePlugin ehCachePlugin = createEhCachePlugin();
			if(ehCachePlugin!=null) me.add(ehCachePlugin);
		}
		if("redis".equals(cache)) {
			RedisPlugin redisPlugin = RedisManager.me().getPlugin();
			if(redisPlugin!=null) me.add(redisPlugin);
		}
		List<ActiveRecordPluginWrapper> arps = DbManager.me().init(me);
        for (ActiveRecordPluginWrapper arp : arps) {
        	me.add(arp.getPlugin());
        	if(DbKit.MAIN_CONFIG_NAME.equals(arp.getConfig().getName())) {
        		if(modules==null) continue;
        		for (LambkitModule module : modules) {
        			module.configMapping(arp);
        		}
        	}
        }
	}
	
	private EhCachePlugin createEhCachePlugin() {
		String path = ConfigManager.me().getValue("lambkit.ehcache.path");
		if(StrKit.notBlank(path)) {
			String ehcacheDiskStorePath = PathKit.getWebRootPath();
			if("webrootpath".equalsIgnoreCase(path)){
				ehcacheDiskStorePath = PathKit.getWebRootPath();
			} else if("classpath".equalsIgnoreCase(path)) {
				ehcacheDiskStorePath = PathKit.getRootClassPath();
			}
			File pathFile = new File(ehcacheDiskStorePath, ".ehcache");

			Configuration cfg = ConfigurationFactory.parseConfiguration();
			cfg.addDiskStore(new DiskStoreConfiguration().path(pathFile.getAbsolutePath()));
			
			return new EhCachePlugin(cfg);
		}
		return new EhCachePlugin();
		//return new EhCachePlugin(ehcacheDiskStorePath + File.separator + "ehcache.xml");
	}
	
	@Override
	public void afterJFinalStart() {
		JsonManager.me().setDefaultDatePattern("yyyy-MM-dd HH:mm:ss");
		if(modules==null) return;
		for (LambkitModule module : modules) {
			module.afterJFinalStart();
		}
	}
	
	@Override
	public void beforeJFinalStop() {
		if(modules==null) return;
		for (LambkitModule module : modules) {
			module.beforeJFinalStop();
		}
	}
	
	public void addModule(LambkitModule module) {
		if(modules==null) modules = new ArrayList<LambkitModule>();
		modules.add(module);
	}
	
	public List<LambkitModule> getModules() {
		return modules;
	}

	public void setModules(List<LambkitModule> configs) {
		this.modules = configs;
	}
}
