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

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.lambkit.common.base.Consts;
import com.lambkit.core.cache.CacheManager;
import com.lambkit.core.cache.ICache;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.session.Session;
import com.lambkit.core.session.SessionManager;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.module.DefaultModule;
import com.lambkit.module.LambkitModule;
import com.lambkit.server.LambkitServer;
import com.lambkit.server.LambkitServerConfig;
import com.lambkit.server.LambkitServerFactory;

public class Lambkit {
	
	private static Map<String, String> argMap;
	
	private LambkitModule module;
	private LambkitConfig config = null;
	private LambkitServer server = null;
	private Boolean devMode = null;
	
	private static Lambkit lambkit = new Lambkit();
	
	public static Lambkit me() {
        return lambkit;
    }
	
	/**
     * main 入口方法
     *
     * @param args
     */
    public static void main(String[] args) {
        run(args);
    }

    public static void run(String[] args) {
    	System.out.println("class path: " + PathKit.getRootClassPath());
    	System.out.println("webroot path: " + PathKit.getWebRootPath());
    	System.out.println("user path: " + System.getProperty("user.dir"));
        parseArgs(args);
        /*
        String scanIntervalSeconds = getBootArg("scanIntervalSeconds");
        if(StrKit.isBlank(scanIntervalSeconds)) {
        	JFinal.start(getBootArg("webAppDir","src/main/webapp"), 
            		Integer.parseInt(getBootArg("port","8080")), 
            		getBootArg("context","/"));
        } else {
        	JFinal.start(getBootArg("webAppDir","src/main/webapp"), 
            		Integer.parseInt(getBootArg("port","8080")), 
            		getBootArg("context","/"),
            		Integer.parseInt(scanIntervalSeconds));
        }
        */
        lambkit.start();
    }
    
    /**
     * 解析启动参数
     *
     * @param args
     */
    private static void parseArgs(String[] args) {
        if (args == null || args.length == 0) {
            return;
        }

        for (String arg : args) {
            int indexOf = arg.indexOf("=");
            if (arg.startsWith("--") && indexOf > 0) {
                String key = arg.substring(2, indexOf);
                String value = arg.substring(indexOf + 1);
                setBootArg(key, value);
            }
        }
    }

    public static void setBootArg(String key, Object value) {
        if (argMap == null) {
            argMap = new HashMap<>();
        }
        argMap.put(key, value.toString());
    }

    /**
     * 获取启动参数
     *
     * @param key
     * @return
     */
    public static String getBootArg(String key) {
        if (argMap == null) return null;
        return argMap.get(key);
    }
    
    public static String getBootArg(String key, String defaultValue) {
        if (argMap == null) return defaultValue;
        String value = argMap.get(key);
        if(StrKit.isBlank(value)) return defaultValue;
        return value;
    }

    public static Map<String, String> getBootArgs() {
        return argMap;
    }
    
    /**
     * 开始启动
     */
    public void start() {
        printBannerInfo();
        printLambkitConfigInfo();
        printServerConfigInfo();

        ensureServerCreated();
        
        printServerPath();
        //printServerUrl();
        
        if (!startServer()) {
            System.err.println("lambkit start fail!!!");
            return;
        }
        
        if (isDevMode()) {
            //AutoDeployManager.me().run();
        }
        server.startAfter();
    }

    private boolean startServer() {
        return server.start();
    }

    private void ensureServerCreated() {
        if (server == null) {
            LambkitServerFactory factory = LambkitServerFactory.me();
            server = factory.buildServer();
        }
    }
    
    private void printBannerInfo() {
        System.out.println(getBannerText());
    }

    private String getBannerText() {
        LambkitConfig config = getLambkitConfig();

        if (!config.isBannerEnable()) {
            return "";
        }
        /*
        File bannerFile = new File(getRootClassPath(), config.getBannerFile());
        if (bannerFile.exists() && bannerFile.canRead()) {
            String bannerFileText = FileUtils.readString(bannerFile);
            if (StrKit.notBlank(bannerFileText)) {
                return bannerFileText;
            }
        }
        */
        return 
        		"++++++++++++++++++++++++++++++++++++++++++\n" +
                "+ Lambkit Framework. version " + Consts.LAMBKIT_VERSION + "       +\n" +
                "+ website: www.lambkit.com               +\n" +
                "++++++++++++++++++++++++++++++++++++++++++";

    }

    private void printLambkitConfigInfo() {
        System.out.println(getLambkitConfig());
    }

    private void printServerConfigInfo() {
        System.out.println(config(LambkitServerConfig.class));
    }

    private void printServerPath() {
        System.out.println("Server classPath at " + getRootClassPath());
    }


    public void printServerUrl() {
    	LambkitServerConfig serverConfig = config(LambkitServerConfig.class);

        String host = "0.0.0.0".equals(serverConfig.getHost()) ? "127.0.0.1" : serverConfig.getHost();
        String port = "80".equals(serverConfig.getPort()) ? "" : ":" + serverConfig.getPort();
        String path = serverConfig.getContextPath();

        String url = String.format("http://%s%s%s", host, port, path);
        System.out.println("Please visit our website at " + url);//Server started success. 
        //System.out.println("----------------------------------------------------------------------");
    }

    private static String getRootClassPath() {
        String path = null;
        try {
            path = Lambkit.class.getClassLoader().getResource("").toURI().getPath();
            return new File(path).getAbsolutePath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }


    ///////////get component methods///////////


    /**
     * 是否是开发模式
     *
     * @return
     */
    public boolean isDevMode() {
        if (devMode == null) {
            LambkitConfig config = getLambkitConfig();
            devMode = LambkitMode.DEV.getValue().equals(config.getMode());
        }
        return devMode;
    }
    
    public void addModule(LambkitModule module) {
    	if(this.module==null) {
    		this.module = new DefaultModule();
    	}
    	this.module.addModule(module);
	}
    
	public LambkitModule getModule() {
		if(module==null) {
			module = new DefaultModule();
		}
		return module;
	}
	
	public void setModule(LambkitModule lambkitModule) {
		this.module = lambkitModule;
	}
	
	/**
     * 获取Config 配置文件
     *
     * @return
     */
    public LambkitConfig getLambkitConfig() {
        if (config == null) {
        	config = config(LambkitConfig.class);
        }
        return config;
    }

	/**
     * 获取本地server 例如，jetty
     *
     * @return
     */
    public LambkitServer getServer() {
    	if (server == null) {
            LambkitServerFactory factory = LambkitServerFactory.me();
            server = factory.buildServer();
        }
    	return server;
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
    public Node getNode() {
		return NodeManager.me().getNode();
	}
    
    public ICache getCache() {
    	return CacheManager.me().getCache();
    }
    
    public Session getSession() {
    	return SessionManager.me().getSession();
    }

    private static Boolean isRunInjar = null;

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
