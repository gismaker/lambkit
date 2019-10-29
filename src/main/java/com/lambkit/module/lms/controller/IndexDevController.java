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
package com.lambkit.module.lms.controller;

import java.lang.annotation.Annotation;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.lambkit.common.monitor.SysInfoMonitorManager;
import com.lambkit.common.util.StringUtils;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.web.controller.LambkitController;

//@RequestMapping("/lambkit/dev")
@RequiresPermissions("lms:dev")
public class IndexDevController extends LambkitController {

	public void index() {
		renderTemplate("index.html");
	}
	
	public void console() {
		setAttr("system", NodeManager.me().getNode());
		setAttr("sessionid", getSession().getId());
		setAttr("server", SysInfoMonitorManager.me().getServerInfo());
		renderTemplate("console.html");
	}
	
	/**
	 * 路由列表
	 */
	public void routes() {
		renderTemplate("routes.html");
	}
	
	public void actions() {
		renderTemplate("actions.html");
	}
	
	/**
	 * 处理器列表
	 */
	public void handlers() {
		renderTemplate("handlers.html");
	}
	
	/**
	 * 拦截器列表
	 */
	public void interceptors() {
		keepPara();
		renderTemplate("interceptors.html");
	}
	
	/**
	 * 插件列表
	 */
	public void plugins() {
		renderTemplate("plugins.html");
	}
	
	/**
	 * 标签列表
	 */
	public void tags() {
		renderTemplate("tags.html");
	}
	
	/**
	 * 模型-表格列表
	 */
	public void mappings() {
		renderTemplate("mappings.html");
	}
	
	/**
	 * 配置
	 */
	public void props() {
		String name = getPara("name", "lambkit");
		name += ".properties";
		setAttr("prop", PropKit.use(name).getProperties());
		renderTemplate("props.html");
	}
	
	public void clazz() {
		String name = getPara("name");
		if(StrKit.notBlank(name)) {
			try {
				Class<?> clazz = Class.forName(name);
				
				if(clazz!=null) {
					setAttr("name", clazz.getName());
					setAttr("simpleName", clazz.getSimpleName());
					if(clazz.getSuperclass()!=null) {
						setAttr("superClass", clazz.getSuperclass().getName());
					}
					Annotation[] annotations = clazz.getAnnotations();
					String[] ans = new String[annotations.length];
					for(int i=0; i<annotations.length; i++) {
						ans[i] = annotations[i].annotationType().getName();
					}
					setAttr("annotations", ans);
					setAttr("method", clazz.getDeclaredMethods());
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		renderTemplate("clazz.html");
	}
	
	public void url() {
		// 1.拼接url（当前网页的URL，不包含#及其后面部分）
        String url = getRequest().getRequestURL().toString().split("#")[0];
        String query = getRequest().getQueryString();
        if (StringUtils.isNotBlank(query)) {
            url = url.concat("?").concat(query);
        }
        setAttr("url", url);
		setAttr("baseurl", getBaseUrl());
		renderJson();
	}
}
