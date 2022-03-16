package com.lambkit.component.nacos;

import java.util.concurrent.Executor;

import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.IPlugin;

public class NacosConfigPlugin implements IPlugin {
	
	protected static String configNacosIP ;//nacos的IP
	protected static String configDataId ;//配置 ID
	protected static String configGroup ;//配置分组
	
	NacosConfigThread nacosConfigThread ;
	NacosConfig nacosConfig ;
	
	public NacosConfigPlugin(String configNacosIP, String configDataId, String configGroup) {
		if (StrKit.isBlank(configNacosIP)) {
			throw new IllegalArgumentException("configNacosIP can not be blank");
		}
		if (StrKit.isBlank(configDataId)) {
			throw new IllegalArgumentException("configDataId can not be blank");
		}
		if (StrKit.isBlank(configGroup)) {
			throw new IllegalArgumentException("configGroup can not be blank");
		}
		NacosConfigPlugin.configNacosIP = configNacosIP;
		NacosConfigPlugin.configDataId = configDataId;
		NacosConfigPlugin.configGroup = configGroup;
		
		nacosConfigThread = new NacosConfigThread(configNacosIP, configDataId, configGroup);
		nacosConfig = new NacosConfig();
	}
	
	@Override
	public boolean start() {
		try {
			NacosConfig.NacosConfigString = nacosConfig.getConfig(configNacosIP,configDataId,configGroup);
		} catch (NacosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(nacosConfigThread,"nacos获取配置定时线程").start();
		return true;
	}

	@Override
	public boolean stop() {
		nacosConfigThread.interrupt();
		try {
			nacosConfig.removeListener(configNacosIP, configDataId, configGroup, new Listener() {
				@Override
				public void receiveConfigInfo(String configInfo) { }
				@Override
				public Executor getExecutor() { return null; }
			});
		} catch (NacosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @return 获取配置中心数据
	 */
	public static String getNacosConfigString() {
		return NacosConfig.NacosConfigString;
	}
}
