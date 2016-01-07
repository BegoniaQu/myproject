package com.pro.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeSocket {

	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			// InetAddress
			// ia=InetAddress.getByName("time-A.timefreq.bldrdoc.gov");
			Socket s = new Socket("time-A.timefreq.bldrdoc.gov", 13);// 经测试发现，构造时超过一段时间就会抛出Connection
																		// timed
																		// out:
																		// connect
			if (s == null) {
				System.out.println("wow,it doesn't work");
			}
			s.shutdownInput(); // 关闭socket的输入了流，半关闭状态，但是socket的输出流还是可以用
			// s.setSoTimeout(10); //最多等待时间10s
			br = new BufferedReader(new InputStreamReader(s.getInputStream())); // 当设置了setSoTimeout后，此处指阻塞10s
			boolean more = true;
			while (more) {
				String line = br.readLine();
				if (line == null) {
					more = false;
				} else {
					System.out.println(line);
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
