package com.insigma.dto;

/**
 *  ��Ϣ����
 */
public enum InfoType  {
	
	I100000("I10000"," ��ø��˻�����Ϣ"),
	I100001("I10001","������˻�����Ϣ");
	private String code;
	private String name;
	
	private InfoType(String code,String name){
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
