package com.insigma.dto;

/**
 *  ϵͳmessage����
 */
public enum MessageType  {
	
	MESSAGE_TYPE_LOG("log","�ӿ���־"),
	MESSAGE_TYPE_USERLOG("userlog","�û���־"),
	MESSAGE_TYPE_INFO("info","ϵͳ��Ϣվ����"),
	MESSAGE_TYPE_EMAIL("email","�ʼ�"),
	MESSAGE_TYPE_EMAIL_RESUME("emailResume","���������ʼ�"),
	MESSAGE_TYPE_SMS("sms","ע����֤��"),
	MESSAGE_TYPE_SMS_VERCODE("smsCode","�һ�������֤��"),
	MESSAGE_TYPE_SMS_LOGIN("smsCodeLogin","���ŵ�¼��֤��");


	private String code;
	private String name;
	
	MessageType(String code,String name){
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
