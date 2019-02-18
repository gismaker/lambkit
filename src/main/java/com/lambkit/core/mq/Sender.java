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
package com.lambkit.core.mq;

import java.io.Closeable;
import java.io.IOException;

/**
 * 发送者
 * 泛型发送器接口，统一（MQ，Topic，Event方式）
 * @author 孤竹行
 */
public interface Sender<T> extends Closeable {
	/**
	 * @Title: publish
	 * @Description: 发送对象到MQ／topic（同步方式/异步方式）
	 * @param message 发送对象
	 * @param bSync 同步方式/异步方式
	 * @throws IOException
	 * @throws InterruptedException 
	 * @since V1.0.0
	 */
	public void publish(T message, boolean bSync) throws IOException, InterruptedException;
	
	public String getName();
	
	public MqType getType();
	
}
