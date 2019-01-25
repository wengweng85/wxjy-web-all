package com.insigma.mvc.model;


/**
 * 系统框架表之系统参数管理
 * @author wengsh
 *
 */
public class SysParam extends PageInfo implements java.io.Serializable {

	private String paramid;
	private String paramtype;
	private String paramvalue;
	private String paramname;
	private String paramisvalid;
	public String getParamid() {
		return paramid;
	}
	public void setParamid(String paramid) {
		this.paramid = paramid;
	}
	public String getParamtype() {
		return paramtype;
	}
	public void setParamtype(String paramtype) {
		this.paramtype = paramtype;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	public String getParamname() {
		return paramname;
	}
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	public String getParamisvalid() {
		return paramisvalid;
	}
	public void setParamisvalid(String paramisvalid) {
		this.paramisvalid = paramisvalid;
	}
	
	
	
}