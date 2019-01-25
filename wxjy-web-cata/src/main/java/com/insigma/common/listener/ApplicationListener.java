package com.insigma.common.listener;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目初始化
 *
 * @author xxx
 */
public class ApplicationListener implements ServletContextListener {

    private Log log = LogFactory.getLog(ApplicationListener.class);

    private static String gateway_base_url;
    
    private static boolean isencrpty;

    private static String redisswitch;

    static {
        Properties pro = new Properties();
        try {
            InputStream fis = ApplicationListener.class.getClassLoader().getResourceAsStream("/app.properties");
            pro.load(fis);
            gateway_base_url = pro.getProperty("gateway_base_url");
            isencrpty= Boolean.parseBoolean(pro.getProperty ("isencrpty"));
            System.out.println("请求是否加密:"+isencrpty);
            redisswitch = pro.getProperty("redisswitch");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    /**
     * 基于ehcache
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }
}