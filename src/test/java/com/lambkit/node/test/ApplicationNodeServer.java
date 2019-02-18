package com.lambkit.node.test;

import com.jfinal.config.Routes;
import com.lambkit.Lambkit;
import com.lambkit.core.http.proxy.ProxyRender;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.distributed.node.ManagerNodeService;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.api.NodeApi;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.module.LambkitModule;
import com.lambkit.web.controller.BaseController;

public class ApplicationNodeServer extends BaseController {

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
			render(new ProxyRender("hello", url));
		} else {
			System.out.println("api is null");
			render(new ProxyRender("hello", "http://127.0.0.1:9090/hello"));
		}
	}
	
	public void say() {
		render(new ProxyRender("say", "http://127.0.0.1:9090/say"));
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
    			me.add("/", ApplicationNodeServer.class, "");
    		}
		};
		Lambkit.me().addModule(config);
		Lambkit.setBootArg("lambkit.server.webAppDir", "src/main/webapp");
		Lambkit.setBootArg("lambkit.node.use", "true");
		Lambkit.setBootArg("lambkit.node.type", "ServiceNode");
		Lambkit.setBootArg("lambkit.node.major", "lambkit-manager-node");
		Lambkit.setBootArg("lambkit.node.manager.id", "lambkit-manager-node");
		Lambkit.setBootArg("lambkit.node.manager.port", "9527");
		Lambkit.setBootArg("lambkit.server.port", 8080);
		Lambkit.run(args);
	}
}
