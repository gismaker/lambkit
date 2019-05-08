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
 */package com.lambkit.common.service;

import java.io.Serializable;

import com.lambkit.core.aop.AopKit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcConfig;
import com.lambkit.core.rpc.RpcKit;

/**
 * 接口服务对象
 */
public class ServiceObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String CLIENT = "client";
	public static final String SERVER = "server";
	
	private Class<?> interfaceClass;
	private Class<?> implementClass;
	private Class<?> mockClass;
	private String group;
	private String version;
	private int port;
	private String type;
	
	public ServiceObject(Class<?> interfaceClass) {
		this.interfaceClass = interfaceClass;
		RpcConfig rpcConfig = ConfigManager.me().get(RpcConfig.class);
		this.group = rpcConfig.getDefaultGroup();
		this.version = rpcConfig.getDefaultVersion();
		this.port = rpcConfig.getDefaultPort();
		this.type = SERVER;
	}
	
	public ServiceObject(Class<?> interfaceClass, Class<?> implementClass) {
		this.interfaceClass = interfaceClass;
		this.implementClass = implementClass;
		RpcConfig rpcConfig = ConfigManager.me().get(RpcConfig.class);
		this.group = rpcConfig.getDefaultGroup();
		this.version = rpcConfig.getDefaultVersion();
		this.port = rpcConfig.getDefaultPort();
		this.type = SERVER;
	}
	
	public ServiceObject(Class<?> interfaceClass, Class<?> implementClass, Class<?> mockClass, String group, String version, int port) {
		this.interfaceClass = interfaceClass;
		this.implementClass = implementClass;
		this.mockClass = mockClass;
		this.group = group;
		this.version = version;
		this.port = port;
		this.type = SERVER;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T inject() {
		T t = null;
		if(type.equals(SERVER)) {
			if(implementClass!=null) {
				t = (T) AopKit.singleton(implementClass);
			}
			if(t==null && mockClass!=null) {
				t = (T) AopKit.singleton(mockClass);
			}
		} else if(type.equals(CLIENT)) {
			if(mockClass!=null) {
				t = (T) RpcKit.obtain(interfaceClass, AopKit.singleton(mockClass));
			} else {
				t = (T) RpcKit.obtain(interfaceClass);
			}
		}
		return t;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Class<?> getInterfaceClass() {
		return interfaceClass;
	}
	public void setInterfaceClass(Class<?> interfaceClass) {
		this.interfaceClass = interfaceClass;
	}
	public Class<?> getImplementClass() {
		return implementClass;
	}
	public void setImplementClass(Class<?> implementClass) {
		this.implementClass = implementClass;
	}

	public Class<?> getMockClass() {
		return mockClass;
	}

	public void setMockClass(Class<?> mockClass) {
		this.mockClass = mockClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
