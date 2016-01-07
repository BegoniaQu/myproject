package com.pro.queue.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;


public class Consumer implements Runnable{

	
	private Exchanger<Integer> exchanger;
	
	private volatile boolean isStopFlag;
	
	public Consumer(Exchanger<Integer> e){
		this.exchanger = e;
	}
	
	
	@Override
	public void run() {
		
		while(!isStopFlag){
			Integer data = 0;
			try {
				TimeUnit.SECONDS.sleep(2);
				Integer result = exchanger.exchange(data);
				System.out.println("consumer get the exchanger data:"+result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	 
	public void close(Thread consThread){
		isStopFlag = true;
		consThread.interrupt();
	}
}
