package com.pro.designPattern.adapter.objAdapter;

public class Wrapper implements Targetable {

	private Source source;

	public Wrapper(Source source) {
		this.source = source;
	}

	@Override
	public void method1() {
		this.source.method1();
	}

	@Override
	public void method2() {
		System.out.println("this is targetable method!");
	}

}
