package com.insigma.common.listener;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.insigma.common.util.DateUtil;
import com.insigma.common.util.EhCacheUtil;
import com.insigma.http.HttpRequestUtils;
import com.insigma.mvc.ApiUriContraints;
import com.insigma.mvc.component.appcontext.MyApplicationContextUtil;
import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;
import com.insigma.redis.RedisManager;

/**
 * 项目初始化
 *
 * @author xxx
 */
public class ApplicationListener implements ServletContextListener {

    private Log log = LogFactory.getLog(ApplicationListener.class);

    private static String gateway_base_url;
    
    private static boolean isencrpty;
    
    private static String contextype="APPLICATION_JSON";

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
    public void contextInitialized (ServletContextEvent arg0) {
    	 //使用redis
        if (redisswitch.equals("on")) {
            log.info("使用redis加载参数");
            //通过MyApplicationContextUtil获取bean
            final RedisManager redismanager = MyApplicationContextUtil.getContext().getBean(RedisManager.class);
            redismanager.init();
            //是否同步标志 如果上一次同步时间是1小时之内，不同步下载代码
            boolean syn_flag = true;
            Date code_value_last_update_time = (Date) redismanager.get("code_value_last_update_time");
            if (code_value_last_update_time != null) {
                if (!DateUtil.compare(new Date(), code_value_last_update_time, 3600 * 1000)) {
                    syn_flag = false;
                }
            }
            List<CodeType> list_code_type = null;
            try {
               //需要同步
               if (syn_flag) {
                 list_code_type = new HttpRequestUtils<CodeType>(gateway_base_url,isencrpty,contextype).httpPostReturnList(ApiUriContraints.API_AUTH_CODETYPELIST, CodeType.class);
                 redismanager.set("list_code_type",list_code_type);
                 ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
                 for(int i = 0; i < list_code_type.size(); i++){
                 CodeType codeType = list_code_type.get(i);
                 final String code_type = codeType.getCode_type();
                 fixedThreadPool.execute(new Runnable() {
                       public void run() {
                              log.info("代码类型="+code_type+",当前线程="+Thread.currentThread().getName());
                              try{
                                  HashMap map=new HashMap();
                                  map.put("code_type", code_type);
                                  List<CodeValue> list_code_value = new HttpRequestUtils<CodeValue>(gateway_base_url,isencrpty,contextype).httpPostReturnList(ApiUriContraints.API_AUTH_CODEVALUElIST, map,CodeValue.class);
                                  if (list_code_value.size() > 0) {
                                       //将代码参加加载到redis缓存中
                                      redismanager.set(code_type, list_code_value);
                                  }
                              }catch(Exception e){
                                  //e.printStackTrace();
                              }
                           }
                         });
                       }
                 //上一次更新时间
                 redismanager.set("code_value_last_update_time", new Date());
               }else{
                 list_code_type=( List<CodeType>)redismanager.get("list_code_type");
               }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //从redis中同步到ehcached
            if (list_code_type != null) {
                for (CodeType codetype : list_code_type) {
                    EhCacheUtil.getManager().getCache("webcache").put(new Element(codetype.getCode_type(), (List<CodeValue>) redismanager.get(codetype.getCode_type())));
                }
            }
        } 
       else {
        log.info("不使用redis加载参数");
        //是否同步标志 如果上一次同步时间是1小时之内，不同步下载代码
        try {
            List<CodeType> list_code_type = new HttpRequestUtils(gateway_base_url,isencrpty,contextype).httpPostReturnList(ApiUriContraints.API_AUTH_CODETYPELIST, CodeType.class);
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
            for(int i = 0; i < list_code_type.size(); i++){
                 CodeType codeType = list_code_type.get(i);
                 final String code_type = codeType.getCode_type();
                 fixedThreadPool.execute(new Runnable() {
                    public void run() {
                        log.info("代码类型="+code_type+",当前线程="+Thread.currentThread().getName());
                        //Thread.sleep(2000);
                        try{
                            HashMap map=new HashMap();
                            map.put("code_type", code_type);
                             List<CodeValue> list_code_value = new HttpRequestUtils(gateway_base_url,isencrpty,contextype).httpPostReturnList(ApiUriContraints.API_AUTH_CODEVALUElIST, map, CodeValue.class);
                             if (list_code_value.size() > 0) {
                                 //将代码参加加载到ehcache缓存中
                                 EhCacheUtil.getManager().getCache("webcache").put(new Element(code_type, list_code_value));
                             }
                        }catch(Exception e){
                            //e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }

    /**
     * 基于ehcache
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}