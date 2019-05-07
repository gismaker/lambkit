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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.jfinal.kit.StrKit;
import com.lambkit.core.mq.MqType;
import com.lambkit.core.mq.Receiver;

import io.zbus.mq.ConsumeGroup;
import io.zbus.mq.Consumer;
import io.zbus.mq.ConsumerConfig;
import io.zbus.mq.Message;
import io.zbus.mq.MessageHandler;
import io.zbus.mq.MqClient;

public abstract class ZbusReceiver<T> implements MessageHandler, Receiver<T> {
	
	private final String mq;

	private Consumer consumer;
	/**
	 * 范型类型
	 */
	private final Class<?> tClass;
	
	/**
	 * 编码解码器
	 */
	private static final Coder coder = new JsonCoder();
	
	private final MqType mqType;
	
	/**
	 * <p>
	 * Title: TMsgHandler
	 * </p>
	 * <p>
	 * Description: 构造函数
	 * </p>
	 * 
	 * @since V1.0.0
	 */
	public ZbusReceiver(String mq, MqType mqType) {
		tClass = this.getSuperClassGenricType();
		this.mq = mq;
		this.mqType = mqType;
	}
	
	public ZbusReceiver(String mq) {
		tClass = this.getSuperClassGenricType();
		this.mq = mq;
		this.mqType = MqType.Queue;
	}
	
	public ZbusReceiver(String mq, String topicName) {
		tClass = this.getSuperClassGenricType();
		this.mq = mq;
		if(StrKit.notBlank(topicName)) {
			this.mqType = MqType.Topic;
		} else {
			this.mqType = MqType.Queue;
		}
	}
	
	public void ensureConsumer() {
		if(this.consumer == null){
    		synchronized (this) {
				if(this.consumer == null){
					ConsumerConfig config = ZbusMq.createConsumerConfig();
					config.setTopic(mq); 
					if(getType()==MqType.Topic) {
						ConsumeGroup consumeGroup = ConsumeGroup.createTempBroadcastGroup(); //Group will be dropped if disconnected
						config.setConsumeGroup(consumeGroup);
					}
					config.setMessageHandler(this);
					consumer = new Consumer(config);
				}
			} 
    	}
	}
	
	public Consumer getConsumer() {
		return consumer;
	}
	
	public String getTypeClassName(){
		return this.tClass.getName();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return mq;
	}
	
	@Override
	public MqType getType() {
		// TODO Auto-generated method stub
		return mqType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public final void handle(Message msg, MqClient client) throws IOException {
		Object obj;
		try {
			obj = coder.decode(tClass, msg);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		this.handle((T) obj);
	}
	
	/**
	 * @param <T>
	 * @Title: handle
	 * @Description: 消费者收到消息后的处理函数，子类需实现此方法
	 * @param msg
	 *            收到的消息
	 * @since V1.0.0
	 */
	public abstract void handle(T msg);
	
	private Class<?> getSuperClassGenricType() {
		Class<?> clazz = this.getClass();
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			throw new RuntimeException(clazz.getSimpleName() + "'s superclass not ParameterizedType");
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (!(params[0] instanceof Class)) {
			throw new RuntimeException(
					clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
		}
		return (Class<?>) params[0];
	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		if (null != consumer) {
			try {
				consumer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		consumer = null;
	}
}
