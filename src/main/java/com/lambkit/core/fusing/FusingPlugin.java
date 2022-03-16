package com.lambkit.core.fusing;

import com.jfinal.plugin.IPlugin;

public class FusingPlugin implements IPlugin {
	protected static int maximum;
	protected static long millis;
	protected static String renderJson;//返回的Json串
	QueuePluginThread queuePluginThread;
	/**
	 * @param maximum 时间内最大请求数量
	 * @param millis 时间（毫秒）
	 */
	public FusingPlugin (int maximum,long millis,String renderJson) {
		FusingPlugin.maximum = maximum;
		FusingPlugin.millis = millis;
		FusingPlugin.renderJson = renderJson;
		queuePluginThread = new QueuePluginThread(maximum,millis);
	}

	@Override
	public boolean start() {
		new Thread(queuePluginThread,"熔断定时线程").start();
		return true;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}
}  