package com.insigma.mvc;

/**
 * api��ַԼ��
 * @author wengsh
 *
 */
public class ApiUriContraints {
	
	/**
	 * Ȩ����֤�����ַ
	 */
	private static String API_AUTH="/api-auth";
	
	
	private static String API_CODE="/api-code";
	
	/**
	 */
	private static String API_SXJSY="/api-sxjysy";
	
	
	private static String API_BASE="/api-base";
		
	/**
	 * �����б�����ַ
	 */
	public static String API_CODE_CODETYPELIST=API_CODE+"/codetype/getInitcodetypeList";
	
	/**
	 * ������ϸ�����ַ
	 */
	public static String API_CODE_CODEVALUElIST=API_CODE+"/codetype/getInitCodeValueList";
	
	/**
	 */
	public static String API_SXJSY_AC11S=API_SXJSY+"/ac11s";
	
	public static String API_BASE_ERRORLOGS=API_BASE+"/errorlogs";
	
	public static String API_BASE_ERRORLOG=API_BASE+"/q_errorlog";
}
