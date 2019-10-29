package com.lambkit.test.gateway;

import com.jfinal.config.Routes;
import com.lambkit.LambkitApplicationContext;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;
import com.lambkit.core.gateway.GatewayRender;
import com.lambkit.module.LambkitModule;
import com.lambkit.web.controller.LambkitController;

public class TestProxyController extends LambkitController {

	public void index() {
		System.out.println("test controller...");
	}
	
	public void hello() {
		renderText("hello "+getPara("name")+"! you access url is TestProxyController hello.");
	}
	
	public void say() {
		renderText("hello "+getPara(0)+"! you access url is TestProxyController say.");
	}
	
	public void proxy_hello() {
		render(new GatewayRender("proxy_hello", "http://127.0.0.1:9090/hello"));
	}
	
	public void proxy_say() {
		render(new GatewayRender("proxy_say", "http://127.0.0.1:9090/say"));
	}
	
	public static void main(String[] args) {
		
		LambkitModule config = new LambkitModule() {
    		@Override
    		public void configRoute(Routes me) {
    			// TODO Auto-generated method stub
    			super.configRoute(me);
    			me.add("/tp", TestProxyController.class, "");
    		}
		};
		Lambkit.addModule(config);
		
		Lambkit.setArg("lambkit.server.webAppDir", "src/main/webapp");
		Lambkit.setArg("lambkit.server.port", 8080);
		LambkitApplication.run(LambkitApplicationContext.class, args);
	}
}
