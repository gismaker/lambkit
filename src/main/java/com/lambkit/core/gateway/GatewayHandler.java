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

import com.jfinal.handler.Handler;
import com.jfinal.log.Log;
import com.lambkit.Lambkit;
import com.lambkit.common.exception.LambkitException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 单个接口的代理，无法整站代理
 * An HTTP reverse proxy/gateway JFinal Handler. It is designed to be extended
 * for customization
 */
public class GatewayHandler extends Handler {
	static Log log = Log.getLog(GatewayHandler.class);

	/** The parameter name for the target (destination) URI to proxy to. */
	protected static final String P_TARGET_URI = "targetUri";

	protected Gateway gateway;
	
	private static final ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	
	public GatewayHandler() {
		// TODO Auto-generated constructor stub
		gateway = new Gateway();
	}

	public GatewayHandler(GatewayConfig config) {
		// TODO Auto-generated constructor stub
		gateway = new Gateway(config);
	}
	
	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response,
			boolean[] isHandled) {
		// TODO Auto-generated method stub
		GatewayConfig config = GatewayManager.me().getConfig(target);
		if(config!=null) {
			String targetUri = config.getTargetUri();
			if (targetUri == null) {
				throw new LambkitException(P_TARGET_URI + " is required.");
			}
			String targetName = config.getName();
			String sourceUrlpattern = config.getUrlpattern();
			String turl = targetUri.endsWith("/")  ? "" : "/";
			turl = targetUri + turl;
			String surl = target.replace(sourceUrlpattern.replace("*", ""), "");
			turl += surl;
			String originTargetUri = targetUri;
			if(!targetUri.equals(turl)) {
				targetUri = turl;
			}
			if(Lambkit.isDevMode()) {
				System.out.println();
				System.out.println("Lambkit http proxy report -------- " + sdf.get().format(new Date()) + " -------------------------");
				//System.out.println("http-proxy: " + targetName + " from " + sourceUrlpattern + " to " + targetUri);
				System.out.println("name    : " + targetName);
				System.out.println("from    : " + target);
				System.out.println("pattern : " + sourceUrlpattern);
				System.out.println("to      : " + targetUri);
				System.out.println("--------------------------------------------------------------------------------");
				/*
				StringBuilder sb = new StringBuilder("\nLambkit http proxy report -------- ").append(sdf.get().format(new Date())).append(" ------------------------------\n");
				sb.append("name : ").append(targetName).append("\n");
				sb.append("from : ").append(sourceUrlpattern).append("\n");
				sb.append("to   : ").append(targetUri).append("\n");
				sb.append("--------------------------------------------------------------------------------\n");
				*/
			}
			gateway.service(targetUri, request, response);
			isHandled[0] = true;
			targetUri = originTargetUri;
		} else {
			next.handle(target, request, response, isHandled);
		}
	}
	
}
