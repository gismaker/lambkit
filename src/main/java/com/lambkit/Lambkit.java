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
package com.lambkit;

import java.util.Map;

import com.lambkit.common.LambkitConfig;
import com.lambkit.core.cache.CacheManager;
import com.lambkit.core.cache.LambkitCache;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.session.Session;
import com.lambkit.core.session.SessionManager;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.module.DefaultModule;
import com.lambkit.module.LambkitModule;

public class Lambkit {
	
	/**
     * 获取Config 配置文件
     *
     * @return
     */
    public static LambkitConfig getLambkitConfig() {
        return config(LambkitConfig.class);
    }

    /**
     * 获取配置信息
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T config(Class<T> clazz) {
        return ConfigManager.me().get(clazz);
    }

    /**
     * 读取配置文件信息
     *
     * @param clazz
     * @param prefix
     * @param <T>
     * @return
     */
    public static <T> T config(Class<T> clazz, String prefix) {
        return ConfigManager.me().get(clazz, prefix);
    }
    
    /**
     * 获取系统节点信息
     * @return
     */
    public static Node getNode() {
		return NodeManager.me().getNode();
	}
    
    public static LambkitCache getCache() {
    	return CacheManager.me().getCache();
    }
    
    public static Session getSession() {
    	return SessionManager.me().getSession();
    }
    
    /**
     * 是否是开发模式
     *
     * @return
     */
    public static boolean isDevMode() {
        return getLambkitConfig().isDevMode();
    }
    
    /*****************************
     * 全局变量定义
     *****************************/
    
    private static LambkitModule module;
    private static Boolean isRunInjar = null;
    
    public static void setArg(String key, Object value) {
        ConfigManager.me().setArg(key, value);
    }

    /**
     * 获取启动参数
     *
     * @param key
     * @return
     */
    public static String getArg(String key) {
        return ConfigManager.me().getArg(key);
    }
    
    public static String getArg(String key, String defaultValue) {
        return ConfigManager.me().getArg(key, defaultValue);
    }
    
    public static Map<String, String> getArgs() {
        return ConfigManager.me().getArgs();
    }
    
    public static void addModule(LambkitModule module) {
    	getModule().addModule(module);
	}
    
	public static LambkitModule getModule() {
		if(module==null) {
			module = new DefaultModule();
		}
		return module;
	}
	
	public static void setModule(LambkitModule lambkitModule) {
		module = lambkitModule;
	}
	
	 /**
     * 是否在jar包里运行
     *
     * @return
     */
    public static boolean isRunInJar() {
        if (isRunInjar == null) {
            isRunInjar = Thread.currentThread().getContextClassLoader().getResource("") == null;
        }
        return isRunInjar;
    }
}
