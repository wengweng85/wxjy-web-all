package com.insigma.mvc.model;

/**
 * �ϴ�EXCEL�ļ�ҵ�����Ͳ�����
 */
public class SExcelType implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String businessType; //ҵ����
    private String typeName; //ҵ������
    private String vsbeanname; // excel��������ҵ������ ��fuPingExcelImportService
    private String mincolumns; //excel�����п�� �� 6
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
	public String getVsbeanname() {
		return vsbeanname;
	}
	public void setVsbeanname(String vsbeanname) {
		this.vsbeanname = vsbeanname;
	}
	public String getMincolumns() {
		return mincolumns;
	}
	public void setMincolumns(String mincolumns) {
		this.mincolumns = mincolumns;
	}
    
    
    

    
}
