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
package com.lambkit.module.upms;

import com.lambkit.Lambkit;
import com.lambkit.core.config.annotation.PropertieConfig;
import com.lambkit.core.rpc.RpcConfig;

@PropertieConfig(prefix="lambkit.upms")
public class UpmsConfig {

	private String type = "server";
	private String sessionId = "lambkit-upms-server-session-id";
	private int sessionTimeout = 1800000;
	private String ssoServerUrl = "http://127.0.0.1:8080";
	private String successUrl = "/upms/index";
	private String unauthorizedUrl = "/403";
	private int rememberMeTimeout = 2592000;
	private String appId="lambkit";
	private String ssoIndexUrl = "/sso/index";
	
    private String rpcVersion = "1.0";
	
	private String dbconfig;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getSessionTimeout() {
		return sessionTimeout;
	}
	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	public String getSsoServerUrl() {
		return ssoServerUrl;
	}
	public void setSsoServerUrl(String ssoServerUrl) {
		this.ssoServerUrl = ssoServerUrl;
	}
	public String getSuccessUrl() {
		return successUrl;
	}
	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}
	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}
	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}
	public int getRememberMeTimeout() {
		return rememberMeTimeout;
	}
	public void setRememberMeTimeout(int rememberMeTimeout) {
		this.rememberMeTimeout = rememberMeTimeout;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getDbconfig() {
		return dbconfig;
	}
	public void setDbconfig(String dbconfig) {
		this.dbconfig = dbconfig;
	}
	public String getSsoIndexUrl() {
		return ssoIndexUrl;
	}
	public void setSsoIndexUrl(String ssoIndexUrl) {
		this.ssoIndexUrl = ssoIndexUrl;
	}
	public int getRpcPort() {
		return Lambkit.config(RpcConfig.class).getDefaultPort();
	}
	public String getRpcGroup() {
		return Lambkit.config(RpcConfig.class).getDefaultGroup();
	}
	public String getRpcVersion() {
		return rpcVersion;
	}
	public void setRpcVersion(String rpcVersion) {
		this.rpcVersion = rpcVersion;
	}
}
