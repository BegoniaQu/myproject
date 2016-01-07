package com.pro.designPattern.abstractFac;

/**
 * 满足了设计模式的开闭原则，具备可拓展性，不修改原有代码
 * 
 * @author quyang
 * 
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Provider provider = new SmsSenderFactory();
		Sender smsSender = provider.produce();
		smsSender.send();
	}

}
