package com.insigma.dto;

/**
 *  ϵͳ����״̬��
 */
public enum SysCode  {
	
	SYS_CODE_200("200","�ɹ�"),
	SYS_API_RATELIMIT("401","���ʴ�������"),
	SYS_APPKEY_EMPTY("40001","appkeyΪ��"),
	SYS_APPKEY_ERROR("40002","appkey����ȷ"),
	SYS_TOKEN_EMPTY("40003","tokenΪ��,���ȵ�¼"),
	SYS_TOKEN_ERROR("40004","tokenֵ����ȷ���Ѿ�����,�����µ�¼"),
	SYS_USERID_ERROR("40005","��¼��Ϣ��token��Ϣ��ƥ��,�Ƿ�������ȷ��"),
	SYS_SERVICEURL_ERROR("40006","û�з��ʴ˷����Ȩ�޻��ַ��ַ,��ȷ��"),
	SYS_API_EXCEPTION("50001","api�����쳣");

	
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
