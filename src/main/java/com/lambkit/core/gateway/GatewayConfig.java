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
package com.lambkit.core.gateway;

import java.util.List;

import com.jfinal.kit.StrKit;

//@PropertieConfig(prefix = "lambkit.gateway")
public class GatewayConfig {
	public static final String NAME_DEFAULT = "main";
	
	private String name;
	private String urlpattern;
	
	private boolean log = false;
	private boolean forwardip = true;
	/** User agents shouldn't send the url fragment but what if it does? */
	private boolean sendUrlFragment = true;
	private boolean preserveHost = false;
	private boolean preserveCookies = false;
	private boolean handleRedirects = false;
	private int connectTimeout = -1;
	private int readTimeout = -1;

	// These next 3 are cached here, and should only be referred to in
	// initialization logic. See the
	// ATTR_* parameters.
	/** From the configured parameter "targetUri". */
	private String targetUri;
	
	private List<GatewayConfig> childProxyConfigs;
	

	public boolean isConfigOk() {
        return (StrKit.notBlank(targetUri) && StrKit.notBlank(name));
    }
	
	public boolean isLog() {
		return log;
	}

	public void setLog(boolean log) {
		this.log = log;
	}

	public boolean isForwardip() {
		return forwardip;
	}

	public void setForwardip(boolean forwardip) {
		this.forwardip = forwardip;
	}

	public boolean isSendUrlFragment() {
		return sendUrlFragment;
	}

	public void setSendUrlFragment(boolean sendUrlFragment) {
		this.sendUrlFragment = sendUrlFragment;
	}

	public boolean isPreserveHost() {
		return preserveHost;
	}

	public void setPreserveHost(boolean preserveHost) {
		this.preserveHost = preserveHost;
	}

	public boolean isPreserveCookies() {
		return preserveCookies;
	}

	public void setPreserveCookies(boolean preserveCookies) {
		this.preserveCookies = preserveCookies;
	}

	public boolean isHandleRedirects() {
		return handleRedirects;
	}

	public void setHandleRedirects(boolean handleRedirects) {
		this.handleRedirects = handleRedirects;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public String getTargetUri() {
		return targetUri;
	}

	public void setTargetUri(String targetUri) {
		this.targetUri = targetUri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GatewayConfig> getChildProxyConfigs() {
		return childProxyConfigs;
	}

	public void setChildProxyConfigs(List<GatewayConfig> childProxyConfigs) {
		this.childProxyConfigs = childProxyConfigs;
	}

	public String getUrlpattern() {
		return urlpattern;
	}

	public void setUrlpattern(String urlpattern) {
		this.urlpattern = urlpattern;
	}
}
