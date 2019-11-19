package com.lambkit.test.node;

import com.lambkit.core.gateway.GatewayRender;
import com.lambkit.distributed.node.ManagerNodeService;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.api.NodeApi;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.web.controller.LambkitController;
import com.lambkit.web.controller.annotation.RequestMapping;

//@RequestMapping(value = "/anode")
public class ApplicationNodeController extends LambkitController {

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
	
	public void hello() {
		ManagerNodeService service = NodeManager.me().getMajorRpcService();
		NodeApi api = service.getApi("/hello");
		if(api != null) {
			System.out.println("api: " + api.getNodeId() + ", time:" + api.getRuntime());
			Node node = service.getNode(api.getNodeId());
			System.out.println("api: " + node.getId() + ", url:" + node.getIp()+":"+node.getPort());
			String url = "http://"+node.getIp()+":"+node.getPort()+"/"+api.getUrl();
			render(GatewayRender.by("hello", url));
		} else {
			System.out.println("api is null");
			render(GatewayRender.by("hello", "http://127.0.0.1:9090/hello"));
		}
	}
	
	public void say() {
		render(GatewayRender.by("say", "http://127.0.0.1:9090/say"));
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
