package com.insigma.mvc.model;

import java.util.Date;


/**
 * DemoAc01ExcelTemp
 * @author admin
 *
 */
public class DemoAc01ExcelTemp extends PageInfo implements java.io.Serializable {

	private String excel_temp_id; // varchar2(36) ������ʱ��֮��ʱ������
	private String excel_batch_number; // ������ʱ��֮�������κ�
	private String aac003; // ������ʱ��֮����
	private String aac002; // ������ʱ��֮���֤��
	private String aac004; //������ʱ��֮�Ա�
	private String aac006; //������ʱ��֮����
	private String aae009; //������ʱ��֮����
	private String excel_isvalid;// ������ʱ��֮�Ƿ���ȷ����
	private Long excel_rownum;// ������ʱ��֮����
	private Date excel_impdate;// ������ʱ��֮��������
	private String excel_errormsg;//������ʱ��֮��������ԭ��
	private String excel_isop;//������ʱ��֮�Ƿ��Ѿ�����
	private Date excel_opdate;// ������ʱ��֮��������
	private String excel_errormsg_groupby_total;//���ݴ������ͷ���
	public String getExcel_temp_id() {
		return excel_temp_id;
	}
	public void setExcel_temp_id(String excel_temp_id) {
		this.excel_temp_id = excel_temp_id;
	}
	public String getExcel_batch_number() {
		return excel_batch_number;
	}
	public void setExcel_batch_number(String excel_batch_number) {
		this.excel_batch_number = excel_batch_number;
	}
	public String getAac003() {
		return aac003;
	}
	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}
	public String getAac002() {
		return aac002;
	}
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}
	public String getAac004() {
		return aac004;
	}
	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}
	public String getAac006() {
		return aac006;
	}
	public void setAac006(String aac006) {
		this.aac006 = aac006;
	}
	public String getAae009() {
		return aae009;
	}
	public void setAae009(String aae009) {
		this.aae009 = aae009;
	}
	public String getExcel_isvalid() {
		return excel_isvalid;
	}
	public void setExcel_isvalid(String excel_isvalid) {
		this.excel_isvalid = excel_isvalid;
	}
	public Long getExcel_rownum() {
		return excel_rownum;
	}
	public void setExcel_rownum(Long excel_rownum) {
		this.excel_rownum = excel_rownum;
	}
	public Date getExcel_impdate() {
		return excel_impdate;
	}
	public void setExcel_impdate(Date excel_impdate) {
		this.excel_impdate = excel_impdate;
	}
	public String getExcel_errormsg() {
		return excel_errormsg;
	}
	public void setExcel_errormsg(String excel_errormsg) {
		this.excel_errormsg = excel_errormsg;
	}
	public String getExcel_isop() {
		return excel_isop;
	}
	public void setExcel_isop(String excel_isop) {
		this.excel_isop = excel_isop;
	}
	public Date getExcel_opdate() {
		return excel_opdate;
	}
	public void setExcel_opdate(Date excel_opdate) {
		this.excel_opdate = excel_opdate;
	}
	public String getExcel_errormsg_groupby_total() {
		return excel_errormsg_groupby_total;
	}
	public void setExcel_errormsg_groupby_total(String excel_errormsg_groupby_total) {
		this.excel_errormsg_groupby_total = excel_errormsg_groupby_total;
	}
}