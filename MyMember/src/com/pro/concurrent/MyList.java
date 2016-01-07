package com.pro.concurrent;

import java.util.Arrays;

public class MyList {

	private volatile transient String[] elementData;

	public MyList(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: "
					+ initialCapacity);
		this.elementData = new String[0];
	}

	public MyList() {
		this(10);
	}

	protected transient int modCount = 0;

	public boolean add(String e) {
		int len = elementData.length;
		String[] newElements = Arrays.copyOf(elementData, len + 1);
		newElements[len] = e;
		setArray(newElements);
		return true;
	}

	public String get(int index) {
		return elementData[index];
	}

	public int size() {
		return elementData.length;
	}

	public String[] getElementData() {
		return elementData;
	}

	public void setArray(String[] a) {
		elementData = a;
	}
}
