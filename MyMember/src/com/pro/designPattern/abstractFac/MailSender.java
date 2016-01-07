package com.pro.designPattern.abstractFac;

public class MailSender implements Sender {

	@Override
	public void send() {
		System.out.println("send a mail");
	}

}
