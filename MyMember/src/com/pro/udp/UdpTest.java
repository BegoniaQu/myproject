package com.pro.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpTest {

	public static void main(String[] args) {
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(9095);
			ds.setSoTimeout(2000); // 如果2s 没有接收到数据，则异常
			ds.setReceiveBufferSize(32 * 1024);

			while (true) {
				byte[] buf = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				ds.receive(dp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ds.close();
		}

		System.out.println(0x0A);
	}
}
