package com.lambkit.test.mq.kafka;

import java.util.concurrent.ExecutionException;

import com.lambkit.component.kafka.Kafka;
import com.lambkit.component.kafka.KafkaConsumerTemplate;

/**
 * 注意：：注意：：注意：：
 * 启动是有顺序的，由于测试类我没有使用线程，所以你要先启动TestKafkaConsumer
 * 等TestKafkaConsumer启动完毕后，再启动TestKafkaProducer
 * 然后切换到TestKafkaConsumer的Console，就可以看到生产者发送的消息了
 */
public class TestKafkaConsumer {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
        //添加消费者
        KafkaConsumerTemplate consumer = new KafkaConsumerTemplate(
        		"127.0.0.1:9092", 
        		"org.apache.kafka.common.serialization.StringDeserializer", 
        		"org.apache.kafka.common.serialization.StringDeserializer", 
        		"test.group", 
        		"test.topic");
        Kafka.addConsumer(Kafka.defaultName, consumer);
    }
}
