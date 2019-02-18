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
package com.lambkit.common.base;

/**
 * 全局常量
 */
public class Consts {

	public static final String LAMBKIT_VERSION = "1.0";
	
    public static final String LAMBKIT_TOKEN = "lambkit-token";
	/**
	 * 初始化事件
	 */
	public static final String EVENT_INIT = "lambkit:init";
	/**
     * 启动完成事件
     */
    public static final String EVENT_STARTED = "lambkit:started";

    public static final String ATTR_REQUEST = "REQUEST";
    public static final String ATTR_SESSION = "SESSION";
    public static final String ATTR_CONTEXT_PATH = "CPATH";
}
