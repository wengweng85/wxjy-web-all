package com.insigma.dto;


/**
 * ajax��Ϣ����
 *
 * @author admin
 * @date 2012-9-27
 */

public class AjaxReturnMsg<T> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int syscode; // ϵͳ����״̬��,Ĭ��200
    private boolean success; // ҵ��״̬�룬Ĭ��Ϊtrue
    private String message = ""; // ҵ��״̬����˵��
    private int code ;
    private T obj; //����
    private Long total; //��ҳ�����֮����

    public AjaxReturnMsg() {
        this.syscode = SysCode.SYS_CODE_200.getCode(); // ϵͳ����״̬��,Ĭ��200
        this.success = true; // ҵ��״̬�룬Ĭ��Ϊtrue
    }


    public int getSyscode() {
        return syscode;
    }

    public void setSyscode(int syscode) {
        this.syscode = syscode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
