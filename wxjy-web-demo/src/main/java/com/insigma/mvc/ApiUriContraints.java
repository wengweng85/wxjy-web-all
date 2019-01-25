package com.insigma.mvc;

/**
 * api地址约束
 * @author wengsh
 *
 */
public class ApiUriContraints {
	
	/**
	 * 权限认证服务地址
	 */
	private static String API_AUTH="/api-auth";
	
	/**
	 */
	private static String API_SXJSY="/api-sxjysy";
		
	/**
	 * 参数列表服务地址
	 */
	public static String API_AUTH_CODETYPELIST=API_AUTH+"/codetype/getInitcodetypeList";
	
	/**
	 * 参数明细服务地址
	 */
	public static String API_AUTH_CODEVALUElIST=API_AUTH+"/codetype/getInitCodeValueList";
	
	

	/**
	 */
	public static String API_SXJSY_AC11S=API_SXJSY+"/ac11s";
}
