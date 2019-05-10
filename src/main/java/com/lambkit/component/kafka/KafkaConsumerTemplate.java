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
package com.lambkit.component.kafka;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

/**
 * 消费者模版
 * @author yangyong
 *
 */
public class KafkaConsumerTemplate {
	private boolean runner = true;
    public KafkaConsumerTemplate(String servers,
                                 String keyDeserializer,
                                 String valueDeserializer,
                                 String group,
                                 String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("key.deserializer", keyDeserializer);
        props.put("value.deserializer", valueDeserializer);
        props.put("group.id", group);//不同ID 可以同时订阅消息
        /*
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");*/
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));//订阅TOPIC
        try {
            while (runner) {
                ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
                for (TopicPartition partition : records.partitions()) {
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                        //可以自定义Handler,处理对应的TOPIC消息(partitionRecords.key())
                        System.out.println(record.offset() + ": " + record.value());
                    }
                    /*consumer.commitSync();//同步*/
                }
            }
        } finally {
            consumer.close();
        }
    }
    public void setRunner(boolean runner) {
        this.runner = runner;
    }
}
