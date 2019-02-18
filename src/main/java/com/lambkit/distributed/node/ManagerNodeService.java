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

import java.util.List;
import java.util.Properties;

import com.lambkit.distributed.node.api.NodeApi;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.distributed.node.info.NodeGlobalInfo;

public interface ManagerNodeService {

	/**
	 * 获取配置信息
	 * @param nodeid
	 * @return
	 */
	Properties config(String nodeid, String name, String version);
	
	String getToken();
	/**
	 * 心跳机制
	 * ServiceNode发送心跳给ManagerNode
	 * https://blog.csdn.net/luyee2010/article/details/8682578
	 * 
	 * @return 返回globalToken全局token
	 */
	NodeGlobalInfo sendHeartbeat(Node info);
	/**
	 * 上报主节点
	 * @param id
	 * @return
	 */
	String sendMajor(String id);
	/**
	 * 上报Api
	 * @param apis
	 * @return
	 */
	String sendNodeApi(List<NodeApi> apis);
	/**
	 * Api变化更改
	 * @param api
	 * @param type
	 * @return
	 */
	String sendNodeApiChange(String api, String type);
	/**
	 * 主节点id，
	 * @return
	 */
	String getMajorManagerNodeId();
	/**
	 * 获取管理节点列表
	 * @return
	 */
	List<Node> getManagerNodes();
	
	/**
	 * 节点是否活跃
	 * @param node
	 * @return
	 */
	boolean nodeActived(String nodeid);
	/**
	 * 获取相似节点
	 * @param node
	 * @return
	 */
	Node getSimilarNode(Node node);
	/**
	 * 获取节点信息
	 * @param nodeid
	 * @return
	 */
	Node getNode(String nodeid);
	
	/**
	 * 获取Api列表
	 * @return
	 */
	List<NodeApi> getApiList();
	
	/**
	 * 获取api
	 * @param url
	 * @return
	 */
	NodeApi getApi(String url);
}
