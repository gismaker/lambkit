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

import io.zbus.mq.Broker;

/**
 * Mq泛型发送器(通过MQ发送消息)
 * @ClassName: MqSender  
 */
public class ZbusMqSender<T> extends ZbusSender<T> {
	
	/**
	 * <p>
	 * Title: Sender
	 * </p>
	 * <p>
	 * Description: 构建一个MQ发送器
	 * </p>
	 * 
	 * @param mq
	 *            MQ队列名
	 * @since V1.0.0
	 */
	public ZbusMqSender(String mq) {
		super(mq, MqType.Queue);
	}
	
	public ZbusMqSender(String mq, Broker broker) throws IOException, InterruptedException {
		super(mq, MqType.Queue);
		createProducer(broker);
	}
}
