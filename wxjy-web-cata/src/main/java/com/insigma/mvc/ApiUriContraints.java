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
	
	
	/**
	 * ����Ŀ¼�����ַ
	 */
	private static String API_CATA="/api-cata";
	
	/**
	 * �����б�����ַ
	 */
	public static String API_AUTH_CODETYPELIST=API_AUTH+"/codetype/getInitcodetypeList";
	
	/**
	 * ������ϸ�����ַ
	 */
	public static String API_AUTH_CODEVALUElIST=API_AUTH+"/codetype/getInitCodeValueList";
	
	/**
	 * ����Ŀ¼������
	 */
	public static String API_AUTH_INDEX=API_CATA+"/catalogue/index";

	
	/**
	 * ���˷���Ŀ¼���ݵ�ַ
	 */
	public static String API_CATA_PERSERVICE_HALL=API_CATA+"/catalogue/perServiceHall";
	

	/**
	 * ��λ����Ŀ¼���ݵ�ַ
	 */
	public static String API_CATA_COMSERVICE_HALL=API_CATA+"/catalogue/comServiceHall";
	
	/**
	 * ����Ŀ¼�б�
	 */
	public static String API_CATA_LIST=API_CATA+"/catalogue/list";
	

	/**
	 * ������ϸ
	 */
	public static String API_CATA_DETAIL=API_CATA+"/catalogue/detail";
	
	/**
	 * �����ղ�
	 */
	public static String API_CATA_PERSON_COLLECT=API_CATA+"/person/catalogue/toggleCollect";
}
