package com.pro.breakpointrecuperate;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class DownloadThread extends Thread {

	private String dlurl = "";
	private int startP;
	private int endP;
	private String fileName;
	private RandomAccessFile osf;

	public DownloadThread(int i, String url, String fileName, int start, int end) {
		this.setName("download thread " + i); // 子线程名称
		this.dlurl = url; // 下载地址
		this.fileName = fileName;
		this.startP = start; // 子线程读取/写入起始字节
		this.endP = end;// 子线程写入结束字节长度
	}

	public void run() {
		try {
			osf = new RandomAccessFile(fileName, "rw");
			URL url = new URL(dlurl);
			HttpURLConnection http2 = (HttpURLConnection) url.openConnection();
			http2.setRequestProperty("User-Agent", "NetFox");
			http2.setRequestProperty("RANGE", "bytes=" + startP + "-");
			osf.seek(startP);
			InputStream input = http2.getInputStream();
			byte[] b = new byte[1024];
			Date d = new Date();
			int l = 0;// 计算子线程读取和写入的文件长度，当长度大于每个子线程平均下载长度则终止线程
			int i;
			System.out.println(this.getName() + " 开始下载.....");
			while ((i = input.read(b, 0, 1024)) != -1 && l < endP) { // 线程下载字节长度控制误差小于缓冲池大小，本示例为缓冲池1024字节
				osf.write(b, 0, i);
				b = new byte[1024];// 重新赋值，避免重新读入旧内容
				l += i;
			}
			Date d2 = new Date();// 子线程结束下载时间
			System.out.println(this.getName() + " 线程耗时： "
					+ (d2.getTime() - d.getTime()) / 1000 + " 秒,实际共下载：" + l
					+ "字节");// 子线程下载耗时（秒）

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
