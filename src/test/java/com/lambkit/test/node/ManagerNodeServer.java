package com.lambkit.test.node;

import com.jfinal.config.Routes;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;
import com.lambkit.LambkitApplicationContext;
import com.lambkit.module.LambkitModule;

public class ManagerNodeServer extends LambkitApplicationContext {

	@Override
	public void configModule(LambkitModule module) {
		Lambkit.setArg("lambkit.autoRegisterControllerPackages", "com.lambkit.test.node");
		Lambkit.setArg("lambkit.node.use", "true");
		Lambkit.setArg("lambkit.node.major", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.id", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.type", "ManagerNode");
	}
	
	@Override
	public void configRoute(Routes routes) {
		// TODO Auto-generated method stub
		super.configRoute(routes);
		routes.add("/", ManagerNodeController.class);
	}
	
	public static void main(String[] args) {
		LambkitApplication.run(ManagerNodeServer.class, "undertow9527.txt", args);
	}
}
