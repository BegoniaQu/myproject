package com.pro.asyn;

import java.io.IOException;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="myAsynServlet",urlPatterns={"/asynReq"},asyncSupported=true)
public class MyAsynServlet extends HttpServlet{

	private static final long serialVersionUID = -2948851576231482894L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(">>>>>> asynServlet starts at:"+new Date());
		
		AsyncContext ac = req.startAsync(req,resp);
		
//		ac.addListener(new AsyncListener() {
//			
//			@Override
//			public void onTimeout(AsyncEvent arg0) throws IOException {
//				
//			}
//			
//			@Override
//			public void onStartAsync(AsyncEvent arg0) throws IOException {
//				
//			}
//			
//			@Override
//			public void onError(AsyncEvent arg0) throws IOException {
//				
//			}
//			
//			@Override
//			public void onComplete(AsyncEvent event) throws IOException {
//				event.getAsyncContext().getResponse().getWriter().close();
//				System.out.println("asynContext finished....");
//			}
//		});
		//depend on a thread to execute asyn task
		new MyThread(ac).start();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
