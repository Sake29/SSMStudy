package com.sake.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 用于抽取dao中的重复代码
 */
public class JdbcDaoSupport {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
