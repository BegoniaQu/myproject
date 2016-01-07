package com.pro.jvm;

public class StudyJVM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// testPretenureSizeThreshold(); //-verbose:gc -Xms20M -Xmx20M -Xmn10M
		// -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
		// -XX:+PrintGCDetails
		// testTenuringThreshold(); // -verbose:gc -Xms20M -Xmx20M -Xmn10M
		// -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintGCDetails
		testHandlePromotion(); // -verbose:gc -Xms20M -Xmx20M -Xmn10M
								// -XX:SurvivorRatio=8
								// -XX:-HandlePromotionFailure
								// -XX:+PrintGCDetails
	}

	private static final int _1MB = 1024 * 1024;

	public static void testPretenureSizeThreshold() {
		byte[] allocation;
		allocation = new byte[4 * _1MB];

	}

	public static void testTenuringThreshold() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[_1MB / 4];
		allocation2 = new byte[_1MB / 4];
		allocation3 = new byte[4 * _1MB];
		allocation4 = new byte[4 * _1MB]; // minorGC
		allocation4 = null;
		allocation4 = new byte[4 * _1MB];

	}

	public static void testHandlePromotion() {
		byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];

		allocation1 = null;

		allocation4 = new byte[2 * _1MB];
		allocation5 = new byte[2 * _1MB];
		allocation6 = new byte[2 * _1MB];

		allocation4 = null;
		allocation5 = null;
		allocation6 = null;

		allocation7 = new byte[2 * _1MB];
	}
}
