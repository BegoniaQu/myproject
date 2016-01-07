package com.pro.extension;

/**
 *�ַ��������ʱ�������У�������ʱ�������ڷ�������
 * 
 */
import static com.pro.extension.Constants.NAME; //ע�⿴����Ŷ������һ���÷�Ŷ��Constant Interface Anti-pattern���ӿڳ�����ģʽ

public class AnalyzeString {

	/*
	 * ʹ��intern����ʱ���ȴ��ַ���в����Ƿ���������ȵģ�equals����������ֱ������
	 */
	public boolean compare1() {
		String str = "aa";
		return "aa".intern() == str;
	}

	public boolean compare2() {
		String str = "aa"; // �ַ����
		String one = new String("aa"); // ���ַ����
		return one == str;
	}

	public boolean compare3() {
		// ����룬Ϊʲô������أ���Ϊ����ʱthree����str������û�취ȷ��������û�취��������ַ��
		// ����ֻ�е�ִ����һ��ʱ��three��̬�ڶ��ڴ��еķ��ַ����
		String str = "aa";
		String two = "aacc";
		String three = str + "cc";
		return two == three;
	}

	public boolean compare3_1() {
		final String str = "aa";
		String two = "aacc";
		String three = str + "cc"; // str�ǳ������ʱ���������ȷ��threeֵ����������ַ��
		return two == three;
	}

	public boolean compare4() {
		String two = "aacc";
		String one = "aa" + "cc"; // ����ʱ����ȷ���������ַ�أ���ִ��ʱ���䵽���ڴ��ַ����
		return two == one;
	}

	public void compare5() {
		final String one = "aa";
		final String two = "bb";
		String three = one + two; // ע��one��two�ǳ�������three�ڻ����ַ����allocate
	}

	public void validate() {

		String one = "aa" + "cc"; // ע�⣺���ڱ������Ĵ��?�˴�ֻ������һ�����󣬲�Ҫ���Ϊ���

	}

	public static void main(String[] args) {
		// AnalyzeString as=new AnalyzeString();
		// System.out.println(as.compare1());
		// System.out.println(as.compare2());
		// System.out.println(as.compare3());
		// System.out.println(as.compare3_1());
		// System.out.println(as.compare4());

		System.out.println(NAME);
	}

}
