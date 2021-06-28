package com.lambkit.test.node;

import com.lambkit.LambkitApplicationContext;
import com.jfinal.config.Routes;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;
import com.lambkit.module.LambkitModule;

public class ApplicationNodeServer  extends LambkitApplicationContext {

	@Override
	public void configModule(LambkitModule module) {
		Lambkit.setArg("lambkit.autoRegisterControllerPackages", "com.lambkit.test.node");
		Lambkit.setArg("lambkit.node.use", "true");
		Lambkit.setArg("lambkit.node.type", "ServiceNode");
		Lambkit.setArg("lambkit.node.major", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.manager.id", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.manager.port", "9527");
	}
	
	@Override
	public void configRoute(Routes routes) {
		// TODO Auto-generated method stub
		super.configRoute(routes);
		routes.add("/", ApplicationNodeController.class);
	}
	
	public static void main(String[] args) {
		LambkitApplication.run(ApplicationNodeServer.class, "undertow8080.txt", args);
	}
}
