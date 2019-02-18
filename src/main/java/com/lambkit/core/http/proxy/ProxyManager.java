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
package com.lambkit.core.http.proxy;

import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.common.collect.Maps;
import com.jfinal.config.Handlers;
import com.jfinal.kit.StrKit;
import com.lambkit.Lambkit;
import com.lambkit.core.config.ConfigManager;

/**
 * 反向代理（Reverse Proxy）
 * @author 孤竹行
 *
 */
public class ProxyManager {
	private static final String DATASOURCE_PREFIX = "lambkit.http.proxy.";
	
	private static ProxyManager manager = new ProxyManager();

    public static ProxyManager me() {
        return manager;
    }
    
	private Map<String, ProxyConfig> proxyConfigs = Maps.newHashMap();
    
	public ProxyManager() {
		ProxyConfig proxyConfig = ConfigManager.me().get(ProxyConfig.class, "lambkit.http.proxy");
		proxyConfig.setName(ProxyConfig.NAME_DEFAULT);
        if (proxyConfig.isConfigOk()) {
        	proxyConfigs.put(proxyConfig.getName(), proxyConfig);
        }

        Properties prop = ConfigManager.me().getProperties();
        Set<String> proxyNames = new HashSet<>();
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            String key = entry.getKey().toString();
            if (key.startsWith(DATASOURCE_PREFIX) && entry.getValue() != null) {
                String[] keySplits = key.split("\\.");
                if (keySplits.length == 5) {
                	proxyNames.add(keySplits[3]);
                }
            }
        }


        for (String name : proxyNames) {
        	ProxyConfig dsc = ConfigManager.me().get(ProxyConfig.class, DATASOURCE_PREFIX + name);
            if (StrKit.isBlank(dsc.getName())) {
                dsc.setName(name);
            }
            if (dsc.isConfigOk()) {
            	proxyConfigs.put(name, dsc);
            }
        }
	}
	
	public void init(Handlers me) {
		for (ProxyConfig config : proxyConfigs.values()) {
			if(Lambkit.me().isDevMode()) {
				System.out.println("http-proxy: " + config.getName() + " from " + config.getUrlpattern() + " to " + config.getTargetUri());
			}
			me.add(new ProxyHandler(config));
		}
	}
	
	public Map<String, ProxyConfig> getProxyConfigs() {
		return proxyConfigs;
	}

	public void setProxyConfigs(Map<String, ProxyConfig> proxyConfigs) {
		this.proxyConfigs = proxyConfigs;
	}
	
	public ProxyConfig getDefaultProxyConfigs() {
    	return proxyConfigs.get(ProxyConfig.NAME_DEFAULT);
    }
}
