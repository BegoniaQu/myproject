package com.pro.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class SecurityFilter extends HttpServlet implements Filter {

	static Logger log = Logger.getLogger(SecurityFilter.class.getName());

	public void destroy() {
		log.info("###filter destroyed");
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		// HttpServletResponse response = (HttpServletResponse) resp;
		Object[] user = (Object[]) request.getSession().getAttribute("user");
		String url = request.getRequestURI();
		if (user == null) {
			log.warn("###never login--" + request.getContextPath());
			if (url.indexOf("login") < 0) {
				request.getRequestDispatcher("index.jsp").forward(req, resp);
			}
			chain.doFilter(req, resp);
		} else {
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig fconfig) throws ServletException {
		log.info("###filter initialize");
	}

}
