package com.insigma.common.util;

import java.util.UUID;


/**
 * token工具类
 * @author admin
 *
 */
public class TokenUtil {
	
	/**
	 * 创建token
	 * @return
	 */
	public static String createToken(){
		return UUID.randomUUID().toString();
	}
}