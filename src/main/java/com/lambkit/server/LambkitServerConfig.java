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
package com.lambkit.server;

import com.lambkit.core.config.annotation.PropertieConfig;

@PropertieConfig(prefix = "lambkit.server")
public class LambkitServerConfig {

    public static final String TYPE_JETTY = "jetty";
    public static final String TYPE_APP = "applicaiton";
    
    private String type = TYPE_JETTY;
    private String host = "0.0.0.0";
    private int port = 8080;
    private String contextPath = "/";
    private int scanIntervalSeconds = 0;
    private String webAppDir = "webRoot";
    
    private boolean isWebXml = false;
    private boolean isShiro = false;

    //websocket 的相关配置
    //具体使用请参考：https://github.com/undertow-io/undertow/tree/master/examples/src/main/java/io/undertow/examples/jsrwebsockets
    private boolean websocketEnable = false;
    private int websocketBufferPoolSize = 100;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public boolean isWebsocketEnable() {
        return websocketEnable;
    }

    public void setWebsocketEnable(boolean websocketEnable) {
        this.websocketEnable = websocketEnable;
    }

    public int getWebsocketBufferPoolSize() {
        return websocketBufferPoolSize;
    }

    public void setWebsocketBufferPoolSize(int websocketBufferPoolSize) {
        this.websocketBufferPoolSize = websocketBufferPoolSize;
    }


    @Override
    public String toString() {
        return "LambkitServerConfig {" +
                "type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", contextPath='" + contextPath + '\'' +
                ", webAppDir='" + webAppDir + '\'' +
                ", scanIntervalSeconds=" + scanIntervalSeconds +
                ", websocketEnable=" + websocketEnable +
                ", websocketBufferPoolSize=" + websocketBufferPoolSize +
                '}';
    }

	public int getScanIntervalSeconds() {
		return scanIntervalSeconds;
	}

	public void setScanIntervalSeconds(int scanIntervalSeconds) {
		this.scanIntervalSeconds = scanIntervalSeconds;
	}

	public String getWebAppDir() {
		return webAppDir;
	}

	public void setWebAppDir(String webAppDir) {
		this.webAppDir = webAppDir;
	}

	public boolean isWebXml() {
		return isWebXml;
	}

	public void setWebXml(boolean isWebXml) {
		this.isWebXml = isWebXml;
	}

	public boolean isShiro() {
		return isShiro;
	}

	public void setShiro(boolean isShiro) {
		this.isShiro = isShiro;
	}
}
