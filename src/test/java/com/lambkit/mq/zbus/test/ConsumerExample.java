package com.lambkit.mq.zbus.test;

import java.io.IOException;

import io.zbus.mq.Broker;
import io.zbus.mq.MessageHandler;
import io.zbus.mq.Consumer;
import io.zbus.mq.ConsumerConfig;
import io.zbus.mq.Message;
import io.zbus.mq.MqClient;

public class ConsumerExample {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {   
		Broker broker = new Broker("localhost:15555");   
		
		ConsumerConfig config = new ConsumerConfig(broker);
		config.setTopic("MyTopic"); 
		config.setMessageHandler(new MessageHandler() { 
			@Override
			public void handle(Message msg, MqClient client) throws IOException {
				System.out.println(msg);
			}
		});
		
		Consumer consumer = new Consumer(config);
		consumer.start(); 
	} 
}
