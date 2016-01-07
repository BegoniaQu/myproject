package com.pro.login.ation;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.pro.login.dao.LoginService;

public class LoginAction extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9094511670480839445L;
	static Logger log = Logger.getLogger(LoginAction.class.getName());
	// ���request������ʱ������
	private HttpServletRequest request;
	private LoginService service;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String login() {
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		Object[] user = new Object[] { username, pwd };
		if (service.login(user) == true) {
			request.getSession().setAttribute("user", user);
			return "success";
		} else {
			log.error("###login failed");
			request.setAttribute("status", "0");
			return "fail";

		}
	}

	public void setService(LoginService service) {
		this.service = service;
	}
}
