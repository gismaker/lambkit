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
 */package com.lambkit.plugin.nim;

import com.lambkit.core.config.annotation.PropertieConfig;

@PropertieConfig(prefix = "lambkit.netease.im")
public class NimConfig {
	/**
	 * 开发者平台分配的appkey
	 */
	private String appKey = "94kid09c9ig9k1loimjg012345123456";
	/**
	 * 开发者的密码
	 */
	private String appSecret = "123456789012";
	
	/**
	 * 短信模板编号(由客户顾问配置之后告知开发者)
	 */
	private String messsagTemplateId;
	
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getMesssagTemplateId() {
		return messsagTemplateId;
	}
	public void setMesssagTemplateId(String messsagTemplateId) {
		this.messsagTemplateId = messsagTemplateId;
	}
}
