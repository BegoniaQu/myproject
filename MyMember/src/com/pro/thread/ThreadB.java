package com.pro.thread;

public class ThreadB extends Thread {

	int total;

	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (this) {
			System.out.println("ThreadB is running..");

			for (int i = 0; i < 100; i++) {
				total++;
				System.out.println("total is " + total);
			}
			notify();
		}
	}
}
