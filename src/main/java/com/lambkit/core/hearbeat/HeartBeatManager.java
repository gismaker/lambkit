package com.lambkit.core.hearbeat;

import java.util.Set;

import com.jfinal.aop.Enhancer;
import com.jfinal.config.Plugins;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.lambkit.common.util.ClassUtils;
import com.lambkit.core.hearbeat.annotation.HeartBeatTask;

public class HeartBeatManager {

	private static HeartBeatManager manager;
	
	public static HeartBeatManager me() {
		if(manager==null) {
			manager = Enhancer.enhance(HeartBeatManager.class.getName(), HeartBeatManager.class);
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
                	addTask(task.frequency(), (HeartBeat) Enhancer.enhance(clazz));
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
