package com.lambkit.core.hearbeat;

public class CronTask implements Runnable {

	HeartBeat heartBeat;
	
	public CronTask(HeartBeat heartBeat) {
		this.heartBeat = heartBeat;
	}
	
	@Override
	public void run() {
		if(heartBeat!=null) {
			heartBeat.execute();
		}
	}

}
