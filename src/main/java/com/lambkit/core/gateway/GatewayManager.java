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
package com.lambkit.core.gateway;

import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.common.collect.Maps;
import com.jfinal.kit.StrKit;
import com.lambkit.common.util.StringUtils;
import com.lambkit.core.config.ConfigManager;

/**
 * 网关
 * 反向代理（Reverse Proxy）
 * @author 孤竹行
 */
public class GatewayManager {
	private static final String GATEWAY_PREFIX = "lambkit.gateway.";
	
	private static GatewayManager manager = new GatewayManager();

    public static GatewayManager me() {
        return manager;
    }
    
	private Map<String, GatewayConfig> gatewayConfigs = Maps.newHashMap();
//	private  Gateway gateway;
    
	public GatewayManager() {
		GatewayConfig config = ConfigManager.me().get(GatewayConfig.class, "lambkit.gateway");
		config.setName(GatewayConfig.NAME_DEFAULT);
        if (config.isConfigOk()) {
        	gatewayConfigs.put(config.getName(), config);
//        	gateway = new Gateway(config);
        } else {
//        	gateway = new Gateway();
        }

        Properties prop = ConfigManager.me().getProperties();
        Set<String> names = new HashSet<>();
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            String key = entry.getKey().toString();
            if (key.startsWith(GATEWAY_PREFIX) && entry.getValue() != null) {
                String[] keySplits = key.split("\\.");
                if (keySplits.length == 3) {
                	names.add(keySplits[2]);
                }
            }
        }

        for (String name : names) {
        	GatewayConfig dsc = ConfigManager.me().get(GatewayConfig.class, GATEWAY_PREFIX + name);
            if (StrKit.isBlank(dsc.getName())) {
                dsc.setName(name);
            }
            if (dsc.isConfigOk()) {
            	gatewayConfigs.put(name, dsc);
            }
        }
	}
	
	public Map<String, GatewayConfig> getConfigs() {
		return gatewayConfigs;
	}

	public void setConfigs(Map<String, GatewayConfig> gatewayConfigs) {
		this.gatewayConfigs = gatewayConfigs;
	}
	
	public GatewayConfig getDefaultConfig() {
    	return gatewayConfigs.get(GatewayConfig.NAME_DEFAULT);
    }
	
	public GatewayConfig getConfig(String target) {
		if(StrKit.isBlank(target)) return null;
		for (GatewayConfig config : gatewayConfigs.values()) {
			if(config!=null && StringUtils.isMatch(target, config.getUrlpattern())) {
				return config;
			}
		}
    	return null;
    }

	public Gateway getGateway() {
		GatewayConfig config = getDefaultConfig();
		if(config!=null) {
			return new Gateway(config);
        } else {
        	return new Gateway();
        }
	}
//
//	public void setGateway(Gateway gateway) {
//		this.gateway = gateway;
//	}
	
}
