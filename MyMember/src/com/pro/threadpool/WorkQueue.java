package com.pro.threadpool;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkQueue {

	// һ��ģ��ʵ�֣�����ԭ��������ģ�����Ĺ�������õ���blockingQueue
	private final int nThreads;
	private final PoolWorker[] threads;
	private final LinkedList<Runnable> queue;

	public WorkQueue(int nThreads) {

		this.nThreads = nThreads;
		queue = new LinkedList<Runnable>();
		threads = new PoolWorker[this.nThreads];
		for (int i = 0; i < nThreads; i++) {
			threads[i] = new PoolWorker();
			threads[i].start();
		}
	}

	public void execute(Runnable r) {
		synchronized (queue) {
			queue.addLast(r);
			queue.notify();
		}
	}

	private class PoolWorker extends Thread {
		public void run() {
			Runnable r;
			while (true) {
				synchronized (queue) {
					while (queue.isEmpty()) {
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					r = queue.removeFirst();
				}
				r.run();
			}
		}

	}

	public static void main(String[] args) {
		WorkQueue queue = new WorkQueue(3);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		queue.execute(new Task());
	}
}
