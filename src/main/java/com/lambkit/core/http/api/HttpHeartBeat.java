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
package com.lambkit.core.http.api;

import com.jfinal.log.Log;
import com.lambkit.core.hearbeat.HeartBeat;

public class HttpHeartBeat implements HeartBeat {
	static Log log = Log.getLog(HttpHeartBeat.class);

	private String apiGuid;

	public HttpHeartBeat(String apiGuid) {
		this.setApiGuid(apiGuid);
	}

	/**
	 * Execute heart-beat
	 * <p/>
	 * 1.Send request and checking response 2.Generate FrequencyMonitorLog 3. If
	 * failed will notice
	 * <p/>
	 * <p/>
	 * 执行心跳监测的流程 1. 向指定的 URL 发送请求并检测响应情况 2. 记录每一次的监控日志(FrequencyMonitorLog)
	 * 3.若状态变更则发送提醒
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ApiModel api = null;
		final FrequencyMonitorLog monitorLog = generateMonitorLog(api);
		// instanceRepository.saveOrUpdate(monitorLog);
		log.debug("Generate and persist FrequencyMonitorLog[" + monitorLog + "]");
		// reminder
		remind(monitorLog);
	}

	@Override
	public void remind(FrequencyMonitorLog monitorLog) {
		// TODO Auto-generated method stub
		log.debug(HttpHeartBeat.class.getName() + ".remind not finish!");
	}

	/*
	 * 生成 监控日志
	 */
	private FrequencyMonitorLog generateMonitorLog(ApiModel instance) {
		FrequencyMonitorLogGenerator monitorLogGenerator = new FrequencyMonitorLogGenerator(instance);
		return monitorLogGenerator.generate();
	}

	public String getApiGuid() {
		return apiGuid;
	}

	public void setApiGuid(String apiGuid) {
		this.apiGuid = apiGuid;
	}

}
