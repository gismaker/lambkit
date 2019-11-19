package com.lambkit.core.registry.zookeeper;

import com.lambkit.core.registry.ServiceRegistry;

import org.I0Itec.zkclient.ZkClient;

public class ZkServiceRegistry implements ServiceRegistry {
	
	private String zkAddress = "localhost:2181";
	private String ZK_REGISTRY = "/lambkit";
	private ZkClient zkClient;

	public void init() {
		zkClient = new ZkClient(zkAddress, 1 * 60 * 1000, 1000);
		System.out.println(">>> connect to zookeeper");

	}

	@Override
	public void registry(String serviceName, String serviceAddress) {

		// 创建registry节点（持久）
		String registryPath = ZK_REGISTRY;
		if (!zkClient.exists(registryPath)) {
			zkClient.createPersistent(registryPath);
			System.out.println(">>> create registry node:" + registryPath);
		}

		// 创建service节点（持久）
		String servicePath = registryPath + "/" + serviceName;
		if (!zkClient.exists(servicePath)) {
			zkClient.createPersistent(servicePath);
			System.out.println(">>>create service node:" + servicePath);
		}

		// 创建address节点（临时）
		String addressPath = servicePath + "/address-";
		String addressNode = zkClient.createEphemeralSequential(addressPath, serviceAddress);
		System.out.println(">>> create address node:" + addressNode);

	}
}
