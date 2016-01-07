package com.pro.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadEchoHandle implements Runnable {

	private Socket incoming;
	private boolean done;

	ThreadEchoHandle(Socket incoming) {
		this.incoming = incoming;
	}

	public void run() {
		System.out.println(incoming.getInetAddress().getHostAddress() + ": "
				+ incoming.getPort());
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					incoming.getInputStream()));
			// 其实我们发现用printwriter就不用关心那些换行和回到头部等东西了
			// PrintWriter pw=new PrintWriter(incoming.getOutputStream(),true);
			// pw.println("Hello!Enter BYE to exit.");
			OutputStream os = incoming.getOutputStream();
			os.write("Hello!Enter BYE to exit.\r\n".getBytes());
			while (!done) {
				String line = br.readLine(); // 没有消息过来时堵塞。
				System.out.println("message comes");
				if (line == null) {
					done = true;
				} else {
					// pw.println("Echo: "+line);
					String out = "Echo: " + line + "\r\n";
					os.write(out.getBytes());
					os.flush();
					if (line.trim().equals("BYE")) {
						done = true;
					}
				}
			}
			incoming.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
