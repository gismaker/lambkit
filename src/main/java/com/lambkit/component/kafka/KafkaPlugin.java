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

import java.util.Map;

import org.apache.kafka.clients.producer.Producer;

import com.jfinal.log.Log;
import com.jfinal.plugin.IPlugin;

/**
 * kafka插件
 * @author yangyong
 */
public class KafkaPlugin implements IPlugin {
	private final Log logger = Log.getLog(this.getClass());
    public KafkaPlugin(String name,
                       String servers,
                       String keySerializer,
                       String valueSerializer) {
        Kafka.addProducer(name, servers, keySerializer, valueSerializer);
    }
    @Override
    public boolean start() {
        return true;
    }
    @Override
    public boolean stop() {
        logger.info("销毁所有生产者和消费者开始");
        for (Map.Entry<String, Producer> entry : Kafka.producerMap.entrySet()) {
            entry.getValue().close();
        }
        for (Map.Entry<String, KafkaConsumerTemplate> entry : Kafka.consumerMap.entrySet()) {
            entry.getValue().setRunner(false);
        }
        logger.info("销毁所有生产者和消费者结束");
        return true;
    }

}
