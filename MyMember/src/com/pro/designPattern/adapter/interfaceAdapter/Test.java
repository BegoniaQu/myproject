package com.pro.designPattern.adapter.interfaceAdapter;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Targetable source1 = new SourceSub1();
		Targetable source2 = new SourceSub2();

		source1.method1();
		// source1.method2();

		// source2.method1();
		source2.method2();

	}

}
