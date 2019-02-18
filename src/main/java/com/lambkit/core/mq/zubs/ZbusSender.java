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

import com.lambkit.core.mq.MqType;
import com.lambkit.core.mq.Sender;

import io.zbus.mq.Broker;
import io.zbus.mq.Message;
import io.zbus.mq.Producer;

/**  
 * 发送器抽象基类
 * @ClassName: AbstractSender  
 * @author 李飞 
 * @date 2015年9月5日 下午5:27:58
 * @since V1.0.0  
 */
public abstract class ZbusSender<T> implements Sender<T>{
	
	/**
	 * 生产者
	 */
	private Producer producer;
	
	/**
	 * mq名称
	 */
	private final String mq;
	
	/**
	 * mq类型（queue/topic/event）
	 */
	private final MqType mqType;
	
	/**
	 * 编码解码器
	 */
	private static final Coder coder = new JsonCoder();
	
	public ZbusSender(String mq) {
		this.mq = mq;
		this.mqType = MqType.Queue;
	}
	
	/**
	 * <p>Title: AbstractSender</p>  
	 * <p>Description: 默认构造函数</p>  
	 * @param mq MQ队列名
	 * @param mqMode MQ队列类型
	 * @since V1.0.0
	 */
	public ZbusSender(String mq, MqType mqType){
		this.mq = mq;
		this.mqType = mqType;
	}
	
	/**
	 * @Title: ensureProducer  
	 * @Description: 确保生产者使用前被创建  
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @since V1.0.0
	 */
	private void ensureProducer() throws IOException, InterruptedException{
		if(this.producer == null){
    		synchronized (this) {
				if(this.producer == null){
					//创建生产者
					producer = ZbusMq.createProducer(this, mq);
				}
			} 
    	}
	}
	
	protected void createProducer(Broker broker) {
		producer = new Producer(broker);
		try {
			producer.declareTopic(mq);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() throws IOException {
		//将producer重新设定为null，重新获取producer对象
		producer = null;
	}
	
	@Override
	public void publish(T message, boolean bSync) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		if(null != message){
			ensureProducer();
			if(bSync){
				producer.publish(encode(message));
			} else {
				producer.publishAsync(encode(message), null);
			}
		}
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
	/**
	 * @Title: encode  
	 * @Description: 默认编码，子类可重载  
	 * @param obj
	 * @return 
	 * @since V1.0.0
	 */
	protected Message encode(T obj){
		Message msg = coder.encode(obj);
		msg.setTopic(mq);
		return msg;
	}
}
