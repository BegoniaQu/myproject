package com.pro.designPattern.abstractFac;

public class SmsSender implements Sender {

	@Override
	public void send() {
		System.out.println("send a sms");
	}

}
