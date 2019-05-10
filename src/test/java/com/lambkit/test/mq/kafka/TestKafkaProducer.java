package com.lambkit.test.mq.kafka;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.lambkit.component.kafka.Kafka;
import com.lambkit.component.kafka.KafkaPlugin;

/**
 * 注意：：注意：：注意：：
 * 启动是有顺序的，由于测试类我没有使用线程，所以你要先启动TestKafkaConsumer
 * 等TestKafkaConsumer启动完毕后，再启动TestKafkaProducer
 * 然后切换到TestKafkaConsumer的Console，就可以看到生产者发送的消息了
 */
public class TestKafkaProducer {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
        //添加生产者
        KafkaPlugin p = new KafkaPlugin(Kafka.defaultName, 
        		"127.0.0.1:9092", 
        		"org.apache.kafka.common.serialization.StringSerializer", 
        		"org.apache.kafka.common.serialization.StringSerializer");
        p.start();
        
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            //模拟发送消息
            Future f = Kafka.send(Kafka.defaultName, new ProducerRecord<String, String>(
            		"test.topic", "keykey", "msgmsg中文消息啊" + new Date()));
            f.get();
        }
        
        p.stop();
    }
}
