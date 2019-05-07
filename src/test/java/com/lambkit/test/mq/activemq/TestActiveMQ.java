package com.lambkit.test.mq.activemq;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.lambkit.core.mq.MqType;
import com.lambkit.core.mq.activemq.ActiveMQ;
import com.lambkit.core.mq.activemq.ActiveMQReceiver;
import com.lambkit.core.mq.activemq.ActiveMQSender;

public class TestActiveMQ {

	public static void main(String[] args) throws JMSException {
		// 创建了链接池
		ActiveMQ p = new ActiveMQ("failover://(tcp://127.0.0.1:61616)?initialReconnectDelay=1000");
		p.start();

		String subject = "test";

		// 定义发送者
		p.addSender(new ActiveMQSender<Message>("testSender1", p.getConnection(), MqType.Queue, subject));
		// 定义接受者
		p.addReceiver(new ActiveMQReceiver<Message>("testReceiver1", p.getConnection(), MqType.Queue, subject) {
			@Override
			public void handle(Message message) {
				try {
					if (message instanceof TextMessage) {
						TextMessage msg = (TextMessage) message;
						System.out.println(msg.getText());
					} else {
						System.out.println(message);
					}
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		for (int i = 0; i < 10; i++) {
			new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						ActiveMQSender<?> sq1 = p.getSender("testSender1");
						TextMessage msg = sq1.getSession().createTextMessage("测试" + new Date());
						sq1.sendMessage(msg);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}.run();
		}
	}
}
