package com.insigma.common.util;

import java.util.UUID;


/**
 * token������
 * @author admin
 *
 */
public class TokenUtil {
	
	/**
	 * ����token
	 * @return
	 */
	public static String createToken(){
		return UUID.randomUUID().toString();
	}
}