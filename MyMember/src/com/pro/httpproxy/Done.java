package com.pro.httpproxy;

public class Done {

	private int activeThreads = 0;
	private boolean started = false;

	/**
	 * 描述：
	 */
	public synchronized void waitDone() {
		try {
			while (activeThreads > 0) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 描述：
	 */
	public synchronized void waitBegin() {
		try {
			while (!started) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 描述：
	 */
	public synchronized void workerBegin() {
		activeThreads++;
		started = true;
		notify();
	}

	/**
	 * 描述：
	 */
	public synchronized void workerEnd() {
		activeThreads--;
		notify();
	}

	/**
	 * 描述：
	 */
	public synchronized void reset() {
		activeThreads = 0;
	}
}
