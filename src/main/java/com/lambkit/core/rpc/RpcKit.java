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
package com.lambkit.core.rpc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.lambkit.core.aop.AopKit;
import com.lambkit.core.config.ConfigManager;

public class RpcKit {
/*
	public static <T> T obtain(Class<T> serviceClass, Node node) {
		return obtain(serviceClass, node.getRpcGroup(), node.getRpcVersion(), node.getRpcPort());
	}
	*/
	public static <T> T obtain(Class<T> serviceClass) {
		RpcConfig rpcConfig = ConfigManager.me().get(RpcConfig.class);
		return obtain(serviceClass, rpcConfig.getDefaultGroup(), rpcConfig.getDefaultVersion());
	}
	
	public static <T> T obtain(Class<T> serviceClass, String group, String version) {
		Rpc rpc = RpcManager.me().getRpc();
		return rpc==null ? null : rpc.serviceObtain(serviceClass, new RpcServiceConfig(group, version));
	}
	
	/**
	 * 获取rpc服务
	 * @param serviceClass
	 * @param group
	 * @param version
	 * @param url 直连的URL
	 * @return
	 */
	public static <T> T obtain(Class<T> serviceClass, String group, String version, String url) {
		Rpc rpc = RpcManager.me().getRpc();
		return rpc==null ? null : rpc.serviceObtain(serviceClass, new RpcServiceConfig(group, version), url);
	}
	
	/**
	 * 获取rpc服务
	 * @param serviceClass
	 * @param serviceClassMock 降级
	 * @return
	 */
	public static <T> T obtain(Class<T> serviceClass, Object serviceClassMock) {
		RpcConfig rpcConfig = ConfigManager.me().get(RpcConfig.class);
		return obtain(serviceClass, serviceClassMock, rpcConfig.getDefaultGroup(), rpcConfig.getDefaultVersion());
	}
	
	/**
	 * 获取rpc服务
	 * @param serviceClass
	 * @param serviceClassMock 降级
	 * @param group
	 * @param version
	 * @return
	 */
	public static <T> T obtain(Class<T> serviceClass, Object serviceClassMock, String group, String version) {
		Rpc rpc = RpcManager.me().getRpc();
		T service = rpc.serviceObtain(serviceClass, new RpcServiceConfig(group, version));
		return (T) (service==null ? serviceClassMock : service);
	}
	
	/**
	 * 本地获取Service
	 * @param serviceClass
	 * @param serviceClassMock 降级
	 * @return
	 */
	public static <T> T enhance(Class<T> serviceClass, Class<T> serviceClassMock) {
		RpcConfig rpcConfig = ConfigManager.me().get(RpcConfig.class);
		return enhance(serviceClass, serviceClassMock, rpcConfig.getDefaultGroup(), rpcConfig.getDefaultVersion());
	}
	
	public static <T> T enhance(Class<T> serviceClass, Class<T> serviceClassMock, String group, String version) {
		Rpc rpc = RpcManager.me().getRpc();
		T service = rpc.serviceObtain(serviceClass, new RpcServiceConfig(group, version));
		return service==null ? AopKit.get(serviceClassMock) : service;
	}
	
	/**
	 * 发布rpc服务
	 * @param interfaceClass
	 * @param object
	 * @return
	 */
	public static <T> boolean serviceExport(Class<T> interfaceClass, Object object) {
		Rpc rpc = RpcManager.me().getRpc();
		RpcConfig rpcConfig = ConfigManager.me().get(RpcConfig.class);
		if(rpc!=null) {
			return rpc.serviceExport(interfaceClass, object, new RpcServiceConfig(rpcConfig));
		}
		return false;
	}
	
	/**
	 * 发布rpc服务
	 * @param interfaceClass
	 * @param object
	 * @param group
	 * @param version
	 * @param port
	 * @return
	 */
	public static <T> boolean serviceExport(Class<T> interfaceClass, Object object, String group, String version, int port) {
		Rpc rpc = RpcManager.me().getRpc();
		if(rpc!=null) {
			return rpc.serviceExport(interfaceClass, object, new RpcServiceConfig(group, version, port));
		}
		return false;
	}
	
	/**
	 * 检测端口占用
	 * @param port
	 * @return
	 */
	public static boolean isLocalPortUsing(int port){
		boolean flag = true;
		try {
			flag = isPortUsing("127.0.0.1", port);
		} catch (Exception e) {
		}
		return flag;
	}
	/***
	 *  true:already in using  false:not using 
	 * @param host
	 * @param port
	 * @throws UnknownHostException 
	 */
	public static boolean isPortUsing(String host,int port) throws UnknownHostException{
		boolean flag = false;
		InetAddress theAddress = InetAddress.getByName(host);
		try {
			Socket socket = new Socket(theAddress,port);
			flag = true;
		} catch (IOException e) {
			
		}
		return flag;
	}
}
