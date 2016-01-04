package com.pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="myServlet3.0",urlPatterns={"/test","/oneReq.do"},initParams={@WebInitParam(name="servletVersion",value="3.0")})
public class MyServlet extends HttpServlet{
	
	private static final long serialVersionUID = 8161232317258996338L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(this.getInitParameter("servletVersion"));
		PrintWriter pw = resp.getWriter();
		pw.println("Hello Servlet3.0");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
