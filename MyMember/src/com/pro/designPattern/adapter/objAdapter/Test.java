package com.pro.designPattern.adapter.objAdapter;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Targetable wrapper = new Wrapper(new Source());
		wrapper.method1();
		wrapper.method2();
	}

}
