package com.pro.reflection.one;

import java.util.List;

public class MyClass {

	private List<String> list;
	public int count;

	public MyClass(int start) {
		count = start;
	}

	public void increase(int step) {
		count = count + step;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

}
