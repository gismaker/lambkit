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
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * redis集群工具类
 * @author yangyong
 */
public class RedisClusterKit {
	 private static String defaultName = "main";
	    private static final ConcurrentHashMap<String, JedisCluster> jedisClusterMap = new ConcurrentHashMap<>();
	    public static void addJedisCluster(String jedisClusterName,
	                                       JedisCluster jedisCluster) {
	        if (jedisCluster != null && jedisClusterName != null && !jedisClusterMap.containsKey(jedisClusterName)) {
	            jedisClusterMap.put(jedisClusterName, jedisCluster);
	        }
	    }
	    /**
	     * 获得一个JedisCluster
	     *
	     * @param jedisClusterName
	     *
	     * @return
	     */
	    public static JedisCluster getJedisCluster(String jedisClusterName) {
	        return jedisClusterMap.get(jedisClusterName);
	    }
	    /**
	     * 执行集群指令
	     *
	     * @param jedisClusterName
	     * @param action
	     * @param <T>
	     *
	     * @return
	     */
	    public static <T> T clusterExecute(String jedisClusterName,
	                                       JedisClusterCallback<T> action) {
	        JedisCluster jedis = getJedisCluster(jedisClusterName);
	        try {
				return action.doInJedisCluster(jedis);
			} catch (Throwable e) {
				e.printStackTrace();
			}
	        return null;
	    }
	    /**
	     * 执行集群指令
	     *
	     * @param action
	     * @param <T>
	     *
	     * @return
	     */
	    public static <T> T clusterExecute(JedisClusterCallback<T> action) {
	        JedisCluster jedis = getJedisCluster(defaultName);
	        try {
	            return action.doInJedisCluster(jedis);
	        } catch (Throwable e) {
				e.printStackTrace();
			}
	        return null;
	    }
	    /**
	     * 由于JedisCluster没有实现keys操作，这里自己实现以下
	     *
	     * @param pattern
	     *
	     * @return
	     */
	    public static Set<String> clusterKeys(String jedisClusterName,
	                                          String pattern) {
	        Set<String> keys = new HashSet<>();
	        Map<String, JedisPool> clusterNodes = getJedisCluster(jedisClusterName).getClusterNodes();
	        return clusterKeys(pattern, keys, clusterNodes);
	    }
	    private static Set<String> clusterKeys(String pattern,
	                                           Set<String> keys,
	                                           Map<String, JedisPool> clusterNodes) {
	        for (String k : clusterNodes.keySet()) {
	            JedisPool jp = clusterNodes.get(k);
	            Jedis connection = jp.getResource();
	            try {
	                keys.addAll(connection.keys(pattern));
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                connection.close();
	            }
	        }
	        return keys;
	    }
	    /**
	     * 由于JedisCluster没有实现keys操作，这里自己实现以下
	     *
	     * @param pattern
	     *
	     * @return
	     */
	    public static Set<String> clusterKeys(String pattern) {
	        Set<String> keys = new HashSet<>();
	        Map<String, JedisPool> clusterNodes = getJedisCluster(defaultName).getClusterNodes();
	        return clusterKeys(pattern, keys, clusterNodes);
	    }
	    public static Set<byte[]> clusterKeys(String jedisClusterName,
	                                          byte[] pattern) {
	        Set<byte[]> keys = new HashSet<>();
	        Map<String, JedisPool> clusterNodes = getJedisCluster(jedisClusterName).getClusterNodes();
	        return clusterKeys(pattern, keys, clusterNodes);
	    }
	    private static Set<byte[]> clusterKeys(byte[] pattern,
	                                           Set<byte[]> keys,
	                                           Map<String, JedisPool> clusterNodes) {
	        for (Object k : clusterNodes.keySet()) {
	            JedisPool jp = clusterNodes.get(k);
	            Jedis connection = jp.getResource();
	            try {
	                keys.addAll(connection.keys(pattern));
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                connection.close();
	            }
	        }
	        return keys;
	    }
	    public static Set<byte[]> clusterKeys(byte[] pattern) {
	        Set<byte[]> keys = new HashSet<>();
	        Map<String, JedisPool> clusterNodes = getJedisCluster(defaultName).getClusterNodes();
	        return clusterKeys(pattern, keys, clusterNodes);
	    }
	    public static void clusterFlushDB(String jedisClusterName) {
	        Map<String, JedisPool> clusterNodes = getJedisCluster(jedisClusterName).getClusterNodes();
	        clusterFlushDb(clusterNodes);
	    }
	    private static void clusterFlushDb(Map<String, JedisPool> clusterNodes) {
	        for (Object k : clusterNodes.keySet()) {
	            JedisPool jp = clusterNodes.get(k);
	            Jedis connection = jp.getResource();
	            try {
	                connection.flushDB();
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                connection.close();
	            }
	        }
	    }
	    public static void clusterFlushDB() {
	        Map<String, JedisPool> clusterNodes = getJedisCluster(defaultName).getClusterNodes();
	        clusterFlushDb(clusterNodes);
	    }
	    public static Long clusterDbSize(String jedisClusterName) {
	        Long total = 0L;
	        Map<String, JedisPool> clusterNodes = getJedisCluster(jedisClusterName).getClusterNodes();
	        return clusterDbSize(total, clusterNodes);
	    }
	    private static Long clusterDbSize(Long total,
	                                      Map<String, JedisPool> clusterNodes) {
	        for (Object k : clusterNodes.keySet()) {
	            JedisPool jp = clusterNodes.get(k);
	            Jedis connection = jp.getResource();
	            try {
	                total += connection.dbSize();
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                connection.close();
	            }
	        }
	        return total;
	    }
	    public static Long clusterDbSize() {
	        Long total = 0L;
	        Map<String, JedisPool> clusterNodes = getJedisCluster(defaultName).getClusterNodes();
	        return clusterDbSize(total, clusterNodes);
	    }
}
