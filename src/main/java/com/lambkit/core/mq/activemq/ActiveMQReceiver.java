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

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.pool.PooledConnection;

import com.lambkit.core.mq.MqType;
import com.lambkit.core.mq.Receiver;

public abstract class ActiveMQReceiver<T extends Message> implements Receiver<T>, MessageListener {

	private String name;
    private Session session;
    private MessageConsumer consumer;
    private MqType type;
    
    public ActiveMQReceiver(String name,
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
            consumer = session.createConsumer(destination);
        } else {
            Queue destination = session.createQueue(subject);
            consumer = session.createConsumer(destination);
        }
        consumer.setMessageListener(this);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
	public MqType getType() {
		// TODO Auto-generated method stub
		return type;
	}
    
    @Override
	public abstract void handle(T message);

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		handle((T) message);
		/*
		try {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                System.out.println(msg.getText());
            } else if (message instanceof MapMessage) {
                MapMessage msg = (MapMessage) message;
                Enumeration enumer = msg.getMapNames();
                while (enumer.hasMoreElements()) {
                    Object obj = enumer.nextElement();
                    System.out.println(msg.getObject(obj.toString()));
                }
            } else if (message instanceof StreamMessage) {
                StreamMessage msg = (StreamMessage) message;
                System.out.println(msg.readString());
                System.out.println(msg.readBoolean());
                System.out.println(msg.readLong());
            } else if (message instanceof ObjectMessage) {
                ObjectMessage msg = (ObjectMessage) message;
                System.out.println(msg);
            } else if (message instanceof BytesMessage) {
                BytesMessage msg = (BytesMessage) message;
                byte[] byteContent = new byte[1024];
                int length = -1;
                StringBuffer content = new StringBuffer();
                while ((length = msg.readBytes(byteContent)) != -1) {
                    content.append(new String(byteContent, 0, length));
                }
                System.out.println(content.toString());
            } else {
                System.out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		try {
			session.close();
			consumer.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
