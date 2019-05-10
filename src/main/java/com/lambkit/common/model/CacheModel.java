package com.lambkit.common.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.lambkit.core.cache.BaseCache;
import com.lambkit.core.cache.CacheManager;

public class CacheModel implements Serializable {

	private static final long serialVersionUID = 2987528021846072413L;
	
	private BaseCache cache;
	private String cacheName;
	
	public CacheModel(String cacheName, String type) {
		if(StrKit.isBlank(cacheName)) {
			this.cacheName = StrKit.getRandomUUID();
		} else {
			this.cacheName = cacheName;
		}
		cache = CacheManager.me().buildCache(type);
	}
	
	public Long size() {
		return cache==null ? null : cache.size(cacheName);
	}

	public <T> T get(Object key) {
		return cache==null ? null : cache.get(cacheName, key);
	}

	public void put(Object key, Object value) {
		if(cache!=null) {
			cache.put(cacheName, key, value);
		}
	}

	public void put(Object key, Object value, int liveSeconds) {
		if(cache!=null) {
			cache.put(cacheName, key, value, liveSeconds);
		}
	}

	public List getKeys() {
		return cache==null ? null : cache.getKeys(cacheName);
	}

	public Collection getValues() {
		return cache==null ? null : cache.getValues(cacheName);
	}

	public Map getAll() {
		return cache==null ? null : cache.getAll(cacheName);
	}

	public void remove(Object key) {
		if(cache!=null) {
			cache.remove(cacheName, key);
		}
	}

	public void removeAll() {
		if(cache!=null) {
			cache.removeAll(cacheName);
		}
	}

	public <T> T get(Object key, IDataLoader dataLoader) {
		return cache==null ? null : cache.get(cacheName, key, dataLoader);
	}

	public <T> T get(Object key, IDataLoader dataLoader, int liveSeconds) {
		return cache==null ? null : cache.get(cacheName, key, dataLoader, liveSeconds);
	}

	public BaseCache getCache() {
		return cache;
	}

	public void setCache(BaseCache cache) {
		this.cache = cache;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

}
