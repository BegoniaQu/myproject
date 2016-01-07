package com.pro.designPattern.abstractFac;

public class MailSenderFactory implements Provider {

	@Override
	public Sender produce() {
		return new MailSender();
	}

}
