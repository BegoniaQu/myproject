package com.pro.thread;

public class RunnableImpl implements Runnable {
	public void run() {
		try {
			System.out.println("Begin sleep");
			Thread.sleep(12000);
			System.out.println("End sleep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
