package com.pro.designPattern.prototype;

import java.io.IOException;

public class Test {

	/**
	 * @param args
	 * @throws CloneNotSupportedException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws CloneNotSupportedException,
			ClassNotFoundException, IOException {

		Prototype pro = new Prototype();

		pro.setAge(10);
		pro.setName("Andrew");

		Prototype myClone = (Prototype) pro.clone();
		myClone.setName("Martz");
		System.out.println(myClone.getName());
		// //System.out.println(myClone.getAge());
		System.out.println(pro.getName());

		Prototype myDeepClone = (Prototype) pro.deepClone();
		System.out.println(myDeepClone.getName());
		System.out.println(myDeepClone.getAge());
	}

}
