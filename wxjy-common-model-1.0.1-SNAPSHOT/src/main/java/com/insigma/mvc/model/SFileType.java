package com.insigma.mvc.model;

/**
 * �ϴ��ļ�����
 * Created by LENOVO on 2017/8/30.
 */
public class SFileType implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String businessType; //�ļ����ͱ��
    private String typeName; //�ļ���������
    private int fileMaxNum; // �ļ���������
    private double fileMaxSize; //�ļ���С����(��λM)

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getFileMaxNum() {
        return fileMaxNum;
    }

    public void setFileMaxNum(int fileMaxNum) {
        this.fileMaxNum = fileMaxNum;
    }

    public double getFileMaxSize() {
        return fileMaxSize;
    }

    public void setFileMaxSize(double fileMaxSize) {
        this.fileMaxSize = fileMaxSize;
    }
}
