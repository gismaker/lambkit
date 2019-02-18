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
package com.lambkit.core.rpc.motan;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jfinal.kit.StrKit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcConfig;
import com.lambkit.core.rpc.RpcPlugin;
import com.lambkit.exception.LambkitException;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.config.ProtocolConfig;
import com.weibo.api.motan.config.RefererConfig;
import com.weibo.api.motan.config.RegistryConfig;
import com.weibo.api.motan.config.ServiceConfig;
import com.weibo.api.motan.util.MotanSwitcherUtil;

/**
 * Motan rpc
 * 
 * @author 孤竹行
 *
 */
public class MotanRpc extends RpcPlugin {

	private RegistryConfig registryConfig;
	private ProtocolConfig protocolConfig;
	private RpcConfig rpcConfig;

	private static final Map<String, Object> singletons = new ConcurrentHashMap<>();

	public MotanRpc() {
		// TODO Auto-generated constructor stub
		rpcConfig = ConfigManager.me().get(RpcConfig.class);
		registryConfig = new RegistryConfig();
		registryConfig.setCheck(String.valueOf(rpcConfig.isRegistryCheck()));

		/**
		 * 注册中心的调用模式
		 */
		if (rpcConfig.isRegistryCallMode()) {

			registryConfig.setRegProtocol(rpcConfig.getRegistryType());
			registryConfig.setAddress(rpcConfig.getRegistryAddress());
			registryConfig.setName(rpcConfig.getRegistryName());
		}

		/**
		 * 直连模式
		 */
		else if (rpcConfig.isRedirectCallMode()) {
			registryConfig.setRegProtocol("local");
		}

		protocolConfig = new ProtocolConfig();
		protocolConfig.setId("motan");
		protocolConfig.setName("motan");
		// protocolConfig.setFilter("hystrix,opentracing");
		if (StrKit.notBlank(rpcConfig.getSerialization())) {
            protocolConfig.setSerialization(rpcConfig.getSerialization());
        }
	}
	
	@Override
	public <T> T serviceObtain(Class<T> serviceClass, String group, String version, String url) {
		// TODO Auto-generated method stub
		String directUrl = StrKit.isBlank(url) ? rpcConfig.getDirectUrl() : url;
		
		//System.out.println("url:"+url);
		//System.out.println("url:"+rpcConfig.getDirectUrl());
		
		String key = String.format("%s:%s:%s", serviceClass.getName(), group, version);
		
		System.out.println("rpc : [" + url + "] " + key);

		T object = (T) singletons.get(key);
		if (object != null) {
			return object;
		}

		RefererConfig<T> refererConfig = new RefererConfig<T>();

		// 设置接口及实现类
		refererConfig.setInterface(serviceClass);

		// 配置服务的group以及版本号
		refererConfig.setGroup(group);
		refererConfig.setVersion(version);
		refererConfig.setRequestTimeout(rpcConfig.getRequestTimeOut());
		refererConfig.setProtocol(protocolConfig);
		//refererConfig.setProxy(rpcConfig.getProxy());
		//refererConfig.setCheck(String.valueOf(rpcConfig.isConsumerCheck()));

//		System.out.println("rpc timeout : " + rpcConfig.getRequestTimeOut());
//		System.out.println("rpc proxy : " + rpcConfig.getProxy());
//		System.out.println("rpc check : " + rpcConfig.isConsumerCheck());
		
		/**
		 * 注册中心模式
		 */
		if (rpcConfig.isRegistryCallMode()) {
			refererConfig.setRegistry(registryConfig);
		}
		/**
		 * 直连模式
		 */
		else if (rpcConfig.isRedirectCallMode()) {
			if (StrKit.isBlank(directUrl)) {
				throw new LambkitException(
						"directUrl must not be null if you use redirect call mode，please config lambkit.rpc.directUrl value");
			}
			refererConfig.setDirectUrl(directUrl);
			
			// 配置注册中心直连调用
	        RegistryConfig registry = new RegistryConfig();

	        //use direct registry
	        registry.setRegProtocol("local");
	        registry.setAddress(directUrl);
	        refererConfig.setRegistry(registry);
		}

		object = refererConfig.getRef();

		if (object != null) {
			singletons.put(key, object);
		}
		return object;
	}

	@Override
	public <T> T serviceObtain(Class<T> serviceClass, String group, String version) {
		// TODO Auto-generated method stub
		return serviceObtain(serviceClass, group, version, rpcConfig.getDirectUrl());
	}

	/**
	 * motan服务export 参考jboot 参考http://www.cnblogs.com/huangll99/p/6694405.html
	 */
	@Override
	public <T> boolean serviceExport(Class<T> interfaceClass, Object object, String group, String version, int port) {
		// TODO Auto-generated method stub
		super.serviceExport(interfaceClass, object, group, version, port);
		synchronized (this) {
			MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, false);

			ServiceConfig<T> motanServiceConfig = new ServiceConfig<T>();

			//System.out.println(interfaceClass.getName() + "," + group + "," + version + "," + port);
			
			// 设置接口及实现类
			motanServiceConfig.setInterface(interfaceClass);
			motanServiceConfig.setRef((T) object);

			// 配置服务的group以及版本号
			if (StrKit.notBlank(rpcConfig.getHost())) {
                motanServiceConfig.setHost(rpcConfig.getHost());
            }
			motanServiceConfig.setGroup(group);// "motan-demo-rpc"
			motanServiceConfig.setVersion(version);// "1.0"

			// 配置注册中心直连调用
			// RegistryConfig directRegistry = new RegistryConfig();
			// directRegistry.setRegProtocol("local");
			// directRegistry.setCheck("false"); //不检查是否注册成功
			// motanDemoService.setRegistry(directRegistry);
			/*
			 * // 配置ZooKeeper注册中心 RegistryConfig zookeeperRegistry = new
			 * RegistryConfig(); zookeeperRegistry.setRegProtocol("zookeeper");
			 * zookeeperRegistry.setAddress("127.0.0.1:2181");
			 * motanServiceConfig.setRegistry(zookeeperRegistry);
			 * 
			 * // 配置RPC协议 ProtocolConfig protocol = new ProtocolConfig();
			 * protocol.setId("motan"); protocol.setName("motan");
			 * motanServiceConfig.setProtocol(protocol);
			 */
			motanServiceConfig.setRegistry(registryConfig);
			motanServiceConfig.setProtocol(protocolConfig);

			motanServiceConfig.setShareChannel(true);
			// motanDemoService.setApplication("motan");
			motanServiceConfig.setExport(String.format("motan:%s", port));
			// 启动检查
			motanServiceConfig.setCheck(String.valueOf(rpcConfig.isProviderCheck()));

			motanServiceConfig.export();

			MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
		}
		return false;
	}

	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, false);
		return true;
	}

}
