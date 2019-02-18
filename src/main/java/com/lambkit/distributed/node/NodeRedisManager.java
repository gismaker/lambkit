/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.distributed.node;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.distributed.node.annotation.NodeAPI;
import com.lambkit.distributed.node.api.NodeApi;
import com.lambkit.distributed.node.api.NodeApiTable;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.distributed.node.info.NodeBuilder;
import com.lambkit.distributed.node.info.NodeTable;
import com.lambkit.distributed.node.info.NodeType;
import com.lambkit.distributed.node.redis.RedisNodeSystem;

/**
 * 节点管理
 * 
 * @author 孤竹行
 *
 */
public class NodeRedisManager {
	private static NodeRedisManager manager = new NodeRedisManager();

	public static NodeRedisManager me() {
		return manager;
	}

	/**
	 * 节点信息
	 */
	private Node node = null;
	/**
	 * 所有节点
	 */
	private NodeTable nodeTable = null;
	/**
	 * 本节点所有的api
	 */
	private NodeApiTable apiTable = null;
	
	private int isInitOk = 0;
	
	private boolean buse = false;
	
	private void initNode() {
		node = ConfigManager.me().get(Node.class, "lambkit.node");
		String type = ConfigManager.me().getValue("lambkit.node.type");
		node.setType(NodeType.valueOf(type));
		if (!node.isUsable()) {
			NodeBuilder builder = new NodeBuilder();
			node = builder.initNode(node);
		}
		node.enable();
		node.initRpc();
	}
	
	public void init() {
		buse = ConfigManager.me().containsKey("lambkit.node.use");
		if(!buse) return;
		
		if (isInitOk == 2)
			return;

		if(node==null) initNode();

		String memory = ConfigManager.me().getValue("lambkit.node.memory");
		if(apiTable==null)  {
			apiTable = new NodeApiTable(node.getType(), memory);
		} else {
			apiTable.setType(node.getType());
		}
		nodeTable = new NodeTable(NodeType.RedisNode, memory);
		//启动上报心跳检测
		RedisNodeSystem.me().start();
		isInitOk = 1;
		
		print();
	}

	public void initWeb(HttpServletRequest request) {
		if(!buse) return;
		if (isInitOk == 2)
			return;
		if (isInitOk == 0) {
			init();
		}
		if (request == null)
			return;
		if (isInitOk == 1) {
			node.setPort(request.getLocalPort());
			node.setContexPath(request.getContextPath());
		}
		isInitOk = 2;
		printAll();
	}
	
	public void init(Routes routes) {
		buse = ConfigManager.me().containsKey("lambkit.node.use");
		if(!buse) return;
		
		if(apiTable==null) {
			String memory = ConfigManager.me().getValue("lambkit.node.memory");
			apiTable = new NodeApiTable(NodeType.RedisNode, memory);
		}
		if(node==null) initNode();
		for (Routes.Route route : routes.getRouteItemList()) {
			Class<? extends Controller> controller = route.getControllerClass();
			Method[] methods = controller.getMethods();
			for (Method method : methods) {
				NodeAPI nodeApi = method.getAnnotation(NodeAPI.class);
				if (nodeApi == null) {
	                continue;
	            }
				String ckey = route.getControllerKey().endsWith("/") ? route.getControllerKey() : route.getControllerKey() + "/";
				String url = ckey+method.getName();			
				Node node = NodeRedisManager.me().getNode();
				String nodeid = node.getId();
				NodeApi api = new NodeApi(nodeid, url, nodeApi.method().getValue(), 0);
				api.enable();
				NodeRedisManager.me().addApi(api);
			}
		}
	}
	
	public void destroy() {
		//停止上报心跳检测
		RedisNodeSystem.me().stop();
	}
	
	public boolean isUsable() {
		if(buse && isInitOk==2) {
			return true;
		}
		return false;
	}
	
	public void updateApiTime(String url, long time) {
		if(apiTable==null) return;
		NodeApi api = apiTable.get(url);
		if(api!=null) {
			api.setRuntime(time);
			//addApi(api);
		}
	}
	
	//基础功能//////////////
	public void putNode(Node node) {
		if(nodeTable!=null) {
			nodeTable.put(node);
		}
	}

	public Node getNode(String nodeid) {
		return nodeTable!=null ? nodeTable.get(nodeid) : null;
	}
	
	public void addApi(NodeApi api) {
		if (apiTable != null) {
			apiTable.put(api);
		}
	}

	public NodeApi getApi(String url) {
		return apiTable != null ? apiTable.get(url) : null;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}
	
	public NodeTable getNodeTable() {
		return nodeTable;
	}

	public void setNodeTable(NodeTable nodeTable) {
		this.nodeTable = nodeTable;
	}

	public NodeApiTable getApiTable() {
		return apiTable;
	}

	public void setApiTable(NodeApiTable apiTable) {
		this.apiTable = apiTable;
	}

	public void print() {
		System.out.print("Node[" + node.getClass().getName() + "]-->>id=" + node.getId());
		System.out.print(", name=" + node.getName());
		System.out.println(", type=" + node.getType());
	}

	public void printAll() {
		System.out.println();
		System.out.println("Lambkit Node Info ---------------------------------");
		System.out.println("id    :" + node.getId());
		System.out.println("name  :" + node.getName());
		System.out.println("host  :" + node.getHost());
		System.out.println("ip    :" + node.getIp());
		System.out.println("port  :" + node.getPort());
		System.out.println("path  :" + node.getContexPath());
		System.out.println("type  :" + node.getType());
		System.out.println("---------------------------------------------------");
	}
}
