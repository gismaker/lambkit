package com.lambkit.test.gateway;

import com.lambkit.core.gateway.GatewayRender;
import com.lambkit.web.controller.LambkitController;

public class TestGatewayController extends LambkitController {

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
		render(GatewayRender.by("proxy_hello", "http://127.0.0.1:9090/hello"));
	}
	
	public void proxy_say() {
		render(GatewayRender.by("proxy_say", "http://127.0.0.1:9090/say"));
	}

}
