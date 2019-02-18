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
package com.lambkit.web.controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Widget {

	private String renderTemplate;
	private List<WidgerInterceptor> interceptors = null;
	private String name = "default";
	
	public Widget(){
	}
	
	public Widget(String renderview){
		this.renderTemplate = renderview;
	}
	
	/**
	 * 处理数据
	 * @param c
	 * @param tbc
	 * @return
	 */
	public abstract WidgetResult handle(BaseController c);
	
	/**
	 * 执行
	 */
	public WidgetResult execute(BaseController c){
		//拦截器
		if(interceptors!=null && interceptors.size() > 0) {
			for (WidgerInterceptor lambInterceptor : interceptors) {
				lambInterceptor.excute(c);
			}
		}
		//处理器
		return handle(c);
	}
	
	public Widget addInterceptor(WidgerInterceptor interceptor) {
		if(interceptors==null) {
			interceptors = new ArrayList<WidgerInterceptor>();
		}
		interceptors.add(interceptor);
		return this;
	}
	
	public String getRenderTemplate() {
		return renderTemplate;
	}

	public Widget setRenderTemplate(String renderTemplate) {
		this.renderTemplate = renderTemplate;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
