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
package com.lambkit.component.shiro.cache;

import com.jfinal.kit.LogKit;
import com.jfinal.plugin.redis.Redis;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;

/**
 * 自定义RedisCache
 */
public class RedisCache<K, V> implements org.apache.shiro.cache.Cache<K, V> {
    public static final String SHIRO_KEY = "shiro_";
    private int expire = 0;

    public RedisCache(int expire) {
        this.expire = expire;
    }

    public V get(K key) throws CacheException {
        LogKit.debug("根据key从Redis中获取对象 key [" + key + "]");
        if (key == null) {
            return null;
        } else {
            return Redis.use().get(SHIRO_KEY + key);
        }
    }

    public V put(K key, V value) throws CacheException {
        LogKit.debug("根据key从存储 key [" + key + "]");
        Redis.use().set(SHIRO_KEY + key, value);
        Redis.use().expire(SHIRO_KEY + key, expire);
        return value;
    }

    /**
     * shiro 的默认删除KEY：用户的
     *
     * @throws CacheException CacheException
     */
    public V remove(K key) throws CacheException {
        LogKit.debug("从redis中删除 key [" + key + "]");
        V previous = get(key);
        return previous;
    }

    public void clear() throws CacheException {
        LogKit.debug("从redis中删除所有元素");
        try {
            Set<String> keys = Redis.use().keys(SHIRO_KEY + "*");
            Redis.use().del(keys);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    public int size() {
        Set<String> keys = Redis.use().keys(SHIRO_KEY + "*");
        return keys.size();
    }

    public Set<K> keys() {
        return (Set<K>) Redis.use().hkeys(SHIRO_KEY);
    }

    public Collection<V> values() {
        return Redis.use().hvals(SHIRO_KEY);
    }
}
