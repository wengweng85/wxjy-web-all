package com.insigma.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ajax��Ϣ����
 *
 * @author admin
 * @date 2012-9-27
 */

@ApiModel(value="�ӿ�ͨ�÷�������̬Ϊjson")
public class AjaxReturnMsg<T> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "ϵͳ����״̬��",value = "\"200\",\"�ɹ�\" \"40001\",\"appkeyΪ��\"\"40002\",\"appkey����ȷ\"\"40003\",\"tokenΪ��,���ȵ�¼\"\"40004\",\"tokenֵ����ȷ���Ѿ�����,�����µ�¼\"\"40005\",\"��¼��Ϣ��token��Ϣ��ƥ��,�Ƿ�������ȷ��\"\"40006\",\"û�з��ʴ˷����Ȩ�޻��ַ��ַ,��ȷ��\"\"50001\",\"api�����쳣\"")
    private String syscode; // ϵͳ����״̬��,Ĭ��200
    @ApiModelProperty(name="ҵ��״̬��",value="true �ɹ� flase ʧ��")
    private boolean success; // ҵ��״̬�룬Ĭ��Ϊtrue
    @ApiModelProperty(name="ҵ��״̬����˵��",value="ҵ��״̬����˵��")
    private String message = ""; // ҵ��״̬����˵��
    @ApiModelProperty(name="ҵ��״̬����״̬��",value="ҵ��״̬����״̬��")
    private String code = "";
    @ApiModelProperty(name="ҵ�񷵻ض��󼯺�",value="ҵ�񷵻ض��󼯺�")
    private T obj; //����
    @ApiModelProperty(name="��ҳ����֮����",value="��ҳ����֮����")
    private Long total; //��ҳ�����֮����

    public AjaxReturnMsg() {
        this.syscode = SysCode.SYS_CODE_200.getCode(); // ϵͳ����״̬��,Ĭ��200
        this.success = true; // ҵ��״̬�룬Ĭ��Ϊtrue
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
