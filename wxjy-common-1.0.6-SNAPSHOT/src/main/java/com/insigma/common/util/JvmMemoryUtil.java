package com.insigma.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * java�鿴��ǰϵͳ�ڴ�ʹ�����
 * @author admin
 *
 */
public class JvmMemoryUtil {
	
	private static Log log=LogFactory.getLog(JvmMemoryUtil.class);
	public static  void showJvmMemory(){
		Runtime run = Runtime.getRuntime();
		long max = run.maxMemory();
		long total = run.totalMemory();
		long free = run.freeMemory();
		long usable = max - total + free;
		log.info("����ڴ� = " + max/1024/1024+"M");
		//log.info("�ѷ����ڴ� = " + total/1024/1024+"M");
		//log.info("�ѷ����ڴ��е�ʣ��ռ� = " + free/1024/1024+"M");
		log.info("�������ڴ� = " + usable/1024/1024+"M");
	}
	
	public static void main(String []  a){
		showJvmMemory();
	}
}
