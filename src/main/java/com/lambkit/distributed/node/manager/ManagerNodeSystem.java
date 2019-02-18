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
package com.lambkit.distributed.node.manager;

import java.util.List;

import com.lambkit.common.util.ClassNewer;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.distributed.node.ManagerNodeService;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.api.NodeApi;
import com.lambkit.distributed.node.api.NodeApiTable;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.distributed.node.info.NodeGlobalInfo;
import com.lambkit.distributed.node.info.NodeTable;
import com.lambkit.distributed.node.info.NodeType;
import com.lambkit.distributed.token.TokenManager;

/**
 * 实现ManagerNode应该有的功能
 * 
 * @author 孤竹行
 *
 */
public class ManagerNodeSystem {

	private static ManagerNodeSystem me = null;

	private ManagerNodeSystem() {
		// TODO Auto-generated constructor stub
	}

	public static ManagerNodeSystem me() {
		if (me == null) {
			me = ClassNewer.singleton(ManagerNodeSystem.class);
		}
		return me;
	}

	/**
	 * 处理心跳
	 */
	public NodeGlobalInfo handleHeartbeat(Node node) {
		// 更新节点
		System.out.println("add node by "+node.getId());
		node.setCommitTime(System.currentTimeMillis());
		NodeManager.me().putNode(node);
		return createNodeGlobalInfo();
	}
	
	public NodeGlobalInfo createNodeGlobalInfo() {
		return new NodeGlobalInfo();
	}

	/**
	 * 切换主节点，选择node为主节点，通知其他managerNode
	 */
	public void handleMajorManagerNode(Node node) {
		// first remove old major node
		// 未完成, not finish
		NodeManager.me().setMajorManagerId(node.getId());
	}

	public void setMajorManagerNode() {
		Node node = NodeManager.me().getNode();
		if (node.getType() == NodeType.ManagerNode) {
			NodeTable nodeTable = NodeManager.me().getSimilarNodes();
			for (String key : nodeTable.getKeys()) {
				Node mnode = nodeTable.get(key);
				ManagerNodeService mns = getRpcService(mnode);
				if (mns != null)
					mns.sendMajor(getMajorManagerId());
			}
		}
	}

	public String handleNodeApi(List<NodeApi> apis) {
		// add or update api
		addOrUpdateApi(apis);
		return TokenManager.me().getGlobalToken();
	}
	
	public Node getSimilarNode(Node node) {
		// TODO Auto-generated method stub
		NodeTable nodes = getSimilarNodes();
		for (String key : nodes.getKeys()) {
			Node mnode = nodes.get(key);
			boolean flag = node.isSimilar(mnode);
			if (flag) {
				return mnode;
			}
		}
		return null;
	}
	
	public void addOrUpdateApi(List<NodeApi> apis) {
		for (NodeApi nodeApi : apis) {
			System.out.println("add api at: " + nodeApi.getNodeId() + nodeApi.getUrl());
			NodeManager.me().addApi(nodeApi);
		}
	}

	public Node getNode(String nodeid) {
		return NodeManager.me().getNode(nodeid);
	}

	public Node getNode() {
		return NodeManager.me().getNode();
	}

	public NodeTable getNodeTable() {
		return NodeManager.me().getNodeTable();
	}

	public NodeTable getSimilarNodes() {
		return NodeManager.me().getSimilarNodes();
	}

	public NodeApiTable getApiTable() {
		return NodeManager.me().getApiTable();
	}

	public String getMajorManagerId() {
		return NodeManager.me().getMajorManagerId();
	}

	private ManagerNodeService getRpcService(Node node) {
		return RpcKit.obtain(ManagerNodeService.class, node.getRpcGroup(), node.getRpcVersion(),
				node.getIp() + ":" + node.getRpcPort());
	}
}
