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
package com.lambkit.core.cache;

import com.jfinal.plugin.ehcache.IDataLoader;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 孤竹行
 *
 */
public interface LambkitCache extends com.jfinal.plugin.activerecord.cache.ICache {

    public boolean isNoneCache();
    
    public Long size(String cacheName);
    
    public <T> T get(String cacheName, Object key);

    public void put(String cacheName, Object key, Object value);

    public void put(String cacheName, Object key, Object value, int liveSeconds);

    public List getKeys(String cacheName);
    
    public Collection getValues(String cacheName);
    
    public Map getAll(String cacheName);

    public void remove(String cacheName, Object key);

    public void removeAll(String cacheName);

    public <T> T get(String cacheName, Object key, IDataLoader dataLoader);

    public <T> T get(String cacheName, Object key, IDataLoader dataLoader, int liveSeconds);
    
    /**
     * 缓存到列表中
     * @param cacheName
     * @param key
     * @param value
     */
    public void lpush(String cacheName, Object key, Object... values);
	/**
	 * 返回列表 key 的长度。
	 * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
	 * 如果 key 不是列表类型，返回一个错误。
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Long llen(String cacheName, Object key);
	/**
	 * 根据参数 count 的值，移除列表中与参数 value 相等的元素。 
	 * count 的值可以是以下几种： 
	 * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。 
	 * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。 
	 * count = 0 : 移除表中所有与 value 相等的值。
	 * @param cacheName
	 * @param key
	 * @param count
	 * @param value
	 */
	public void lrem(String cacheName, Object key, int count, Object value);
	/**
	 * 返回缓存的列表
	 * @param cacheName
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List lrange(String cacheName, Object key, int start, int end);
	/**
	 * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
	 * @param cacheName
	 * @param key
	 * @param members
	 */
	public void srem(String cacheName, Object key, Object... members);
	/**
	 * 返回集合 key 中的所有成员。不存在的 key 被视为空集合。
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Set smembers(String cacheName, Object key);
	/**
	 * 返回集合 key 的基数(集合中元素的数量)。
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Long scard(String cacheName, Object key);
	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
	 * 在 Redis 中，带有生存时间的 key 被称为『易失的』(volatile)。
	 * @param key
	 * @param seconds
	 * @return 0：设置失败，1：设置成果
	 */
	public Long expire(String cacheName, Object key, int seconds);
	/**
	 * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
	 * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
	 * 当 key 不是集合类型时，返回一个错误。
	 */
	public Long sadd(String cacheName, Object key, Object... members);
}
