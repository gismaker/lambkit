package com.lambkit.web;

import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.common.collect.Maps;
import com.lambkit.core.config.ConfigManager;


public class WebConfigManager {

    private static final String DATASOURCE_PREFIX = "lambkit.web.";

    private static WebConfigManager manager = null;

    public static WebConfigManager me() {
    	if(manager==null) {
    		manager = new WebConfigManager();
    	}
        return manager;
    }

    private Map<String, WebConfig> webConfigs = Maps.newHashMap();
    
    private WebConfigManager() {
        WebConfig defaultConfig = ConfigManager.me().get(WebConfig.class, "lambkit.web");
        defaultConfig.setName(WebConfig.NAME_DEFAULT);
        if (defaultConfig.isConfigOk()) {
            webConfigs.put(defaultConfig.getName(), defaultConfig);
        }

        Properties prop = ConfigManager.me().getProperties();
        Set<String> datasourceNames = new HashSet<>();
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            String key = entry.getKey().toString();
            if (key.startsWith(DATASOURCE_PREFIX) && entry.getValue() != null) {
                String[] keySplits = key.split("\\.");
                if (keySplits.length == 4) {
                    datasourceNames.add(keySplits[2]);
                }
            }
        }
        
        for (String name : datasourceNames) {
            WebConfig dsc = ConfigManager.me().get(WebConfig.class, DATASOURCE_PREFIX + name);
            dsc.setName(name);
            if (dsc.isConfigOk()) {
                webConfigs.put(name, dsc);
            }
        }
    }
    
    public WebConfig getDefaultWebConfig() {
    	return webConfigs.get(WebConfig.NAME_DEFAULT);
    }
    
    public WebConfig getWebConfig(String configName) {
    	return webConfigs.get(configName);
    }

    public Map<String, WebConfig> getWebConfigs() {
        return webConfigs;
    }
}
