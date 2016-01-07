package com.pro.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestOne {

	public static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

	// public static List<String> list=new ArrayList<String>();
	// public static MyList list=new MyList();
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			list.add("11111" + i);
		}
		TestTwo two = new TestTwo();
		two.start();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (String str : list) {
			System.out.println("hashcode:" + list.hashCode());
			System.out.println(str);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
