package com.pro.concurrent;

public class TestTwo extends Thread {

	public void run() {
		int count = 0;
		while (count < 10) {
			TestOne.list.remove(count++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(1 | 2 | 4);
	}
}
