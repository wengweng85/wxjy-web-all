package com.insigma.dto;

/**
 * ��֤������
 */
public enum VerCodeType {
    VerCode_TYPE_SMS_MOBILEREG("SMS_MOBILEREG","�ֻ���ע����֤��"),
    VerCode_TYPE_SMS_PWDBACK("SMS_PWDBACK","�ֻ������һ���֤��");

    private String code;
    private String name;

    VerCodeType(String code,String name){
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
