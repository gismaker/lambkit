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
package com.lambkit.web.websocket.jetty;

import java.io.IOException;

import org.eclipse.jetty.websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lambkit.web.websocket.IWebSocketMsg;
import com.lambkit.web.websocket.WebSocketService;

public class WebSocketJettyMsg implements WebSocket.OnTextMessage, IWebSocketMsg {
	 
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String sessionId;
    private Connection connection;
    
    public WebSocketJettyMsg(String sessionid) {
		// TODO Auto-generated constructor stub
    	setSessionId(sessionid);
	}

    @Override
    public void onMessage(String data) {
        if (logger.isDebugEnabled()) {
            logger.debug("onMessage");
        }
        WebSocketService.bo.receive(sessionId, this, data);
    }

    @Override
    public void onOpen(Connection connection) {
        if (logger.isDebugEnabled()) {
            logger.debug("onOpen");
        }
        this.connection = connection;

        WebSocketService.bo.add(sessionId, this);
    }

    @Override
    public void onClose(int closeCode, String message) {
        if (logger.isDebugEnabled()) {
            logger.debug("onClose");
        }
        WebSocketService.bo.close(sessionId, this, message);
    }

    @Override
    public boolean send(String msg) {
    	try {
            if (logger.isDebugEnabled()) {
                logger.debug("send:" + msg);
            }
            connection.sendMessage(msg);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
    
    @Override
	public boolean receive(String msg) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
