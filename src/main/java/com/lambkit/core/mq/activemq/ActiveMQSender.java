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

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.pool.PooledConnection;

import com.lambkit.core.mq.MqType;
import com.lambkit.core.mq.Sender;

public class ActiveMQSender<T extends Message> implements Sender<T> {

	private String name;
    private Session session;
    private MessageProducer producer;
    private MqType type;
    
    public ActiveMQSender(String name,
                     PooledConnection connection,
                     MqType type,
                     String subject) throws JMSException {
        this.name = name;
        this.type = type;
        // 事务性会话，自动确认消息
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 消息的目的地（Queue/Topic）
        if (type.equals(MqType.Topic)) {
            Topic destination = session.createTopic(subject);
            producer = session.createProducer(destination);
        } else {
            Queue destination = session.createQueue(subject);
            producer = session.createProducer(destination);
        }
        // 不持久化消息
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }
    
    @Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public MqType getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public Session getSession() {
        return session;
    }
	
	
    public void sendMessage(Message message) throws JMSException {
        producer.send(message);
    }
    
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		try {
			session.close();
			producer.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void publish(T message, boolean bSync) throws IOException, InterruptedException {
		try {
			sendMessage(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	

}
