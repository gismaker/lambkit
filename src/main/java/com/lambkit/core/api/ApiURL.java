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
package com.lambkit.core.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApiURL implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8007847649111484623L;

	// 监测地址URL
	private String url;

	/**
	 * All available contentTypes: Default is null
	 */
	private String contentType;

	/**
	 * {@link #monitorUrl} send request method, default: GET
	 */
	private UrlRequestMethod requestMethod = UrlRequestMethod.GET;

	// Request parameters(optional)
	private List<URLParameter> urlParameters = new ArrayList<>();
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public UrlRequestMethod getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(UrlRequestMethod requestMethod) {
		this.requestMethod = requestMethod;
	}

	public List<URLParameter> getUrlParameters() {
		return urlParameters;
	}

	public void setUrlParameters(List<URLParameter> urlParameters) {
		this.urlParameters = urlParameters;
	}
}
