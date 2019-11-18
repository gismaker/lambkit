package com.lambkit.component.dubbo;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.lambkit.core.config.annotation.PropertieConfig;

@PropertieConfig(prefix = "lambkit.component.dubbo.rpc")
public class DubborpcConfig {
	
	private String protocolName = "dubbo";
	private String protocolServer = "netty";
	private String protocolContextPath;
	private String protocolTransporter;
	private Integer protocolThreads;
	private Boolean qosEnable = Boolean.valueOf(false);
	private Integer qosPort;
	private Boolean qosAcceptForeignIp;
	private String protocolHost;
	private Integer protocolPort;
	private String protocolContextpath;
	private String protocolThreadpool;
	private Integer protocolIothreads;
	private Integer protocolQueues;
	private Integer protocolAccepts;
	private String protocolCodec;
	private String protocolSerialization;
	private String protocolCharset;
	private Integer protocolPayload;
	private Integer protocolBuffer;
	private Integer protocolHeartbeat;
	private String protocolAccesslog;
	private String protocolExchanger;
	private String protocolDispatcher;
	private String protocolNetworker;
	private String protocolClient;
	private String protocolTelnet;
	private String protocolPrompt;
	private String protocolStatus;
	private Boolean protocolRegister;
	private Boolean protocolKeepAlive;
	private String protocolOptimizer;
	private String protocolExtension;
	private Boolean protocolIsDefault;

