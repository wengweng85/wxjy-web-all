package com.insigma.mvc.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 *  系统框架表之用户角色表
 * 
 */
public class SysRole extends PageInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String userid;
   
	private String roleid;
	
	
	//@NotNull(message="角色名称不能为空")
	private String rolename;
	
	//@NotNull(message="角色编码不能为空")
    private String rolecode;
	
	//@NotNull(message="角色描述不能为空")
    private String roledescribe;
	
	private String pid;
	
	private String name;
	
	private String open;
	private String checked;
	@JsonIgnore
	private String permissionid;
	
	@JsonIgnore
	private String selectnodes;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getRoledescribe() {
		return roledescribe;
	}

	public void setRoledescribe(String roledescribe) {
		this.roledescribe = roledescribe;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}

	public String getSelectnodes() {
		return selectnodes;
	}

	public void setSelectnodes(String selectnodes) {
		this.selectnodes = selectnodes;
	}
	
	
	
}
