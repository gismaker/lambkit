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
package com.lambkit.module.upms.rpc.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.lambkit.common.model.BaseModel;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-12-26
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class BaseUpmsLog<M extends BaseUpmsLog<M>> extends BaseModel<M> implements IBean {

	public String tableName() {
		return "upms_log";
	}
    
	public java.lang.Integer getLogId() {
		return this.get("log_id");
	}

	public void setLogId(java.lang.Integer logId) {
		this.set("log_id", logId);
	}
	public java.lang.String getDescription() {
		return this.get("description");
	}

	public void setDescription(java.lang.String description) {
		this.set("description", description);
	}
	public java.lang.String getUsername() {
		return this.get("username");
	}

	public void setUsername(java.lang.String username) {
		this.set("username", username);
	}
	public java.lang.Long getStartTime() {
		return this.get("start_time");
	}

	public void setStartTime(java.lang.Long startTime) {
		this.set("start_time", startTime);
	}
	public java.lang.Integer getSpendTime() {
		return this.get("spend_time");
	}

	public void setSpendTime(java.lang.Integer spendTime) {
		this.set("spend_time", spendTime);
	}
	public java.lang.String getBasePath() {
		return this.get("base_path");
	}

	public void setBasePath(java.lang.String basePath) {
		this.set("base_path", basePath);
	}
	public java.lang.String getUri() {
		return this.get("uri");
	}

	public void setUri(java.lang.String uri) {
		this.set("uri", uri);
	}
	public java.lang.String getUrl() {
		return this.get("url");
	}

	public void setUrl(java.lang.String url) {
		this.set("url", url);
	}
	public java.lang.String getMethod() {
		return this.get("method");
	}

	public void setMethod(java.lang.String method) {
		this.set("method", method);
	}
	public java.lang.String getParameter() {
		return this.get("parameter");
	}

	public void setParameter(java.lang.String parameter) {
		this.set("parameter", parameter);
	}
	public java.lang.String getUserAgent() {
		return this.get("user_agent");
	}

	public void setUserAgent(java.lang.String userAgent) {
		this.set("user_agent", userAgent);
	}
	public java.lang.String getIp() {
		return this.get("ip");
	}

	public void setIp(java.lang.String ip) {
		this.set("ip", ip);
	}
	public java.lang.String getResult() {
		return this.get("result");
	}

	public void setResult(java.lang.String result) {
		this.set("result", result);
	}
	public java.lang.String getPermissions() {
		return this.get("permissions");
	}

	public void setPermissions(java.lang.String permissions) {
		this.set("permissions", permissions);
	}
}
