package com.insigma.mvc.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 *  ϵͳ��ܱ�֮�쳣Ȩ�ޱ�
 * 
 */
public class SPermission implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**Ȩ�ޱ���*/
	//@NotNull(message="Ȩ�ޱ��벻��Ϊ��")
	private String permcode;
	/**Ȩ������*/
	//@NotNull(message="Ȩ�����Ʋ���Ϊ��")
	private String permname;
	/**Ȩ������*/
	//@NotNull(message="Ȩ����������Ϊ��")
	private String permdescribe;
	/**Ȩ�޵�ַ*/
	//@NotNull(message="Ȩ����������Ϊ��")
	private String url;
	/**Ȩ������*/
	//@NotNull(message="Ȩ�����Ͳ���Ϊ��")
	private String permtype;
	
	//@NotNull(message="��Ч��־����Ϊ��")
	private String enabled="0";
	
	private String iconcss;
	
	/**Ȩ�ޱ��*/
	private String permissionid;
	/**Ȩ�ޱ��*/
	private String id;
	/**Ȩ�޸������*/
	private String parentid;
	/**Ȩ�޸��������*/
	private String parentname;
	/**Ȩ�޸������*/
	private String pid;
	private String name;
	/**�����*/
	private String sortnum;
	
	private Date updatetime;

	private String code;

	private String type;


	private List<SPermission> child;
	
	private String isblanktarget="0";
	
	private String selectnodes;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String nocheck;
    
    private String systypecode;

    private String describe;


	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getPermcode() {
		return permcode;
	}

	public void setPermcode(String permcode) {
		this.permcode = permcode;
	}

	public String getPermname() {
		return permname;
	}

	public void setPermname(String permname) {
		this.permname = permname;
	}

	public String getPermdescribe() {
		return permdescribe;
	}

	public void setPermdescribe(String permdescribe) {
		this.permdescribe = permdescribe;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermtype() {
		return permtype;
	}

	public void setPermtype(String permtype) {
		this.permtype = permtype;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getIconcss() {
		return iconcss;
	}

	public void setIconcss(String iconcss) {
		this.iconcss = iconcss;
	}

	public String getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSortnum() {
		return sortnum;
	}

	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public List<SPermission> getChild() {
		return child;
	}

	public void setChild(List<SPermission> child) {
		this.child = child;
	}

	public String getIsblanktarget() {
		return isblanktarget;
	}

	public void setIsblanktarget(String isblanktarget) {
		this.isblanktarget = isblanktarget;
	}

	public String getSelectnodes() {
		return selectnodes;
	}

	public void setSelectnodes(String selectnodes) {
		this.selectnodes = selectnodes;
	}

	public String getNocheck() {
		return nocheck;
	}

	public void setNocheck(String nocheck) {
		this.nocheck = nocheck;
	}

	public String getSystypecode() {
		return systypecode;
	}

	public void setSystypecode(String systypecode) {
		this.systypecode = systypecode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
