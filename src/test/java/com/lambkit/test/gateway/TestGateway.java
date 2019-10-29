package com.lambkit.test.gateway;

import com.jfinal.config.Handlers;
import com.jfinal.config.Routes;
import com.lambkit.LambkitApplicationContext;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;
import com.lambkit.core.gateway.GatewayManager;
import com.lambkit.module.LambkitModule;
import com.lambkit.test.web.controller.TestController;

public class TestGateway {
	
	public static void main(String[] args) {
		LambkitModule config = new LambkitModule() {
			@Override
			public void configHandler(Handlers me) {
				// TODO Auto-generated method stub
				GatewayManager.me().init(me);
			}
			@Override
    		public void configRoute(Routes me) {
    			// TODO Auto-generated method stub
    			super.configRoute(me);
    			me.add("/", TestController.class, "");
    		}
		};
		Lambkit.addModule(config);
		
		Lambkit.setArg("lambkit.server.webAppDir", "src/main/webapp");
		Lambkit.setArg("lambkit.server.port", 8080);
		LambkitApplication.run(LambkitApplicationContext.class, args);
	}
}
