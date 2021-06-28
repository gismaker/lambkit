package com.lambkit.test.gateway;

import com.jfinal.config.Handlers;
import com.jfinal.config.Routes;
import com.lambkit.LambkitApplicationContext;
import com.lambkit.LambkitApplication;
import com.lambkit.core.gateway.GatewayHandler;
import com.lambkit.module.LambkitModule;

public class TestGatewayClient extends LambkitApplicationContext {
	
	@Override
	public void configModule(LambkitModule module) {
		LambkitModule config = new LambkitModule() {
			@Override
			public void configHandler(Handlers me) {
				// TODO Auto-generated method stub
				me.add(new GatewayHandler());
			}
			@Override
    		public void configRoute(Routes me) {
    			// TODO Auto-generated method stub
    			super.configRoute(me);
    			me.add("/tp", TestGatewayController.class, "");
    		}
		};
		module.addModule(config);
	}
	
	public static void main(String[] args) {
		LambkitApplication.run(TestGatewayClient.class, "undertow8080.txt", args);
	}
}
