package com.pro.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StudySHA1 {

	public static void main(String[] args) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
