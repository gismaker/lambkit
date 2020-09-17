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
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.distributed.node.annotation.NodeAPI;
import com.lambkit.distributed.node.api.NodeApi;
import com.lambkit.distributed.node.api.NodeApiTable;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.distributed.node.info.NodeBuilder;
import com.lambkit.distributed.node.info.NodeTable;
import com.lambkit.distributed.node.info.NodeType;
import com.lambkit.distributed.node.manager.ManagerNodeServiceImp;
import com.lambkit.distributed.node.service.ServiceNodeSystem;
import com.lambkit.distributed.token.TokenManager;

/**
 * 节点管理
 * 
 * @author 孤竹行
 *
 */
public class NodeManager {
	private static final String NODE_MANAGER_PREFIX = "lambkit.node.manager.";
	private static final String NODE_SIMILAR_PREFIX = "lambkit.node.similar.";

	private static NodeManager manager = new NodeManager();

	public static NodeManager me() {
		return manager;
	}

	/**
	 * 节点信息
	 */
	private Node node = null;
	/**
	 * ManagerNode主节点的id
	 */
	private String majorManagerId;
	/**
	 * ManagerNode or ServiceNode
	 */
	private NodeTable nodeTable = null;
	/**
	 * 临近的相同(name相同)节点
	 */
	private NodeTable similarNodes = null;
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
		String enable = ConfigManager.me().getValue("lambkit.node.enable");
		buse = "true".equals(enable) ? true : false; //ConfigManager.me().containsKey("lambkit.node.enable")
		if(!buse) {
			NodeBuilder builder = new NodeBuilder();
			node = builder.createNode(NodeType.ManagerNode);
			node = builder.initNode(node);
			node.enable();
			return;
		}
		
		if (isInitOk == 2)
			return;

		if(node==null) initNode();

		majorManagerId = ConfigManager.me().getValue("lambkit.node.major");
		String memory = ConfigManager.me().getValue("lambkit.node.memory");
		if(apiTable==null)  {
			apiTable = new NodeApiTable(node.getType(), memory);
		} else {
			apiTable.setType(node.getType());
		}
		similarNodes = new NodeTable(NodeType.SimilarNode, memory);
		if (node.getType() == NodeType.ManagerNode) {
			nodeTable = new NodeTable(NodeType.ServiceNode, memory);
			majorManagerId = node.getId();
			// 管理节点发布服务
			RpcKit.serviceExport(ManagerNodeService.class, new ManagerNodeServiceImp(),
					node.getRpcGroup(), node.getRpcVersion(), node.getRpcPort());
			if(StrKit.notBlank(majorManagerId) && majorManagerId.equals(node.getId())) {
				TokenManager.me().start();
			}
		} else {
			nodeTable = new NodeTable(NodeType.ManagerNode, memory);
			//初始化管理节点
			initManagerNode();
			//启动上报心跳检测
			ServiceNodeSystem.me().start();
		}
		//初始化similar节点
		initSimilarNode();
		isInitOk = 1;
		
