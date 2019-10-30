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
package com.lambkit.web;

import com.jfinal.kit.StrKit;

public class WebConfig {
	public static final String NAME_DEFAULT = "default";
	
	private String name;
	private String url;
	private String path;
	private boolean jsonp = false;
	private boolean excel = false;
	
	public boolean isConfigOk() {
		// TODO Auto-generated method stub
		return StrKit.notBlank(name) && StrKit.notBlank(url) && StrKit.notBlank(path);
	}
	
	public boolean isExcel() {
		return excel;
	}
	public void setExcel(boolean excel) {
		this.excel = excel;
	}
	public boolean isJsonp() {
		return jsonp;
	}
	public void setJsonp(boolean jsonp) {
		this.jsonp = jsonp;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
