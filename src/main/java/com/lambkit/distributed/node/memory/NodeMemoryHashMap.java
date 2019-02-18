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
import com.lambkit.distributed.node.info.Node;

public class NodeMemoryHashMap implements NodeMemory {

	private ConcurrentHashMap<String, Node> nodes = new ConcurrentHashMap<>();

	public void add(Node node) {
		nodes.put(node.getId(), node);
	}

	public ConcurrentHashMap<String, Node> getAll() {
		return nodes;
	}

	@Override
	public Node get(String key) {
		// TODO Auto-generated method stub
		return nodes.get(key);
	}

	@Override
	public void put(String key, Node value) {
		// TODO Auto-generated method stub
		nodes.put(key, value);
	}

	@Override
	public void put(String key, Node value, int liveSeconds) {
		// TODO Auto-generated method stub
		nodes.put(key, value);
	}

	@Override
	public List<String> getKeys() {
		// TODO Auto-generated method stub
		return (List<String>) nodes.keySet();
	}
	
	@Override
	public List<Node> getValues() {
		// TODO Auto-generated method stub
    	List<Node> list = new ArrayList<>();
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
	public Node get(String key, IDataLoader dataLoader) {
		// TODO Auto-generated method stub
		return nodes.get(key);
	}

	@Override
	public Node get(String key, IDataLoader dataLoader, int liveSeconds) {
		// TODO Auto-generated method stub
		return nodes.get(key);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return nodes.size();
	}
}
