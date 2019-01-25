package com.insigma.dto;

/**
 *  ϵͳ����״̬��
 */
public enum SysCode  {

	SYS_CODE_200("200","�ɹ�"),
	SYS_APPKEY_EMPTY("401","appkeyΪ��"),
	SYS_APPKEY_ERROR("402","appkey����ȷ"),
	SYS_APPKEY_NO_PERM("408","û�з��ʴ�΢����Ȩ��"),
	SYS_TOKEN_EMPTY("403","tokenΪ��,���ȵ�¼"),
	SYS_TOKEN_ERROR("404","tokenֵ����ȷ���Ѿ�����,�����µ�¼"),
	SYS_USERID_ERROR("405","��¼��Ϣ��token��Ϣ��ƥ��,�Ƿ�������ȷ��"),
	SYS_SERVICEURL_ERROR("406","û�з��ʴ˷����Ȩ�޻��ַ��ַ,��ȷ��"),
	SYS_API_RATELIMIT("407","���ʴ�������"),
	SYS_API_EXCEPTION("500","�ӿڷ����쳣");


	private String code;
	private String name;

	SysCode(String code,String name){
		this.code=code;
		this.name=name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




}
