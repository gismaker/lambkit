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
package com.lambkit.distributed.node.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.jfinal.plugin.ehcache.IDataLoader;
import com.lambkit.distributed.node.api.NodeApi;

public class NodeApiMemoryHashMap implements NodeApiMemory {

	private ConcurrentHashMap<String, NodeApi> nodes = new ConcurrentHashMap<>();

	public void add(NodeApi node) {
		nodes.put(node.getUrl(), node);
	}

	public ConcurrentHashMap<String, NodeApi> getAll() {
		return nodes;
	}

	@Override
	public NodeApi get(String key) {
		// TODO Auto-generated method stub
		return nodes.get(key);
	}

	@Override
	public void put(String key, NodeApi value) {
		// TODO Auto-generated method stub
		nodes.put(key, value);
	}

	@Override
	public void put(String key, NodeApi value, int liveSeconds) {
		// TODO Auto-generated method stub
		nodes.put(key, value);
	}

	@Override
	public List<String> getKeys() {
		// TODO Auto-generated method stub
		return (List<String>) nodes.keySet();
	}
	
	@Override
	public List<NodeApi> getValues() {
		// TODO Auto-generated method stub
    	List<NodeApi> list = new ArrayList<>();
    	for (String key : nodes.keySet()) {
			list.add(get(key));
		}
		return list;
	}

	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub
		nodes.remove(key);
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		nodes.clear();
	}

	@Override
	public NodeApi get(String key, IDataLoader dataLoader) {
		// TODO Auto-generated method stub
		return nodes.get(key);
	}

	@Override
	public NodeApi get(String key, IDataLoader dataLoader, int liveSeconds) {
		// TODO Auto-generated method stub
		return nodes.get(key);
	}
}
