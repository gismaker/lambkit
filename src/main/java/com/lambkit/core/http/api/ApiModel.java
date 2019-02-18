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
package com.lambkit.core.http.api;

import java.io.Serializable;

import com.lambkit.core.hearbeat.HeartBeatFrequency;

public class ApiModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 947245611949726175L;


	//实例名称
    private String apiName;


    private ApiURL apiURL = new ApiURL();

    /**
     * 连接时超时的时间
     * 0,表示无超时
     */
    private int maxConnectionSeconds;

    //enabled or disabled
    //是否启用
    private boolean enabled;

    /**
     * 心跳检测频率, 默认30秒
     */
    private HeartBeatFrequency frequency = HeartBeatFrequency.THIRTY;

    /**
     * 若出现测试正常或不正常时提醒的邮件地址
     * 若有多个请用英文分号(;)分隔
     */
    private String email;


    /**
     * Schedule中的任务名称,
     * 当启用该监听任务时, 将会有唯一对应的jobName
     */
    private String jobName;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * The instance belong to the system (id)
     */
    private String systemId;

    /**
     * 是否为私有应用, 私有应用只有自己登录后才能查看,
     * 别人不能看见, 默认为false,公开
     * <p/>
     * From Version 0.5
     */
    private boolean privateInstance;


    /**
     * 当连续连接失败 指定的次数后才发送提醒.
     * 这用于处理有时连接在检测链接状态的时候，不要发现一次链接故障的时候就马上发邮件通知，
     * 这个过程可能因为网络故障（如常见的：丢包、dns故障）而出现问题
     * <p/>
     * 默认为连续2次
     */
    private int continueFailedTimes = 2;

    public UrlRequestMethod requestMethod() {
    	return apiURL.getRequestMethod();
    }
    
	public String monitorUrl() {
		return apiURL.getUrl();
	}

	public ApiURL getApiURL() {
		return apiURL;
	}


	public void setApiURL(ApiURL apiURL) {
		this.apiURL = apiURL;
	}


	public int getMaxConnectionSeconds() {
		return maxConnectionSeconds;
	}


	public void setMaxConnectionSeconds(int maxConnectionSeconds) {
		this.maxConnectionSeconds = maxConnectionSeconds;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public HeartBeatFrequency getFrequency() {
		return frequency;
	}


	public void setFrequency(HeartBeatFrequency frequency) {
		this.frequency = frequency;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getSystemId() {
		return systemId;
	}


	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}


	public boolean isPrivateInstance() {
		return privateInstance;
	}


	public void setPrivateInstance(boolean privateInstance) {
		this.privateInstance = privateInstance;
	}


	public int getContinueFailedTimes() {
		return continueFailedTimes;
	}


	public void setContinueFailedTimes(int continueFailedTimes) {
		this.continueFailedTimes = continueFailedTimes;
	}


	public String getApiName() {
		return apiName;
	}


	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	

    
}
