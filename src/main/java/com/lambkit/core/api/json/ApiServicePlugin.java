package com.lambkit.core.api.json;

import com.jfinal.plugin.IPlugin;

public class ApiServicePlugin implements IPlugin {

	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		ApiServiceManager.me().onStart();
		return true;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return true;
	}

}
