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
package com.lambkit.module.wechat;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.lambkit.common.util.StringUtils;
import com.lambkit.core.config.annotation.PropertieConfig;

@PropertieConfig(prefix = "lambkit.wechat")
public class WechatConfig {

    private String debug = "false";
    private String appId;
    private String appSecret;
    private String token;
    private String partner;
    private String paternerKey;
    private String cert;
    private String domain;

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public boolean isDebug() {
        return "true".equalsIgnoreCase(debug);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getPaternerKey() {
        return paternerKey;
    }

    public void setPaternerKey(String paternerKey) {
        this.paternerKey = paternerKey;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public boolean isConfigOk() {
        return StringUtils.isNotBlank(appId)
                && StringUtils.isNotBlank(appSecret)
                && StringUtils.isNotBlank(token);
    }

    public ApiConfig getApiConfig() {

        if (!isConfigOk()) {
            return null;
        }

        ApiConfig config = new ApiConfig();
        config.setAppId(appId);
        config.setAppSecret(appSecret);
        config.setToken(token);
        return config;
    }

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
