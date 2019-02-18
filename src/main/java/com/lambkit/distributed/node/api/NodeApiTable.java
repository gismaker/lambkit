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
package com.lambkit.distributed.node.api;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.lambkit.distributed.node.info.NodeType;
import com.lambkit.distributed.node.memory.NodeApiMemory;
import com.lambkit.distributed.node.memory.NodeApiMemoryCache;
import com.lambkit.distributed.node.memory.NodeApiMemoryHashMap;
import com.lambkit.distributed.node.redis.NodeApiMemoryRedis;

/**
 * Api管理表格Map[url, api]
 * @author 孤竹行
 *
 */
public class NodeApiTable {

	/**
	 * 类型， 1-普通节点使用，当map用，2-管理节点使用，当map(map)用。
	 */
	private NodeType type;
	private NodeApiMemory apis;

	public NodeApiTable(NodeType type, String memory) {
		// TODO Auto-generated constructor stub
		this.type = type;
		if(StrKit.notBlank(memory) && memory.equals("map")) {
			apis = new NodeApiMemoryHashMap();
		} else if(StrKit.notBlank(memory) && memory.equals("redis")) {
			apis = new NodeApiMemoryRedis();
		} else {
			apis = new NodeApiMemoryCache();
		}
	}
	
	public void put(NodeApi api) {
		if(type==NodeType.ManagerNode || type==NodeType.RedisNode) {
			NodeApi oldApi = get(api.getUrl());
			if(oldApi!=null) {
				//如果url已存在，更新
				oldApi.update(api);
				oldApi.put(api.getNodeId(), api.getRuntime());
			} else {
				api.put(api.getNodeId(), api.getRuntime());
				apis.put(api.getUrl(), api);
			}
		} else {
			api.put(api.getNodeId(), api.getRuntime());
			apis.put(api.getUrl(), api);
		}
	}
	
	public NodeApi get(String url) {
		return apis.get(url);
	}
	
	public List getKeys() {
		return apis.getKeys();
	}
	
	public List<NodeApi> getValues() {
		return apis.getValues();
	}
	
	public NodeApiMemory getApis() {
		return apis;
	}
	
	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}
}
