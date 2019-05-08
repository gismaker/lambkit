package com.lambkit.test.node;

import com.jfinal.config.Routes;
import com.lambkit.Application;
import com.lambkit.Lambkit;
import com.lambkit.LambkitApplication;
import com.lambkit.core.aop.AopKit;
import com.lambkit.distributed.node.ManagerNodeService;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.manager.ManagerNodeServiceImp;
import com.lambkit.module.LambkitModule;
import com.lambkit.web.controller.BaseController;

public class ManagerNodeServer extends BaseController {
	
	public void index() {
		setAttr("id", NodeManager.me().getNode().getId());
		setAttr("name", NodeManager.me().getNode().getName());
		setAttr("host", NodeManager.me().getNode().getHost());
		setAttr("ip", NodeManager.me().getNode().getIp());
		setAttr("port", NodeManager.me().getNode().getPort());
		setAttr("path", NodeManager.me().getNode().getContexPath());
		setAttr("type", NodeManager.me().getNode().getType());
		setAttr("major", NodeManager.me().getMajorManagerId());

		ManagerNodeService service = AopKit.newInstance(ManagerNodeServiceImp.class);
		setAttr("token", service.getToken());
		renderJson();
	}
	
	public void apis() {
		setAttr("apis", NodeManager.me().getApiTable().getValues());
		renderJson();
	}
	
	public void nodes() {
		setAttr("nodes", NodeManager.me().getNodeTable().getValues());
		renderJson();
	}
	
	public void similar() {
		setAttr("nodes", NodeManager.me().getSimilarNodes().getValues());
		renderJson();
	}
	
	public static void main(String[] args) {
		LambkitModule config = new LambkitModule() {
    		@Override
    		public void configRoute(Routes me) {
    			// TODO Auto-generated method stub
    			super.configRoute(me);
    			me.add("/", ManagerNodeServer.class, "");
    		}
		};
		Lambkit.addModule(config);
		Lambkit.setArg("lambkit.server.webAppDir", "src/main/webapp");
		Lambkit.setArg("lambkit.node.use", "true");
		Lambkit.setArg("lambkit.node.major", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.id", "lambkit-manager-node");
		Lambkit.setArg("lambkit.node.type", "ManagerNode");
		Lambkit.setArg("lambkit.server.port", 9527);
		LambkitApplication.run(Application.class, args);
	}
}
