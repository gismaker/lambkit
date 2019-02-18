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
import java.util.List;
import java.util.Map;

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
	public Long size(String cacheName) {
		// TODO Auto-generated method stub
		return Long.valueOf(getOrAddCache(cacheName).getSize());
	}

}
