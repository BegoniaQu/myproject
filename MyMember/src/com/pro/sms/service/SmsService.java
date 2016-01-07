package com.pro.sms.service;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class SmsService {

	static Logger log = Logger.getLogger(SmsService.class.getName());
	private JdbcTemplate jdbcTemplate;

	public int loadSms() {
		return this.jdbcTemplate.queryForInt("select count(*) from xt_user");
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
