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
package com.lambkit.distributed.node.controller;

import java.util.List;

import com.lambkit.common.ResultKit;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.distributed.node.info.NodeBuilder;
import com.lambkit.web.controller.LambkitController;

public class NodeIndexController extends LambkitController {

	public void index() {
		//user
		if(hasUser()) {
			set("auth", getUser());
		}
				
		NodeBuilder nb = new NodeBuilder();
		setAttr("node", nb.resetNodeInfo(NodeManager.me().getNode()));
		int size = 0;
		if(NodeManager.me().getNodeTable()!=null) {
			setAttr("ntable", NodeManager.me().getNodeTable().getValues());
			size = NodeManager.me().getNodeTable().getNodes().size();
		}
		setAttr("ntsize", size);
		
		renderTemplate("index.html");
	}
	
	public void info() {
		setAttr("node", NodeManager.me().getNode(getPara(0)));
		renderTemplate("node.html");
	}
	
	public void node() {
		NodeBuilder nb = new NodeBuilder();
		renderJson(ResultKit.json(1, "success", nb.resetNodeInfo(NodeManager.me().getNode())));
	}
	
	public void table() {
		int size = 0;
		List<Node> values = null;
		if(NodeManager.me().getNodeTable()!=null) {
			values = NodeManager.me().getNodeTable().getValues();
			size = NodeManager.me().getNodeTable().getNodes().size();
		}
		renderJson(ResultKit.layui(1, "success", size, values));
	}
}
