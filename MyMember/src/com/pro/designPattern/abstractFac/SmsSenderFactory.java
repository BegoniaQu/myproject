package com.pro.designPattern.abstractFac;

public class SmsSenderFactory implements Provider {

	@Override
	public Sender produce() {
		return new SmsSender();
	}

}
