package com.pro.operators;

public class Operators {

	/*
	 * ~位非，&位与，|位或，^异或，>>右移，>>>右移[左边空出的位以零填充] &=按位于赋值，|=按位或赋值，^=按位异或赋值，>>=右移赋值
	 * 对于&：两个都为1时才为1，否则都为0 对于|,有一个为1即为1
	 */
	public static void main(String[] args) {

		String[] binary = { "0000", "0001", "0010", "0011", "0100", "0101",
				"0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101",
				"1110", "1111" };

		char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		int a = 3;
		int b = 6;
		int c = a | b;
		int d = a & b;
		int e = a ^ b;
		int f = (~a & b) | (a & ~b);
		int g = ~a & 0x0f; // 0x0f 是十六进制数：15
		System.out.println("a=" + a + ":" + binary[a]);
		System.out.println("b=" + b + ":" + binary[b]);
		System.out.println("a | b=c=" + c + ":" + binary[c]);
		System.out.println("a & b=d=" + d + ":" + binary[d]);
		System.out.println("a ^ b=e=" + e + ":" + binary[e]);

		System.out.println("~m= " + ~21);
		System.out.println((7 & 19));
		System.out.println((-7 & 19));
		// 对于&的运算，得到的值比任何一个相运算的值都小
		// 对于|的运算，得到的值在两个相运算的值之间

		byte m = 64;
		int i = m << 2;
		int xx = m << 2;
		System.out.println(xx);

		System.out.println("binary: " + Integer.toBinaryString(i)); // 二进制
		System.out.println("hex: " + Integer.toHexString(241)); // 十六进制

		// 左移的规则只记住一点：丢弃最高位，0补最低位
		// 向左移动32位，而int类型4字节占用32位，移动的位数超过了该类型的最大位数，
		// 那么编译器会对移动的位数取模。如对int型移动33位，实际上只移动了33%32=1位。
		/*
		 * 按二进制形式把所有的数字向左移动对应的位数，高位移出(舍弃)，低位的空位补零。 当左移的运算数是int
		 * 类型时，每移动1位它的第31位就要被移出并且丢弃; 当左移的运算数是long类型时，每移动1位它的第63位就要被移出并且丢弃。
		 * 当左移的运算数是byte和short类型时，将自动把这些类型扩大为 int 型。
		 * 
		 * 无符号右移运算符>>> 忽略了符号位扩展，0补最高位。只是对32位和64位的值有意义
		 */
		System.out.println(64 << 33);

		// 每一位十六进制对应四位二进制数，整数和小数部分都是一样的转换规则
		/*
		 * 0 0000 0 1 0001 1 2 0010 2 3 0011 3 4 0100 4 5 0101 5 6 0110 6 7 0111
		 * 7 8 1000 8 9 1001 9 A 1010 10 B 1011 11 C 1100 12 D 1101 13 E 1110 14
		 * F 1111 15
		 */

		String x = Integer.toBinaryString(10);
		System.out.println(x);
		StringBuilder sb = new StringBuilder();
		sb.append(HEXCHAR[(10 & 0xf0) >>> 4]);
		sb.append(HEXCHAR[10 & 0x0f]);
		System.out.println(sb.toString());

		System.out.println(64 >> 1);
		System.out.println(64 << 1);
	}

	// java 中八种基本类类型说明
	// byte occupy 1byte
	// short occupy 2byte
	// int occupy 4byte
	// long occupy 8byte
	// float occupy 4byte
	// double occupy 8byte
	// char occupy 2byte
	// boolean occupy 1bit

	// 在八位二进制数下，换算成十进制最小值：0，最大值：255 【11111111=255=2的8次方-1，1111=15=2的四次方-1】
	//
}
