package com.pro.securitymanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CaesarEncrypt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("USAGE: java Caesar in out key");
			return;
		}
		try {
			FileInputStream fis = new FileInputStream(args[0]);
			FileOutputStream fos = new FileOutputStream(args[1]);
			int key = Integer.parseInt(args[2]);
			int ch;
			while ((ch = fis.read()) != -1) {
				byte c = (byte) (ch + key);
				fos.write(c);
			}
			fos.flush();
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
