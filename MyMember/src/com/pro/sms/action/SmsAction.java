package com.pro.sms.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.pro.sms.service.SmsService;

@SuppressWarnings("serial")
public class SmsAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private static Logger log = Logger.getLogger(SmsAction.class.getName());
	private HttpServletRequest request;
	private HttpServletResponse response;
	private SmsService smsService;

	public String loadsms() {
		try {
			request.getParameter("id");
			response.getWriter().print(smsService.loadSms());
		} catch (IOException e) {
			log.error("###loadsms exception occurs", e);
		}
		return null;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}
}
