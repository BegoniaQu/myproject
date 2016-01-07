package com.pro.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SynReference {

	private AtomicReference<Customer> atomicR = new AtomicReference<Customer>(
			new Customer(0));

	public static void main(String[] args) {
		final SynReference sr = new SynReference();
		List<Thread> ts = new ArrayList<Thread>();

		for (int i = 0; i < 20; i++) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < 10; i++) {
						sr.changeAge();
					}
				}
			});
			ts.add(t);
		}

		for (Thread t : ts) {
			t.start();
		}
		for (Thread t : ts) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sr.atomicR.get().getCount());
	}

	public void changeAge() {
		Customer cus = atomicR.get();
		for (;;) {
			if (atomicR.compareAndSet(cus, new Customer(cus.getCount() + 1))) {
				break;
			}
		}
	}
}
