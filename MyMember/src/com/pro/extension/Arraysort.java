package com.pro.extension;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Arraysort {

	public static void main(String[] args) {

		// hashmap
		Map<String, String> map = new HashMap<String, String>();
		map.put("a3", "aa");
		map.put("a2", "bb");
		map.put("b1", "cc");
		for (Iterator<String> iterator = map.values().iterator(); iterator
				.hasNext();) {
			String name = iterator.next();
			System.out.println(name);
		}
		System.out.println("--------------");
		// linkedhashmap 保存了插入顺序
		Map<String, String> linkmap = new LinkedHashMap<String, String>();
		linkmap.put("a3", "aa");
		linkmap.put("a4", "bb");
		linkmap.put("b1", "cc");

		for (Iterator<String> iterator = linkmap.values().iterator(); iterator
				.hasNext();) {
			String name = iterator.next();
			System.out.println(name);
		}

		System.out.println("--------------");
		// treemap Ĭ�϶Լ������˳��Ϊ����
		Map<String, String> treeMap = new TreeMap<String, String>(
				new Comparator<String>() {
					Collator collator = Collator.getInstance();

					public int compare(String element1, String element2) {
						CollationKey key1 = collator.getCollationKey(element1
								.toString());
						CollationKey key2 = collator.getCollationKey(element2
								.toString());
						return -key1.compareTo(key2);
					}

				});
		treeMap.put("Evan", "aa");
		treeMap.put("Rose", "bb");
		treeMap.put("Magic", "cc");

		Collection<String> co = treeMap.values();
		Iterator<String> iterator = co.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

	}

}
