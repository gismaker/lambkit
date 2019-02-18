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
package com.lambkit.distributed.node.memory;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.ehcache.IDataLoader;
import com.lambkit.Lambkit;
import com.lambkit.core.cache.ICache;
import com.lambkit.distributed.node.api.NodeApi;

public class NodeApiMemoryCache implements NodeApiMemory {

	private String cacheName = "nodeapilist";
	
	public NodeApiMemoryCache() {
		// TODO Auto-generated constructor stub
	}
	
	public NodeApiMemoryCache(String cachename) {
		// TODO Auto-generated constructor stub
		this.cacheName = cachename;
	}
	
	public NodeApi get(String key) {
		if(getCache()!=null) {
			return getCache().get(cacheName, key);
		}
		return null;
	}

    public void put(String key, NodeApi value) {
    	if(getCache()!=null) {
			getCache().put(cacheName, key, value);
		}
    }

    public void put(String key, NodeApi value, int liveSeconds) {
    	if(getCache()!=null) {
    		getCache().put(cacheName, key, value, liveSeconds);
		}
    }

    public List<String> getKeys() {
    	if(getCache()!=null) {
			return getCache().getKeys(cacheName);
		}
    	return null;
    }
    
    public void remove(String key) {
    	if(getCache()!=null) {
			getCache().remove(cacheName, key);
		}
    }

    public void removeAll() {
    	if(getCache()!=null) {
    		getCache().removeAll(cacheName);
		}
    }

    public NodeApi get(String key, IDataLoader dataLoader) {
    	if(getCache()!=null) {
			return getCache().get(cacheName, key, dataLoader);
		}
    	return null;
    }

    public NodeApi get(String key, IDataLoader dataLoader, int liveSeconds) {
    	if(getCache()!=null) {
			return getCache().get(cacheName, key, dataLoader, liveSeconds);
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
    
    public ICache getCache() {
		return Lambkit.me().getCache();
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

}
