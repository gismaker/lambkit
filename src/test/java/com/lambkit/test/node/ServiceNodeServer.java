package com.lambkit.test.node;

import com.jfinal.config.Routes;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;
import com.lambkit.LambkitApplicationContext;
import com.lambkit.module.LambkitModule;

public class ServiceNodeServer extends LambkitApplicationContext {

	@Override
	public void configModule(LambkitModule module) {
		Lambkit.setArg("lambkit.autoRegisterControllerPackages", "com.lambkit.test.node");
		Lambkit.setArg("lambkit.node.use", "true");
		Lambkit.setArg("lambkit.node.type", "ServiceNode");
		Lambkit.setArg("lambkit.node.major", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.manager.id", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.manager.rpcPort", "8002");
		Lambkit.setArg("lambkit.node.manager.port", "9527");
	}
	
	@Override
	public void configRoute(Routes routes) {
		// TODO Auto-generated method stub
		super.configRoute(routes);
		routes.add("/", ServiceNodeController.class);
	}
	
	public static void main(String[] args) {
		LambkitApplication.run(ServiceNodeServer.class, 9090, args);
	}
}
