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

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;

import com.jfinal.kit.StrKit;

public abstract class BaseWebSocketServer implements IWebSocketMsg {
	//与某个客户端的连接会话，需要通过它来给客户端发送数据
    protected Session session;
    protected HttpSession httpSession;
    protected String userid = "";
    /**
     * 连接建立成功调用的方法
     * websocket = new WebSocket("ws://" + window.location.host + "/socket/"+userid);
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userid") String param, Session session, EndpointConfig config){
    	userid = param;
        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties()
                .get(HttpSession.class.getName());
        if(StrKit.isBlank(userid)) {
        	userid = httpSession.getId();
        }
        WebSocketService.bo.add(userid, this);     //加入set中
        WebSocketService.addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + WebSocketService.getOnlineCount()+", 当前session.id为"+userid);
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
    	if(StrKit.notBlank(userid)) {
    		WebSocketService.bo.close(userid, this, null);  ///从set中删除
        	WebSocketService.subOnlineCount();           //在线数减1    
            System.out.println("有一连接关闭！当前在线人数为" + WebSocketService.getOnlineCount());
    	}
    }
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        WebSocketService.bo.receive(userid, this, message);
        /*
        //群发消息
        for(WebSocketController item: webSocketSet){             
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }*/
    }
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }


    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
    	 if(this.session==null) {
    		 System.out.println("session is null");
    		 return;
    	 }
    	 
    	 if(!this.session.isOpen()) {
    		 System.out.println("session is closed");
    		 return;
    	 }
    	 
    	 System.out.println("当前在线人数为" + WebSocketService.getOnlineCount());
    	 System.out.println("发送消息:" + message);
    	 System.out.println("session:" + userid);
    	
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
    
    @Override
	public boolean send(String msg) {
		// TODO Auto-generated method stub
    	try {
    		sendMessage(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
