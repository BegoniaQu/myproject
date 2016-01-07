package com.pro.jvm;

import java.io.IOException;

public class UseJconsole1 {

	public static void createBusyThread() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					;
				}
			}
		}, "testBusyThread");
		thread.start();
	}

	public static void createLockThread(final Object lock) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "testLockThread");
		thread.start();
	}

	public static void testInteger() {
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i <= 127; i++) { // Integer.valueOf(i)
											// 对于i在[-128,127]之间的数字会将缓存中的对象直接返回
			Integer ir1 = Integer.valueOf(i);
			Integer ir2 = Integer.valueOf(i);
			if (ir1 == ir2) { // 对于i 在[-128,127]之间的数字，内存地址不变
				count1++;
			} else {
				count2++;
			}
		}
		System.out.println(count1 + ":" + count2);
	}

	public static void main(String[] args) throws IOException {
		// BufferedReader br=new BufferedReader(new
		// InputStreamReader(System.in));
		// br.readLine();
		// createBusyThread();
		// br.readLine();
		// Object obj=new Object();
		// createLockThread(obj);

		testInteger();

	}
}
