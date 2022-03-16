package com.lambkit.component.nacos;

import java.util.LinkedHashSet;
import java.util.List;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;

public class NacosService {
	
	public static LinkedHashSet<Instance> NacosServiceSet;
	/**
	 * serviceName 服务名 ：服务提供的标识，通过该标识可以唯一确定其指代的服务。
	 * groupName 服务分组：不同的服务可以归类到同一分组。
	 * 提供服务的  本机IP以及访问端口     集群只需要修改IP或端口就行了
	 */
	/**注册实例 
	 * 注册一个实例到服务。
     * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @param ip 服务实例IP
	 * @param port 服务实例端口
	 * @throws NacosException
	 */

	public void registerInstance(String nacosIP, String serviceName, String ip, int port) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		naming.registerInstance(serviceName, ip, port);
	}
	/**注册实例
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @param ip 服务实例IP
	 * @param port 服务实例端口
	 * @param clusterName 集群名
	 * @throws NacosException
	 */
	public void registerInstance(String nacosIP,String serviceName, String ip, int port,String clusterName) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		naming.registerInstance(serviceName, ip, port, clusterName);
	}
	/**注册实例
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @param groupName 组名
	 * @param ip 服务实例IP
	 * @param port 服务实例端口
	 * @param clusterName 集群名
	 * @throws NacosException
	 */
	public void registerInstance(String nacosIP,String serviceName, String groupName, String ip, int port,String clusterName) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		naming.registerInstance(serviceName,groupName, ip, port, clusterName);
	}
	/**注销实例
	 * 删除服务下的一个实例。
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @param ip 服务实例IP
	 * @param port 服务实例端口
	 * @throws NacosException
	 */
	public void deregisterInstance(String nacosIP,String serviceName, String ip, int port) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		naming.deregisterInstance(serviceName, ip, port);
	}
	/**注销实例 
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @param groupName 组名
	 * @param ip 服务实例IP
	 * @param port 服务实例端口
	 * @throws NacosException
	 */
	public void deregisterInstance(String nacosIP,String serviceName, String groupName, String ip, int port) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		naming.deregisterInstance(serviceName,groupName, ip, port);
	}
	/**获取全部实例 
	 * 获取服务下的所有实例。
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @return 实体Instance 
	 * @throws NacosException
	 */
	public List<Instance> getAllInstances(String nacosIP,String serviceName) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		return naming.getAllInstances(serviceName);
	}
	/**获取全部实例
	 * 获取服务下的所有实例。
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @param groupName 组名
	 * @return 实体Instance 
	 * @throws NacosException
	 */
	public List<Instance> getAllInstances(String nacosIP,String serviceName, String groupName) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		return naming.getAllInstances(serviceName,groupName);
	}
	
	
	/**获取健康或不健康实例列表
	 * 根据条件获取过滤后的实例列表。
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @param healthy 是否健康
	 * @return 实体Instance
	 * @throws NacosException
	 */
	public List<Instance> selectInstances(String nacosIP,String serviceName, boolean healthy) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		return naming.selectInstances(serviceName, healthy);
	}
	/**获取健康或不健康实例列表
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @param clusters list集群名
	 * @param healthy 是否健康
	 * @return 实体Instance
	 * @throws NacosException
	 */
	public List<Instance> selectInstances(String nacosIP,String serviceName,  List<String> clusters, boolean healthy) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		return naming.selectInstances(serviceName ,clusters ,healthy);
	}
	
	/**获取一个健康实例
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @return 实体Instance
	 * @throws NacosException
	 */
	public Instance selectOneHealthyInstance(String nacosIP,String serviceName) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		return naming.selectOneHealthyInstance(serviceName);
	}
	
	/**获取一个健康实例
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @param clusters list集群名
	 * @return 实体Instance
	 * @throws NacosException
	 */
	public Instance selectOneHealthyInstance(String nacosIP,String serviceName, List<String> clusters) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		return naming.selectOneHealthyInstance(serviceName);
	}
	/**监听服务
	 * 监听服务下的实例列表变化。
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @throws NacosException
	 */
	public void subscribe(String nacosIP,String serviceName) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		naming.subscribe(serviceName, event -> {
		    if (event instanceof NamingEvent) {
		       List<Instance> instances = ((NamingEvent) event).getInstances();
		       getfind(instances);
		    }
		});
	}
	/**取消监听服务
	 * 取消监听服务下的实例列表变化。
	 * @param nacosIP nacos 的ip
	 * @param serviceName 服务名
	 * @throws NacosException
	 */
	public void unsubscribe(String nacosIP,String serviceName) throws NacosException {
		NamingService naming = NamingFactory.createNamingService(nacosIP);
		naming.unsubscribe(serviceName, event -> {});
	}
	
	//创建一个参数接受匿名内部类的数据
	public void getfind(List<Instance> instances) {
		LinkedHashSet<Instance> Set = new LinkedHashSet<Instance>(instances);
		NacosServiceSet = Set;
	}
	
}