		print();
	}

	public void initWeb(HttpServletRequest request) {
		if(!buse) {
			node.setPort(request.getLocalPort());
			node.setContexPath(request.getContextPath());
			return;
		}
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
		String enable = ConfigManager.me().getValue("lambkit.node.enable");
		buse = "true".equals(enable) ? true : false; //ConfigManager.me().containsKey("lambkit.node.enable")
		if(!buse) return;
		
		if(apiTable==null) {
			String memory = ConfigManager.me().getValue("lambkit.node.memory");
			apiTable = new NodeApiTable(NodeType.ServiceNode, memory);
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
				Node node = NodeManager.me().getNode();
				String nodeid = node.getId();
				NodeApi api = new NodeApi(nodeid, url, nodeApi.method().getValue(), 0);
				api.enable();
				NodeManager.me().addApi(api);
			}
		}
	}
	
	public void destroy() {
		if (node!=null && node.getType() == NodeType.ManagerNode) {
			if(StrKit.notBlank(majorManagerId) && majorManagerId.equals(node.getId())) {
				TokenManager.me().stop();
			}
		} else {
			//停止上报心跳检测
			ServiceNodeSystem.me().stop();
		}
	}
	
	public boolean isManagerNode() {
		if(node!=null && node.getType()==NodeType.ManagerNode) {
			return true;
		}
		return false;
	}
	
	public boolean isMajorManagerNode() {
		if(node!=null && node.getType()==NodeType.ManagerNode) {
			if(StrKit.notBlank(majorManagerId) && majorManagerId.equals(node.getId())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isMajorManagerNode(Node node) {
		if(node!=null && node.getType()==NodeType.ManagerNode) {
			if(StrKit.notBlank(majorManagerId) && majorManagerId.equals(node.getId())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isUsable() {
		if(buse && isInitOk==2) {
			return true;
		}
		return false;
	}
	/**
	 * 节点变换
	 * @return
	 */
	public boolean transServiceToManager() {
		if(isInitOk < 2) return false;
		node.setType(NodeType.ManagerNode);
		nodeTable.trans(similarNodes);
		nodeTable.setType(NodeType.ServiceNode);		
		return true;
	}
	
	public ManagerNodeService getRpcService(Node node) {
		return RpcKit.obtain(ManagerNodeService.class, node.getRpcGroup(), node.getRpcVersion(), node.getIp() + ":" + node.getRpcPort());
	}
	
	public ManagerNodeService getMajorRpcService() {
		if(nodeTable==null) return null;
		Node mgrnode = nodeTable.get(majorManagerId);
		if(mgrnode==null) return null;
		return RpcKit.obtain(ManagerNodeService.class, mgrnode.getRpcGroup(), mgrnode.getRpcVersion(), mgrnode.getIp() + ":" + mgrnode.getRpcPort());
	}
	
	public void updateApiTime(String url, long time) {
		if(apiTable==null) return;
		NodeApi api = apiTable.get(url);
		if(api!=null) api.setRuntime(time);
	}
	
	//基础功能//////////////
	
	public void putNode(Node node) {
		if(nodeTable!=null && nodeTable.getType() == node.getType()) {
			//example nodeTable=ManagerNode, node=ServiceNode
			//example nodeTable=ServiceNode, node=ManagerNode
			nodeTable.put(node);
		} else if(similarNodes!=null && getNode()!=null && getNode().getType()==NodeType.ManagerNode) {
			similarNodes.put(node);
		}
	}

	public Node getNode(String nodeid) {
		return nodeTable!=null ? nodeTable.get(nodeid) : null;
	}

	public void putSimilarNode(Node node) {
		if(nodeTable!=null) nodeTable.put(node);
	}

	/**
	 * 获取相同功能节点
	 * @param nodeid
	 * @return
	 */
	public Node getSimilarNode(String nodeid) {
		return similarNodes!=null ? similarNodes.get(nodeid) : null;
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

	public String getMajorManagerId() {
		return majorManagerId;
	}

	public void setMajorManagerId(String majorManagerId) {
		this.majorManagerId = majorManagerId;
	}

	public NodeApiTable getApiTable() {
		return apiTable;
	}

	public void setApiTable(NodeApiTable apiTable) {
		this.apiTable = apiTable;
	}
	
	public NodeTable getNodeTable() {
		return nodeTable;
	}

	public void setNodeTable(NodeTable nodeTable) {
		this.nodeTable = nodeTable;
	}

	public NodeTable getSimilarNodes() {
		return similarNodes;
	}

	public void setSimilarNodes(NodeTable similarNodes) {
		this.similarNodes = similarNodes;
	}

	private void initManagerNode() {
		if(ConfigManager.me().containsKey("lambkit.node.manager.id")) {
			Node mgrNode = ConfigManager.me().get(Node.class, "lambkit.node.manager");
			mgrNode.setType(NodeType.ManagerNode);
			if (!mgrNode.isUsable()) {
				NodeBuilder builder = new NodeBuilder();
				mgrNode = builder.initNode(mgrNode);
			}
			mgrNode.initRpc();
			putNode(mgrNode);
		}
		
		Properties prop = ConfigManager.me().getProperties();
		Set<String> nodeNames = new HashSet<>();
		for (Map.Entry<Object, Object> entry : prop.entrySet()) {
			String key = entry.getKey().toString();
			if (key.startsWith(NODE_MANAGER_PREFIX) && entry.getValue() != null) {
				String[] keySplits = key.split("\\.");
				if (keySplits.length == 5) {
					nodeNames.add(keySplits[3]);
				}
			}
		}

		for (String name : nodeNames) {
			Node dsc = ConfigManager.me().get(Node.class, NODE_MANAGER_PREFIX + name);
			if (StrKit.isBlank(dsc.getName())) {
				dsc.setName(name);
			}
			if (dsc.isUsable()) {
				dsc.setType(NodeType.ManagerNode);
				putNode(dsc);
			}
		}
	}

	/**
	 * 如果配置的相似节点和系统判断是否相似不一致怎么处理？
	 */
	private void initSimilarNode() {
		if(ConfigManager.me().containsKey("lambkit.node.similar.id")) {
			Node simNode = ConfigManager.me().get(Node.class, "lambkit.node.similar");
			String stype = ConfigManager.me().getValue("lambkit.node.similar.type");
			if(StrKit.notBlank(stype)) simNode.setType(NodeType.valueOf(stype));
			if (!simNode.isUsable()) {
				NodeBuilder builder = new NodeBuilder();
				simNode = builder.initNode(simNode);
			}
			simNode.initRpc();
			putNode(simNode);
		}
		
		Properties prop = ConfigManager.me().getProperties();
		Set<String> nodeNames = new HashSet<>();
		for (Map.Entry<Object, Object> entry : prop.entrySet()) {
			String key = entry.getKey().toString();
			if (key.startsWith(NODE_SIMILAR_PREFIX) && entry.getValue() != null) {
				String[] keySplits = key.split("\\.");
				if (keySplits.length == 5) {
					nodeNames.add(keySplits[3]);
				}
			}
		}

		for (String name : nodeNames) {
			Node dsc = ConfigManager.me().get(Node.class, NODE_SIMILAR_PREFIX + name);
			String type = ConfigManager.me().getValue(NODE_SIMILAR_PREFIX + name + ".type");
			dsc.setType(NodeType.valueOf(type));
			if (StrKit.isBlank(dsc.getName())) {
				dsc.setName(name);
			}
			if (dsc.isUsable()) {
				putSimilarNode(dsc);
			}
		}
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
		System.out.println("major :" + getMajorManagerId());
		System.out.println("---------------------------------------------------");
	}
}
