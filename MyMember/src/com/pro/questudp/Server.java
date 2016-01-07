package com.pro.questudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(4567);
			byte[] bytes = new byte[1024];
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
			while (true) {
				socket.receive(packet);
				byte[] byts = packet.getData();
				System.out.println(packet.getOffset() + "|"
						+ packet.getLength());
				String message = new String(byts, packet.getOffset(),
						packet.getLength());
				System.out.println(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
