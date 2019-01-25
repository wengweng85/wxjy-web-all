package com.insigma.mvc.model;


/**
 * 公共服务网之系统类型表
 * @author admin
 *
 */
public class SSysType extends PageInfo implements java.io.Serializable {

	private String systypeid;
	private String systypecode;
	private String sysname;
	private String sysdesc;
	private String sysurl;
	public String getSystypeid() {
		return systypeid;
	}
	public void setSystypeid(String systypeid) {
		this.systypeid = systypeid;
	}
	public String getSystypecode() {
		return systypecode;
	}
	public void setSystypecode(String systypecode) {
		this.systypecode = systypecode;
	}
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public String getSysdesc() {
		return sysdesc;
	}
	public void setSysdesc(String sysdesc) {
		this.sysdesc = sysdesc;
	}
	public String getSysurl() {
		return sysurl;
	}
	public void setSysurl(String sysurl) {
		this.sysurl = sysurl;
	}
	
	
	
	
}