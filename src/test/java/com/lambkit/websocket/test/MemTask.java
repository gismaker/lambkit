package com.lambkit.websocket.test;

import java.util.Random;
import java.util.TimerTask;

public class MemTask extends TimerTask {

	private WebSocketMsgTest myWebSocket;
	 
    public MemTask(WebSocketMsgTest webSocketMsg) {
        this.myWebSocket = webSocketMsg;
    }
 
    @Override
    public void run() {
        myWebSocket.send("" +new Random().nextInt(100));
 
    }

}
