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

import com.jfinal.kit.Kv;
import com.jfinal.render.RenderManager;
import com.jfinal.template.Engine;
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
		Kv attr = Kv.create();
		NodeBuilder nb = new NodeBuilder();
		attr.set("node", nb.resetNodeInfo(NodeManager.me().getNode()));
		int size = 0;
		if(NodeManager.me().getNodeTable()!=null) {
			attr.set("ntable", NodeManager.me().getNodeTable().getValues());
			size = NodeManager.me().getNodeTable().getNodes().size();
		}
		attr.set("ntsize", size);
		Engine engine = RenderManager.me().getEngine();
		String content = "<!DOCTYPE html>\n" + 
				"\n" + 
				"<html>\n" + 
				"\n" + 
				"	<head>\n" + 
				"		<meta charset=\"utf-8\">\n" + 
				"		<title>Lambkit</title>\n" + 
				"		<meta name=\"renderer\" content=\"webkit\">\n" + 
				"		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" + 
				"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n" + 
				"		<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n" + 
				"		<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n" + 
				"		<meta name=\"format-detection\" content=\"telephone=no\">\n" + 
				"\n" + 
				"		<link rel=\"stylesheet\" type=\"text/css\" href=\"https://www.layuicdn.com/layui/css/layui.css\" />\n" + 
				"	</head>\n" + 
				"\n" + 
				"	<body style=\"background-color: #f2f2f2\">\n" + 
				"		<div class=\"layui-layout layui-layout-admin\">\n" + 
				"		\n" + 
				"			<div class=\"layui-header\">\n" + 
				"			<div class=\"layui-container\">\n" + 
				"				<div class=\"layui-logo\">\n" + 
				"				<span style=\"color:white; background-color:green; padding: 5px 1px; padding-left:5px\">Lamb</span><strong style=\"color:yellow; background-color:blue; padding: 5px 2px; padding-right:5px\">kit</strong></div>\n" + 
				"				<!-- 头部区域（可配合layui已有的水平导航） -->\n" + 
				"				<ul class=\"layui-nav layui-layout-left\">\n" + 
				"					<li class=\"layui-nav-item layui-this\">\n" + 
				"						<a href=\"#\">Overview</a>\n" + 
				"					</li>\n" + 
				"				</ul>\n" + 
				"			</div>	</div>\n" + 
				"		</div>\n" + 
				"		<div class=\"layui-container\">\n" + 
				"			<div class=\"layui-row\" style=\"background: white; height: 40px;\">\n" + 
				"			</div>\n" + 
				"			<div class=\"layui-row\" style=\"background: white;\">\n" + 
				"				<div class=\"layui-col-md12\">\n" + 
				"					<div class=\"layui-card\">\n" + 
				"						<div class=\"layui-card-header\"><h1 style=\"display: inline;\">Overview</h1>\n" + 
				"							<span style=\"color: gray;\">#(node.name)#if(node.status==1) (active) #end</span></div>\n" + 
				"						<div class=\"layui-card-body\">\n" + 
				"							<table class=\"layui-table\">\n" + 
				"								<colgroup>\n" + 
				"									<col width=\"50\">\n" + 
				"									<col width=\"200\">\n" + 
				"									<col>\n" + 
				"								</colgroup>\n" + 
				"								<thead>\n" + 
				"									<tr>\n" + 
				"									</tr>\n" + 
				"								</thead>\n" + 
				"								<tbody>\n" + 
				"									<tr>\n" + 
				"										<td>Started:</td>\n" + 
				"										<td>#long2date(node.startTime)</td>\n" + 
				"									</tr>\n" + 
				"									<tr>\n" + 
				"										<td>Version:</td>\n" + 
				"										<td>#(node.lambkitVersion)</td>\n" + 
				"									</tr>\n" + 
				"									<tr>\n" + 
				"										<td>Cluster ID:</td>\n" + 
				"										<td>#(node.id)</td>\n" + 
				"									</tr>\n" + 
				"								</tbody>\n" + 
				"							</table>\n" + 
				"						</div>\n" + 
				"					</div>\n" + 
				"					\n" + 
				"					<div class=\"layui-card\">\n" + 
				"						<div class=\"layui-card-header\"><h1 style=\"\">Summary</h1></div>\n" + 
				"						<div class=\"layui-card-body\">\n" + 
				"							<table class=\"layui-table\">\n" + 
				"								<colgroup>\n" + 
				"									<col width=\"50\">\n" + 
				"									<col width=\"200\">\n" + 
				"									<col>\n" + 
				"								</colgroup>\n" + 
				"								<thead>\n" + 
				"									<tr>\n" + 
				"									</tr>\n" + 
				"								</thead>\n" + 
				"								<tbody>\n" + 
				"									<tr>\n" + 
				"										<td>Name:</td>\n" + 
				"										<td><a href=\"#(ctx)/lambkit/node/info/#(node.id)\">#(node.name)</a></td>\n" + 
				"									</tr>\n" + 
				"									<tr>\n" + 
				"										<td>Type:</td>\n" + 
				"										<td>#(node.type)</td>\n" + 
				"									</tr>\n" + 
				"									<tr>\n" + 
				"										<td>IP:</td>\n" + 
				"										<td>#(node.ip):#(node.port)#(node.contexPath)</td>\n" + 
				"									</tr>\n" + 
				"									<tr>\n" + 
				"										<td>OS Name:</td>\n" + 
				"										<td>#(node.osName)</td>\n" + 
				"									</tr>\n" + 
				"									<tr>\n" + 
				"										<td>OS Memory:</td>\n" + 
				"										<td>#(node.maxMemory) MB</td>\n" + 
				"									</tr>\n" + 
				"									<tr>\n" + 
				"										<td>Total Memory:</td>\n" + 
				"										<td>#(node.totalMemory) MB</td>\n" + 
				"									</tr>\n" + 
				"									\n" + 
				"									<tr>\n" + 
				"										<td>Free Memory:</td>\n" + 
				"										<td>#(node.freeMemory) MB</td>\n" + 
				"									</tr>\n" + 
				"									<tr>\n" + 
				"										<td>Run Time:</td>\n" + 
				"										<td>#(node.runtime/1000) s</td>\n" + 
				"									</tr>\n" + 
				"								</tbody>\n" + 
				"							</table>\n" + 
				"						</div>\n" + 
				"					</div>\n" + 
				"					\n" + 
				"					<div class=\"layui-card\">\n" + 
				"						<div class=\"layui-card-header\"><h1 style=\"\">NodeTable</h1></div>\n" + 
				"						<div class=\"layui-card-body\">\n" + 
				"							<table class=\"layui-table\">\n" + 
				"								<colgroup>\n" + 
				"									<col width=\"150\">\n" + 
				"									<col width=\"200\">\n" + 
				"									<col>\n" + 
				"								</colgroup>\n" + 
				"								<thead>\n" + 
				"									<tr>\n" + 
				"									</tr>\n" + 
				"								</thead>\n" + 
				"								<tbody>\n" + 
				"									#if(ntsize>0)\n" + 
				"									#for(x:ntable)\n" + 
				"									<tr>\n" + 
				"										<td>#(x.name)</td>\n" + 
				"										<td>#(x.type)</td>\n" + 
				"										<td>#(x.lambkitVersion)</td>\n" + 
				"										<td>#(x.ip):#(x.port)</td>\n" + 
				"										<td>#(x.startTime)</td>\n" + 
				"									</tr>\n" + 
				"									#end\n" + 
				"									#end\n" + 
				"								</tbody>\n" + 
				"							</table>\n" + 
				"						</div>\n" + 
				"					</div>\n" + 
				"				</div>\n" + 
				"			</div>\n" + 
				"		</div>\n" + 
				"		<script src=\"https://www.layuicdn.com/layui/layui.js\"></script>\n" + 
				"		<script>\n" + 
				"			//JavaScript代码区域\n" + 
				"			layui.use('element', function() {\n" + 
				"				var element = layui.element;\n" + 
				"			});\n" + 
				"		</script>\n" + 
				"	</body>\n" + 
				"\n" + 
				"</html>";
		renderHtml(engine.getTemplateByString(content).renderToString(attr));
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
