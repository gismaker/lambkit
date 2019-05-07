package com.lambkit.test.node;

import java.util.List;

import com.jfinal.config.Routes;
import com.lambkit.Lambkit;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.distributed.node.ManagerNodeService;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.annotation.NodeAPI;
import com.lambkit.distributed.node.api.NodeApi;
import com.lambkit.module.LambkitModule;
import com.lambkit.web.controller.BaseController;

public class ServiceNodeServer extends BaseController {

	public void index() {
		setAttr("id", NodeManager.me().getNode().getId());
		setAttr("name", NodeManager.me().getNode().getName());
		setAttr("host", NodeManager.me().getNode().getHost());
		setAttr("ip", NodeManager.me().getNode().getIp());
		setAttr("port", NodeManager.me().getNode().getPort());
		setAttr("path", NodeManager.me().getNode().getContexPath());
		setAttr("type", NodeManager.me().getNode().getType());
		setAttr("major", NodeManager.me().getMajorManagerId());
		renderJson();
	}
	
	@NodeAPI
	public void hello() {
		renderText("hello "+getPara("name")+"! you access url is ["+
				NodeManager.me().getNode().getId()
				+ ", "
				+ NodeManager.me().getNode().getIp() + ":" + NodeManager.me().getNode().getPort()
				+"] hello.");
	}
	
	@NodeAPI
	public void say() {
		renderText("hello "+getPara(0)+"! you access url is ["+
				NodeManager.me().getNode().getId()
				+ ", "
				+ NodeManager.me().getNode().getIp() + ":" + NodeManager.me().getNode().getPort()
				+"] say.");
	}
	
	public void test() {
		ManagerNodeService service = NodeManager.me().getMajorRpcService();
		setAttr("token", service.getToken());
	}
	
	public void apis() {
		/*
		List<NodeApi> list = NodeManager.me().getApiTable().getValues();
		for (NodeApi nodeApi : list) {
			System.out.println("apis: " + nodeApi.getNodeId() + nodeApi.getUrl());
			setAttr(nodeApi.getNodeId(), nodeApi.getUrl());
		}
		*/
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
	
	public void ns() {
		ManagerNodeService service = RpcKit.obtain(ManagerNodeService.class, "lambkit", "1.0", "127.0.0.1:8002");
		if(service!=null) {
			renderText(service.getToken());
		} else {
			renderText("fail");
		}
	}
	
	public static void main(String[] args) {
		
		LambkitModule config = new LambkitModule() {
    		@Override
    		public void configRoute(Routes me) {
    			// TODO Auto-generated method stub
    			super.configRoute(me);
    			me.add("/", ServiceNodeServer.class, "");
    		}
		};
		Lambkit.me().addModule(config);
		Lambkit.setBootArg("lambkit.server.webAppDir", "src/main/webapp");
		Lambkit.setBootArg("lambkit.node.use", "true");
		Lambkit.setBootArg("lambkit.node.type", "ServiceNode");
		Lambkit.setBootArg("lambkit.node.major", "lambkit-manager-node");
		Lambkit.setBootArg("lambkit.node.manager.id", "lambkit-manager-node");
		Lambkit.setBootArg("lambkit.node.manager.rpcPort", "8002");
		Lambkit.setBootArg("lambkit.node.manager.port", "9527");
		Lambkit.setBootArg("lambkit.server.port", 9090);
		Lambkit.run(args);
	}
}
