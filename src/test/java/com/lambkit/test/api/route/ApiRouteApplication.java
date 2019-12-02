package com.lambkit.test.api.route;

import com.jfinal.config.Handlers;
import com.jfinal.config.Routes;
import com.lambkit.LambkitApplication;
import com.lambkit.LambkitApplicationContext;
import com.lambkit.common.service.ServiceManager;
import com.lambkit.core.api.route.ApiRoute;
import com.lambkit.module.LambkitModule;

/**
 * 浏览器访问： http://127.0.0.1/api?method=lambkit.api.goods.get&params={id:999}
 * 
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.test.api.route
 */
public class ApiRouteApplication extends LambkitApplicationContext {

	@Override
	public void configModule(LambkitModule module) {
		// TODO Auto-generated method stub
		ServiceManager.me().mapping(GoodsService.class, GoodsServiceImpl.class, null);
	}
	
	@Override
	public void configRoute(Routes routes) {
		// TODO Auto-generated method stub
		super.configRoute(routes);
		routes.add("/test/api", ApiRouteController.class, "/lambkit/test/api");
	}

	@Override
	public void configHandler(Handlers handlers) {
		// TODO Auto-generated method stub
		super.configHandler(handlers);
		handlers.add(ApiRoute.me().getHandler("/api"));
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		ApiRoute.me().onStart();
	}
	
	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 * 
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM
	 * argument 可配置为： -XX:PermSize=64M -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) {
		/**
		 * 特别注意：Eclipse 之下建议的启动方式
		 */
		// JFinal.start("src/main/webapp", 9090, "/", 5);
		LambkitApplication.run(ApiRouteApplication.class, args);
		/**
		 * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
		 */
		// JFinal.start("src/main/webapp", 8080, "/", 5);
	}
}
