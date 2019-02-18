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
package com.lambkit.core.rpc.zbus;

import com.jfinal.kit.StrKit;
import com.lambkit.component.zbus.ZbusConfig;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcConfig;
import com.lambkit.core.rpc.RpcPlugin;

import io.zbus.rpc.RpcInvoker;
import io.zbus.rpc.bootstrap.mq.ClientBootstrap;
import io.zbus.rpc.bootstrap.mq.ServiceBootstrap;

public class ZbusRpc extends RpcPlugin {
	
	private ServiceBootstrap service;
	private ClientBootstrap client;
	private ZbusConfig zbusConfig;
	private RpcConfig rpcConfig;
	
	public ZbusRpc() {
		// TODO Auto-generated constructor stub
		zbusConfig = ConfigManager.me().get(ZbusConfig.class);
		rpcConfig = ConfigManager.me().get(RpcConfig.class);
		
		service = new ServiceBootstrap();
		service.serviceName(rpcConfig.getRegistryName());//(zbusConfig.getName());
		if(zbusConfig.getPort()!=0) {
			//[redirect直连模式]内部启动了zbus服务器，zbus与rpc服务之间不通过网络协议栈
			service.port(zbusConfig.getPort());
		} else {
			//[registry注册中心]也可以通过网络链接到远程服务器上
			service.serviceAddress(rpcConfig.getRegistryAddress());
			//service.serviceAddress(zbusConfig.getAddress());
		}
		if(StrKit.notBlank(zbusConfig.getSsl()) && StrKit.notBlank(zbusConfig.getSslkey())) {
			//启用SSL
			service.ssl(zbusConfig.getSsl(), zbusConfig.getSslkey());
		}
		if(StrKit.notBlank(zbusConfig.getToken())) {
			//启用Token权限验证
			service.serviceToken(zbusConfig.getToken());
		}
	
		client = new ClientBootstrap(); 
		client.serviceName(rpcConfig.getRegistryName());//(zbusConfig.getName());
		client.serviceAddress(rpcConfig.getRegistryAddress());//(zbusConfig.getAddress());
		if(StrKit.notBlank(zbusConfig.getToken())) {
			//启用Token权限验证
			client.serviceToken(zbusConfig.getToken());
		}
	}
	
	public boolean start() {
		try {
			service.start();
			System.out.println("[zbus service "+zbusConfig.getName()+"] start :)");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean stop() {
		try {
			client.close();
			service.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public <T> T serviceObtain(Class<T> serviceClass, String group, String version, String url) {
		// TODO Auto-generated method stub
		RpcInvoker rpc = client.invoker();
		return rpc.createProxy(serviceClass);
	}
	
	@Override
	public <T> T serviceObtain(Class<T> serviceClass, String group, String version) {
		// TODO Auto-generated method stub
		RpcInvoker rpc = client.invoker();
		return rpc.createProxy(serviceClass);
	}

	@Override
	public <T> boolean serviceExport(Class<T> interfaceClass, Object object, String group, String version, int port) {
		// TODO Auto-generated method stub
		super.serviceExport(interfaceClass, object, group, version, port);
	    //手动增加模块，使用默认构造，也可以指定模块细节
		service.addModule(object.getClass()); 
		return true;
	}

}
