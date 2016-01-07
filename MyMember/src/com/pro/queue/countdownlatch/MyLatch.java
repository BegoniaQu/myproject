package com.pro.queue.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁，同步工具类，可以延迟线程的进度直到其到达终止状态。
 * 
 * @author Administrator
 * 
 */
public class MyLatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public long timeTasks(int nthreads, final Runnable task)
			throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nthreads);

		for (int i = 0; i < nthreads; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						startGate.await();
						try {
							task.run();
						} finally {
							endGate.countDown();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		long start = System.currentTimeMillis();
		startGate.countDown();
		endGate.await();
		long end = System.currentTimeMillis();

		return end - start;
	}
}
