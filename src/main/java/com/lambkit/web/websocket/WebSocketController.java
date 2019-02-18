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
package com.lambkit.web.websocket;

import javax.websocket.server.ServerEndpoint;

import com.lambkit.web.websocket.BaseWebSocketServer;

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping
//需要Tomcat8
@ServerEndpoint(value="/websocket/{userid}",configurator=GetHttpSessionConfigurator.class)
public class WebSocketController extends BaseWebSocketServer {

	@Override
	public boolean receive(String msg) {
		// TODO Auto-generated method stub
		return false;
	}
}
