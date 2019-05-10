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

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.jfinal.log.Log;

public class Kafka {
	public static final String defaultName = "main";
    private static final Log logger = Log.getLog(Kafka.class);
    public static final ConcurrentHashMap<String, Producer> producerMap = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, KafkaConsumerTemplate> consumerMap = new ConcurrentHashMap<>();
    public static void addProducer(String name,
                                   String servers,
                                   String keySerializer,
                                   String valueSerializer) {
    	logger.info("添加生产者：" + name);
        if (producerMap.containsKey(name)) {
            logger.error(name + "已存在!");
        } else {
            Properties props = new Properties();
            props.put("bootstrap.servers", servers);
            props.put("key.serializer", keySerializer);
            props.put("value.serializer", valueSerializer);
            /*props.put("acks", "all");
            props.put("retries ", 1);
            props.put("buffer.memory", 33554432);
            */
            Producer<String, String> producer = new KafkaProducer<>(props);
            producerMap.put(name, producer);
        }
    }
    public Producer getProducer(String name) {
        return producerMap.get(name);
    }
    public static Future send(String producerName,
                              ProducerRecord record) {
        return producerMap.get(producerName).send(record);
    }
    public static void addConsumer(String name,
                                   KafkaConsumerTemplate consumer) {
        logger.info("添加消费者：" + name);
        if (consumerMap.containsKey(name)) {
            logger.error(name + "已存在!");
        } else {
            consumerMap.put(name, consumer);
        }
    }
    public KafkaConsumerTemplate getConsumer(String name) {
        return consumerMap.get(name);
    }
}
