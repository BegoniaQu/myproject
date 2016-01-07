package com.pro.securitymanager;

import java.io.FileReader;
import java.security.ProtectionDomain;

public class Policy {

	public static void main(String[] args) {
		Policy policy = new Policy();
		ProtectionDomain domain = policy.getClass().getProtectionDomain(); // 每一个类都有一个保护域，包含指定代码来源和权限集
		System.out.println(domain.getCodeSource());
		System.out.println(domain.getPermissions());

	}
}
