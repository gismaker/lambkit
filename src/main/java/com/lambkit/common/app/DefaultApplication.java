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
package com.lambkit.common.app;

import com.jfinal.config.Plugins;
import com.jfinal.plugin.IPlugin;
import com.lambkit.Lambkit;
import com.lambkit.common.util.TimeUtils;
import com.lambkit.module.LambkitModule;

/**
 * JFinal数据库等部分插件脱离web独立使用
 * @author yangyong
 */
public class DefaultApplication extends LambkitApplication {

	Plugins plugins;
	
	public DefaultApplication() {
		// TODO Auto-generated constructor stub
		Lambkit.me().setModule(new DefaultApplicationModule());
		plugins = new Plugins();
	}
	
	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		if(plugins==null) {
			plugins = new Plugins();
		}
		TimeUtils.startTime("start lambkit app");
		LambkitModule module = Lambkit.me().getModule();
		module.configPlugin(plugins);
		for(IPlugin plugin : plugins.getPluginList()) {
			plugin.start();
		}
		return true;
	}

	@Override
	public boolean startAfter() {
		// TODO Auto-generated method stub
		LambkitModule module = Lambkit.me().getModule();
		module.afterJFinalStart();
		TimeUtils.endTime("start lambkit app");
		return true;
	}

	@Override
	public boolean restart() {
		// TODO Auto-generated method stub
		stop();
		start();
		startAfter();
		return false;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		LambkitModule module = Lambkit.me().getModule();
		for(IPlugin plugin : plugins.getPluginList()) {
			plugin.stop();
		}
		plugins.getPluginList().clear();
		plugins = null;
		module.beforeJFinalStop();
		return true;
	}

}
