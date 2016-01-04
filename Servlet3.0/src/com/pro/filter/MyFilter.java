package com.pro.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName="myFilter3.0",urlPatterns={"/*"},asyncSupported=true)
public class MyFilter implements Filter{

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest sreq, ServletResponse sresp,
			FilterChain fChain) throws IOException, ServletException {
		System.out.println(">>>>> come into filter");
		fChain.doFilter(sreq, sresp);;
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		
		
	}

}
