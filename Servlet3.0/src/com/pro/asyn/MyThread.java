package com.pro.asyn;

import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;

public class MyThread extends Thread{

	private AsyncContext ac;
	public MyThread(AsyncContext ac){
		this.ac = ac;
	}
	
	public void run(){
		//在tomcat7下面，异步连接超时时间为10000单位，若不指定超时时间,将会出连接过期
		ac.setTimeout(15000);
		try {
			PrintWriter pw = ac.getResponse().getWriter();
			pw.println("bula1 bula1 bula1");
			TimeUnit.SECONDS.sleep(5);
			pw.println("bula2 bula2 bula2");
			System.out.println(">>>>>> asynServlet ends at:"+new Date());
			pw.flush();
			pw.close();
			ac.complete(); //notify
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
