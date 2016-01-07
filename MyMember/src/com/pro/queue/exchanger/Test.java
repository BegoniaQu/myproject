package com.pro.queue.exchanger;

import java.util.concurrent.Exchanger;

/**
 *Exchanger 
 *类提供对外的操作是同步的；
 *用于成对出现的线程之间交换数据；
 *可以视作双向的同步队列；
 *可应用于基因算法、流水线设计等场景。
 *@author Quyang
 *
 */
public class Test {

	private static Exchanger<Integer> exchanger = new Exchanger<Integer>();
	
	public static void main(String[] args) {
		
		Producer pro = new Producer(exchanger);
		Consumer cons = new Consumer(exchanger);
		new Thread(pro).start();
		Thread consThread = new Thread(cons);
		consThread.start();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cons.close(consThread);
		
		
	}
}
