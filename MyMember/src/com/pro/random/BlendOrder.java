package com.pro.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BlendOrder {

	public static void main(String[] args) {

		List<String> TitleList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			TitleList.add("1" + i);
		}

		// 打乱TitleList存放顺序
		long current = System.currentTimeMillis();
		List<String> orderList = new BlendOrder().blendOrder(TitleList);
		for (String str : orderList) {
			System.out.println(str);
		}
		long later = System.currentTimeMillis();
		// System.out.println(later-current);
	}

	public List<String> blendOrder(List<String> list) {
		int length = list.size();
		int slice = 0;
		if (length % 2 > 0) {
			slice = length / 2 + 1;
		} else {
			slice = length / 2;
		}
		System.out.println("length=" + length + ",slice=" + slice);
		Map<Integer, int[]> map = new HashMap<Integer, int[]>();
		int key = 1, i = -1;
		int[] arrone = new int[2];
		for (int index = 0; index < length; index++) {
			i++;
			arrone[i] = index;
			if ((index + 1) % 2 == 0) {
				int[] arrtemp = arrone;
				map.put(key, arrtemp);
				i = -1;
				arrone = new int[2];
				key++;
			}
			if ((index + 1) % 2 != 0 && index == length - 1) {
				arrone[i] = index;
				map.put(key, arrone);
			}
		}
		Random random = new Random();
		int num = random.nextInt(slice);
		if (num == 0 || num == 1) {
			num++;
		}
		int[] arandom = map.get((Integer) num);
		for (int n = 1; n < num; n++) {
			int x = (int) (Math.round(Math.random()));
			int y = (int) (Math.round(Math.random()));
			int temp = arandom[x];
			arandom[x] = map.get((Integer) n)[y];
			map.get((Integer) n)[y] = temp;
		}

		for (int n = slice; n > num; n--) {
			int x = (int) (Math.round(Math.random()));
			int y = (int) (Math.round(Math.random()));
			int temp = map.get((Integer) n)[x];
			map.get((Integer) n)[x] = map.get((Integer) (n - 1))[y];
			map.get((Integer) (n - 1))[y] = temp;
		}
		List<String> orderList = new ArrayList<String>();
		for (int keyone : map.keySet()) {
			int arrFi[] = map.get(keyone);
			for (int fi = 0; fi < arrFi.length; fi++) {
				orderList.add(list.get(arrFi[fi]));
			}

		}
		return orderList;
	}

}
