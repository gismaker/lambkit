package com.lambkit.web.controller.test;

import com.jfinal.config.Routes;
import com.lambkit.Lambkit;
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
		Lambkit.me().addModule(config);
		
		Lambkit.run(null);
	}
}
