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
 */package com.lambkit.plugin.jwt;

/**
 * FOR : IJwtAble对象构建异常
 */
public class JwtAbleCreatedException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3263468529743522500L;

	private static String messagePrefix = "构建 IJwtAble 的对象中的 对应的 ";

    private static String messageEnd = "属性不可以为 ";

    /**
     * 组合异常产生原因
     *
     * @param keyWord
     * @param status
     */
    public JwtAbleCreatedException(String keyWord, String status) {
        super(messagePrefix + keyWord + messageEnd + status);
    }
}