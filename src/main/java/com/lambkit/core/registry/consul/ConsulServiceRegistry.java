package com.lambkit.core.registry.consul;

import com.google.common.net.HostAndPort;
import com.lambkit.Lambkit;
import com.lambkit.core.registry.ServiceRegistry;
import com.lambkit.core.rpc.RpcConfig;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.agent.ImmutableRegCheck;
import com.orbitz.consul.model.agent.ImmutableRegistration;

public class ConsulServiceRegistry implements ServiceRegistry {

	private Consul consul;

	public void init() {
		RpcConfig config = Lambkit.config(RpcConfig.class);
		consul = Consul.builder().withHostAndPort(HostAndPort.fromString(config.getRegistryAddress())).build();
	}

	@Override
	public void registry(String serviceName, String serviceAddress) {
		AgentClient agent = consul.agentClient();

		// 健康检测
		ImmutableRegCheck check = ImmutableRegCheck.builder().http("http://192.168.1.104:9020/health").interval("5s")
				.build();

		ImmutableRegistration.Builder builder = ImmutableRegistration.builder();
		builder.id("tomcat1").name("tomcat").addTags("v1").address("192.168.1.104").port(8080).addChecks(check);

		agent.register(builder.build());
	}

}
