package com.insigma.mvc.model;


import java.util.Date;

/**
 *  ϵͳ��ܱ�֮������Ϣ��
 * 
 */
public class SysGroup extends PageInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
   
	private String groupid;
	private String id;
	private String open;
	private String groupname;
	private String name;
	private String description;
	private String isParent;
	private String grouptype;
	private String pid;
	private String parentid;
	private String status;


	//������Ŀ����
	private String organize	;//	��������֯��������
	private String poid	;//	������������֯��������
	private String grid_scope	;//	����Χ
	private String link_phone	;//	��ϵ�绰
	private Date last_update_time	;//	����޸�ʱ��
	private String last_update_by	;//	�޸��˱���
	private String source	;//	������Դ 0������ͬ����ȡ  1ϵͳ����
	private String orderby	;//	�����
	private String grouplevel	;//	��֯��������
	private String otherinfo;//��ע
	private String chargedept;//���ܲ���
	private String tel;//��֯������ϵ�绰
	private String address;//��֯������ַ
	private String principal;//����������
	private String oid;//	����ϵͳ��������
	private String org;//ϵͳ��������
	private String license;//������֯��������
	private String parentname;//������������
	
	public String getOrganize() {
		return organize;
	}

	public void setOrganize(String organize) {
		this.organize = organize;
	}

	public String getOtherinfo() {
		return otherinfo;
	}

	public void setOtherinfo(String otherinfo) {
		this.otherinfo = otherinfo;
	}

	public String getChargedept() {
		return chargedept;
	}

	public void setChargedept(String chargedept) {
		this.chargedept = chargedept;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getPoid() {
		return poid;
	}

	public void setPoid(String poid) {
		this.poid = poid;
	}

	public String getGrid_scope() {
		return grid_scope;
	}

	public void setGrid_scope(String grid_scope) {
		this.grid_scope = grid_scope;
	}

	public String getLink_phone() {
		return link_phone;
	}

	public void setLink_phone(String link_phone) {
		this.link_phone = link_phone;
	}

	public Date getLast_update_time() {
		return last_update_time;
	}

	public void setLast_update_time(Date last_update_time) {
		this.last_update_time = last_update_time;
	}

	public String getLast_update_by() {
		return last_update_by;
	}

	public void setLast_update_by(String last_update_by) {
		this.last_update_by = last_update_by;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getGrouplevel() {
		return grouplevel;
	}

	public void setGrouplevel(String grouplevel) {
		this.grouplevel = grouplevel;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	
	
	
	
}
