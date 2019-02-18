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
package com.lambkit.web.cache;

import java.io.Serializable;

public class ActionCacheInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7409160147802228292L;
	private String key;
    private String group;
    private int liveSeconds;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getLiveSeconds() {
        return liveSeconds;
    }

    public void setLiveSeconds(int liveSeconds) {
        this.liveSeconds = liveSeconds;
    }
}
