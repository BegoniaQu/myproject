package com.pro.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIpaddress {

	public static void main(String[] args) {
		getIp();
	}

	// 像百度这样通信量大的主机一般都有多个网络地址，已取得装载平衡
	public static void getIp() {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println(ip);
			InetAddress[] addrs = InetAddress.getAllByName("www.baidu.com");
			for (InetAddress one : addrs) {

				System.out.println(one.getHostAddress());
			}
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
	}
}
