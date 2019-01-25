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
	 * 服务目录服务地址
	 */
	private static String API_CATA="/api-cata";
	
	/**
	 * 参数列表服务地址
	 */
	public static String API_AUTH_CODETYPELIST=API_AUTH+"/codetype/getInitcodetypeList";
	
	/**
	 * 参数明细服务地址
	 */
	public static String API_AUTH_CODEVALUElIST=API_AUTH+"/codetype/getInitCodeValueList";
	
	/**
	 * 服务目录主服务
	 */
	public static String API_AUTH_INDEX=API_CATA+"/catalogue/index";

	
	/**
	 * 个人服务目录数据地址
	 */
	public static String API_CATA_PERSERVICE_HALL=API_CATA+"/catalogue/perServiceHall";
	

	/**
	 * 单位服务目录数据地址
	 */
	public static String API_CATA_COMSERVICE_HALL=API_CATA+"/catalogue/comServiceHall";
	
	/**
	 * 服务目录列表
	 */
	public static String API_CATA_LIST=API_CATA+"/catalogue/list";
	

	/**
	 * 服务明细
	 */
	public static String API_CATA_DETAIL=API_CATA+"/catalogue/detail";
	
	/**
	 * 个人收藏
	 */
	public static String API_CATA_PERSON_COLLECT=API_CATA+"/person/catalogue/toggleCollect";
}
