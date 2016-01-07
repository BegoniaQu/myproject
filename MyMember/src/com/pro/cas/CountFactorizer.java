package com.pro.cas;

import java.util.concurrent.atomic.AtomicLong;

public class CountFactorizer {

	private static final AtomicLong count = new AtomicLong(0);

	public void service() {
		count.incrementAndGet();
	}

	public long getCount() {
		return count.get();
	}

	public static void main(String[] args) {
		CountFactorizer factorizer1 = new CountFactorizer();
		factorizer1.service();
		System.out.println(factorizer1.getCount());

		CountFactorizer factorizer2 = new CountFactorizer();
		factorizer2.service();
		System.out.println(factorizer2.getCount());
	}
}
