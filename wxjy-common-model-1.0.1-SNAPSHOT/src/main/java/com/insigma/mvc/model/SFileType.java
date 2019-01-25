package com.insigma.mvc.model;

/**
 * 上传文件类型
 * Created by LENOVO on 2017/8/30.
 */
public class SFileType implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String businessType; //文件类型编号
    private String typeName; //文件类型名称
    private int fileMaxNum; // 文件数量限制
    private double fileMaxSize; //文件大小限制(单位M)

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
