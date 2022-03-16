package com.lambkit.component.nacos;

import com.alibaba.nacos.api.exception.NacosException;

public class NacosConfigThread extends Thread{
	//配置是推送过来的
	public String configNacosIP ;
	public String configDataId ;
	public String configGroup ;

	public NacosConfigThread(String configNacosIP, String configDataId ,String configGroup) {
		this.configNacosIP = configNacosIP;
		this.configDataId = configDataId;
		this.configGroup = configGroup;
	}
	
	@Override
	public void run() {
		NacosConfig nacosPlugin = new NacosConfig();
		while(true) {
			try {	
				NacosConfig.NacosConfigString = nacosPlugin.addListener(configNacosIP, configDataId, configGroup);
				System.out.println(NacosConfig.NacosConfigString );
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NacosException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	

}
