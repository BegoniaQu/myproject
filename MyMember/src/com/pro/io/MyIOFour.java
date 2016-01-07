package com.pro.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

/**
 * 
 * @author Administrator
 * 
 */
public class MyIOFour {

	public static void main(String[] args) {
		DigestInputStream din = null;
		try {
			File file = new File("D:\\secrets.txt");
			FileInputStream in = new FileInputStream(file);
			MessageDigest sha = MessageDigest.getInstance("SHA");
			din = new DigestInputStream(in, sha);
			while (din.read() != -1) {
			}
			byte[] digest = sha.digest();
			StringBuilder sb = new StringBuilder(file.toString());
			sb.append(": ");
			String md = Hex.encodeHexString(digest).toUpperCase();
			sb.append(md);
			System.out.println(sb);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (din != null) {
					din.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
