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
package com.lambkit.component.shiro;

import com.jfinal.kit.PathKit;
import com.lambkit.core.config.annotation.PropertieConfig;

import java.io.File;

@PropertieConfig(prefix="lambkit.component.shiro")
public class ShiroConfig {

	public static final String TYPE_SERVER = "server";
    public static final String TYPE_CLIENT = "client";
    
    private boolean enable = false;
    private String loginUrl = "/login";
    private String successUrl = "/";
    private String unauthorizedUrl = "/error/403";
    private String shiroIniFile = "shiro.ini";
    private String shiroType = TYPE_SERVER;


    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
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

    public String getShiroIniFile() {
        return shiroIniFile;
    }

    public void setShiroIniFile(String shiroIniFile) {
        this.shiroIniFile = shiroIniFile;
    }


    private Boolean config;

    public boolean isConfigOK() {
        if (config == null) {
            config = new File(PathKit.getRootClassPath(), shiroIniFile).exists();
        }
        return config;
    }

	public String getShiroType() {
		return shiroType;
	}

	public void setShiroType(String shiroType) {
		this.shiroType = shiroType;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}



