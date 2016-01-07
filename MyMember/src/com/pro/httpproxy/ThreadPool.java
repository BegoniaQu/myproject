package com.pro.httpproxy;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

	protected Thread[] threads = null;
	private List<Runnable> assignments = new ArrayList<Runnable>(3); // 初始为3个元素，动态增减。
	protected Done done = new Done();
	private static ThreadPool pool = null;

	// 连接池配置-应从配置文件中读取
	private static int threadSize = 10;

	private ThreadPool(int size) {
		threads = new WorkerThread[size];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new WorkerThread(this);
			threads[i].start();
		}
	}

	// 得到线程池实例
	public static ThreadPool getInstance() {
		if (pool == null) {
			pool = new ThreadPool(threadSize);
		}
		return pool;
	}

	// 分配线程
	public synchronized void assign(Runnable r) {
		done.workerBegin();
		assignments.add(r);
		notify();
	}

	public synchronized Runnable getAssignment() {
		try {
			while (!assignments.iterator().hasNext()) {
				wait();
			}
			Runnable r = (Runnable) assignments.iterator().next();
			assignments.remove(r);
			return r;
		} catch (InterruptedException e) {
			done.workerEnd();
			return null;
		}
	}

	public void complete() {
		done.waitBegin();
		done.waitDone();
	}

	protected void finalize() {
		done.reset();
		for (int i = 0; i < threads.length; i++) {
			threads[i].interrupt();
			done.workerBegin();
			// threads[i].destroy();
		}
		done.waitDone();
	}

}
