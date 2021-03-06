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
package com.lambkit.core.rpc;

import com.jfinal.plugin.IPlugin;
import com.lambkit.Lambkit;
import com.lambkit.common.LambkitManager;
import com.lambkit.common.bean.RpcBean;

public abstract class RpcPlugin implements IPlugin, Rpc {

	private RpcConfig config = Lambkit.config(RpcConfig.class);
	
	@Override
	public <T> boolean serviceExport(Class<T> interfaceClass, Object object, RpcServiceConfig serviceConfig) {
		// TODO Auto-generated method stub
		LambkitManager.me().addRpc(new RpcBean(interfaceClass.getName(), serviceConfig.getGroup(), serviceConfig.getVersion(), serviceConfig.getPort()));
		return false;
	}

	public RpcConfig getConfig() {
		return config;
	}

	public void setConfig(RpcConfig config) {
		this.config = config;
	}

}
