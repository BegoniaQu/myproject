package com.pro.breakpointrecuperate;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadApp {

	static int len;// 线程平均下载文件长度
	static int bn;// 每个线程写入文件的字节数
	static int tn; // 线程数
	static String urlt;// 下载地址
	static String fileName;
	static RandomAccessFile osf; // 文件操作

	public static void main(String[] args) {
		try {
			urlt = "http://gcwdown.wymp4.net:99/avi//20140411/最美的太阳%20张杰[www.wymp4.net].avi";
			fileName = "D:\\mms\\goodstar.mkv";
			URL url = new URL(urlt);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			System.out.println("file size:" + http.getContentLength());
			tn = 5;
			len = http.getContentLength() / tn;// 舍去余数（余数自动舍去）计算每个线程应下载平均长度，最后一个线程再加上余数，则是整个文件的长度,
			File f = new File(fileName);
			if (f.exists()) {
				f.delete();
			}

			Thread t;// 下载子线程，
			for (int j = 0; j < tn; j++) {
				if (j == tn - 1) {// 如果最后一个线程则加上余数长度字节
					bn = len + (http.getContentLength() % tn);
				} else {
					bn = len;
				}
				System.out
						.println("t" + j + "线程下载长度：" + bn + "起始字节：" + len * j);
				t = new DownloadThread(j, urlt, fileName, len * j, bn);
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
