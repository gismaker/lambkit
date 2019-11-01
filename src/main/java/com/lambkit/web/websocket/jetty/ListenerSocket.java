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
 */package com.lambkit.web.websocket.jetty;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

import com.lambkit.web.websocket.IWebSocketMsg;
import com.lambkit.web.websocket.WebSocketService;

public abstract class ListenerSocket implements WebSocketListener, IWebSocketMsg {

	private String sessionId;
	private Session outbound;

    @Override
    public void onWebSocketBinary(byte[] payload, int offset, int len)
    {
        /* only interested in text messages */
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        this.outbound = null;
        WebSocketService.bo.close(sessionId, this, reason);
    }

    @Override
    public void onWebSocketConnect(Session session)
    {
        this.outbound = session;
        sessionId = this.outbound.getRemoteAddress().getHostString();
        WebSocketService.bo.add(sessionId, this);
    }

    @Override
    public void onWebSocketError(Throwable cause)
    {
        cause.printStackTrace(System.err);
    }

    @Override
    public void onWebSocketText(String message)
    {
    	WebSocketService.bo.receive(sessionId, this, message);
    }

	@Override
	public boolean receive(String msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean send(String msg) {
		// TODO Auto-generated method stub
		if ((outbound != null) && (outbound.isOpen()))
        {
            System.out.printf("Echoing back message [%s]%n",msg);
            // echo the message back
            outbound.getRemote().sendString(msg,null);
        }
		return true;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
