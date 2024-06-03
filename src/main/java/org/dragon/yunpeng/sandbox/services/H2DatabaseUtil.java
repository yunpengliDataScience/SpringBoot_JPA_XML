package org.dragon.yunpeng.sandbox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service(value = "h2DatabaseUtil")
public class H2DatabaseUtil {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void disableConstraints() {
		jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
	}

	public void enableConstraints() {
		jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
	}
}
