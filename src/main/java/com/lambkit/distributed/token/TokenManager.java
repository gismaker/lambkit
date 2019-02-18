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
package com.lambkit.distributed.token;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;

import com.jfinal.kit.StrKit;
import com.lambkit.common.base.Consts;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.distributed.node.NodeManager;
import com.lambkit.distributed.node.info.Node;
import com.lambkit.distributed.node.ManagerNodeService;

public class TokenManager {

	private static TokenManager manager = new TokenManager();

	public static TokenManager me() {
		return manager;
	}

	/**
	 * Token更新周期(注意：PERIOD_TIME 一定要大于 SLEEP_TIME )
	 */
	private static final int PERIOD_TIME = 1000 * 60 * 15;
	/**
	 * 此类中Thread.sleep()里的线程睡眠时间
	 */
	// private static final int SLEEP_TIME = 1000 * 60 * 9;

	/**
	 * 全局Token,管理节点发送过来的token
	 */
	private String globalToken = TokenBuilder.createToken();
	private int tokenVersion = 0;

	/**
	 * 定时任务
	 */
	ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
	boolean threadStarted = false;

	/**
	 * 启动globalToken定时更新任务
	 */
	public void start() {
		if (NodeManager.me().isMajorManagerNode()) {
			threadStarted = true;
			newScheduledThreadPool.scheduleAtFixedRate(new TokenTimerTask(), 10 * 1000, PERIOD_TIME,
					TimeUnit.MILLISECONDS);
		}
	}

	/**
	 * 停止globalToken定时更新任务
	 */
	public void stop() {
		if (threadStarted) {
			newScheduledThreadPool.shutdownNow();
		}
	}

	/**
	 * 生成客户端token
	 * 
	 * @param node
	 * @return
	 */
	public String getNodeToken(Node node) {
		return TokenBuilder.encodeToken(node.getId(), globalToken);
	}
	
	/**
	 * 在Request中加入客户端token信息
	 * @param request
	 */
	public void setRequestToken(HttpRequest request) {
		request.setHeader(Consts.LAMBKIT_TOKEN, getNodeToken(NodeManager.me().getNode()));
	}
	
	public String getRequestToken(HttpServletRequest servletRequest) {
		return servletRequest.getHeader(Consts.LAMBKIT_TOKEN);
	}

	/**
	 * 验证客户端发送过来的token
	 * 
	 * @param nodeToken
	 * @return
	 */
	public String getNodeId(String nodeToken) {
		String nodeid = TokenBuilder.decodeToken(nodeToken, globalToken);
		if (nodeid == null && !NodeManager.me().isManagerNode()) {
			ManagerNodeService service = RpcKit.obtain(ManagerNodeService.class);
			if (service != null) {
				String newToken = service.getToken();
				if (StrKit.notBlank(newToken)) {
					setGlobalToken(newToken);
					nodeid = TokenBuilder.decodeToken(nodeToken, globalToken);
				}
			}
		}
		return nodeid;
	}

	public int getTokenVersion() {
		return tokenVersion;
	}

	public void setTokenVersion(int tokenVersion) {
		this.tokenVersion = tokenVersion;
	}

	public void tokenVersionRefresh() {
		this.tokenVersion++;
	}

	public String getGlobalToken() {
		return globalToken;
	}

	public void setGlobalToken(String globalToken) {
		this.globalToken = globalToken;
	}
}
