package com.lambkit.test.rpc.motan;

import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.lambkit.LambkitApplicationContext;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;
import com.lambkit.core.rpc.Rpc;
import com.lambkit.core.rpc.RpcManager;
import com.lambkit.module.LambkitModule;
import com.lambkit.test.node.ManagerNodeServer;

import test.service.CategoryService;
import test.service.CategoryServiceImpl;
import test.service.UserService;
import test.service.UserServiceImpl;

public class MotanServerDemo {

	public static void main(String[] args) {
		LambkitModule config = new LambkitModule() {
    		@Override
    		public void configRoute(Routes me) {
    			// TODO Auto-generated method stub
    			super.configRoute(me);
    			me.add("/", ManagerNodeServer.class, "");
    		}
    		public void configPlugin(Plugins me) {
    			Rpc rpc = RpcManager.me().getRpc();
    			rpc.serviceExport(UserService.class, new UserServiceImpl(), "lambkit", "1.0", 8002);
    			rpc.serviceExport(CategoryService.class, new CategoryServiceImpl(), "lambkit", "1.0", 8002);
    			me.add(RpcManager.me().getPlugin());
    		}
		};
		Lambkit.addModule(config);
		Lambkit.setArg("lambkit.server.webAppDir", "src/main/webapp");
		Lambkit.setArg("lambkit.node.major", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.id", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.type", "ManagerNode");
		Lambkit.setArg("lambkit.server.port", 9527);
		LambkitApplication.run(LambkitApplicationContext.class, args);
	}
}
