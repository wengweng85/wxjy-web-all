package com.insigma.mvc.model;

/**
 * 上传EXCEL文件业务类型参数表
 */
public class SExcelType implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String businessType; //业务编号
    private String typeName; //业务名称
    private String vsbeanname; // excel解析处理业务类名 如fuPingExcelImportService
    private String mincolumns; //excel解析列宽度 如 6
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
