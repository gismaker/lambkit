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
package com.lambkit.component.redis;

import com.jfinal.config.Plugins;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.redis.RedisPlugin;
import com.lambkit.Lambkit;
import com.lambkit.core.aop.AopKit;

public class RedisManager {
	private static RedisManager manager;
	private RedisPlugin plugin;
	private RedisConfig config = Lambkit.config(RedisConfig.class);

    public static RedisManager me() {
        if (manager == null) {
            manager = AopKit.singleton(RedisManager.class);
        }
        return manager;
    }
    
    public void addDefaultPlugin(Plugins me) {
    	if(plugin==null) {
    		if(StrKit.notBlank(config.getPassword())) {
    			plugin = new RedisPlugin(config.getDatabase(), config.getAddress(), config.getPassword());
    		} else {
    			plugin = new RedisPlugin(config.getDatabase(), config.getAddress());
    		}
    		me.add(plugin);
    	}
    }
    
    public RedisPlugin getPlugin() {
    	if(plugin==null) {
    		if(StrKit.notBlank(config.getPassword())) {
    			plugin = new RedisPlugin(config.getDatabase(), config.getAddress(), config.getPassword());
    		} else {
    			plugin = new RedisPlugin(config.getDatabase(), config.getAddress());
    		}
    	}
    	return plugin;
    }

    public RedisPlugin getPlugin(String cacheName, String host) {
    	return new RedisPlugin(cacheName, host);
    }
    
    public RedisPlugin getPlugin(String cacheName, String host, String password) {
    	return new RedisPlugin(cacheName, host, password);
    }
}
