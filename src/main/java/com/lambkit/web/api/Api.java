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
package com.lambkit.web.api;

import java.io.Serializable;

public class Api implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4024033985346119293L;

	private String nodeId;
	
	private String url;
	
	private String method;
	
	/**
	 * 平均消耗时间
	 */
	private long runtime = 0;
	private long runNums = 0;
	/**
	 * 节点状态, 0-初始状态，1-活跃状态，2-停止状态
	 */
	private int status = 0;
	
	public Api() {
		// TODO Auto-generated constructor stub
	}
	
	public Api(String nodeid, String url, String mothod, long runtime) {
		// TODO Auto-generated constructor stub
		this.nodeId = nodeid;
		this.url = url;
		this.method = mothod;
		this.runtime = runtime;
	}
	
	public void update(Api api) {
		this.nodeId = api.getNodeId();
		this.url = api.getUrl();
		this.method = api.getMethod();
		this.runtime = api.getRuntime();
		this.status = api.getStatus();
	}
	
	/**
	 * 激活
	 */
	public void active() {
		this.status = 1;
	}
	
	public void enable() {
		this.status = 1;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getRuntime() {
		return runtime;
	}

	public void setRuntime(long runtime) {
		this.runtime = (this.runtime*this.runNums + runtime)/(this.runNums+1);
		this.runNums++;
	}

	public long getRunNums() {
		return runNums;
	}

	public void setRunNums(long runNums) {
		this.runNums = runNums;
	}
}
