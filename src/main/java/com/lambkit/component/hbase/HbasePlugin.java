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
package com.lambkit.component.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;

import com.jfinal.log.Log;
import com.jfinal.plugin.IPlugin;

public class HbasePlugin implements IPlugin {
	private static final Log logger = Log.getLog(Thread.currentThread().getClass());
	private String quorum;
	private String znode = "/hbase";
	private String encoding = "UTF-8";

	public HbasePlugin(String quorum) {
		this.quorum = quorum;
	}

	@Override
	public boolean start() {
		Configuration config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", quorum);
		config.set("hbase.zookeeper.znode.parent", znode);
		config.set("hbase.encoding", encoding);
		try {
			Hbase.connection = ConnectionFactory.createConnection(config);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Hbase.aggregationClient = new AggregationClient(config);
		return true;
	}

	@Override
	public boolean stop() {
		if (!Hbase.connection.isClosed()) {
			try {
				Hbase.connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Hbase.aggregationClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
