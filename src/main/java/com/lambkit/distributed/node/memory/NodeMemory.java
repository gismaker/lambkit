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

import java.util.List;

import com.jfinal.plugin.ehcache.IDataLoader;
import com.lambkit.distributed.node.info.Node;

public interface NodeMemory {

	public Node get(String key);

    public void put(String key, Node value);

    public void put(String key, Node value, int liveSeconds);

    public List<String> getKeys();
    
    public List<Node> getValues();

    public void remove(String key);

    public void removeAll();

    public Node get(String key, IDataLoader dataLoader);

    public Node get(String key, IDataLoader dataLoader, int liveSeconds);
    
    public int size();
}
