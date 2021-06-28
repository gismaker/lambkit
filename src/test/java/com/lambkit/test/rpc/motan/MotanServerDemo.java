package com.lambkit.test.rpc.motan;

import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.lambkit.LambkitApplicationContext;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;
import com.lambkit.core.rpc.RpcManager;
import com.lambkit.core.rpc.RpcPlugin;
import com.lambkit.core.rpc.RpcServiceConfig;
import com.lambkit.module.LambkitModule;
import com.lambkit.test.node.ManagerNodeController;

import test.service.CategoryService;
import test.service.CategoryServiceImpl;
import test.service.UserService;
import test.service.UserServiceImpl;

public class MotanServerDemo extends LambkitApplicationContext {
	
	@Override
	public void configModule(LambkitModule module) {
		LambkitModule config = new LambkitModule() {
    		@Override
    		public void configRoute(Routes me) {
    			// TODO Auto-generated method stub
    			super.configRoute(me);
    			me.add("/", ManagerNodeController.class, "");
    		}
    		public void configPlugin(Plugins me) {
    			RpcPlugin rpc = RpcManager.me().getPlugin();
    			RpcServiceConfig config = new RpcServiceConfig("lambkit", "1.0", 8002);
    			rpc.serviceExport(UserService.class, new UserServiceImpl(), config);
    			rpc.serviceExport(CategoryService.class, new CategoryServiceImpl(), config);
    			me.add(rpc);
    		}
		};
		Lambkit.addModule(config);
		Lambkit.setArg("lambkit.server.webAppDir", "src/main/webapp");
		Lambkit.setArg("lambkit.node.major", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.id", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.type", "ManagerNode");
	}

	public static void main(String[] args) {
		LambkitApplication.run(MotanServerDemo.class, "undertow9527.txt", args);
	}
}
