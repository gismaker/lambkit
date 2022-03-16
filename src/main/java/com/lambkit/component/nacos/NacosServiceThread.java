package com.lambkit.component.nacos;

import com.alibaba.nacos.api.exception.NacosException;

public class NacosServiceThread extends Thread{

	public String serviceName ;
	public String nacosIP ;
	public int Threadsleep ;

	public NacosServiceThread(String nacosIP, String serviceName ,int Threadsleep) {
		this.nacosIP = nacosIP;
		this.serviceName = serviceName;
		this.Threadsleep = Threadsleep;
		
		
	}
	@Override
	public void run() {
		while(true) {
			try {	
				NacosService nacosPlugin = new NacosService();
				Thread.sleep(Threadsleep);
				nacosPlugin.subscribe(nacosIP ,serviceName);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (NacosException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	

}
