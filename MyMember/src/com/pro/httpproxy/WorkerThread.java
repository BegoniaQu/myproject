package com.pro.httpproxy;

public class WorkerThread extends Thread {

	public boolean busy;
	public ThreadPool owner;

	/**
	 * 构造一个新的 WorkerThread 对象，并关联到池.
	 * 
	 * @param o
	 *            描述：
	 */
	WorkerThread(ThreadPool o) {
		owner = o;
	}

	/**
	 * 描述：实现接口
	 */
	public void run() {
		Runnable target = null;
		do {
			target = owner.getAssignment();
			if (target != null) {
				target.run();
				owner.done.workerEnd();
			}
		} while (target != null);
	}

}
