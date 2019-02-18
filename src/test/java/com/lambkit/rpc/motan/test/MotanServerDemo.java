package com.lambkit.rpc.motan.test;

import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.lambkit.Lambkit;
import com.lambkit.core.rpc.Rpc;
import com.lambkit.core.rpc.RpcManager;
import com.lambkit.module.LambkitModule;
import com.lambkit.node.test.ManagerNodeServer;

import service.CategoryService;
import service.CategoryServiceImpl;
import service.UserService;
import service.UserServiceImpl;

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
		Lambkit.me().addModule(config);
		Lambkit.setBootArg("lambkit.server.webAppDir", "src/main/webapp");
		Lambkit.setBootArg("lambkit.node.major", "lambkit-manager-node");
		Lambkit.setBootArg("lambkit.node.id", "lambkit-manager-node");
		Lambkit.setBootArg("lambkit.node.type", "ManagerNode");
		Lambkit.setBootArg("lambkit.server.port", 9527);
		Lambkit.run(args);
	}
}
