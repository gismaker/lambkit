package com.lambkit.test.node;

import com.lambkit.core.rpc.RpcKit;

import com.lambkit.distributed.node.ManagerNodeService;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.annotation.NodeAPI;
import com.lambkit.web.controller.LambkitController;
import com.lambkit.web.controller.annotation.RequestMapping;

//@RequestMapping("/snode")
public class ServiceNodeController extends LambkitController {

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
		renderText("hello " + getPara("name") + "! you access url is [" + NodeManager.me().getNode().getId() + ", "
				+ NodeManager.me().getNode().getIp() + ":" + NodeManager.me().getNode().getPort() + "] hello.");
	}

	@NodeAPI
	public void say() {
		renderText("hello " + getPara(0) + "! you access url is [" + NodeManager.me().getNode().getId() + ", "
				+ NodeManager.me().getNode().getIp() + ":" + NodeManager.me().getNode().getPort() + "] say.");
	}

	public void test() {
		ManagerNodeService service = NodeManager.me().getMajorRpcService();
		setAttr("token", service.getToken());
	}

	public void apis() {
		/*
		 * List<NodeApi> list = NodeManager.me().getApiTable().getValues(); for (NodeApi
		 * nodeApi : list) { System.out.println("apis: " + nodeApi.getNodeId() +
		 * nodeApi.getUrl()); setAttr(nodeApi.getNodeId(), nodeApi.getUrl()); }
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
		if (service != null) {
			renderText(service.getToken());
		} else {
			renderText("fail");
		}
	}
}
