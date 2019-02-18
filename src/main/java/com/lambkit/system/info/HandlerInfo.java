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
package com.lambkit.system.info;

import java.io.Serializable;

import com.jfinal.handler.Handler;

public class HandlerInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1859534575449373383L;
	private String name;
	private String simpleName;
	
	public HandlerInfo(Class<? extends Handler> handlerClass) {
		// TODO Auto-generated constructor stub
		this.name = handlerClass.getName();
		this.simpleName = handlerClass.getSimpleName();
	}
	
	public HandlerInfo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	
}
