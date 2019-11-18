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
package com.lambkit.core.api.url;

public class URLParameter {

	private String key;

	private String value;

	/**
	 * If the value = true, {@link #value} is null
	 */
	private boolean randomValue = false;

	private UrlApiModel api;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isRandomValue() {
		return randomValue;
	}

	public void setRandomValue(boolean randomValue) {
		this.randomValue = randomValue;
	}

	public UrlApiModel getApi() {
		return api;
	}

	public void setApi(UrlApiModel api) {
		this.api = api;
	}

}
