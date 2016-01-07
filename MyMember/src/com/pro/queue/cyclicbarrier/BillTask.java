package com.pro.queue.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class BillTask extends Thread {
	// 计费服务
	private BillService billService;
	private CyclicBarrier barrier;
	// 代码，按省代码分类，各省数据库独立。
	private String code;

	BillTask(BillService billService, CyclicBarrier barrier, String code) {
		this.billService = billService;
		this.barrier = barrier;
		this.code = code;
	}

	public void run() {
		System.out.println("开始计算--" + code + "省--数据！");
		billService.bill(code);
		// 把bill方法结果存入内存，如ConcurrentHashMap,vector等,代码略
		System.out.println(code + "省已经计算完成,并通知汇总Service！");
		try {
			// 通知barrier已经完成
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
