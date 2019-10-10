package com.lambkit.test.redis;

import java.util.Set;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lambkit.component.redis.JedisClusterCallback;
import com.lambkit.component.redis.RedisClusterKit;
import com.lambkit.component.redis.RedisClusterPlugin;

import redis.clients.jedis.JedisCluster;

public class TestRedisCluster {
	/*
	@Test
	public void t1() {
		String clusterJson = "[{host:'192.168.11.81',port:'7001'},{host:'192.168.11.81',port:'7002'},{host:'192.168.11.81',port:'7003'},{host:'192.168.11.81',port:'7007'},{host:'192.168.11.81',port:'7008'},{host:'192.168.11.82',port:'7004'},{host:'192.168.11.82',port:'7005'},{host:'192.168.11.82',port:'7006'},{host:'192.168.11.82',port:'7009'},{host:'192.168.11.82',port:'7010'}]";
		RedisClusterPlugin p = new RedisClusterPlugin(clusterJson);
		p.start();
		// 集群模式本身是没有keys命令的，这里我自己实现了
		Set<String> allKeys = RedisClusterKit.clusterKeys("*");
		System.out.println(JSON.toJSONString(allKeys));
		// 大小
		// RedisClusterTools.clusterDbSize();
		// 删除
		// RedisClusterTools.clusterFlushDB();
		// 其他操作，其他操作全都是回调方式，因为我懒得写里面所有方法了，只要是jedis支持的方法，集群模式这个工具都支持
		RedisClusterKit.clusterExecute(new JedisClusterCallback<Object>() {
			@Override
			public Object doInJedisCluster(JedisCluster jedis) throws Throwable {
				// jedis.get("");
				// jedis.set("", "");
				// jedis.del("");
				// jedis.hget("", "");
				// ....不写了，自己看文档吧
				return null;
			}
		});
	}
	*/
}
