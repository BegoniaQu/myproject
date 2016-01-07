package com.pro.jvm;

import java.util.ArrayList;
import java.util.List;

public class UseJconsole {

	static class OOMObject {
		public byte[] placeholder = new byte[64 * 1024];
	}

	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<OOMObject>();
		for (int i = 0; i < num; i++) {
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		// System.gc();//GC在此处回收时，由于fillHeap方法并没有结束，list还在作用域内，故不会被回收
	}

	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);
		System.gc();
	}
}
