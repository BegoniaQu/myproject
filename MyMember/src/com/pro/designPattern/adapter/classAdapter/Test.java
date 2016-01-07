package com.pro.designPattern.adapter.classAdapter;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Targetable adapter = new Adapter();
		adapter.method1();
		adapter.method2();
	}

}
