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
package com.lambkit.distributed.node.info;

import java.io.Serializable;

import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.token.TokenManager;

public class NodeGlobalInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 845096717821874355L;

	/**
	 * 管理节点数量
	 */
	private int managerNode = 0;
	/**
	 * 管理节点的主节点
	 */
	private String majorMangerId;
	/**
	 * 全局token
	 */
	private String token;
	
	public NodeGlobalInfo() {
		// TODO Auto-generated constructor stub
		if(NodeManager.me().isUsable() && NodeManager.me().isManagerNode()) {
			managerNode = NodeManager.me().getNodeTable().getNodes().size();
			majorMangerId = NodeManager.me().getMajorManagerId();
			token = TokenManager.me().getGlobalToken();
		}
	}
	
	public NodeGlobalInfo(int managerNode, String majorMangerId) {
		// TODO Auto-generated constructor stub
		this.managerNode = managerNode;
		this.majorMangerId = majorMangerId;
	}

	public int getManagerNode() {
		return managerNode;
	}

	public void setManagerNode(int managerNode) {
		this.managerNode = managerNode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMajorMangerId() {
		return majorMangerId;
	}

	public void setMajorMangerId(String majorMangerId) {
		this.majorMangerId = majorMangerId;
	}
	
}
