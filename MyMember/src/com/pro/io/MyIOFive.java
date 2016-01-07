package com.pro.io;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class MyIOFive {

	public static void main(String[] args) {
		try {
			InetAddress ia = InetAddress.getByName("www.baidu.com");
			System.out.println(ia.getHostName());
			System.out.println(ia.getHostAddress()); // IP
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		try {
			InetAddress me = InetAddress.getLocalHost();
			System.out.println(me.getHostAddress());
			byte[] address = me.getAddress();
			if (address.length == 4) {
				System.out.println("四字节");
			} else if (address.length == 16) {
				System.out.println("16字节");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface
					.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface ni = interfaces.nextElement();
				System.out.println(ni);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}
}
