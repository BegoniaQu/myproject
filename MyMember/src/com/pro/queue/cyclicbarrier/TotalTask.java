package com.pro.queue.cyclicbarrier;

public class TotalTask implements Runnable {
	private TotalService totalService;

	TotalTask(TotalService totalService) {
		this.totalService = totalService;
	}

	public void run() {
		totalService.count();
		System.out.println("=======================================");
		System.out.println("开始全国汇总");

	}
}
