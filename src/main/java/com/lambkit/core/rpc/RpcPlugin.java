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
import com.lambkit.system.SystemManager;
import com.lambkit.system.info.RpcInfo;

public abstract class RpcPlugin implements IPlugin, Rpc {

	@Override
	public <T> boolean serviceExport(Class<T> interfaceClass, Object object, String group, String version, int port) {
		// TODO Auto-generated method stub
		SystemManager.me().addRpc(new RpcInfo(interfaceClass.getName(), group, version, port));
		return false;
	}


}
