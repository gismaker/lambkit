package com.lambkit.common.service;

import java.io.Serializable;

import com.lambkit.common.util.ClassNewer;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.rpc.RpcConfig;
import com.lambkit.core.rpc.RpcKit;

/**
 * 接口服务对象
 */
public class Service implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String CLIENT = "client";
	public static final String SERVER = "server";
	
	private Class<?> interfaceClass;
	private Class<?> implementClass;
	private Class<?> mockClass;
	private String group;
	private String version;
	private int port;
	private String type;
	
	public Service(Class<?> interfaceClass) {
		this.interfaceClass = interfaceClass;
		RpcConfig rpcConfig = ConfigManager.me().get(RpcConfig.class);
		this.group = rpcConfig.getDefaultGroup();
		this.version = rpcConfig.getDefaultVersion();
		this.port = rpcConfig.getDefaultPort();
		this.type = SERVER;
	}
	
	public Service(Class<?> interfaceClass, Class<?> implementClass) {
		this.interfaceClass = interfaceClass;
		this.implementClass = implementClass;
		RpcConfig rpcConfig = ConfigManager.me().get(RpcConfig.class);
		this.group = rpcConfig.getDefaultGroup();
		this.version = rpcConfig.getDefaultVersion();
		this.port = rpcConfig.getDefaultPort();
		this.type = SERVER;
	}
	
	public Service(Class<?> interfaceClass, Class<?> implementClass, Class<?> mockClass, String group, String version, int port) {
		this.interfaceClass = interfaceClass;
		this.implementClass = implementClass;
		this.mockClass = mockClass;
		this.group = group;
		this.version = version;
		this.port = port;
		this.type = SERVER;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T inject() {
		T t = null;
		if(type.equals(SERVER)) {
			if(implementClass!=null) {
				t = (T) ClassNewer.singleton(implementClass);
			}
			if(t==null && mockClass!=null) {
				t = (T) ClassNewer.singleton(mockClass);
			}
		} else if(type.equals(CLIENT)) {
			if(mockClass!=null) {
				t = (T) RpcKit.obtain(interfaceClass, ClassNewer.singleton(mockClass));
			} else {
				t = (T) RpcKit.obtain(interfaceClass);
			}
		}
		return t;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Class<?> getInterfaceClass() {
		return interfaceClass;
	}
	public void setInterfaceClass(Class<?> interfaceClass) {
		this.interfaceClass = interfaceClass;
	}
	public Class<?> getImplementClass() {
		return implementClass;
	}
	public void setImplementClass(Class<?> implementClass) {
		this.implementClass = implementClass;
	}

	public Class<?> getMockClass() {
		return mockClass;
	}

	public void setMockClass(Class<?> mockClass) {
		this.mockClass = mockClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
