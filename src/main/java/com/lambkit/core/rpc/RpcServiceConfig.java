/**
 * Copyright (c) 2015-2020, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.core.rpc;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.lambkit.Lambkit;

public class RpcServiceConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int port = 0;
	private String group;
	private String version;
	private Integer timeout;
	private Integer retries;
	private Integer actives;
	private String loadbalance;
	private Boolean async;
	private Boolean check;
	private String proxy;
	private String filter;
	private Map<Object, Object> params;
	private static RpcConfig defaultConfig = (RpcConfig) Lambkit.config(RpcConfig.class);

	public RpcServiceConfig() {
		this.port = defaultConfig.getDefaultPort();
		this.group = defaultConfig.getDefaultGroup();
		this.version = defaultConfig.getDefaultVersion();
		this.timeout = Integer.valueOf(defaultConfig.getRequestTimeOut());
		this.retries = defaultConfig.getRetries();
		this.proxy = defaultConfig.getProxy();
		this.filter = defaultConfig.getFilter();
	}
	
	public RpcServiceConfig(String group, String version) {
		this.port = defaultConfig.getDefaultPort();
		this.group = group;
		this.version = version;
		this.timeout = Integer.valueOf(defaultConfig.getRequestTimeOut());
		this.retries = defaultConfig.getRetries();
		this.proxy = defaultConfig.getProxy();
		this.filter = defaultConfig.getFilter();
	}
	
	public RpcServiceConfig(String group, String version, int port) {
		this.port = port;
		this.group = group;
		this.version = version;
		this.timeout = Integer.valueOf(defaultConfig.getRequestTimeOut());
		this.retries = defaultConfig.getRetries();
		this.proxy = defaultConfig.getProxy();
		this.filter = defaultConfig.getFilter();
	}
	
	public RpcServiceConfig(RpcConfig rpcConfig) {
		this.port = rpcConfig.getDefaultPort();
		this.group = rpcConfig.getDefaultGroup();
		this.version = rpcConfig.getDefaultVersion();
		this.timeout = Integer.valueOf(rpcConfig.getRequestTimeOut());
		this.retries = rpcConfig.getRetries();
		this.proxy = rpcConfig.getProxy();
		this.filter = rpcConfig.getFilter();
	}
	

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Integer getTimeout() {
		return this.timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Integer getRetries() {
		return this.retries;
	}

	public void setRetries(Integer retries) {
		this.retries = retries;
	}

	public Integer getActives() {
		return this.actives;
	}

	public void setActives(Integer actives) {
		this.actives = actives;
	}

	public String getLoadbalance() {
		return this.loadbalance;
	}

	public void setLoadbalance(String loadbalance) {
		this.loadbalance = loadbalance;
	}

	public Boolean getAsync() {
		return this.async;
	}

	public void setAsync(Boolean async) {
		this.async = async;
	}

	public Boolean getCheck() {
		return this.check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	public Map<Object, Object> getParams() {
		return this.params;
	}

	public void setParams(Map<Object, Object> params) {
		this.params = params;
	}

	public void addParam(Object key, Object value) {
		if (this.params == null) {
			this.params = new HashMap();
		}
		this.params.put(key, value);
	}

	public String getProxy() {
		return this.proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
