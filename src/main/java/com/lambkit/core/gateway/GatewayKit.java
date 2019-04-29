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
package com.lambkit.core.gateway;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import com.jfinal.core.Controller;

/**
 * 网关工具
 * @author 孤竹行
 */
public class GatewayKit {

	public static GatewayRender render(String targetName, String targetUri) {
		return new GatewayRender(targetName, targetUri);
	}
	
	public static void render(Controller c, String targetName, String targetUri) {
		c.render(new GatewayRender(targetName, targetUri));
	}
	
	public static String get(String targetName, String targetUri, Map<String, String> params) {
		GatewayRender proxy = new GatewayRender(targetName, targetUri);
		return proxy.get(params);
    }
	
	public static String get(String targetName, String targetUri, List<NameValuePair> params) {
		GatewayRender proxy = new GatewayRender(targetName, targetUri);
		return proxy.get(params);
    }

	public static HttpResponse httpGet(String targetName, String targetUri, Map<String, String> params) {
		GatewayRender proxy = new GatewayRender(targetName, targetUri);
		try {
			return proxy.httpGet(params);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HttpResponse httpGet(String targetName, String targetUri, List<NameValuePair> params) {
		GatewayRender proxy = new GatewayRender(targetName, targetUri);
		try {
			return proxy.httpGet(params);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String post(String targetName, String targetUri, Map<String, String> params) {
		GatewayRender proxy = new GatewayRender(targetName, targetUri);
		return proxy.post(params);
	}
	
	public static String post(String targetName, String targetUri, List<NameValuePair> params) {
		GatewayRender proxy = new GatewayRender(targetName, targetUri);
		return proxy.post(params);
	}
	
	public static HttpResponse httpPost(String targetName, String targetUri, Map<String, String> params) {
		GatewayRender proxy = new GatewayRender(targetName, targetUri);
		try {
			return proxy.httpPost(params);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HttpResponse httpPost(String targetName, String targetUri, List<NameValuePair> params) {
		GatewayRender proxy = new GatewayRender(targetName, targetUri);
		try {
			return proxy.httpPost(params);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String accessStr(String targetName, String targetUri, HttpServletRequest servletRequest) {
		return accessStr(targetName, targetUri, servletRequest, null);
	}

	public static String accessStr(String targetName, String targetUri, HttpServletRequest servletRequest, Map<String, String> params) {
		GatewayRender proxy = new GatewayRender(targetName, targetUri);
		return proxy.accessStr(servletRequest, params);
	}
	
	/**
	 * 在现有的代理中，加入自己的参数，再转发
	 * @param servletRequest
	 * @return
	 */
	public static HttpResponse access(String targetName, String targetUri, HttpServletRequest servletRequest) {
		return access(targetName, targetUri, servletRequest, null);
	}

	/**
	 * 在现有的代理中，加入自己的参数，再转发
	 * @param servletRequest
	 * @param params
	 * @return
	 */
	public static HttpResponse access(String targetName, String targetUri, HttpServletRequest servletRequest, Map<String, String> params) {
		GatewayRender proxy = new GatewayRender(targetName, targetUri);
		return proxy.access(servletRequest, params);
	}
}
