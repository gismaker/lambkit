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
import java.util.Properties;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.api.NodeApi;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.distributed.node.info.NodeGlobalInfo;
import com.lambkit.distributed.node.ManagerNodeService;
import com.lambkit.distributed.token.TokenManager;

public class ManagerNodeServiceImp implements ManagerNodeService {

	@Override
	public Properties config(String nodeid, String name, String version) {
		// TODO Auto-generated method stub
		Prop p = PropKit.use("prop/"+nodeid+"/lambkit.properties");
		if(p==null) {
			p = PropKit.use("prop/"+name+"/"+version+"/lambkit.properties");
		}
		if(p==null) {
			p = PropKit.use("prop/"+name+"/lambkit.properties");
		}
		if(p==null) {
			p = PropKit.use("prop/lambkit.properties");
		}
		return p!=null ? p.getProperties() : null;
	}

	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return TokenManager.me().getGlobalToken();
	}
	
	@Override
	public NodeGlobalInfo sendHeartbeat(Node info) {
		// TODO Auto-generated method stub
		return ManagerNodeSystem.me().handleHeartbeat(info);
	}

	@Override
	public String sendNodeApi(List<NodeApi> apis) {
		// TODO Auto-generated method stub
		return ManagerNodeSystem.me().handleNodeApi(apis);
	}
	
	@Override
	public String sendNodeApiChange(String api, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMajorManagerNodeId() {
		// TODO Auto-generated method stub
		return NodeManager.me().getMajorManagerId();
	}

	@Override
	public List<Node> getManagerNodes() {
		// TODO Auto-generated method stub
		List<Node> list = ManagerNodeSystem.me().getSimilarNodes().getValues();
		list.add(ManagerNodeSystem.me().getNode());
		return list;
	}

	@Override
	public boolean nodeActived(String nodeid) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Node node = ManagerNodeSystem.me().getNode(nodeid);
		if(node!=null && node.isActived()) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Node getSimilarNode(Node node) {
		// TODO Auto-generated method stub
		return ManagerNodeSystem.me().getSimilarNode(node);
	}

	@Override
	public Node getNode(String nodeid) {
		// TODO Auto-generated method stub
		return ManagerNodeSystem.me().getNode(nodeid);
	}

	@Override
	public List<NodeApi> getApiList() {
		// TODO Auto-generated method stub
		return ManagerNodeSystem.me().getApiTable().getValues();
	}

	@Override
	public NodeApi getApi(String url) {
		// TODO Auto-generated method stub
		return NodeManager.me().getApi(url);
	}

	@Override
	public String sendMajor(String id) {
		// TODO Auto-generated method stub
		NodeManager.me().setMajorManagerId(id);
		return null;
	}
}
