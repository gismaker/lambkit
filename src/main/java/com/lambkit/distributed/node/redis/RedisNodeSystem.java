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
package com.lambkit.distributed.node.redis;


import com.lambkit.common.aop.AopKit;
import com.lambkit.distributed.node.NodeRedisManager;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.distributed.node.info.NodeBuilder;
import com.lambkit.distributed.node.info.NodeGlobalInfo;
import com.lambkit.distributed.node.service.ServiceNodeHeartbeat;

/**
 * 有个专门处理rpc的断路重连，主机切换，节点更新
 */
public class RedisNodeSystem {

	private static RedisNodeSystem me = null;
	private ServiceNodeHeartbeat heartbeat = null;

	public static RedisNodeSystem me() {
		if (me == null) {
			me = AopKit.singleton(RedisNodeSystem.class);
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
		Node node = getNode();
		NodeBuilder builder = new NodeBuilder();
		node = builder.resetNodeInfo(node);
		//提交时间
		node.setCommitTime(System.currentTimeMillis());
		//提交次数
		node.commitNumsPlus();
		//提交node
		info = handleHeartbeat(node);
		info.setToken(node.getId());
		return info;
	}
	
	/**
	 * 处理心跳
	 */
	public NodeGlobalInfo handleHeartbeat(Node node) {
		// 更新节点
		System.out.println("add node by "+node.getId());
		//redis保存node信息
		NodeRedisManager.me().putNode(node);
		return createNodeGlobalInfo();
	}
	
	public NodeGlobalInfo createNodeGlobalInfo() {
		return new NodeGlobalInfo(1, "redis");
	}
	
	public Node getNode() {
		return NodeRedisManager.me().getNode();
	}

}