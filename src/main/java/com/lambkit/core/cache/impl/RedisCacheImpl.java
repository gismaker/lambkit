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
package com.lambkit.core.cache.impl;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.lambkit.core.cache.BaseCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisCacheImpl extends BaseCache {
	
	Cache redis;

    public RedisCacheImpl() {
    	redis = Redis.use();
    }


    @Override
    public <T> T get(String cacheName, Object key) {
        return redis.get(buildKey(cacheName, key));
    }

    @Override
    public void put(String cacheName, Object key, Object value) {
        if (value == null) {
            // if value is null : java.lang.NullPointerException: null at redis.clients.jedis.Protocol.sendCommand(Protocol.java:99)
            return;
        }
        redis.set(buildKey(cacheName, key), value);
    }
    
    @Override
	public Long size(String cacheName) {
		// TODO Auto-generated method stub
		return Long.valueOf(redis.keys(cacheName + ":*").size());
	}

    @Override
    public List getKeys(String cacheName) {
        List<String> keys = new ArrayList<String>();
        keys.addAll(redis.keys(cacheName + ":*"));
        for (int i = 0; i < keys.size(); i++) {
            keys.set(i, keys.get(i).substring(cacheName.length() + 3));
        }
        return keys;
    }
    
    @Override
    public Collection getValues(String cacheName) {
    	// TODO Auto-generated method stub
    	List<Object> values = new ArrayList<Object>();
    	for (String key : redis.keys(cacheName + ":*")) {
    		values.add(redis.get(key));
		}
    	return values;
    }
    
    @Override
	public Map getAll(String cacheName) {
		// TODO Auto-generated method stub
    	Map<String, Object> map = new HashMap<>();
    	for (String key : redis.keys(cacheName + ":*")) {
    		map.put(key.substring(cacheName.length() + 3), redis.get(key));
		}
    	return map;
	}

    @Override
    public void remove(String cacheName, Object key) {
        redis.del(buildKey(cacheName, key));
    }


    @Override
    public void removeAll(String cacheName) {
        String[] keys = new String[]{};
        keys = redis.keys(cacheName + ":*").toArray(keys);
        redis.del(keys);
    }


    @Override
    public <T> T get(String cacheName, Object key, IDataLoader dataLoader) {
        Object data = get(cacheName, key);
        if (data == null) {
            data = dataLoader.load();
            put(cacheName, key, data);
        }
        return (T) data;
    }


    private Object buildKey(String cacheName, Object key) {
    	if(StrKit.isBlank(cacheName)) return key;
        if (key instanceof Number)
            return String.format("%s:I:%s", cacheName, key);
        else {
            Class keyClass = key.getClass();
            if (String.class.equals(keyClass) ||
                    StringBuffer.class.equals(keyClass) ||
                    StringBuilder.class.equals(keyClass)) {
                return String.format("%s:S:%s", cacheName, key);
            }
        }
        return String.format("%s:O:%s", cacheName, key);
    }


	@Override
	public void put(String cacheName, Object key, Object value, int liveSeconds) {
		// TODO Auto-generated method stub
		if (value == null) {
            // if value is null : java.lang.NullPointerException: null at redis.clients.jedis.Protocol.sendCommand(Protocol.java:99)
            return;
        }
        if (liveSeconds <= 0) {
            put(cacheName, key, value);
            return;
        }
		redis.setex(buildKey(cacheName, key), liveSeconds, value);
	}


	@Override
	public Long expire(String cacheName, Object key, int seconds) {
		// TODO Auto-generated method stub
		return redis.expire(buildKey(cacheName, key), seconds);
	}


	@Override
	public <T> T get(String cacheName, Object key, IDataLoader dataLoader, int liveSeconds) {
		// TODO Auto-generated method stub
		if (liveSeconds <= 0) {
            return get(cacheName, key, dataLoader);
        }
		Object data = get(cacheName, key);
        if (data == null) {
            data = dataLoader.load();
            put(cacheName, key, data, liveSeconds);
        }
        return (T) data;
	}


	@Override
	public void lpush(String cacheName, Object key, Object... values) {
		// TODO Auto-generated method stub
		redis.lpush(buildKey(cacheName, key), values);
	}


	@Override
	public Long llen(String cacheName, Object key) {
		// TODO Auto-generated method stub
		return redis.llen(buildKey(cacheName, key));
	}


	@Override
	public void lrem(String cacheName, Object key, int count, Object value) {
		// TODO Auto-generated method stub
		redis.lrem(buildKey(cacheName, key), count, value);
	}


	@Override
	public List lrange(String cacheName, Object key, int start, int end) {
		// TODO Auto-generated method stub
		return redis.lrange(buildKey(cacheName, key), start, end);
	}

	@Override
	public void srem(String cacheName, Object key, Object... members) {
		// TODO Auto-generated method stub
		redis.srem(buildKey(cacheName, key), members);
	}

	@Override
	public Set smembers(String cacheName, Object key) {
		// TODO Auto-generated method stub
		return redis.smembers(buildKey(cacheName, key));
	}


	@Override
	public Long scard(String cacheName, Object key) {
		// TODO Auto-generated method stub
		return redis.scard(buildKey(cacheName, key));
	}

	@Override
	public Long sadd(String cacheName, Object key, Object... members) {
		// TODO Auto-generated method stub
		return redis.sadd(buildKey(cacheName, key), members);
	}
}
