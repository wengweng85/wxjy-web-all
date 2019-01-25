package com.insigma.dto;

/**
 *  系统请求状态码
 */
public enum SysCode  {

	SYS_CODE_200(200,"成功"),
	SYS_APPKEY_EMPTY(401,"appkey为空"),
	SYS_APPKEY_ERROR(402,"appkey不正确"),
	SYS_TOKEN_EMPTY(403,"token为空,请先登录"),
	SYS_TOKEN_ERROR(404,"token值不正确或已经过期,请重新登录"),
	SYS_USERID_ERROR(405,"登录信息与token信息不匹配,非法请求请确认"),
	SYS_SERVICEURL_ERROR(406,"没有访问此服务的权限或地址地址,请确认"),
	SYS_API_RATELIMIT(407,"访问次数受限"),
	SYS_API_EXCEPTION(500,"接口异常");

	private int code;
	private String name;

	SysCode(int code,String name){
		this.code=code;
		this.name=name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}






}
