package com.lambkit.http.proxy.test;

import com.jfinal.config.Handlers;
import com.jfinal.config.Routes;
import com.lambkit.Lambkit;
import com.lambkit.core.http.proxy.ProxyManager;
import com.lambkit.module.LambkitModule;
import com.lambkit.web.controller.test.TestController;

public class TestHttpProxy {
	
	public static void main(String[] args) {
		LambkitModule config = new LambkitModule() {
			@Override
			public void configHandler(Handlers me) {
				// TODO Auto-generated method stub
				ProxyManager.me().init(me);
			}
			@Override
    		public void configRoute(Routes me) {
    			// TODO Auto-generated method stub
    			super.configRoute(me);
    			me.add("/", TestController.class, "");
    		}
		};
		Lambkit.me().addModule(config);
		
		Lambkit.setBootArg("lambkit.server.webAppDir", "src/main/webapp");
		Lambkit.setBootArg("lambkit.server.port", 8080);
		Lambkit.run(args);
	}
}
