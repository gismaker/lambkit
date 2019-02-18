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
 * 泛型事件发送器
 * @ClassName: EventSender  
 * @author 李飞  
 * @since V1.0.0
 */
public class ZbusEventSender<T> extends ZbusSender<T>{
	
	public ZbusEventSender() {
		super("_mq_event_", MqType.Event);
	}
	
	@Override
	protected Message encode(T obj) {
		//设定topic为obj的类名
		return super.encode(obj).setTopic(obj.getClass().getName());
	}
}
