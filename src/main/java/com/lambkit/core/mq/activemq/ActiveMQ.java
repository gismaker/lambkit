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
package com.lambkit.core.mq.activemq;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;

import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.lambkit.Lambkit;
import com.lambkit.core.mq.MqPlugin;
import com.lambkit.core.mq.Receiver;
import com.lambkit.core.mq.Sender;

/**
 * ActiveMQPlugin p = new ActiveMQPlugin("failover://(tcp://127.0.0.1:61616)?initialReconnectDelay=1000");
 * p.start();
 * @author yangyong
 */
public class ActiveMQ extends MqPlugin {

	private static final Log LOG = Log.getLog(Thread.currentThread().getClass());

	private static final ConcurrentHashMap<String, PooledConnection> pooledConnectionMap = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, ActiveMQSender<?>> senderMap = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, ActiveMQReceiver<?>> receiverMap = new ConcurrentHashMap<>();
	private static final String defaultName = "main";

	private String url;
	private String name;

	public ActiveMQ() {
		ActiveMQConfig config = Lambkit.config(ActiveMQConfig.class);
		if(StrKit.notBlank(config.getName())) {
			this.name = config.getName();
		} else {
			this.name = defaultName;
		}
		if(StrKit.notBlank(config.getUrl())) {
			this.url = config.getUrl();
		}
	}
	
	public ActiveMQ(String url, String name) {
		this.url = url;
		this.name = name;
	}

	public ActiveMQ(String url) {
		this.url = url;
		this.name = defaultName;
	}

	@Override
	public boolean start() {
		if(StrKit.isBlank(url)) {
			return false;
		}
		LOG.info("初始化activeMQ配置");
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setUserName(ActiveMQConnection.DEFAULT_USER);
		activeMQConnectionFactory.setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
		activeMQConnectionFactory.setBrokerURL(url);
		activeMQConnectionFactory.setDispatchAsync(true);// 异步发送消息
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);
		pooledConnectionFactory.setMaximumActiveSessionPerConnection(200);
		pooledConnectionFactory.setIdleTimeout(120);
		pooledConnectionFactory.setMaxConnections(5);
		pooledConnectionFactory.setBlockIfSessionPoolIsFull(true);
		try {
			PooledConnection connection = (PooledConnection) pooledConnectionFactory.createConnection();
			connection.start();
			pooledConnectionMap.put(name, connection);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return true;
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
		if (sender instanceof ActiveMQSender) {
			senderMap.put(sender.getName(), (ActiveMQSender<?>) sender);
		}
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
		if (receiver instanceof ActiveMQReceiver) {
			ActiveMQReceiver<?> msgHandler = (ActiveMQReceiver<?>) receiver;
			String mq = msgHandler.getName();
			if (receiverMap.containsKey(mq)) {
				LOG.warn("(mq=" + mq + ")对应的消息处理器已存在!");
			}
			receiverMap.put(mq, msgHandler);
		}
	}

	@Override
	public ActiveMQSender<?> getSender(String name) {
		// TODO Auto-generated method stub
		return senderMap.get(name);
	}

	@Override
	public ActiveMQReceiver<?> getReceiver(String name) {
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
			for (Entry<String, ActiveMQSender<?>> sender : senderMap.entrySet()) {
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
		Receiver<?> receiver = receiverMap.get(name);
		if (null != receiver) {
			try {
				receiver.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		receiver = null;
		receiverMap.remove(name);
	}

	@Override
	public void removeAllReceiver() {
		// TODO Auto-generated method stub
		try {
			for (Entry<String, ActiveMQReceiver<?>> c : receiverMap.entrySet()) {
				Receiver<?> receiver = c.getValue();
				if (null != receiver) {
					receiver.close();
				}
				receiver = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		receiverMap.clear();
	}

	public void addConnection(String connectionName, PooledConnection connection) {
		pooledConnectionMap.put(connectionName, connection);
	}

	public PooledConnection getConnection() {
		return pooledConnectionMap.get(defaultName);
	}

	public PooledConnection getConnection(String connectionName) {
		return pooledConnectionMap.get(connectionName);
	}
}
