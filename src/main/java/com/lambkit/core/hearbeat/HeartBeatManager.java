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
 */package com.lambkit.core.hearbeat;

import java.util.Set;

import com.jfinal.config.Plugins;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.lambkit.common.util.ClassUtils;
import com.lambkit.core.aop.AopKit;
import com.lambkit.core.hearbeat.annotation.HeartBeatTask;

public class HeartBeatManager {

	private static HeartBeatManager manager;
	
	public static HeartBeatManager me() {
		if(manager==null) {
			manager = AopKit.singleton(HeartBeatManager.class);
		}
		return manager;
	}
	
	private Cron4jPlugin plugin;
	
	public void configPlugin(Plugins me) {
		if(plugin==null) {
			plugin = new Cron4jPlugin();
			me.add(plugin);
		}
	}
	
	public void scanTask(String packageName) {
		if(StrKit.notBlank(packageName)) {
    		Set<Class<?>> ctrlClassSet = ClassUtils.scanPackageByAnnotation(packageName, true, HeartBeatTask.class);
            for (Class<?> clazz : ctrlClassSet) {
            	HeartBeatTask task = clazz.getAnnotation(HeartBeatTask.class);
                if (task != null) {
                	addTask(task.frequency(), (HeartBeat) AopKit.get(clazz));
                }
            }
    	}
	}
	
	public void addTask(String cronExpression, HeartBeat heartBeat) {
		if(plugin==null) {
			return;
		}
		plugin.addTask(cronExpression, new CronTask(heartBeat));
	}
	
	public void addTask(HeartBeatFrequency frequency, HeartBeat heartBeat) {
		if(plugin==null) {
			return;
		}
		plugin.addTask(frequency.getCronExpression(), new CronTask(heartBeat));
	}
	
	public Cron4jPlugin getPlugin() {
		return plugin;
	}
}
