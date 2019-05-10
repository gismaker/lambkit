/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.distributed.node.info;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.lambkit.Lambkit;
import com.lambkit.common.Consts;
import com.lambkit.common.LambkitConfig;
import com.lambkit.common.util.DateTimeUtils;

public class NodeBuilder {
	static Log log = Log.getLog(NodeBuilder.class);
	
	public Node createNode() {
		return createNode(NodeType.ServiceNode);
	}

	public Node createNode(NodeType ntype) {
		String name = Lambkit.getLambkitConfig().getName();
		String host = "127.0.0.1";
		String ip = host;
		InetAddress localHost;
		try {
			localHost = InetAddress.getLocalHost();
			host = localHost.getHostName();
			ip = localHost.getHostAddress();
		} catch (UnknownHostException e) {
		}
		int p = 8080;// RequestManager.me().port();
		Node node = new Node(name, host, ip, p, ntype);
		name = StrKit.isBlank(name) ? node.getType() + "-" + node.getId() : name;
		node.setName(name);
		// node.setContexPath(RequestManager.me().contexPath());
		LambkitConfig config = Lambkit.getLambkitConfig();
		node.setProjectName(config.getName());
		node.setProjectMode(config.getMode());
		node.setProjectVersion(config.getVersion());
		node.setLambkitVersion(Consts.LAMBKIT_VERSION);
		return node;
	}
	
	public Node initNode(Node node) {
		if(node==null) return createNode();
		if(StrKit.isBlank(node.getName())) {
			String name = Lambkit.getLambkitConfig().getName();
			name = StrKit.isBlank(name) ? node.getType() + "-" + node.getId() : name;
			node.setName(name);
		}
		String host = "127.0.0.1";
		String ip = host;
		InetAddress localHost;
		try {
			localHost = InetAddress.getLocalHost();
			host = localHost.getHostName();
			ip = localHost.getHostAddress();
		} catch (UnknownHostException e) {
		}
		int p = 8080;// RequestManager.me().port();
		
		if(StrKit.isBlank(node.getHost())) {
			node.setHost(host);
		}
		if(StrKit.isBlank(node.getIp())) {
			node.setIp(ip);
		}
		
		if(node.getType()==null) {
			node.setType(NodeType.ServiceNode);
		}
		if(node.getPort() < 0) {
			node.setPort(p);
		}
		LambkitConfig config = Lambkit.getLambkitConfig();
		node.setProjectName(config.getName());
		node.setProjectMode(config.getMode());
		node.setProjectVersion(config.getVersion());
		node.setLambkitVersion(Consts.LAMBKIT_VERSION);
		return resetNodeInfo(node);
	}

	public Node resetNodeInfo(Node info) {
		Runtime runtime = Runtime.getRuntime();
		// 空闲内存
		long freeMemory = runtime.freeMemory();
		info.setFreeMemory(byteToM(freeMemory));
		// 内存总量
		long totalMemory = runtime.totalMemory();
		info.setTotalMemory(byteToM(totalMemory));
		// 最大允许使用的内存
		long maxMemory = runtime.maxMemory();
		info.setMaxMemory(byteToM(maxMemory));
		// 操作系统
		info.setOsName(System.getProperty("os.name"));
		/*
		InetAddress localHost;
		try {
			localHost = InetAddress.getLocalHost();
			String hostName = localHost.getHostName();
			info.setHost(hostName);
			if (info.getIp() == null) {
				info.setIp(localHost.getHostAddress());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			log.error("无法获取当前主机的主机名与Ip地址");
		}
		*/
		// info.setId(makeClientId(projectName,ipAddress));
		// 程序启动时间
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		long startTime = runtimeMXBean.getStartTime();
		info.setStartTime(startTime);
		// 类所在路径
		//info.setClassPath(runtimeMXBean.getBootClassPath());
		// 程序运行时间
		info.setRuntime(runtimeMXBean.getUptime());
		// 线程总数
		info.setThreadCount(ManagementFactory.getThreadMXBean().getThreadCount());
		//info.setProjectPath(new File("").getAbsolutePath());
		info.setCommitTime(DateTimeUtils.getCurrentTimeLong());
		info.setPid(getPid());
		
		//SysInfoAcquirerService sias = new SysInfoAcquirerService();
		//int threadCount = sias.getThreadCount();
		//String cpuRate = sias.getCPURate(); // CPU使用率
		//String memoryRate = sias.getMemoryRate(); // 内存占用率
		//JSONObject jsonObj = JSON.parseObject(sias.getNetworkThroughput());
		//String upSpeed = jsonObj.getString("txPercent");// 上行速度
		//String downSpeed = jsonObj.getString("rxPercent"); // 下行速度
		//info.setCpuRate(Float.parseFloat(cpuRate));
		//info.setMemoryRate(Float.parseFloat(memoryRate));
		//info.setThreadCount(threadCount);
		//info.setUpSpeed(Float.parseFloat(upSpeed));
		//info.setDownSpeed(Float.parseFloat(downSpeed));
		return info;
	}

	/**
	 * 把byte转换成M
	 * 
	 * @param bytes
	 * @return
	 */
	public long byteToM(long bytes) {
		long kb = (bytes / 1024 / 1024);
		return kb;
	}

	/**
	 * 创建一个客户端ID
	 * 
	 * @param projectName
	 * @param ipAddress
	 * @return
	 */
	public String makeNodeId(String projectName, String ipAddress) {
		String t = projectName + ipAddress + new File("").getAbsolutePath();
		int client_id = t.hashCode();
		client_id = Math.abs(client_id);
		return String.valueOf(client_id);
	}

	/**
	 * 获取进程号，适用于windows与linux
	 * 
	 * @return
	 */
	public long getPid() {
		try {
			String name = ManagementFactory.getRuntimeMXBean().getName();
			String pid = name.split("@")[0];
			return Long.parseLong(pid);
		} catch (NumberFormatException e) {
			log.warn("无法获取进程Id");
			return 0;
		}
	}

}
