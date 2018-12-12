package com.an.acquisition.manage.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class CommonDao {

protected static final Logger logger = LogManager.getLogger();
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}
