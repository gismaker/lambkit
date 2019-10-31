package com.lambkit.component.ehcache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jfinal.log.Log;
import com.jfinal.plugin.ehcache.IDataLoader;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 *  实现ehcache动态创建cache，以及超期判断的具体逻辑 https://yq.aliyun.com/articles/616867
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.component.ehcache
 */
public class Ehcache {
	
	private static CacheManager cacheManager;
	private static Object locker = new Object();
	private static final Log log = Log.getLog(Ehcache.class);
	
	
	public Ehcache() {
		cacheManager = CacheManager.create();
	}

	/**
	 * 获取Cache，当Cache不存在时自动创建
	 * 
	 * @param cacheName
	 * @return Cache
	 */
	public static Cache getOrAddCache(String cacheName) {
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
	
	/**
	 * 缓存是否存在
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public boolean exist(String cacheName, Object key) {
		Cache cache = getOrAddCache(cacheName);
		if(cache.isKeyInCache(key) && cache.getQuiet(key) != null){
		    return true;
		}
		return false;
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
	
	public <T> T get(String cacheName, Object key, IDataLoader dataLoader) {
		Object data = get(cacheName, key);
		if (data == null) {
			data = dataLoader.load();
			put(cacheName, key, data);
		}
		return (T)data;
	}
	
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

	public Long size(String cacheName) {
		// TODO Auto-generated method stub
		return Long.valueOf(getOrAddCache(cacheName).getSize());
	}
	
	
	/////////////////////////////////////////////////////
	/**
	 * 缓存到列表中
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public void lpush(String cacheName, Object key, Object value) {
		Cache cache = getOrAddCache(cacheName);
		List<Object> list = (List<Object>) cache.get(key).getObjectValue();
		if(list==null) {
			list = new ArrayList<Object>();
		}
		list.add(value);
		cache.put(new Element(key, list)); 
	}
	/**
	 * 返回列表 key 的长度。
	 * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
	 * 如果 key 不是列表类型，返回一个错误。
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Long llen(String cacheName, Object key) {
		Cache cache = getOrAddCache(cacheName);
		List<Object> list = (List<Object>) cache.get(key).getObjectValue();
		if(list==null) {
			return 0L;
		} else {
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
		List<Object> list = (List<Object>) cache.get(key).getObjectValue();
		if(list==null) {
			return;
		}
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
		List<Object> list = (List<Object>) cache.get(key).getObjectValue();
		if(list==null) {
			return null;
		} else {
			return list.subList(start, end);
		}
	}
	
	public void srem(String cacheName, Object key, Object... members) {
		Cache cache = getOrAddCache(cacheName);
		Set set = (Set) cache.get(key).getObjectValue();
		if(set==null) {
			return;
		}
		for (Object val : members) {
			set.remove(val);
		}
		cache.put(new Element(key, set)); 
	}

	public Set smembers(String cacheName, Object key) {
		// TODO Auto-generated method stub
		Cache cache = getOrAddCache(cacheName);
		return (Set) cache.get(key).getObjectValue();
	}

	public Long scard(String cacheName, Object key) {
		// TODO Auto-generated method stub
		Cache cache = getOrAddCache(cacheName);
		Set set = (Set) cache.get(key).getObjectValue();
		if(set==null) {
			return 0L;
		} else {
			return (long) set.size();
		}
	}
}
