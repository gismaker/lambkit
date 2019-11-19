package com.lambkit.core.registry.consul;

import com.lambkit.Lambkit;
import com.lambkit.core.registry.ServiceDiscovery;
import com.lambkit.core.rpc.RpcConfig;

import com.google.common.net.HostAndPort;  
import com.orbitz.consul.Consul;  
import com.orbitz.consul.HealthClient;  

public class ConsulServiceDiscovery implements ServiceDiscovery {
	private Consul consul;
		
	public void init() {
		RpcConfig config = Lambkit.config(RpcConfig.class);
		consul = Consul.builder().withHostAndPort(HostAndPort.fromString(config.getRegistryAddress())).build();  
	}
	
	@Override
	public String discover(String name) {
		// TODO Auto-generated method stub
		HealthClient client = consul.healthClient();  
        //获取所有服务  
        System.out.println(client.getAllServiceInstances(name).getResponse().size());  
          
        //获取所有正常的服务（健康检测通过的）  
        client.getHealthyServiceInstances(name).getResponse().forEach((resp) -> {  
            System.out.println(resp);  
        });
        return name;
	}

}
