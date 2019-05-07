package com.lambkit.test.mq.zbus;


import com.lambkit.core.mq.zubs.ZbusMqSender;
import com.lambkit.core.mq.zubs.ZbusSender;

import io.zbus.mq.Broker;
import io.zbus.mq.Message;
import io.zbus.mq.Producer; 

public class ProducerExample { 
	public static void main(String[] args) throws Exception { 
		Broker broker = new Broker("localhost:15555"); 
		  /*
		Producer p = new Producer(broker);
		p.declareTopic("MyTopic"); 
		 
		Message msg = new Message();
		msg.setTopic("MyTopic");
		//msg.setTag("oo.account.pp");
		msg.setBody("hello " + System.currentTimeMillis()); 
		
		Message res = p.publish(msg);
		System.out.println(res);   
		 */
		
		ZbusSender sender = new ZbusMqSender<String>("MyTopic", broker);
		sender.publish("hello " + System.currentTimeMillis(), true);
		broker.close();
	} 
}
