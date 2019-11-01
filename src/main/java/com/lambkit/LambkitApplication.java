package com.lambkit;

import com.jfinal.config.Plugins;
import com.jfinal.plugin.IPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.lambkit.common.util.TimeUtils;
import com.lambkit.core.aop.AopKit;
import com.lambkit.module.LambkitModule;

public class LambkitApplication {
	
	private boolean isWebEnvironment = true;
	private Plugins plugins = null;
	
	private Class<? extends LambkitApplicationContext> contextClass = null;
	
	public LambkitApplication(Class<? extends LambkitApplicationContext> jfinalConfigClass) {
		this.contextClass = jfinalConfigClass;
	}
	
	public static void main(String[] args) {
		run(LambkitApplicationContext.class, args);
	}
	
	public static void run(Class<? extends LambkitApplicationContext> jfinalConfigClass, String[] args) {
		parseArgs(args);
		if(jfinalConfigClass==null) {
			jfinalConfigClass = LambkitApplicationContext.class;
		}
		UndertowServer.create(jfinalConfigClass).configWeb(builder->{
			builder.addListener("org.apache.shiro.web.env.EnvironmentLoaderListener");
			builder.addFilter("shiro", "org.apache.shiro.web.servlet.ShiroFilter");
			builder.addFilterUrlMapping("shiro", "/*");
			// 配置 WebSocket，DefaultWebSocketServer 需使用 ServerEndpoint 注解
	        builder.addWebSocketEndpoint("com.lambkit.web.websocket.DefaultWebSocketServer");
		}).addHotSwapClassPrefix("org.apache.shiro").start(); 
	}
	
	public void run(String[] args) {
		parseArgs(args);
		run();
	}
	
	public void run() {
		if(contextClass==null) {
			contextClass = LambkitApplicationContext.class;
		}
		if(isWebEnvironment) {
			UndertowServer.create(contextClass).configWeb(builder->{
				builder.addListener("org.apache.shiro.web.env.EnvironmentLoaderListener");
				builder.addFilter("shiro", "org.apache.shiro.web.servlet.ShiroFilter");
				builder.addFilterUrlMapping("shiro", "/*");
			}).addHotSwapClassPrefix("org.apache.shiro").start(); 
		} else {
			if(plugins==null) {
				plugins = new Plugins();
			}
			TimeUtils.startTime("start lambkit app");
			LambkitApplicationContext context = AopKit.singleton(contextClass);
			context.configPlugin(plugins);
			for(IPlugin plugin : plugins.getPluginList()) {
				plugin.start();
			}
			context.onStart();
			TimeUtils.endTime("start lambkit app");
		}
	}
	
	public void stop() {
		if(!isWebEnvironment) {
			LambkitApplicationContext context = AopKit.singleton(contextClass);
			context.onStop();
			for(IPlugin plugin : plugins.getPluginList()) {
				plugin.stop();
			}
			plugins.getPluginList().clear();
			plugins = null;
		}
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
                Lambkit.setArg(key, value);
            }
        }
    }
    
	/**
     * 是否需要Web运行环境
     * @param flag
     */
    public void setWebEnvironment(boolean flag) {
    	isWebEnvironment = flag;
    }
    
    public boolean isWebEnvironment() {
    	return isWebEnvironment;
    }
    
    public void setArg(String key, Object value) {
        Lambkit.setArg(key, value.toString());
    }
    
    public void addModule(LambkitModule module) {
    	Lambkit.addModule(module);
	}

	public void setModule(LambkitModule module) {
		Lambkit.setModule(module);
	}
}
