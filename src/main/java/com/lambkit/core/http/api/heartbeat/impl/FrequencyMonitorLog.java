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
package com.lambkit.core.http.api.heartbeat.impl;

import java.io.Serializable;

import com.lambkit.core.http.api.ApiModel;

public class FrequencyMonitorLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5934045711503861246L;
	/**
	 * 所属实例
	 */
	private ApiModel api;

	/**
	 * 是否正常
	 */
	private boolean normal;

	/**
	 * 消耗时间 ms
	 */
	private long costTime;

	/**
	 * 响应返回的数据长度: byte
	 */
	private long responseSize;

	/**
	 * 备注信息
	 */
	private String remark;

	public ApiModel getApi() {
		return api;
	}

	public void setApi(ApiModel api) {
		this.api = api;
	}

	public boolean isNormal() {
		return normal;
	}

	public void setNormal(boolean normal) {
		this.normal = normal;
	}

	public long getCostTime() {
		return costTime;
	}

	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}

	public long getResponseSize() {
		return responseSize;
	}

	public void setResponseSize(long responseSize) {
		this.responseSize = responseSize;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
