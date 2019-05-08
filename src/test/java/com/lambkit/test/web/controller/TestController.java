package com.lambkit.test.web.controller;

import com.jfinal.config.Routes;
import com.lambkit.Application;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;
import com.lambkit.module.LambkitModule;
import com.lambkit.web.controller.BaseController;
import com.lambkit.web.controller.annotation.RequestMapping;

@RequestMapping(value="/lambkit/test", viewPath="")
public class TestController extends BaseController {

	public void index() {
		renderText("hello world!");
	}
	
	public static void main(String[] args) {
		
		LambkitModule config = new LambkitModule() {
    		@Override
    		public void configRoute(Routes me) {
    			// TODO Auto-generated method stub
    			super.configRoute(me);
    			me.add("/", TestController.class, "");
    		}
		};
		Lambkit.addModule(config);
		
		LambkitApplication.run(Application.class, null);
	}
}
