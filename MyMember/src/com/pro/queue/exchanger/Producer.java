package com.pro.queue.exchanger;

import java.util.concurrent.Exchanger;

public class Producer implements Runnable{

	private Exchanger<Integer> exchanger;
	public Producer(Exchanger<Integer> e){
		this.exchanger = e;
	}
	@Override
	public void run() {
		for(int i=1;i<5;i++){
			try {
				Integer result = exchanger.exchange(i);
				System.out.println("-----"+result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
