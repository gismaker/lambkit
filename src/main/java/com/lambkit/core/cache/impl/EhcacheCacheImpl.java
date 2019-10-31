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

import com.jfinal.log.Log;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.lambkit.core.cache.BaseCache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EhcacheCacheImpl extends BaseCache {

	private static CacheManager cacheManager;
	private static Object locker = new Object();
	private static final Log log = Log.getLog(EhcacheCacheImpl.class);
	
	public EhcacheCacheImpl() {
		// TODO Auto-generated constructor stub
		cacheManager = CacheManager.create();
	}
	
	public CacheManager getCacheManager() {
		return cacheManager;
	}
	
	static Cache getOrAddCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			synchronized(locker) {
				cache = cacheManager.getCache(cacheName);
				if (cache == null) {
					log.warn("Could not find cache config [" + cacheName + "], using default.");
					cacheManager.addCacheIfAbsent(cacheName);
					cache = cacheManager.getCache(cacheName);
					log.debug("Cache [" + cacheName + "] started.");
				}
			}
		}
		return cache;
	}
	
	public void put(String cacheName, Object key, Object value) {
		getOrAddCache(cacheName).put(new Element(key, value));
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, Object key) {
		Element element = getOrAddCache(cacheName).get(key);
		return element != null ? (T)element.getObjectValue() : null;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public List getKeys(String cacheName) {
		return getOrAddCache(cacheName).getKeys();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Collection getValues(String cacheName) {
		// TODO Auto-generated method stub
		List<Object> values = new ArrayList<>();
		Cache cache = getOrAddCache(cacheName);
		for (Object key : cache.getKeys()) {
			Element element = cache.get(key);
			Object emt = element != null ? element.getObjectValue() : null;
			values.add(emt);
		}
		return values;
	}
	
	@Override
	public Map getAll(String cacheName) {
		// TODO Auto-generated method stub
		Map<Object, Object> map = new HashMap<>();
		Cache cache = getOrAddCache(cacheName);
		List list = cache.getKeys();
		for (Object key : list) {
			Element element = cache.get(key);
			map.put(key, element != null ? element.getObjectValue() : null);
		}
		return map;
	}
	
	public void remove(String cacheName, Object key) {
		getOrAddCache(cacheName).remove(key);
	}
	
	public void removeAll(String cacheName) {
		getOrAddCache(cacheName).removeAll();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, Object key, IDataLoader dataLoader) {
		Object data = get(cacheName, key);
		if (data == null) {
			data = dataLoader.load();
			put(cacheName, key, data);
		}
		return (T)data;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, Object key, Class<? extends IDataLoader> dataLoaderClass) {
		Object data = get(cacheName, key);
		if (data == null) {
			try {
				IDataLoader dataLoader = dataLoaderClass.newInstance();
				data = dataLoader.load();
				put(cacheName, key, data);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return (T)data;
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
	public void put(String cacheName, Object key, Object value, int liveSeconds) {
		// TODO Auto-generated method stub
		if (liveSeconds <= 0) {
            put(cacheName, key, value);
            return;
        }
        Element element = new Element(key, value);
        element.setTimeToLive(liveSeconds);
        getOrAddCache(cacheName).put(element);
	}

	@Override
	public Long expire(String cacheName, Object key, int seconds) {
		// TODO Auto-generated method stub
		if (seconds <= 0) {
            return 0L;
        }
		Element element = getOrAddCache(cacheName).get(key);
		element.setTimeToLive(seconds);
		getOrAddCache(cacheName).put(element);
		return 1L;
	}

	@Override
	public Long size(String cacheName) {
		// TODO Auto-generated method stub
		return Long.valueOf(getOrAddCache(cacheName).getSize());
	}

	/**
	 * 缓存到列表中
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public void lpush(String cacheName, Object key, Object... values) {
		Cache cache = getOrAddCache(cacheName);
		Object val = get(cacheName, key);
		if(val==null) {
			List list = new ArrayList<Object>();
			list.add(values);
			cache.put(new Element(key, list)); 
		} else {
			List list = (List) val;
			list.add(values);
			cache.put(new Element(key, list)); 
		}
	}
	/**
	 * 列表长度
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Long llen(String cacheName, Object key) {
		Cache cache = getOrAddCache(cacheName);
		Object val = get(cacheName, key);
		if(val==null) {
			return 0L;
		} else {
			List list = (List) val;
			return (long) list.size();
		}
	}
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
	public void lrem(String cacheName, Object key, int count, Object value) {
		Cache cache = getOrAddCache(cacheName);
		Object objectValue = get(cacheName, key);
		if(objectValue==null) {
			return;
		}
		List list = (List) objectValue;
		if(count<0) {
			int c = Math.abs(count);
			for(int i=list.size() - 1; i >= 0; i--) {
				if(c==0) break;
				Object val = list.get(i);
				if(val==null && value==null) list.remove(i);
				else if(val !=null && val.equals(value)) {
					list.remove(i);
				}
				c--;
			}
		} else {
			int c = count;
			for(int i=0; i < list.size(); i++) {
				if(c==0 && count!=0) break;
				Object val = list.get(i);
				if(val==null && value==null) list.remove(i);
				else if(val !=null && val.equals(value)) {
					list.remove(i);
				}
				c--;
			}
		}
		cache.put(new Element(key, list)); 
	}
	
	public List lrange(String cacheName, Object key, int start, int end) {
		Cache cache = getOrAddCache(cacheName);
		Object objectValue = get(cacheName, key);
		if(objectValue==null) {
			return null;
		} else {
			List list = (List) objectValue;
			return list.subList(start, end);
		}
	}

	@Override
	public void srem(String cacheName, Object key, Object... members) {
		Cache cache = getOrAddCache(cacheName);
		Object objectValue = get(cacheName, key);
		if(objectValue==null) {
			return;
		}
		Set set = (Set) objectValue;
		for (Object val : members) {
			set.remove(val);
		}
		cache.put(new Element(key, set)); 
	}

	@Override
	public Set smembers(String cacheName, Object key) {
		// TODO Auto-generated method stub
		Cache cache = getOrAddCache(cacheName);
		Object objectValue = get(cacheName, key);
		if(objectValue==null) {
			return null;
		}
		return (Set) objectValue;
	}

	@Override
	public Long scard(String cacheName, Object key) {
		// TODO Auto-generated method stub
		Cache cache = getOrAddCache(cacheName);
		Object objectValue = get(cacheName, key);
		if(objectValue==null) {
			return 0L;
		} else {
			Set set = (Set) objectValue;
			return (long) set.size();
		}
	}

	@Override
	public Long sadd(String cacheName, Object key, Object... members) {
		// TODO Auto-generated method stub
		Cache cache = getOrAddCache(cacheName);
		Object objectValue = get(cacheName, key);
		Long res = 0L;
		if(objectValue==null) {
			Set set = new HashSet<Object>();
			res = 1L;
			set.add(members);
			cache.put(new Element(key, set)); 
		} else {
			Set set = (Set) objectValue;
			set.add(members);
			cache.put(new Element(key, set)); 
		}
		return res;
	}

}
