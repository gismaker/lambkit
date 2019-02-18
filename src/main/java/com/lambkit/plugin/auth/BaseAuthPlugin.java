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
package com.lambkit.plugin.auth;

import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.lambkit.plugin.auth.AuthManager;
import com.lambkit.plugin.auth.AuthService;
import com.lambkit.plugin.auth.cache.IUserCache;

public abstract class BaseAuthPlugin implements IPlugin {

	public void init(Class<? extends IUserCache> cacheClazz, Class<? extends AuthService> authClazz) {
		if (cacheClazz == null) {
			throw new RuntimeException("clazz must not be null");
		}
		if (authClazz == null) {
			throw new RuntimeException("clazz must not be null");
		}
		AuthManager.me().init(cacheClazz, authClazz);
	}
	
	public abstract void addMapping(ActiveRecordPlugin arp);

	public IUserCache getCache() {
		return AuthManager.me().getCache();
	}
	
	public AuthService getAuth() {
		return AuthManager.me().getService();
	}

	@Override
	public boolean start() {
		return true;
	}

	@Override
	public boolean stop() {
		AuthManager.me().destroy();
		return true;
	}

}
