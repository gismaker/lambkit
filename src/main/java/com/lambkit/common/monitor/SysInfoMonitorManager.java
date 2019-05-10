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
package com.lambkit.common.monitor;

import org.apache.shiro.util.ClassUtils;

import com.jfinal.kit.PathKit;
import com.lambkit.common.util.DateTimeUtils;

public class SysInfoMonitorManager {

	private SysInfoAcquirerService service;
	
	private ServerSystemInfo serverInfo;

	private static SysInfoMonitorManager manager = null;
	
	public static SysInfoMonitorManager me() {
		if(manager==null) {
			manager = (SysInfoMonitorManager) ClassUtils.newInstance(SysInfoMonitorManager.class);
		}
		return manager;
	}
	
	public boolean start() {
		// TODO Auto-generated method stub
		service = new SysInfoAcquirerService();
		service.init();
		return true;
	}

	public boolean stop() {
		// TODO Auto-generated method stub
		service.destroy();
		service = null;
		return true;
	}
	
	public SysInfoAcquirerService getService() {
		return service;
	}

	public void setService(SysInfoAcquirerService service) {
		this.service = service;
	}
	
	public ServerSystemInfo getServerInfo() {
		if(serverInfo==null) {
			serverInfo = new ServerSystemInfo();
			serverInfo.setDir(PathKit.getWebRootPath());
			String osName = System.getProperty("os.name");
			serverInfo.setOsName(osName);
			serverInfo.setJavaVersion(System.getProperty("java.version"));
		}
		serverInfo.setCurrentTime(DateTimeUtils.longToString(DateTimeUtils.getCurrentTimeLong()));
		return serverInfo;
	}

	public void setServerInfo(ServerSystemInfo serverInfo) {
		this.serverInfo = serverInfo;
	}
}
