package com.lambkit.test.mq.zbus;

import com.lambkit.core.mq.MqManager;
import com.lambkit.core.mq.MqPlugin;
import com.lambkit.core.mq.zubs.ZbusReceiver;

public class TestMq {

	public static void main(String[] args) {
		MqPlugin mq = MqManager.me().getPlugin();
		ZbusReceiver<String> zr = new ZbusReceiver<String>("MyTopic") {
			@Override
			public void handle(String msg) {
				// TODO Auto-generated method stub
				System.out.println("[zbus receiver message]" + msg);
			}
		};
		mq.addReceiver(zr);
		mq.start();
	}
}
