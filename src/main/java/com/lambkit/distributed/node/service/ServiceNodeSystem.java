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
package com.lambkit.distributed.node.service;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.lambkit.core.aop.AopKit;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.distributed.node.ManagerNodeService;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.api.NodeApiTable;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.distributed.node.info.NodeBuilder;
import com.lambkit.distributed.node.info.NodeGlobalInfo;
import com.lambkit.distributed.node.info.NodeTable;
import com.lambkit.distributed.node.info.NodeType;
import com.lambkit.distributed.token.TokenManager;

/**
 * 有个专门处理rpc的断路重连，主机切换，节点更新
 */
public class ServiceNodeSystem {

	private static ServiceNodeSystem me = null;
	private ServiceNodeHeartbeat heartbeat = null;

	public static ServiceNodeSystem me() {
		if (me == null) {
			me = AopKit.singleton(ServiceNodeSystem.class);
		}
		return me;
	}
	
	/**
	 * 开启心跳，启动上报
	 */
	public void start() {
		if(heartbeat==null) {
			heartbeat = new ServiceNodeHeartbeat();
		}
		System.out.println("ServiceNodeSystem.heartbeat.start");
		heartbeat.setHeartbeatOpen(true);
		heartbeat.start();
	}
	
	public void stop() {
		if(heartbeat!=null) {
			heartbeat.close();
		}
		heartbeat=null;
	}

	/**
	 * 
	 */
	public void api() {
		
	}
	/**
	 * 心跳检测内容
	 * @return
	 */
	public NodeGlobalInfo sendHeartbeat() {
		NodeGlobalInfo info = null;
		if(getNode()!=null && getNode().getType()==NodeType.ServiceNode) {
			List<Node> nodeList = null;
			NodeTable nodes = getNodeTable();
			List<String> list = nodes.getNodes().getKeys();
			for (int i = 0; i < list.size(); i++) {
				Node node = nodes.getNodes().get(list.get(i));
				//System.out.println("send from node: " + node.getId());
				ManagerNodeService mns = getRpcService(node);
				if(mns==null) continue;
				NodeGlobalInfo ngi = sendNodeAndApi(mns);
				if(NodeManager.me().isMajorManagerNode(node)) {
					String token = ngi.getToken();
					info = ngi;
					if(StrKit.notBlank(token)) {
						TokenManager.me().setGlobalToken(token);
						//System.out.println("token: " + token);
					}
				}
				if(nodeList==null) {
					nodeList = mns.getManagerNodes();
					//if(nodeList!=null) nodeList.add(node);
				}
			}
			if(nodeList!=null) {
				for (Node node : nodeList) {
					NodeManager.me().putNode(node);
				}
			}
		}
		return info;
	}

	public NodeGlobalInfo sendNodeAndApi(ManagerNodeService mns) {
		NodeGlobalInfo info = null;
		if(mns!=null) {
			Node node = getNode();
			NodeBuilder builder = new NodeBuilder();
			node = builder.resetNodeInfo(node);
			//提交时间
			node.setCommitTime(System.currentTimeMillis());
			//提交次数
			node.commitNumsPlus();
			//提交node
			info = mns.sendHeartbeat(node);
			if(info != null) {
				/*
				List<NodeApi> list = getApiTable().getValues();
				for (NodeApi nodeApi : list) {
					System.out.println("commit api at: " + nodeApi.getNodeId() + nodeApi.getUrl());
				}
				*/
				//提交API
				mns.sendNodeApi(getApiTable().getValues());
			}
		}
		return info;
	}

	public Node getNode() {
		return NodeManager.me().getNode();
	}

	public NodeTable getNodeTable() {
		return NodeManager.me().getNodeTable();
	}

	public NodeApiTable getApiTable() {
		return NodeManager.me().getApiTable();
	}

	private ManagerNodeService getRpcService(Node node) {
		return RpcKit.obtain(ManagerNodeService.class, node.getRpcGroup(), node.getRpcVersion(),
				node.getIp() + ":" + node.getRpcPort());
	}

}