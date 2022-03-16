package com.lambkit.component.nacos;

import java.util.Properties;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

public class NacosConfig {
	public static String  NacosConfigString;
	
	
	/**
	 * dataId 配置 ID : 采用类似 package.class（如com.taobao.tc.refund.log.level）的命名规则保证全局唯一性，
	 * class 部分建议是配置的业务含义。全部字符小写。只允许英文字符和 4 种特殊字符（"."、":"、"-"、"_"），不超过 256 字节。
	 * 
	 * group	配置分组 : 建议填写产品名:模块名（Nacos:Test）保证唯一性，只允许英文字符和4种特殊字符（"."、":"、"-"、"_"），不超过128字节。
	 */
	
	/**获取配置
	 * @param dataId 配置 ID
	 * @param group	配置分组
	 * @param timeoutMs 读取配置超时时间，单位 ms，推荐值 3000。
	 * @return 配置信息
	 * @throws NacosException 
	 */
	public String getConfig(String nacosIP, String dataId, String group) throws NacosException {
			Properties properties = new Properties();
			properties.put("serverAddr", NacosConfigPlugin.configNacosIP);
			ConfigService configService = NacosFactory.createConfigService(properties);
			return configService.getConfig(dataId, group, 5000);
	}

	/**监听配置  如果希望 Nacos 推送配置变更，可以使用 Nacos 动态监听配置接口来实现。
	 * @param dataId 配置 ID，
	 * @param group	配置分组
	 * @return 
	 * @throws NacosException
	 */
	public String addListener(String nacosIP, String dataId, String group) throws NacosException {
		Properties properties = new Properties();
		properties.put("serverAddr", NacosConfigPlugin.configNacosIP);
		ConfigService configService = NacosFactory.createConfigService(properties);
		return  configService.getConfig(dataId, group, 5000);
	}

	/**删除监听    取消监听配置，取消监听后配置不会再推送。
	 * @param dataId 配置 ID，
	 * @param group	配置分组
	 * @throws NacosException
	 */
	public void removeListener(String nacosIP, String dataId, String group, Listener listener) throws NacosException {
		Properties properties = new Properties();
		properties.put("serverAddr", NacosConfigPlugin.configNacosIP);
		ConfigService configService = NacosFactory.createConfigService(properties);
		configService.removeListener(dataId, group, listener);
	}

	/**发布配置
	 * @param dataId 配置 ID，
	 * @param group	配置分组
	 * @param content 配置文件内容
	 * @return boolean
	 * @throws NacosException 
	 */
	public boolean publishConfig(String nacosIP, String dataId, String group, String content) throws NacosException {
			Properties properties = new Properties();
			properties.put("serverAddr", NacosConfigPlugin.configNacosIP);
			ConfigService configService = NacosFactory.createConfigService(properties);
			return configService.publishConfig(dataId, group, content);
	}

	/**删除配置
	 * @param dataId 配置 ID，
	 * @param group	配置分组
	 * @return boolean
	 * @throws NacosException 
	 */
	public boolean removeConfig(String nacosIP, String dataId, String group) throws NacosException {
			Properties properties = new Properties();
			properties.put("serverAddr", NacosConfigPlugin.configNacosIP);
			ConfigService configService = NacosFactory.createConfigService(properties);
			return configService.removeConfig(dataId, group);
	}
}
