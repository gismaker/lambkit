package com.lambkit.test.mq.kafka;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.lambkit.component.kafka.Kafka;
import com.lambkit.component.kafka.KafkaConsumerTemplate;
import com.lambkit.component.kafka.KafkaPlugin;

public class TestKafkaMq {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//添加生产者
		KafkaPlugin p = new KafkaPlugin(Kafka.defaultName, 
				"127.0.0.1:9092", 
				"org.apache.kafka.common.serialization.StringSerializer", 
				"org.apache.kafka.common.serialization.StringSerializer");
		p.start();
		
		//添加消费者
		KafkaConsumerTemplate consumer = new KafkaConsumerTemplate("127.0.0.1:9092", 
				"org.apache.kafka.common.serialization.StringDeserializer", 
				"org.apache.kafka.common.serialization.StringDeserializer", 
				"test.group", "test.topic");
		Kafka.addConsumer(Kafka.defaultName, consumer);
		
		//发送消息
		Kafka.send(Kafka.defaultName, new ProducerRecord<String, String>(
				"test.topic", "keykey", "msgmsg中文消息啊"));
		
		//即时看到消息
		Future f = Kafka.send(Kafka.defaultName, new ProducerRecord<String, String>(
				"test.topic", "keykey", "msgmsg中文消息啊" + new Date()));
		f.get();
		
		
		//停止
		p.stop();
	}
}
