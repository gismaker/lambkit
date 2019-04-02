package com.lambkit.plugin.nim;

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
