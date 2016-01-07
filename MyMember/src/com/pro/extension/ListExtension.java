package com.pro.extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ListExtension {

	public static void testStub(List<String> staff) {
		List<String> sub = staff.subList(0, 2); // 获取其视图，内存地址一样
		sub.add("ddd");
		for (String one : staff) {
			System.out.println(one);
		}
	}

	public static void testArray(List<String> staff) {
		String[] strArr = new String[5];
		staff.toArray(strArr); // 个人觉得没多大用途，以strArr的长度作为参照物
		for (Object o : strArr) {
			System.out.println(o);
		}
	}

	public static void main(String[] args) {
		List<String> staff = new LinkedList<String>();
		staff.add("111");
		staff.add("222");
		staff.add("333");
		staff.add("444");

		/*
		 * Arrays.asList(arr)返回的是个List的封装器，与List对象是不同的
		 */
		List<String> uList = Collections.unmodifiableList(staff);
		String[] arr = new String[10];
		List<String> fixedList = Arrays.asList(arr);
		// fixedList.add("dd");//无法添加值

		List<String> al = new ArrayList<String>(Collections.nCopies(3,
				"default"));
		// al.add("555");
		for (String element : al) {
			// System.out.println(element);
		}
		List<List<String>> newL = Collections.nCopies(3, staff); // 有三份List，都是同一块存储空间
		// newL.add(staff);//exception s is immutable 不可改变的
		// System.out.println(newL.size());
		Set<List<String>> s = Collections.singleton(staff); // 只有一份数据
		// s.add(staff); //exception s is immutable 不可改变的
		// System.out.println(s.size());
		for (List<String> list : newL) {
			list.add("555");
			break;
		}
		for (List<String> list : newL) {
			for (String one : list) {
				// System.out.println(one);
			}
		}

		Set<String> set1 = new HashSet<String>();
		set1.add("aa");
		set1.add("bb");
		set1.add("cc");
		Set<String> set2 = new HashSet<String>();
		set2.add("aa");
		set2.add("bb");

		Set<String> newSet = new HashSet<String>(set1); // 这种其实是从新分配了一块内存
		newSet.retainAll(set2); // 获取两集合交集
		// System.out.println("交集："+newSet.size());
		// System.out.println(set1.size());

		// Collections.sort(list, new Comparator(){public int compare(oj 1,oj
		// 2){}});c可以自己设置根据什么排序 默认升序
		// Collections.sort(list, Collections.reverseOrder());
		// //Collections.reverseOrder() 递减排序

		// 洗牌后选取一部分进行排序
		List<Integer> numbers = new LinkedList<Integer>();
		for (int i = 1; i <= 10; i++) {
			numbers.add(new Integer(i));
		}
		Collections.shuffle(numbers);// 对于链表，效率不敢保证
		List<Integer> subNum = numbers.subList(0, 6);
		Collections.sort(subNum);
		System.out.println(numbers);

		// 二分法算法[arithmetic、algorithm]
		List<Integer> binAlgorithm = new LinkedList<Integer>();
		for (int i = 1; i <= 10; i++) {
			binAlgorithm.add(new Integer(i));
		}
		// index的值经过 -index-1就是该没有的元素插入的地方
		int index = Collections.binarySearch(binAlgorithm, 11);
		System.out.println(index);
		binAlgorithm.add(10, 11);
		// 说明：如果binAlgorithm是个链表，也就是继承了abstractsequentiallist类，则用线性方法查找；
		// 否则用二分法查找

		// 位数组
		BitSet bitset = new BitSet();
		bitset.set(100);
		bitset.clear(100); // 变成false
		System.out.println(bitset.get(100));// true || false
	}

	public enum Name {
		ANDREW(1), MARTZ(2);
		private Name(int i) {
			this.i = i;
		}

		private int i;
	}

}
