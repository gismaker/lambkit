package com.lambkit.component.nacos;

import java.util.LinkedHashSet;
import java.util.List;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.IPlugin;

/**
 * @author Administrator
 *
 */
public class NacosServicePlugin implements IPlugin {
	
	protected static String nacosIP ;//nacos的IP
	protected static String serviceName ;//服务名称
	protected static String ip ;//ip
	protected static int port ;//端口
	protected static String clusterName ;//集群名称
	protected static int Threadsleep= 2000 ;// 获取服务列表时间
	
	protected static List<String> clusters ;
	protected static boolean healthy = true ;//是否健康
	
	public static boolean threadisType = true ;//是否停止获取服务器
	
	
	NacosServiceThread nacosServiceThread ;
	NacosService nacosPlugin ;
	
	public NacosServicePlugin(String nacosIP, String serviceName, String ip, int port, int Threadsleep) {
		if (StrKit.isBlank(nacosIP)) {
			throw new IllegalArgumentException("nacosIP can not be blank");
		}
		if (StrKit.isBlank(serviceName)) {
			throw new IllegalArgumentException("serviceName can not be blank");
		}
		if (StrKit.isBlank(ip)) {
			throw new IllegalArgumentException("ip can not be blank");
		}
		NacosServicePlugin.nacosIP = nacosIP;
		NacosServicePlugin.serviceName = serviceName;
		NacosServicePlugin.ip = ip;
		NacosServicePlugin.port = port;
		NacosServicePlugin.Threadsleep = Threadsleep;
		
		nacosServiceThread = new NacosServiceThread(nacosIP, serviceName, Threadsleep);
		nacosPlugin = new NacosService();
	}
	
	@Override
	public boolean start() {
	
		try {
			nacosPlugin.registerInstance(nacosIP,serviceName,ip,port);
		} catch (NacosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		new Thread(nacosServiceThread,"nacos获取服务定时线程").start();
		return true;
	}

	@Override
	public boolean stop() {
		nacosServiceThread.interrupt();
		try {
			nacosPlugin.deregisterInstance(nacosIP, serviceName, ip, port);
		} catch (NacosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @return 获得注册中心的数据
	 */
	public static LinkedHashSet<Instance> getServiceSet() {
		return NacosService.NacosServiceSet;
	}
}
