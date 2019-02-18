package com.lambkit.websocket.test;

import java.io.IOException;
import java.util.Timer;

import org.eclipse.jetty.websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketMsgTest implements WebSocket.OnTextMessage {
	 
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Connection connection;
    
    private Timer timer = new Timer();

    @Override
    public void onMessage(String data) {
        if (logger.isDebugEnabled()) {
            logger.debug("onMessage");
        }
    }

    @Override
    public void onOpen(Connection connection) {
        if (logger.isDebugEnabled()) {
            logger.debug("onOpen");
        }
        this.connection = connection;

        timer.schedule(new MemTask(this), 0, 500);
    }

    @Override
    public void onClose(int closeCode, String message) {
        if (logger.isDebugEnabled()) {
            logger.debug("onClose");
        }
        timer.cancel();
    }

    public void send(String msg) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("send:" + msg);
            }
            connection.sendMessage(msg);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            timer.cancel();
        }
    }
}
