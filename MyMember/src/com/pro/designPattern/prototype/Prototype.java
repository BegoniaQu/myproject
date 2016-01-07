package com.pro.designPattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Prototype implements Cloneable, Serializable {

	private int age;
	private String name;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6555241036340618056L;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// 浅复制基本数据类型各自独立，引用数据类型共享
		return super.clone();
	}

	protected Object deepClone() throws IOException, ClassNotFoundException {
		// 深复制属性完全各自独立，是独立的内存区域,要实现深复制，需要采用流的形式读入当前对象的二进制输入，再写出二进制数据对应的对象。
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);

		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
