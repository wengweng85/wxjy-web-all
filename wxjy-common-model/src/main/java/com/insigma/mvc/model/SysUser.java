package com.insigma.mvc.model;

import java.util.Date;
import java.util.List;



/**
 *  ϵͳ�����֮�û���Ϣ��
 */
public class SysUser implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String userid;
	private String cnname;
	private String password;
	private String username;
	private String enabled;
	private String groupid;
	private String groupname;
	private String grouptype;
	private String groupparentid;
	private String type;
	private String isgrant;
	private String aab301;
	private String aab301name;
	private String isvercode;
	private String verifycode;
	private List<SysPermission> permlist;
	private String usertype;
	private Date createdate;
	private String token;
	private String usergroupid;
	private String memberid;
	private String phone;
	private String email;
	private String address;
	
	private String selectnodes;
	private String abz182;


	//������Ŀ����
	private String sys_user_logintimes	;//	��¼����
	private String uid	;//�������û�����
	private String oid;//�����������������
	private String sys_user_idcard	;//	�����û����֤
	private String area_code	;//	�����û��������
	private String nation	;//	�����û�����
	private String sex	;//	�����û��Ա�1�� 2Ů
	private String birthday	;//	�����û�����
	private String politicsface	;//	�����û�������ò
	private String headpicture	;//	�����û�ͷ���ַ
	private String userstatus	;//	�����û�״̬����1.��ְ 2.��ְ
	private String title	;//	�����û�ְ��
	private String officeaddr	;//	�����û��칫��
	private String mobilephone2	;//	�����û������ֻ�
	private String virtualnum	;//	�����û�����̺�
	private String postcode	;//	�����û��ʱ�

	private String province	;//	�����û�ʡ��
	private String city	;//	�����û�����
	private String jobids;//��λ�б����֮���� "," ����
	private String positionids;//ְ���б����֮���� "," ����

	public String getSys_user_logintimes() {
		return sys_user_logintimes;
	}

	public void setSys_user_logintimes(String sys_user_logintimes) {
		this.sys_user_logintimes = sys_user_logintimes;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSys_user_idcard() {
		return sys_user_idcard;
	}

	public void setSys_user_idcard(String sys_user_idcard) {
		this.sys_user_idcard = sys_user_idcard;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPoliticsface() {
		return politicsface;
	}

	public void setPoliticsface(String politicsface) {
		this.politicsface = politicsface;
	}

	public String getHeadpicture() {
		return headpicture;
	}

	public void setHeadpicture(String headpicture) {
		this.headpicture = headpicture;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOfficeaddr() {
		return officeaddr;
	}

	public void setOfficeaddr(String officeaddr) {
		this.officeaddr = officeaddr;
	}

	public String getMobilephone2() {
		return mobilephone2;
	}

	public void setMobilephone2(String mobilephone2) {
		this.mobilephone2 = mobilephone2;
	}

	public String getVirtualnum() {
		return virtualnum;
	}

	public void setVirtualnum(String virtualnum) {
		this.virtualnum = virtualnum;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSelectnodes() {
		return selectnodes;
	}
	public void setSelectnodes(String selectnodes) {
		this.selectnodes = selectnodes;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<SysPermission> getPermlist() {
		return permlist;
	}
	public void setPermlist(List<SysPermission> permlist) {
		this.permlist = permlist;
	}
	public String getIsvercode() {
		return isvercode;
	}
	public void setIsvercode(String isvercode) {
		this.isvercode = isvercode;
	}

	public String getVerifycode() {
		return verifycode;
	}
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGroupparentid() {
		return groupparentid;
	}
	public void setGroupparentid(String groupparentid) {
		this.groupparentid = groupparentid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsgrant() {
		return isgrant;
	}
	public void setIsgrant(String isgrant) {
		this.isgrant = isgrant;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	public String getAab301() {
		return aab301;
	}
	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}
	public String getAab301name() {
		return aab301name;
	}
	public void setAab301name(String aab301name) {
		this.aab301name = aab301name;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getUsergroupid() {
		return usergroupid;
	}
	public void setUsergroupid(String usergroupid) {
		this.usergroupid = usergroupid;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public String getAbz182() {
		return abz182;
	}
	public void setAbz182(String abz182) {
		this.abz182 = abz182;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getJobids() {
		return jobids;
	}

	public void setJobids(String jobids) {
		this.jobids = jobids;
	}

	public String getPositionids() {
		return positionids;
	}

	public void setPositionids(String positionids) {
		this.positionids = positionids;
	}


	public String getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
}
