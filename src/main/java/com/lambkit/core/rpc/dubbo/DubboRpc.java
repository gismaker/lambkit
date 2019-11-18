package com.lambkit.core.rpc.dubbo;

import com.lambkit.Lambkit;
import com.lambkit.common.exception.LambkitIllegalConfigException;
import com.lambkit.common.util.StringUtils;
import com.lambkit.component.dubbo.DubborpcConfig;
import com.lambkit.core.rpc.RpcPlugin;
import com.lambkit.core.rpc.RpcServiceConfig;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.jfinal.kit.StrKit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DubboRpc extends RpcPlugin {
	private static final Map<String, Object> singletons = new ConcurrentHashMap();
	private DubborpcConfig dubboConfig;
	private RegistryConfig registryConfig;

	public DubboRpc() {
		this.dubboConfig = ((DubborpcConfig) Lambkit.config(DubborpcConfig.class));

		this.registryConfig = new RegistryConfig();
		this.registryConfig.setCheck(Boolean.valueOf(getConfig().isRegistryCheck()));
		if (getConfig().getRegistryFile() != null) {
			this.registryConfig.setFile(getConfig().getRegistryFile());
		}
		if (getConfig().isRegistryCallMode()) {
			this.registryConfig.setProtocol(getConfig().getRegistryType());
			this.registryConfig.setAddress(getConfig().getRegistryAddress());
			this.registryConfig.setUsername(getConfig().getRegistryUserName());
			this.registryConfig.setPassword(getConfig().getRegistryPassword());
		} else if (getConfig().isDirectCallMode()) {
			this.registryConfig.setAddress("N/A");
		}
	}

	private ApplicationConfig createApplicationConfig(String group) {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName(group);
		if ((this.dubboConfig.getQosEnable() != null) && (this.dubboConfig.getQosEnable().booleanValue())) {
			applicationConfig.setQosEnable(Boolean.valueOf(true));
			applicationConfig.setQosPort(this.dubboConfig.getQosPort());
			applicationConfig.setQosAcceptForeignIp(this.dubboConfig.getQosAcceptForeignIp());
		} else {
			applicationConfig.setQosEnable(Boolean.valueOf(false));
		}
		return applicationConfig;
	}

	public <T> T serviceObtain(Class<T> serviceClass, RpcServiceConfig serviceConfig) {
		return serviceObtain(serviceClass, serviceConfig, getConfig().getDirectUrl());
	}

	public <T> T serviceObtain(Class<T> serviceClass, RpcServiceConfig serviceConfig, String url) {
		String key = String.format("%s:%s:%s",
				new Object[] { serviceClass.getName(), serviceConfig.getGroup(), serviceConfig.getVersion() });
		String directUrl = StrKit.isBlank(url) ? getConfig().getDirectUrl() : url;

		T object = (T) singletons.get(key);
		if (object != null) {
			return object;
		}
		ReferenceConfig<T> reference = new ReferenceConfig();
		reference.setApplication(createApplicationConfig(serviceConfig.getGroup()));
		reference.setInterface(serviceClass);
		reference.setCheck(Boolean.valueOf(getConfig().isConsumerCheck()));

		initReference(reference, serviceConfig);
		if (getConfig().isRegistryCallMode()) {
			reference.setRegistry(this.registryConfig);
		} else if (getConfig().isDirectCallMode()) {
			if (StringUtils.isBlank(directUrl)) {
				throw new LambkitIllegalConfigException(
						"directUrl must not be blank if you use direct call mode, please config lambkit.rpc.directUrl value");
			}
			reference.setUrl(directUrl);
		}
		object = reference.get();
		if (object != null) {
			singletons.put(key, object);
		}
		return object;
	}

	public <T> boolean serviceExport(Class<T> interfaceClass, Object object, RpcServiceConfig serviceConfig) {
		ProtocolConfig protocolConfig = this.dubboConfig.newProtocolConfig();
		if ((protocolConfig.getHost() == null) && (getConfig().getHost() != null)) {
			protocolConfig.setHost(getConfig().getHost());
		}
		if ((protocolConfig.getSerialization() == null) && (getConfig().getSerialization() != null)) {
			protocolConfig.setSerialization(getConfig().getSerialization());
		}
		protocolConfig.setPort(Integer.valueOf(serviceConfig.getPort()));

		ServiceConfig<T> service = new ServiceConfig();
		service.setApplication(createApplicationConfig(serviceConfig.getGroup()));
		service.setRegistry(this.registryConfig);
		service.setProtocol(protocolConfig);
		service.setInterface(interfaceClass);
		service.setRef((T) object);

		initService(service, serviceConfig);

		service.export();

		return true;
	}

	private static void initReference(ReferenceConfig reference, RpcServiceConfig config) {
		reference.setGroup(config.getGroup());
		reference.setVersion(config.getVersion());
		reference.setTimeout(config.getTimeout());
		if (config.getRetries() != null) {
			reference.setRetries(config.getRetries());
		}
		if (config.getActives() != null) {
			reference.setActives(config.getActives());
		}
		if (config.getLoadbalance() != null) {
			reference.setLoadbalance(config.getLoadbalance());
		}
		if (config.getAsync() != null) {
			reference.setAsync(config.getAsync());
		}
		if (config.getCheck() != null) {
			reference.setCheck(config.getCheck());
		}
		if (StringUtils.isNotBlank(config.getProxy())) {
			reference.setProxy(config.getProxy());
		}
		if (StringUtils.isNotBlank(config.getFilter())) {
			reference.setFilter(config.getFilter());
		}
	}

	private static void initService(ServiceConfig service, RpcServiceConfig config) {
		service.setGroup(config.getGroup());
		service.setVersion(config.getVersion());
		service.setTimeout(config.getTimeout());
		if (config.getRetries() != null) {
			service.setRetries(config.getRetries());
		}
		if (config.getActives() != null) {
			service.setActives(config.getActives());
		}
		if (config.getLoadbalance() != null) {
			service.setLoadbalance(config.getLoadbalance());
		}
		if (config.getAsync() != null) {
			service.setAsync(config.getAsync());
		}
		if (StringUtils.isNotBlank(config.getProxy())) {
			service.setProxy(config.getProxy());
		}
		if (StringUtils.isNotBlank(config.getFilter())) {
			service.setFilter(config.getFilter());
		}
	}

	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return true;
	}
}
