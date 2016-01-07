package com.pro.md5;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

public class StudyMd5 {

	private static char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static final String key = "HBHBKEY";

	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String strDate = sdf.format(date);
		String str = key + strDate;
		try {
			byte[] bt = str.getBytes("UTF-8");
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			byte[] md5bt = messageDigest.digest(bt);
			String md51 = byteArr2HexStr(md5bt);
			System.out.println("MD5:" + md51.toUpperCase());
			// System.out.println(md51.substring(8,
			// md51.length()-8).toUpperCase());
			String md52 = customedCalc(md5bt);
			System.out.println("MD5:" + md52.toUpperCase());

			useCodec(bt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void useCodec(byte[] b) {
		String str = DigestUtils.md5Hex(b); // commons-codec.jar包实现了MD5加密，一步到位
		// String str = Hex.encodeHexString(b);
		// //commons-codec.jar包实现了字节到十六进制字符串的转换
		System.out.println("MD5:" + str.toUpperCase());
	}

	public static String customedCalc(byte[] b) {
		int i;
		StringBuffer buf = new StringBuffer();
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}

	public static String byteArr2HexStr(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEXCHAR[(b[i] & 0xf0) >>> 4]); // fo -11110000
			sb.append(HEXCHAR[b[i] & 0x0f]); // 0f -00001111
		}
		return sb.toString();
	}
}
