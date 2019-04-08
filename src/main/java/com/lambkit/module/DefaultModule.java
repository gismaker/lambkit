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
package com.lambkit.module;

import java.io.File;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.json.FastJsonFactory;
import com.jfinal.json.JsonManager;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;
import com.jfinal.template.ext.directive.NowDirective;
import com.lambkit.Lambkit;
import com.lambkit.common.base.Consts;
import com.lambkit.common.util.TimeUtils;
import com.lambkit.component.ehcache.EhcacheConfig;
import com.lambkit.component.redis.RedisConfig;
import com.lambkit.component.redis.RedisManager;
import com.lambkit.component.shiro.ShiroManager;
import com.lambkit.component.shiro.freemarker.ShiroTags;
import com.lambkit.component.swagger.SwaggerConfig;
import com.lambkit.component.swagger.SwaggerController;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.event.EventKit;
import com.lambkit.core.rpc.RpcConfig;
import com.lambkit.core.rpc.RpcManager;
import com.lambkit.db.DbManager;
import com.lambkit.db.datasource.ActiveRecordPluginWrapper;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.controller.NodeIndexController;
import com.lambkit.system.SystemManager;
import com.lambkit.system.controller.HelpIndexController;
import com.lambkit.system.monitor.ApiMontiorHandler;
import com.lambkit.web.ControllerManager;
import com.lambkit.web.cache.ActionCacheHandler;
import com.lambkit.web.handler.LambkitHandler;
import com.lambkit.web.interceptor.CommonInterceptor;

import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import net.sf.ehcache.config.DiskStoreConfiguration;

public class DefaultModule extends LambkitModule {
	static final Log log = Log.getLog(DefaultModule.class);
	
	private List<LambkitModule> modules = null;
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		TimeUtils.startTime("start lambkit module");
		me.setDevMode(Lambkit.me().isDevMode());
		me.setMaxPostSize(1024 * 1024 * 2000);
		me.setReportAfterInvocation(false);
        //me.setControllerFactory(ControllerManager.me());
		me.setJsonFactory(new FastJsonFactory());
		me.setError404View("/lambkit/errors/404.html");
		me.setError500View("/lambkit/errors/500.html");
		//RequiresGuest，RequiresAuthentication，RequiresUser验证异常，返回HTTP401状态码
		me.setErrorView(401, "/manage/login");
		//RequiresRoles，RequiresPermissions授权异常,返回HTTP403状态码
		me.setErrorView(403, PropKit.get("permissionUrl", "/needPermission"));
		if(modules==null) modules = new ArrayList<LambkitModule>();
		if(Lambkit.me().isDevMode()) {
			modules.add(new DevelopModule());
		}
		//自动扫描加入module
		//autoRegisterModule();
		for (LambkitModule module : modules) {
			module.configConstant(me);
		}
		SystemManager.me().getInfo().setConstants(me);
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes routes) {
		//me.add(new SwaggerRoutes());
		SwaggerConfig swaggerConfig = Lambkit.config(SwaggerConfig.class);
        if (swaggerConfig.isConfigOk()) {
        	routes.add(swaggerConfig.getUrl(), SwaggerController.class, swaggerConfig.getPath());
        }
        //自动扫描加入RequestMapping
        //autoRegisterRequestMapping(routes);
        
        for (Routes.Route route : routes.getRouteItemList()) {
            ControllerManager.me().setMapping(route.getControllerKey(), route.getControllerClass());
        }

		for (LambkitModule module : modules) {
			module.configRoute(routes);
		}
		ShiroManager.me().init(routes);
		NodeManager.me().init(routes);
		
		routes.add("/lambkit/node", NodeIndexController.class, "/lambkit/node");
		routes.add("/lambkit/help", HelpIndexController.class, "/lambkit/help");
		SystemManager.me().getInfo().setRoutes(routes);
	}
	
	public void configEngine(Engine engine) {
		/**
         * now 并没有被添加到默认的指令当中
         * 查看：EngineConfig
         */
        addDirective(engine, "now", NowDirective.class);
        addDirective(engine, "long2date", com.lambkit.web.directive.DateDirective.class);
        //自动扫描加入JFinalDirective
        //autoRegisterEngine(engine);
        
		for (LambkitModule module : modules) {
			module.configEngine(engine);
		}
		ShiroManager.me().configEngine(engine);
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		EhcacheConfig ehc = ConfigManager.me().get(EhcacheConfig.class);
		if(ehc.isAddin()) me.add(createEhCachePlugin());
		//me.add(new EventPlugin());
		
		RedisConfig rdc = ConfigManager.me().get(RedisConfig.class);
		if(rdc.isAddin()) {
			//分布式session，分布式缓存
			RedisManager.me().addDefaultPlugin(me);
		}
		
		RpcConfig rpc = ConfigManager.me().get(RpcConfig.class);
		if(StrKit.notBlank(rpc.getType())) {
			me.add(RpcManager.me().getPlugin());
		}
		
		for (LambkitModule module : modules) {
			module.configPlugin(me);
		}
		
		//DbManager未完成
		List<ActiveRecordPluginWrapper> arps = DbManager.me().init(me);
        for (ActiveRecordPluginWrapper arp : arps) {
        	me.add(arp.getPlugin());
        	if(DbKit.MAIN_CONFIG_NAME.equals(arp.getConfig().getName())) {
        		for (LambkitModule module : modules) {
        			module.configMapping(arp);
        		}
        	} else {
        		for (LambkitModule module : modules) {
        			module.configMapping(arp.getConfig().getName(), arp);
        		}
        	}
        }
		SystemManager.me().getInfo().setPlugins(me);
	}
	
	public EhCachePlugin createEhCachePlugin() {
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
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.add(new CommonInterceptor());
		for (LambkitModule module : modules) {
			module.configInterceptor(me);
		}
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
	    me.add(new ApiMontiorHandler());
	    me.add(new LambkitHandler());
		me.add(new ContextPathHandler("ctx"));
		me.add(new ActionCacheHandler());
		for (LambkitModule module : modules) {
			module.configHandler(me);
		}
		SystemManager.me().getInfo().setHandlers(me);
	}
	
	@Override
	public void afterJFinalStart() {
		// TODO Auto-generated method stub
		NodeManager.me().init();
		for (LambkitModule module : modules) {
			module.afterJFinalStart();
		}
		addTag("shiro", new ShiroTags());
		JsonManager.me().setDefaultDatePattern("yyyy-MM-dd HH:mm:ss");
		SystemManager.me().init();
		
		TimeUtils.endTime("start lambkit default module");
		/**
		 * 发送启动完成通知
		 */
		EventKit.sendEvent(Consts.EVENT_STARTED, null);
	}
	
	@Override
	public void beforeJFinalStop() {
		// TODO Auto-generated method stub
		Enumeration<Driver> drivers = DriverManager.getDrivers();
        if (drivers != null) {
            while (drivers.hasMoreElements()) {
                try {
                    Driver driver = drivers.nextElement();
                    DriverManager.deregisterDriver(driver);
                } catch (Exception e) {
                    log.error(e.toString(), e);
                }
            }
        }
        NodeManager.me().destroy();
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
