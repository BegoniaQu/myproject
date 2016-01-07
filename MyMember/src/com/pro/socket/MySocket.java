package com.pro.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocket {

	public static void main(String[] args) {

		// Socket
		// s=SocketOpener.openSocket("time-A.timefreq.bldrdoc.gov",13,10);
		// if(s==null){
		// System.out.println("socket create failed");
		// System.exit(0); //可以终止当前进程
		// }else{
		// System.out.println("ok");
		// }

		MySocket mySocket = new MySocket();
		Socket s = null;
		int count = 0, index = 0;
		byte[] b = new byte[512];
		try {
			s = new Socket("192.168.1.20", 8080);
			String str = "shifenke_apiserver/VIDEOS/play.jsp";
			// s.shutdownInput();
			// s.shutdownOutput();
			OutputStream os = s.getOutputStream();

			os.write(mySocket.getbytes(str));
			InputStream is = s.getInputStream();
			while ((count = is.read()) != -1) {
				System.out.println("client :" + (char) count);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public byte[] getbytes(String str) {
		return str.getBytes();
	}
}
