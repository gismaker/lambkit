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

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.jfinal.kit.StrKit;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcConfig;

/**
 * 节点自己保存信息
 * 
 * @author 孤竹行
 *
 */
public class Node implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2659800322394811809L;

	protected String id;
	
	protected String name;
	protected String version;
	
	/**
	 * host name
	 */
	protected String host;

	protected String ip;
	
	protected int port = -1;
    
	protected String rpcGroup;
	protected String rpcVersion;
	protected int rpcPort = -1;

	/**
     * ctx,request.contextPath
     */
	protected String contexPath;
    /**
     * 节点类型 
     */
	protected NodeType type;
    /**
	 * 节点状态, 0-初始，1-活跃，2-停用，3-过期
	 */
	protected int status = 0;
	/**
	 * 提交数据时间
	 */
	private long commitTime;
    
	////////////////////////////////////

	private String projectName;
	private String projectVersion;
	private String projectMode;
	/**
	 * lambkit包版本
	 */
	private String lambkitVersion;

	/**
	 * 操作系统名称
	 */
	private String osName;
	
	/**
	 * 空闲内存
	 */
	private long freeMemory;
	/**
	 * 内存总量
	 */
	private long totalMemory;
	/**
	 * java虚拟机允许开启的最大的内存
	 */
	private long maxMemory;
	/**
	 * cup占用率
	 */
	private float cpuRate;
	/**
	 * 内存占用率
	 */
	private float memoryRate;
	/**
	 * 上行速度mb/s
	 */
	private float upSpeed;
	/**
	 * 下载速度mb/s
	 */
	private float downSpeed;
	/**
	 * 线程总量
	 */
	private int threadCount;
	/**
	 * 进程号
	 */
	private long pid;

	/**
	 * 程序启动时间
	 */
	private long startTime;

	/**
	 * 类所在路径
	 */
	//private String classPath;

	/**
	 * 工程所在路径
	 */
	//private String projectPath;

	/**
	 * 程序运行时间，单位毫秒
	 */
	private long runtime;

	/**
	 * 提交次数
	 */
	private long commitNums = 0;
	
	
    public Node() {
		// TODO Auto-generated constructor stub
    	this.id = StrKit.getRandomUUID();
    	InetAddress localHost;
		try {
			localHost = InetAddress.getLocalHost();
			String hostName = localHost.getHostName();
			this.setHost(hostName);
			if (this.getIp() == null) {
				this.setIp(localHost.getHostAddress());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

    public Node(String name, String host, String ip, int port, NodeType ntype) {
		// TODO Auto-generated constructor stub
    	this.id = StrKit.getRandomUUID();
    	this.name = name;
    	this.host = ip;
    	this.ip = ip;
    	this.port = port;
    	this.type = ntype;
	}
    
    public Node(String id, String name, String host, String ip, int port, NodeType ntype) {
		// TODO Auto-generated constructor stub
    	this.id = id;
    	this.name = name;
    	this.host = ip;
    	this.ip = ip;
    	this.port = port;
    	this.type = ntype;
	}
    
    public void initRpc() {
    	RpcConfig rpcConfig = ConfigManager.me().get(RpcConfig.class);
    	if(getRpcPort() < 0) {
			setRpcPort(rpcConfig.getDefaultPort());
		}
    	if(StrKit.isBlank(getRpcGroup())) {
			setRpcGroup(rpcConfig.getDefaultGroup());
		}
    	if(StrKit.isBlank(getRpcVersion())) {
			setRpcVersion(rpcConfig.getDefaultVersion());
		}
    }
    /**
     * 是否可用
     * @return
     */
    public boolean isUsable() {
        return (StrKit.notBlank(id) && StrKit.notBlank(ip) && port > 0);
    }
    /**
     * 是否是相同功能的节点
     * @param otherNode
     * @return
     */
    public boolean isSimilar(Node otherNode) {
    	if(StrKit.notBlank(name) && StrKit.notBlank(version) && otherNode!=null) {
    		if(this.name.equalsIgnoreCase(otherNode.getName()) 
    				&& this.version.equalsIgnoreCase(otherNode.getVersion())
    				&& !this.id.equals(otherNode.getId())) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
	 * 激活
	 */
	public void active() {
		this.status = 1;
	}
	
	public void enable() {
		active();
	}
	
	public boolean isActived() {
		return this.status==1 ? true : false;
	}
	
	public boolean isEnable() {
		return isActived();
	}
	
	/**
	 * 停用
	 */
	public void disable() {
		this.status = 2;
	}
	
	public void expired() {
		this.status = 3;
	}
	
	public boolean isExpired() {
		return this.status==3 ? true : false;
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContexPath() {
		return contexPath;
	}

	public void setContexPath(String contexPath) {
		this.contexPath = contexPath;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public long getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(long commitTime) {
		this.commitTime = commitTime;
	}
	
	public int getRpcPort() {
		return rpcPort;
	}

	public void setRpcPort(int rpcPort) {
		this.rpcPort = rpcPort;
	}

	public String getRpcGroup() {
		return rpcGroup;
	}

	public void setRpcGroup(String rpcGroup) {
		this.rpcGroup = rpcGroup;
	}

	public String getRpcVersion() {
		return rpcVersion;
	}

	public void setRpcVersion(String rpcVersion) {
		this.rpcVersion = rpcVersion;
	}
	

	public long getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}

	public long getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public long getMaxMemory() {
		return maxMemory;
	}

	public void setMaxMemory(long maxMemory) {
		this.maxMemory = maxMemory;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/*
	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
	*/

	public long getRuntime() {
		return runtime;
	}

	public void setRuntime(long runtime) {
		this.runtime = runtime;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getLambkitVersion() {
		return lambkitVersion;
	}

	public void setLambkitVersion(String lambkitVersion) {
		this.lambkitVersion = lambkitVersion;
	}

	public String getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}

	public long getCommitNums() {
		return commitNums;
	}

	public void setCommitNums(long commitNums) {
		this.commitNums = commitNums;
	}
	
	public void commitNumsPlus() {
		this.commitNums++;
	}

	public float getCpuRate() {
		return cpuRate;
	}
	public void setCpuRate(float cpuRate) {
		this.cpuRate = cpuRate;
	}
	public float getMemoryRate() {
		return memoryRate;
	}
	public void setMemoryRate(float memoryRate) {
		this.memoryRate = memoryRate;
	}
	public float getUpSpeed() {
		return upSpeed;
	}
	public void setUpSpeed(float upSpeed) {
		this.upSpeed = upSpeed;
	}
	public float getDownSpeed() {
		return downSpeed;
	}
	public void setDownSpeed(float downSpeed) {
		this.downSpeed = downSpeed;
	}
}
