package com.pro.login.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LoginService {

	private JdbcTemplate jdbcTemplate;

	public boolean login(Object[] user) {
		String sql = "select * from xt_user where user_id=? and password=?";
		SqlRowSet srs = this.jdbcTemplate.queryForRowSet(sql, user);
		if (srs.next()) {
			return true;
		}
		return false;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
