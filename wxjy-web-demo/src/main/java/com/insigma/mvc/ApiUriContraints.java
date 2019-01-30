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
	
	
	private static String API_CODE="/api-code";
	
	/**
	 */
	private static String API_SXJSY="/api-sxjysy";
	
	
	private static String API_BASE="/api-base";
		
	/**
	 * 参数列表服务地址
	 */
	public static String API_CODE_CODETYPELIST=API_CODE+"/codetype/getInitcodetypeList";
	
	/**
	 * 参数明细服务地址
	 */
	public static String API_CODE_CODEVALUElIST=API_CODE+"/codetype/getInitCodeValueList";
	
	/**
	 */
	public static String API_SXJSY_AC11S=API_SXJSY+"/ac11s";
	
	public static String API_BASE_ERRORLOGS=API_BASE+"/errorlogs";
	
	public static String API_BASE_ERRORLOG=API_BASE+"/q_errorlog";
}
