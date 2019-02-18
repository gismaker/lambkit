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

import org.apache.mina.util.CopyOnWriteMap;

public class WebSocketService {

	public static final WebSocketService bo = new WebSocketService();
	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    //private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();
	private final CopyOnWriteMap<String, IWebSocketMsg> members = new CopyOnWriteMap<String, IWebSocketMsg>(100);
	
	private WebSocketService() {
	}
	
	public void add(String sessionid, IWebSocketMsg wsm) {
		members.put(sessionid, wsm);
	}
	
	public void close(String sessionid, IWebSocketMsg wsm, String msg) {
		members.remove(sessionid);
		//members.remove(wsm);
	}
	
	public void receive(String sessionid, IWebSocketMsg wsm, String data) {
		wsm.receive(data);
	}
	
	public void send(String msg) {
		for (IWebSocketMsg wsm : members.values()) {
			wsm.send(msg);
		}
	}
	
	public void send(String sessionid, String msg) {
		IWebSocketMsg wsm = members.get(sessionid);
		if(wsm!=null) wsm.send(msg);
		else System.out.println("当前session.id无连接！");
	}
	
	public static synchronized int getOnlineCount() {
        return bo.members.size();//onlineCount;
    }

    public static synchronized void addOnlineCount() {
    	WebSocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
    	WebSocketService.onlineCount--;
    }
}
