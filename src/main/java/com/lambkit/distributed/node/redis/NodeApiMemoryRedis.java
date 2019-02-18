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
package com.lambkit.distributed.node.redis;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.ehcache.IDataLoader;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.lambkit.distributed.node.api.NodeApi;
import com.lambkit.distributed.node.memory.NodeApiMemory;

public class NodeApiMemoryRedis implements NodeApiMemory {

	private String cacheName = "nodeapilist";
	private Cache redis;
	
	public NodeApiMemoryRedis() {
		// TODO Auto-generated constructor stub
		redis = Redis.use();
	}
	
	public NodeApiMemoryRedis(String cachename) {
		// TODO Auto-generated constructor stub
		this.cacheName = cachename;
		redis = Redis.use();
	}
	
	private Cache getCache() {
		if(redis==null) {
			redis = Redis.use();
		}
		return redis;
	}
	
	public NodeApi get(String key) {
		if(getCache()!=null) {
			return getCache().get(buildKey(cacheName, key));
		}
		return null;
	}

    public void put(String key, NodeApi value) {
    	 if (value == null) {
             // if value is null : java.lang.NullPointerException: null at redis.clients.jedis.Protocol.sendCommand(Protocol.java:99)
             return;
         }
    	if(getCache()!=null) {
			getCache().set(buildKey(cacheName, key), value);
		}
    }

    public void put(String key, NodeApi value, int liveSeconds) {
    	 if (value == null) {
             // if value is null : java.lang.NullPointerException: null at redis.clients.jedis.Protocol.sendCommand(Protocol.java:99)
             return;
         }
    	if(getCache()!=null) {
    		getCache().setex(buildKey(cacheName, key), liveSeconds, value);
		}
    }

    public List<String> getKeys() {
    	if(getCache()!=null) {
    		List<String> keys = new ArrayList<String>();
            keys.addAll(getCache().keys(cacheName + ":*"));
            for (int i = 0; i < keys.size(); i++) {
                keys.set(i, keys.get(i).substring(cacheName.length() + 3));
            }
            return keys;
		}
    	return null;
    }
    
    public void remove(String key) {
    	if(getCache()!=null) {
			getCache().del(buildKey(cacheName, key));
		}
    }

    public void removeAll() {
    	if(getCache()!=null) {
    		String[] keys = new String[]{};
            keys = getCache().keys(cacheName + ":*").toArray(keys);
            getCache().del(keys);
		}
    }

    public NodeApi get(String key, IDataLoader dataLoader) {
    	if(getCache()!=null) {
    		Object data = get(key);
    		if (data == null) {
                data = dataLoader.load();
                getCache().set(buildKey(cacheName, key), data);
            }
    		return (NodeApi) data;
    	}
    	return null;
    }

    public NodeApi get(String key, IDataLoader dataLoader, int liveSeconds) {
    	if(getCache()!=null) {
    		Object data = get(key);
    		if (data == null) {
                data = dataLoader.load();
                getCache().setex(buildKey(cacheName, key), liveSeconds, data);
            }
    		return (NodeApi) data;
    	}
    	return null;
    }
    
    @Override
	public List<NodeApi> getValues() {
		// TODO Auto-generated method stub
    	List<NodeApi> list = new ArrayList<>();
    	List<String> keys = getKeys();
    	for (String key : keys) {
			list.add(get(key));
		}
		return list;
	}
    
    private String buildKey(String cacheName, Object key) {
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
    
	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

}
