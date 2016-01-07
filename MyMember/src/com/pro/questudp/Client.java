package com.pro.questudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("<sms>");
		sb.append("<cd>").append("uprequest").append("</cd>");
		sb.append("<modid>").append("mi").append("</modid>");
		sb.append("<sp>").append("1065868124").append("</sp>");
		sb.append("<ms>").append("13482241021").append("</ms>");
		sb.append("<msg>").append("屈洋").append("</msg>");
		sb.append("<st>").append("2014-03-27 17:02:30").append("</st>");
		sb.append("</sms>");
		return sb.toString();
	}

	public static void main(String[] args) {
		Client client = new Client();
		String message = client.getMessage();
		byte[] bytes = message.getBytes();
		System.out.println(bytes.length);
		try {
			InetAddress sa = InetAddress.getByName("127.0.0.1");
			DatagramSocket ds = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length, sa,
					4567);
			ds.send(packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
