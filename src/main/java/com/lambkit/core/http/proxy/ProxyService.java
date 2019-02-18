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
package com.lambkit.core.http.proxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

public class ProxyService {

	public ProxyService createService() {
		return new ProxyService();
	}

	private List<NameValuePair> params;

	public void addParam(String name, String value) {
		addParam(new BasicNameValuePair(name, value));
	}

	public void addParam(NameValuePair param) {
		getParams().add(param);
	}

	public List<NameValuePair> getParams() {
		if (params == null) {
			params = new ArrayList<>();
		}
		return params;
	}

	public String get(String targetUri) {
		ProxyRender proxy = new ProxyRender(null, targetUri);
		return proxy.get(params);
	}

	public HttpResponse httpGet(String targetUri) throws ClientProtocolException, IOException {
		ProxyRender proxy = new ProxyRender(null, targetUri);
		return proxy.httpGet(params);
	}

	public String post(String targetUri) {
		ProxyRender proxy = new ProxyRender(null, targetUri);
		return proxy.post(params);
	}

	public HttpResponse httpPost(String targetUri) throws ClientProtocolException, IOException {
		ProxyRender proxy = new ProxyRender(null, targetUri);
		return proxy.httpPost(params);
	}
}
