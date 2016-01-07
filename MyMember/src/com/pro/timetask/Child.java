package com.pro.timetask;

public class Child extends Father {

	public Child(String str) {
		setInputType(str);
	}

	public void print() {
		System.out.println(inputType);
	}

	public static void main(String[] args) {
		Child c1 = new Child("qqq");
		Child c2 = new Child("aaa");
		c2.print();
		c1.print();
	}
}