	public String getProtocolName() {
		return this.protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public String getProtocolServer() {
		return this.protocolServer;
	}

	public void setProtocolServer(String protocolServer) {
		this.protocolServer = protocolServer;
	}

	public String getProtocolContextPath() {
		return this.protocolContextPath;
	}

	public void setProtocolContextPath(String protocolContextPath) {
		this.protocolContextPath = protocolContextPath;
	}

	public String getProtocolTransporter() {
		return this.protocolTransporter;
	}

	public void setProtocolTransporter(String protocolTransporter) {
		this.protocolTransporter = protocolTransporter;
	}

	public int getProtocolThreads() {
		return this.protocolThreads.intValue();
	}

	public void setProtocolThreads(int protocolThreads) {
		this.protocolThreads = Integer.valueOf(protocolThreads);
	}

	public Boolean getQosEnable() {
		return this.qosEnable;
	}

	public void setQosEnable(Boolean qosEnable) {
		this.qosEnable = qosEnable;
	}

	public Integer getQosPort() {
		return this.qosPort;
	}

	public void setQosPort(Integer qosPort) {
		this.qosPort = qosPort;
	}

	public Boolean getQosAcceptForeignIp() {
		return this.qosAcceptForeignIp;
	}

	public void setQosAcceptForeignIp(Boolean qosAcceptForeignIp) {
		this.qosAcceptForeignIp = qosAcceptForeignIp;
	}

	public String getProtocolHost() {
		return this.protocolHost;
	}

	public void setProtocolHost(String protocolHost) {
		this.protocolHost = protocolHost;
	}

	public Integer getProtocolPort() {
		return this.protocolPort;
	}

	public void setProtocolPort(Integer protocolPort) {
		this.protocolPort = protocolPort;
	}

	public String getProtocolContextpath() {
		return this.protocolContextpath;
	}

	public void setProtocolContextpath(String protocolContextpath) {
		this.protocolContextpath = protocolContextpath;
	}

	public String getProtocolThreadpool() {
		return this.protocolThreadpool;
	}

	public void setProtocolThreadpool(String protocolThreadpool) {
		this.protocolThreadpool = protocolThreadpool;
	}

	public Integer getProtocolIothreads() {
		return this.protocolIothreads;
	}

	public void setProtocolIothreads(Integer protocolIothreads) {
		this.protocolIothreads = protocolIothreads;
	}

	public Integer getProtocolQueues() {
		return this.protocolQueues;
	}

	public void setProtocolQueues(Integer protocolQueues) {
		this.protocolQueues = protocolQueues;
	}

	public Integer getProtocolAccepts() {
		return this.protocolAccepts;
	}

	public void setProtocolAccepts(Integer protocolAccepts) {
		this.protocolAccepts = protocolAccepts;
	}

	public String getProtocolCodec() {
		return this.protocolCodec;
	}

	public void setProtocolCodec(String protocolCodec) {
		this.protocolCodec = protocolCodec;
	}

	public String getProtocolSerialization() {
		return this.protocolSerialization;
	}

	public void setProtocolSerialization(String protocolSerialization) {
		this.protocolSerialization = protocolSerialization;
	}

	public String getProtocolCharset() {
		return this.protocolCharset;
	}

	public void setProtocolCharset(String protocolCharset) {
		this.protocolCharset = protocolCharset;
	}

	public Integer getProtocolPayload() {
		return this.protocolPayload;
	}

	public void setProtocolPayload(Integer protocolPayload) {
		this.protocolPayload = protocolPayload;
	}

	public Integer getProtocolBuffer() {
		return this.protocolBuffer;
	}

	public void setProtocolBuffer(Integer protocolBuffer) {
		this.protocolBuffer = protocolBuffer;
	}

	public Integer getProtocolHeartbeat() {
		return this.protocolHeartbeat;
	}

	public void setProtocolHeartbeat(Integer protocolHeartbeat) {
		this.protocolHeartbeat = protocolHeartbeat;
	}

	public String getProtocolAccesslog() {
		return this.protocolAccesslog;
	}

	public void setProtocolAccesslog(String protocolAccesslog) {
		this.protocolAccesslog = protocolAccesslog;
	}

	public String getProtocolExchanger() {
		return this.protocolExchanger;
	}

	public void setProtocolExchanger(String protocolExchanger) {
		this.protocolExchanger = protocolExchanger;
	}

	public String getProtocolDispatcher() {
		return this.protocolDispatcher;
	}

	public void setProtocolDispatcher(String protocolDispatcher) {
		this.protocolDispatcher = protocolDispatcher;
	}

	public String getProtocolNetworker() {
		return this.protocolNetworker;
	}

	public void setProtocolNetworker(String protocolNetworker) {
		this.protocolNetworker = protocolNetworker;
	}

	public String getProtocolClient() {
		return this.protocolClient;
	}

	public void setProtocolClient(String protocolClient) {
		this.protocolClient = protocolClient;
	}

	public String getProtocolTelnet() {
		return this.protocolTelnet;
	}

	public void setProtocolTelnet(String protocolTelnet) {
		this.protocolTelnet = protocolTelnet;
	}

	public String getProtocolPrompt() {
		return this.protocolPrompt;
	}

	public void setProtocolPrompt(String protocolPrompt) {
		this.protocolPrompt = protocolPrompt;
	}

	public String getProtocolStatus() {
		return this.protocolStatus;
	}

	public void setProtocolStatus(String protocolStatus) {
		this.protocolStatus = protocolStatus;
	}

	public Boolean getProtocolRegister() {
		return this.protocolRegister;
	}

	public void setProtocolRegister(Boolean protocolRegister) {
		this.protocolRegister = protocolRegister;
	}

	public Boolean getProtocolKeepAlive() {
		return this.protocolKeepAlive;
	}

	public void setProtocolKeepAlive(Boolean protocolKeepAlive) {
		this.protocolKeepAlive = protocolKeepAlive;
	}

	public String getProtocolOptimizer() {
		return this.protocolOptimizer;
	}

	public void setProtocolOptimizer(String protocolOptimizer) {
		this.protocolOptimizer = protocolOptimizer;
	}

	public String getProtocolExtension() {
		return this.protocolExtension;
	}

	public void setProtocolExtension(String protocolExtension) {
		this.protocolExtension = protocolExtension;
	}

	public Boolean getProtocolIsDefault() {
		return this.protocolIsDefault;
	}

	public void setProtocolIsDefault(Boolean protocolIsDefault) {
		this.protocolIsDefault = protocolIsDefault;
	}

	public ProtocolConfig newProtocolConfig() {
		ProtocolConfig config = new ProtocolConfig();
		if (this.protocolDispatcher != null) {
			config.setDispatcher(this.protocolDispatcher);
		}
		if (this.protocolIsDefault != null) {
			config.setDefault(this.protocolIsDefault);
		}
		if (this.protocolClient != null) {
			config.setClient(this.protocolClient);
		}
		if (this.protocolCharset != null) {
			config.setCharset(this.protocolCharset);
		}
		if (this.protocolAccepts != null) {
			config.setAccepts(this.protocolAccepts);
		}
		if (this.protocolAccesslog != null) {
			config.setAccesslog(this.protocolAccesslog);
		}
		if (this.protocolBuffer != null) {
			config.setBuffer(this.protocolBuffer);
		}
		if (this.protocolCodec != null) {
			config.setCodec(this.protocolCodec);
		}
		if (this.protocolContextpath != null) {
			config.setContextpath(this.protocolContextpath);
		}
		if (this.protocolExchanger != null) {
			config.setExchanger(this.protocolExchanger);
		}
		if (this.protocolExtension != null) {
			config.setExtension(this.protocolExtension);
		}
		if (this.protocolHeartbeat != null) {
			config.setHeartbeat(this.protocolHeartbeat);
		}
		if (this.protocolHost != null) {
			config.setHost(this.protocolHost);
		}
		if (this.protocolIothreads != null) {
			config.setIothreads(this.protocolIothreads);
		}
		if (this.protocolKeepAlive != null) {
			config.setKeepAlive(this.protocolKeepAlive);
		}
		if (this.protocolName != null) {
			config.setName(this.protocolName);
		}
		if (this.protocolNetworker != null) {
			config.setNetworker(this.protocolNetworker);
		}
		if (this.protocolOptimizer != null) {
			config.setOptimizer(this.protocolOptimizer);
		}
		if (this.protocolPayload != null) {
			config.setPayload(this.protocolPayload);
		}
		if (this.protocolPort != null) {
			config.setPort(this.protocolPort);
		}
		if (this.protocolPrompt != null) {
			config.setPrompt(this.protocolPrompt);
		}
		if (this.protocolQueues != null) {
			config.setQueues(this.protocolQueues);
		}
		if (this.protocolRegister != null) {
			config.setRegister(this.protocolRegister);
		}
		if (this.protocolSerialization != null) {
			config.setSerialization(this.protocolSerialization);
		}
		if (this.protocolServer != null) {
			config.setServer(this.protocolServer);
		}
		if (this.protocolStatus != null) {
			config.setStatus(this.protocolStatus);
		}
		if (this.protocolTelnet != null) {
			config.setTelnet(this.protocolTelnet);
		}
		if (this.protocolThreadpool != null) {
			config.setThreadpool(this.protocolThreadpool);
		}
		if (this.protocolThreads != null) {
			config.setThreads(this.protocolThreads);
		}
		return config;
	}
}
