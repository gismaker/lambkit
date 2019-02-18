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

import java.util.List;

import com.jfinal.kit.StrKit;
import com.lambkit.distributed.node.memory.NodeMemory;
import com.lambkit.distributed.node.memory.NodeMemoryCache;
import com.lambkit.distributed.node.memory.NodeMemoryHashMap;
import com.lambkit.distributed.node.redis.NodeMemoryRedis;

/**
 * 节点更新
 * 
 * @author 孤竹行
 *
 */
public class NodeTable {

	private NodeMemory nodes;
	
	private NodeType type;
	
	public NodeTable(NodeType type, String memory) {
		// TODO Auto-generated constructor stub
		this.type = type;
		if(StrKit.notBlank(memory) && memory.equals("map")) {
			nodes = new NodeMemoryHashMap();
		} else if(StrKit.notBlank(memory) && memory.equals("redis")) {
			nodes = new NodeMemoryRedis();
		} else {
			nodes = new NodeMemoryCache();
		}
	}
	
	/**
	 * 存储交换
	 * @param nodeTable
	 */
	public void trans(NodeTable nodeTable) {
		NodeMemory temp = nodes;
		nodes = nodeTable.getNodes();
		nodeTable.setNodes(temp);
	}
	
	public void put(Node node) {
		nodes.put(node.getId(), node);
    }
    
    public Node get(String nodeid) {
    	return nodes.get(nodeid);
    }
    
    public void removeAll() {
    	nodes.removeAll();
    }
    
    public List<String> getKeys() {
		return nodes.getKeys();
	}
	
	public List<Node> getValues() {
		return nodes.getValues();
	}
    
    public NodeMemory getNodes() {
		return nodes;
	}

	public void setNodes(NodeMemory nodes) {
		this.nodes = nodes;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

}
