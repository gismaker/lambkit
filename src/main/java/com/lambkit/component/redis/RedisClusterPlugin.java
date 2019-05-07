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
package com.lambkit.component.redis;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.jfinal.plugin.IPlugin;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis集群模式插件
 * 
 * 集群的搭建可以参考 http://blog.csdn.net/myrainblues/article/details/25881535/
 * @author yangyong
 */
public class RedisClusterPlugin implements IPlugin {
	private Integer maxTotal = 1000;
    private Integer maxIdle = 200;
    private Long maxWaitMillis = 2000L;
    private Boolean testOnBorrow = true;
    private String cluster;
    private String defaultName = "main";
    public RedisClusterPlugin(String cluster) {
        this.cluster = cluster;
    }
    @Override
    public boolean start() {
        if (cluster != null) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(maxTotal);
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            jedisPoolConfig.setTestOnBorrow(testOnBorrow);
            Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
            List<Map> clusterList = JSON.parseArray(cluster, Map.class);
            if (clusterList != null && clusterList.size() > 0) {
                for (Map<String, String> c : clusterList) {
                    jedisClusterNodes.add(new HostAndPort(c.get("host"), Integer.parseInt(c.get("port"))));
                }
                JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, jedisPoolConfig);
                RedisClusterKit.addJedisCluster(defaultName, jedisCluster);
            }
            return true;
        }
        return false;
    }
    @Override
    public boolean stop() {
        return true;
    }
    public Integer getMaxTotal() {
        return maxTotal;
    }
    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }
    public Integer getMaxIdle() {
        return maxIdle;
    }
    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }
    public Long getMaxWaitMillis() {
        return maxWaitMillis;
    }
    public void setMaxWaitMillis(Long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }
    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }
    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }
    public String getCluster() {
        return cluster;
    }
    public void setCluster(String cluster) {
        this.cluster = cluster;
    }
    public String getDefaultName() {
        return defaultName;
    }
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
}
