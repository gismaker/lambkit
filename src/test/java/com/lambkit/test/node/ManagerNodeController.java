package com.lambkit.test.node;

import com.lambkit.core.aop.AopKit;
import com.lambkit.distributed.node.ManagerNodeService;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.manager.ManagerNodeServiceImp;
import com.lambkit.web.controller.LambkitController;
import com.lambkit.web.controller.annotation.RequestMapping;

//@RequestMapping(value = "/mnode")
public class ManagerNodeController extends LambkitController {
	
	public void index() {
		setAttr("id", NodeManager.me().getNode().getId());
		setAttr("name", NodeManager.me().getNode().getName());
		setAttr("host", NodeManager.me().getNode().getHost());
		setAttr("ip", NodeManager.me().getNode().getIp());
		setAttr("port", NodeManager.me().getNode().getPort());
		setAttr("path", NodeManager.me().getNode().getContexPath());
		setAttr("type", NodeManager.me().getNode().getType());
		setAttr("major", NodeManager.me().getMajorManagerId());

		ManagerNodeService service = AopKit.get(ManagerNodeServiceImp.class);
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
}
