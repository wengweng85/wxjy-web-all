package com.insigma.common.util;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletContext;

/**
 * Created by admin on 2014-12-25.
 */
public class DBUtil {

	
	 /**
     * ͨ��spring��jdbctemple��ȡ����
     * @param context
     * @return
     */
    public static JdbcTemplate getJdbcTemplate(ServletContext context) {
        JdbcTemplate jdbcTemplate=(JdbcTemplate) SpringUtil.getBean(context,"jdbcTemplate");
        return  jdbcTemplate;
    }

}
