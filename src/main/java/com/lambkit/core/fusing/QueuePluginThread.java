package com.lambkit.core.fusing;

import java.util.concurrent.LinkedBlockingQueue;

public class QueuePluginThread extends Thread{
	
	public static LinkedBlockingQueue<Integer> lq ;
	//单生产者，单消费者  用 LinkedBlockingqueue
	//多生产者，单消费者   用 LinkedBlockingqueue
	//单生产者 ，多消费者   用 ConcurrentLinkedQueue
	//多生产者 ，多消费者   用 ConcurrentLinkedQueue
	public int maximum;
	public long millis;
	
	/**
	 * @param maximum 请求最大值，达到就熔断
	 * @param millis 重置时间（毫秒）
	 */
	public QueuePluginThread(int maximum,long millis) {
		this.maximum = maximum;
		this.millis = millis;
		lq = new LinkedBlockingQueue<Integer>(maximum);
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(millis);
				lq.clear();//只有生产。没有消费，1秒一清空
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
