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

import java.util.List;

import com.jfinal.log.Log;
import com.lambkit.core.hearbeat.HeartBeatFrequencyMonitorLog;

/**
 * 每一次 监控的 监控日志生成操作类
 * 
 * @author 孤竹行
 *
 */
public class FrequencyMonitorLogGenerator {
	static Log log = Log.getLog(FrequencyMonitorLogGenerator.class);

	private UrlApiModel api;

	public FrequencyMonitorLogGenerator(UrlApiModel api) {
		this.api = api;
	}

	/**
	 * 生成监控日志. 先通过HttpClient 发送请求 并根据响应情况 记录日志
	 *
	 * @return FrequencyMonitorLog api
	 */
	public HeartBeatFrequencyMonitorLog generate() {
		HttpClientHandler httpClientHandler = createHttpClientHandler();
		log.debug("Send Request to URL: " + monitorUrl() + " use HttpClientHandler: " + httpClientHandler);

		final HeartBeatFrequencyMonitorLog monitorLog = httpClientHandler.handleAndGenerateFrequencyMonitorLog();
		monitorLog.setTarget(api);
		return monitorLog;
	}

	/**
	 * 创建 HttpClientHandler, 分GET, POST两类 请求
	 *
	 * @return HttpClientHandler
	 */
	private HttpClientHandler createHttpClientHandler() {
		HttpClientHandler clientHandler = api.requestMethod().isPost() ? new HttpClientPostHandler(monitorUrl())
				: new HttpClientHandler(monitorUrl());

		final List<URLParameter> urlParameters = api.getApiURL().getUrlParameters();
		for (URLParameter param : urlParameters) {
			clientHandler.addRequestParam(param.getKey(), param.getValue());
		}

		return clientHandler.maxConnectionSeconds(maxConnectionSeconds()).contentType(api.getApiURL().getContentType());
	}

	private int maxConnectionSeconds() {
		return api.getMaxConnectionSeconds();
	}

	private String monitorUrl() {
		return api.monitorUrl();
	}
}
