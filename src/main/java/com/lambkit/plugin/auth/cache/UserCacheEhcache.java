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
package com.lambkit.plugin.auth.cache;


import com.jfinal.plugin.activerecord.cache.ICache;
import com.jfinal.plugin.ehcache.CacheKit;

public class UserCacheEhcache extends UserCacheBase {

	JCache cache = new JCache();

	@Override
	public ICache getCache() {
		// TODO Auto-generated method stub
		return cache;
	}
	
	class JCache implements ICache {

		@Override
		public <T> T get(String cacheName, Object key) {
			// TODO Auto-generated method stub
			return CacheKit.get(cacheName, key);
		}

		@Override
		public void put(String cacheName, Object key, Object value) {
			// TODO Auto-generated method stub
			CacheKit.put(cacheName, key, value);
		}

		@Override
		public void remove(String cacheName, Object key) {
			// TODO Auto-generated method stub
			CacheKit.remove(cacheName, key);
		}

		@Override
		public void removeAll(String cacheName) {
			// TODO Auto-generated method stub
			CacheKit.removeAll(cacheName);
		}
		
	}
}
