package com.pro.queue.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class Total {

	public static void main(String[] args) {
		TotalService totalService = new TotalService();
		CyclicBarrier barrier = new CyclicBarrier(5,
				new TotalTask(totalService));
		

		// 实际系统是查出所有省编码code的列表，然后循环，每个code生成一个线程。
		new BillTask(new BillService(), barrier, "北京").start();
		new BillTask(new BillService(), barrier, "上海").start();
		new BillTask(new BillService(), barrier, "广西").start();
		new BillTask(new BillService(), barrier, "四川").start();
		new BillTask(new BillService(), barrier, "黑龙江").start();
	}

}
