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
package com.lambkit.distributed.node.redis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.jfinal.log.Log;
import com.lambkit.distributed.node.NodeConstants;
import com.lambkit.distributed.node.service.ServiceNodeSystem;

public class RedisNodeHeartbeat {
	static Log log = Log.getLog(RedisNodeHeartbeat.class);

	private ThreadPoolExecutor jobExecutor;
	private ScheduledExecutorService heartbeatExecutor;
	// 上一次心跳开关的状态
	private boolean lastHeartBeatSwitcherStatus = false;
	private volatile boolean currentHeartBeatSwitcherStatus = false;
	// 开关检查次数。
	private int switcherCheckTimes = 0;
	
	public RedisNodeHeartbeat() {
		heartbeatExecutor = Executors.newSingleThreadScheduledExecutor();
		ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10000);
		jobExecutor = new ThreadPoolExecutor(5, 30, 30 * 1000, TimeUnit.MILLISECONDS, workQueue);
	}
	
	public void start() {
		heartbeatExecutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				//System.out.println("ServiceNodeHeartbeat.heartbeatExecutor.run: "+ switcherCheckTimes);
				// 由于consul的check set pass会导致consul
				// server的写磁盘操作，过于频繁的心跳会导致consul
				// 性能问题，只能将心跳方式改为较长的周期进行一次探测。又因为想在关闭心跳开关后尽快感知
				// 就将心跳改为以较小周期检测心跳开关是否变动，连续检测多次后给consul server发送一次心跳。
				// TODO 改为开关listener方式。
				try {
					boolean switcherStatus = isHeartbeatOpen();
					if (isSwitcherChange(switcherStatus)) { // 心跳开关状态变更
						processHeartbeat();
					} else {// 心跳开关状态未变更
						if (switcherStatus) {// 开关为开启状态，则连续检测超过MAX_SWITCHER_CHECK_TIMES次发送一次心跳
							switcherCheckTimes++;
							if (switcherCheckTimes >= NodeConstants.MAX_SWITCHER_CHECK_TIMES) {
								switcherCheckTimes = 0;
								processHeartbeat();
							}
						}
					}

				} catch (Exception e) {
					log.error("consul heartbeat executor err:", e);
				}
			}
		}, NodeConstants.SWITCHER_CHECK_CIRCLE, NodeConstants.SWITCHER_CHECK_CIRCLE, TimeUnit.MILLISECONDS);
	}

	/**
	 * 判断心跳开关状态是否改变，如果心跳开关改变则更新lastHeartBeatSwitcherStatus为最新状态
	 * 
	 * @param switcherStatus
	 * @return
	 */
	private boolean isSwitcherChange(boolean switcherStatus) {
		boolean ret = false;
		if (switcherStatus != lastHeartBeatSwitcherStatus) {
			ret = true;
			lastHeartBeatSwitcherStatus = switcherStatus;
			log.info("heartbeat switcher change to " + switcherStatus);
		}
		return ret;
	}

	protected void processHeartbeat() {
		RedisNodeSystem.me().sendHeartbeat();
	}

	public void close() {
		heartbeatExecutor.shutdown();
		jobExecutor.shutdown();
		log.info("Consul heartbeatManager closed.");
	}
	
	// 检查心跳开关是否打开
	private boolean isHeartbeatOpen() {
		return currentHeartBeatSwitcherStatus;
	}

	public void setHeartbeatOpen(boolean open) {
		currentHeartBeatSwitcherStatus = open;
	}
}
