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

import com.lambkit.core.mq.MqType;

import io.zbus.mq.Message;

/**
 * Topic泛型发送器（通过Topic发送）
 * @ClassName: TopicSender
 */
public class ZbusTopicSender<T> extends ZbusSender<T>{
	
	/**
	 * 主题
	 */
	private final String topic;

	/**
	 * 
	 * <p>
	 * Title: TopicSender
	 * </p>
	 * <p>
	 * Description: 构建一个Topic发送器
	 * </p>
	 * 
	 * @param mq
	 *            MQ队列名
	 * @param topic
	 *            主题名
	 * @since V1.0.0
	 */
	public ZbusTopicSender(String mq, String topic) {
		super(mq, MqType.Topic);
		this.topic = topic;
	}
	
	@Override
	protected Message encode(T obj) {
		//设定topic
		return super.encode(obj).setTopic(this.topic);
	}
}
