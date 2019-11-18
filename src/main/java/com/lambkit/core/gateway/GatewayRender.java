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

import com.jfinal.core.JFinal;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.render.Render;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;

/**
 * http反向代理服务
 * public void proxy_hello() { 
 *     render(new GatewayRender("proxy_hello", "http://127.0.0.1:9090/hello")); 
 * } 
 * 通过render返回代理服务的结果
 */
public class GatewayRender extends Render {
	static Log log = Log.getLog(GatewayRender.class);

	protected static final String TYPE_PROXY = "proxy"; 
	protected static final String TYPE_RESPONSE = "response"; 
	private String renderType = TYPE_PROXY;
	protected String targetName;
	protected String targetUri;
	//private Gateway gateway;

	public GatewayRender(String targetName, String targetUri) {
		// TODO Auto-generated constructor stub
		this.targetName = targetName;
		setTargetUri(targetUri);		
		//gateway = new Gateway();
	}
	
	public static GatewayRender by(String targetName, String targetUri) {
		return new GatewayRender(targetName, targetUri);
	}

	private void setTargetUri(String uri) {
		targetUri = uri;
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
	

	public void resetTargetUri(HttpServletRequest servletRequest) {
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
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		if(renderType.equals(TYPE_PROXY)) {
			renderProxy();
		}
	}
	
	protected void renderProxy() {
		resetTargetUri(request);
		GatewayManager.me().getGateway().service(targetUri, request, response);
		//gateway.service(targetUri, request, response);
		//gateway.destroy();
	}
	
	public GatewayRender renderResponse(HttpResponse httpResponse) {
		resetTargetUri(request);
		GatewayManager.me().getGateway().service(targetUri, httpResponse, request, response);
		//gateway.service(targetUri, httpResponse, request, response);
		renderType = TYPE_RESPONSE;
		return this;
	}

}
