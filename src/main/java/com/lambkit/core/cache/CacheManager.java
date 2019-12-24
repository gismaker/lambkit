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

import com.lambkit.Lambkit;
import com.lambkit.core.cache.impl.EhcacheCacheImpl;
import com.lambkit.core.cache.impl.RedisCacheImpl;
import com.lambkit.core.cache.impl.RedisHashCacheImpl;

public class CacheManager {

    private static CacheManager me = new CacheManager();

    private CacheManager() {
    }

    private BaseCache cache;
    private BaseCache hashCache;

    public static CacheManager me() {
        return me;
    }

    public BaseCache getCache() {
        if (cache == null) {
            cache = buildCache();
        }
        return cache;
    }
    
    public BaseCache getHash() {
    	CacheConfig config = Lambkit.config(CacheConfig.class);
    	if(hashCache==null && CacheConfig.TYPE_REDIS.equals(config.getType())) {
    		hashCache = new RedisHashCacheImpl(); 
    	}
    	return hashCache;
    }

    public BaseCache buildCache() {
        CacheConfig config = Lambkit.config(CacheConfig.class);
        return buildCache(config.getType());
    }
    
    public BaseCache buildCache(String type) {
        switch (type) {
            case CacheConfig.TYPE_EHCACHE:
                return new EhcacheCacheImpl();
            case CacheConfig.TYPE_REDIS:
                return new RedisCacheImpl();
            case CacheConfig.TYPE_NONE_CACHE:
                return new NoneCacheImpl();
            default:
                return null;
        }
    }
}
