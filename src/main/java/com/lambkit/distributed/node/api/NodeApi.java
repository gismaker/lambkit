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

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.jfinal.kit.StrKit;
import com.lambkit.distributed.node.NodeConstants;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.web.api.Api;

/**
 * 节点的api，nodes保存的是map[nodeid, runtime],通过qps选举出最快的api给client端使用
 * @author 孤竹行
 *
 */
public class NodeApi extends Api {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8502738914184789355L;
	
	private ConcurrentHashMap<String, NodeApiMonitor> nodes;
	
	public NodeApi() {
		// TODO Auto-generated constructor stub
	}
	
	public NodeApi(String nodeid, String url, String mothod, long time) {
		// TODO Auto-generated constructor stub
		super(nodeid, url, mothod, time);
	}
	
	/**
	 * 获取速度最快的nodeid给应用端
	 * @return
	 */
	public String fastNode() {
		if(nodes.size() < 1) return null;
		if(nodes.size() < 2) return getNodeId();
		long fast = 0;
		long thisTime = System.currentTimeMillis();
		String nodeid = null;
		for(Entry<String, NodeApiMonitor> entry: nodes.entrySet()){
			NodeApiMonitor nam = entry.getValue();
			//提交的时间差
			long deltaTime = nam.getCommitTime() - thisTime;
			if(deltaTime > NodeConstants.HEARTBEAT_CIRCLE) {
				if(!entry.equals(NodeManager.me().getNode())) {
					nodes.remove(entry, nam);
				}
				continue;
			}
			if(nam.getRuntime() < fast) {
				fast = nam.getRuntime();
				nodeid = entry.getKey();
			}
        }
		if(StrKit.notBlank(nodeid)) this.setNodeId(nodeid);
		return nodeid;
	}
	
	public void put(String nodeid, long time) {
		if(nodes==null) {
			nodes = new ConcurrentHashMap<>();
		}
		nodes.put(nodeid, new NodeApiMonitor(time, System.currentTimeMillis()));
	}

	public ConcurrentHashMap<String, NodeApiMonitor> getNodes() {
		return nodes;
	}

	public void setNodes(ConcurrentHashMap<String, NodeApiMonitor> nodes) {
		this.nodes = nodes;
	}
	
	public void setRuntime(long runtime) {
		//setRuntime(runtime);
		if(nodes!=null && StrKit.notBlank(getNodeId())) {
			nodes.put(getNodeId(), new NodeApiMonitor(runtime, System.currentTimeMillis()));
		}
	}

	/**
	private List<Api> apis;
	 * 获取速度最快的api给应用端
	 * @return
	
	public Api getFastApi() {
		if(apis!=null) {
			float maxQps = 0;
			int index = 0;
			for(int i=0; i<apis.size(); i++) {
				if(i==0) {
					maxQps = apis.get(i).getQps();
				} else {
					long time = apis.get(i).getQps();
					if(qps > maxQps) {
						maxQps = qps;
						index = i;
					}
				}
			}
			return apis.get(index);
		}
		return null;
	}

	public List<Api> getApis() {
		return apis;
	}

	public void setApis(List<Api> apis) {
		this.apis = apis;
	}
	 */
}