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

import com.jfinal.plugin.ehcache.IDataLoader;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author 孤竹行
 *
 */
public interface ICache extends com.jfinal.plugin.activerecord.cache.ICache {

    public boolean isNoneCache();
    
    public Long size(String cacheName);
    
    public <T> T get(String cacheName, Object key);

    public void put(String cacheName, Object key, Object value);

    public void put(String cacheName, Object key, Object value, int liveSeconds);

    public List getKeys(String cacheName);
    
    public Collection getValues(String cacheName);
    
    public Map getAll(String cacheName);

    public void remove(String cacheName, Object key);

    public void removeAll(String cacheName);

    public <T> T get(String cacheName, Object key, IDataLoader dataLoader);

    public <T> T get(String cacheName, Object key, IDataLoader dataLoader, int liveSeconds);
}
