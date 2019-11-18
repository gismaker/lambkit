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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.kit.StrKit;

/**
 * 反向代理服务
 * @author yangyong
 */
public class GatewayService {

	public static GatewayService by(HttpServletRequest request) {
		return new GatewayService(request);
	}
	
	public static GatewayService by(Controller controller) {
		return new GatewayService(controller);
	}
	
	public GatewayService(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
		this.servletRequest = request;
	}
	
	public GatewayService(Controller controller) {
		// TODO Auto-generated constructor stub
		this.servletRequest = controller.getRequest();
	}

	private Map<String, String> mapParams = null;
	private HttpServletRequest servletRequest;

	public GatewayService set(String name, String value) {
		if(mapParams==null) {
			mapParams = new HashMap<String, String>();
		}
		mapParams.put(name, value);
		return this;
	}

	private String getContxtPath() {
		String cp = JFinal.me().getContextPath();
		return ("".equals(cp) || "/".equals(cp)) ? null : cp;
	}

	private String getServerUrl(HttpServletRequest servletRequest) {
		/*
		String serverUrl = servletRequest.getServerName() + ":" + servletRequest.getServerPort();
		if (servletRequest.getScheme().equals("https")) {
			serverUrl = "https://" + serverUrl;
		} else if (servletRequest.getScheme().equals("http")) {
			serverUrl = "http://" + serverUrl;
		}
		return serverUrl;
		*/
		StringBuffer url = servletRequest.getRequestURL();
		String serverUrl = url.toString();
		serverUrl = serverUrl.replace(servletRequest.getRequestURI(), "");
		return serverUrl;
	}
	

	private String processTargetUri(String targetName, String targetUri) {
		if(targetUri.startsWith("//")) {
			targetUri = targetUri.substring(1);
		}
		if(StrKit.isBlank(targetUri)) {
			targetUri = "/";
		}
		String contextPath = getContxtPath();
		// 如果一个url为/login/connect?goto=http://www.jfinal.com，则有错误
		// ^((https|http|ftp|rtsp|mms)?://)$   ==> indexOf 取值为 (3, 5)
		if (contextPath != null && (targetUri.indexOf("://") == -1 || targetUri.indexOf("://") > 5)) {
			targetUri = contextPath + targetUri;
		}
		if (StrKit.notBlank(targetName) && StrKit.notBlank(targetUri)) {
			// JFinal特有的链接方式处理
			String uri = servletRequest.getRequestURI();
			int tid = uri.indexOf(targetName);
			if (tid > -1) {
				String tu = uri.substring(tid + targetName.length());
				if (targetUri.endsWith("/")) {
					targetUri += tu.substring(1);
				} else {
					targetUri += tu;
				}
			}
			
			// 支持 https 协议下的重定向
			if (!targetUri.startsWith("http")) {	// 跳过 http/https 已指定过协议类型的 url
				String serverUrl = getServerUrl(servletRequest);
				//System.out.println("proxy serverUrl: " + serverUrl);
				if (targetUri.charAt(0) != '/') {
					targetUri = serverUrl + "/" + targetUri;
				} else {
					targetUri = serverUrl + targetUri;
				}
			}
		}
		return targetUri;
	}

	public String get(String targetName, String targetUri) {
		return GatewayManager.me().getGateway().get(processTargetUri(targetName, targetUri), mapParams);
	}

	public HttpResponse httpGet(String targetName, String targetUri) throws ClientProtocolException, IOException {
		return GatewayManager.me().getGateway().httpGet(processTargetUri(targetName, targetUri), mapParams);
	}

	public String post(String targetName, String targetUri) {
		return GatewayManager.me().getGateway().post(processTargetUri(targetName, targetUri), mapParams);
	}

	public HttpResponse httpPost(String targetName, String targetUri) throws ClientProtocolException, IOException {
		return GatewayManager.me().getGateway().httpPost(processTargetUri(targetName, targetUri), mapParams);
	}
	
	///////////////////////////////////////////////////////////////
	
	public String get(String targetName, String targetUri, Map<String, String> params) {
		return GatewayManager.me().getGateway().get(processTargetUri(targetName, targetUri), params);
    }
	
	public String get(String targetName, String targetUri, List<NameValuePair> params) {
		return GatewayManager.me().getGateway().get(processTargetUri(targetName, targetUri), params);
    }

	public HttpResponse httpGet(String targetName, String targetUri, Map<String, String> params) {
		try {
			return GatewayManager.me().getGateway().httpGet(processTargetUri(targetName, targetUri), params);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HttpResponse httpGet(String targetName, String targetUri, List<NameValuePair> params) {
		try {
			return GatewayManager.me().getGateway().httpGet(processTargetUri(targetName, targetUri), params);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String post(String targetName, String targetUri, Map<String, String> params) {
		return GatewayManager.me().getGateway().post(processTargetUri(targetName, targetUri), params);
	}
	
	public String post(String targetName, String targetUri, List<NameValuePair> params) {
		return GatewayManager.me().getGateway().post(processTargetUri(targetName, targetUri), params);
	}
	
	public HttpResponse httpPost(String targetName, String targetUri, Map<String, String> params) {
		try {
			return GatewayManager.me().getGateway().httpPost(processTargetUri(targetName, targetUri), params);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HttpResponse httpPost(String targetName, String targetUri, List<NameValuePair> params) {
		try {
			return GatewayManager.me().getGateway().httpPost(processTargetUri(targetName, targetUri), params);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String accessStr(String targetName, String targetUri) {
		return accessStr(targetName, targetUri, null);
	}

	public String accessStr(String targetName, String targetUri, Map<String, String> params) {
		return GatewayManager.me().getGateway().accessStr(processTargetUri(targetName, targetUri), servletRequest, params);
	}
	
	/**
	 * 在现有的代理中，加入自己的参数，再转发
	 * @param servletRequest
	 * @return
	 */
	public HttpResponse access(String targetName, String targetUri) {
		return access(targetName, targetUri, null);
	}

	/**
	 * 在现有的代理中，加入自己的参数，再转发
	 * @param servletRequest
	 * @param params
	 * @return
	 */
	public HttpResponse access(String targetName, String targetUri, Map<String, String> params) {
		return GatewayManager.me().getGateway().access(processTargetUri(targetName, targetUri), servletRequest, params);
	}
}
