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
package com.lambkit.core.mq.zubs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.log.Log;
import com.lambkit.component.zbus.ZbusConfig;
import com.lambkit.core.config.ConfigManager;
import com.lambkit.core.mq.MqPlugin;
import com.lambkit.core.mq.Receiver;
import com.lambkit.core.mq.Sender;

import io.zbus.mq.Broker;
import io.zbus.mq.Consumer;
import io.zbus.mq.ConsumerConfig;
import io.zbus.mq.Producer;

public class ZbusMq extends MqPlugin {
	/**
	 * 日志
	 */
	private static final Log LOG = Log.getLog("ZbusPlugin");

	/**
	 * MQ消费者配置Map
	 */
	private final Map<String, ZbusReceiver<?>> receiverMap = new HashMap<String, ZbusReceiver<?>>();

	/**
	 * 发送器列表
	 */
	private final static Map<String, Sender<?>> senderMap = new HashMap<String, Sender<?>>();

	/**
	 * broker对象
	 */
	private static Broker broker = null;

	/**
	 * 默认构造函数
	 */
	public ZbusMq() {
		// 创建broker
		ensureBroker();
	}

	public ZbusMq(String brokerAddress) {
		ZbusConfig config = ConfigManager.me().get(ZbusConfig.class);
		config.setAddress(brokerAddress);
		// 创建broker
		ensureBroker();
	}

	/**
	 * @Title: ensureBroker
	 * @Description: 确保broker可用
	 * @throws Exception
	 * @since V1.0.0
	 */
	private void ensureBroker() {
		if (broker == null) {
			synchronized (this) {
				if (broker == null) {
					ZbusConfig config = ConfigManager.me().get(ZbusConfig.class);
					broker = new Broker(config.getAddress());
					//LOG.info
					System.out.println("创建broker成功(brokerAddress=" + config.getAddress() + ")");
				}
			}
		}
	}
	
	private void startConsumer() throws IOException {
		// 创建Mq消费者
		for (Entry<String, ZbusReceiver<?>> entry : this.receiverMap.entrySet()) {
			String mq = entry.getKey();
			ZbusReceiver<?> receiver = entry.getValue();
			if(receiver!=null) {
				receiver.ensureConsumer();
				LOG.info("创建MQ消费者成功(mq=" + mq + ")");
			}
		}
		for (Entry<String, ZbusReceiver<?>> entry : this.receiverMap.entrySet()) {
			ZbusReceiver<?> receiver = entry.getValue();
			if(receiver!=null) {
				receiver.getConsumer().start();
			}
		}
	}

	public boolean start() {
		try {
			// 确保创建
			ensureBroker();
			startConsumer();
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public boolean stop() {
		try {
			// 关闭消费者
			removeAllReceiver();
			// 关闭所有发送器
			removeAllSender();
			// 关闭broker
			if (broker != null) {
				broker.close();
				broker = null;
			}
			return true;
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * @Title: createProducer
	 * @Description: 创建一个Producer
	 * @return Producer
	 * @throws InterruptedException
	 * @throws IOException
	 * @since V1.0.0
	 */
	public static Producer createProducer(Sender<?> sender, String mq)
			throws IOException, InterruptedException {
		Producer producer = new Producer(broker);
		producer.declareTopic(mq);
		return producer;
	}
	
	public static ConsumerConfig createConsumerConfig() {
		return new ConsumerConfig(broker);
	}

	/**
	 * @Title: createProducer
	 * @Description: 创建一个Producer
	 * @return Producer
	 * @throws InterruptedException
	 * @throws IOException
	 * @since V1.0.0
	 */
	@Override
	public void addSender(Sender<?> sender) {
		// TODO Auto-generated method stub
		if (sender == null)
			return;
		senderMap.put(sender.getName(), sender);
	}

	/**
	 * @Title: addReceiver
	 * @Description: 注册Mq的消息回调接口
	 * @param msgHandler
	 *            消息到达回调接口
	 */
	@Override
	public void addReceiver(Receiver<?> receiver) {
		// TODO Auto-generated method stub
		if (receiver == null)
			return;
		if (receiver instanceof ZbusReceiver) {
			ZbusReceiver<?> msgHandler = (ZbusReceiver<?>) receiver;
			String mq = msgHandler.getName();
			if (receiverMap.containsKey(mq)) {
				LOG.warn("(mq=" + mq + ")对应的消息处理器已存在!");
			}
			receiverMap.put(mq, msgHandler);
		}
	}

	@Override
	public Sender<?> getSender(String name) {
		// TODO Auto-generated method stub
		return senderMap.get(name);
	}

	@Override
	public Receiver<?> getReceiver(String name) {
		// TODO Auto-generated method stub
		return receiverMap.get(name);
	}

	@Override
	public void removeSender(String name) {
		// TODO Auto-generated method stub
		Sender<?> sender = senderMap.get(name);
		if (null != sender) {
			try {
				sender.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sender = null;
		senderMap.remove(name);
	}

	@Override
	public void removeAllSender() {
		// TODO Auto-generated method stub
		try {
			for (Entry<String, Sender<?>> sender : senderMap.entrySet()) {
				sender.getValue().close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		senderMap.clear();
	}

	@Override
	public void removeReceiver(String name) {
		// TODO Auto-generated method stub
		Consumer c = receiverMap.get(name).getConsumer();
		if (null != c) {
			try {
				c.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		c = null;
		receiverMap.remove(name);
	}

	@Override
	public void removeAllReceiver() {
		// TODO Auto-generated method stub
		try {
			for (Entry<String, ZbusReceiver<?>> c : receiverMap.entrySet()) {
				if(c.getValue()!=null && c.getValue().getConsumer()!=null) {
					c.getValue().getConsumer().close();
				}
				c = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		receiverMap.clear();
	}
}
