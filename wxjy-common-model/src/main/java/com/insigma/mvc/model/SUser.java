package com.insigma.mvc.model;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *  用户帐号表
 * 
 */
public class SUser extends PageInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String userid;
	@NotEmpty(message="用户名不能为空")
	private String username;
	@NotEmpty(message="密码不能为空")
	private String password;
	private transient String oldpassword;
	private String usertype;
	private String email;
	private String mobile;
	private long logintimes;
	private String lastlogintime;
	private String lastloginip;
	private String enabled;
	private String baseinfoid;
	private String isblacklist;
	private String reason;
	private transient String openId;
	private String fromaddr;
	private String ismainaccount;
	private String aae013;

	private String name;
	private transient  String isvercode;
	
	private transient  String newpassword;
	private transient  String confirmpassword;

	private transient  String verifycode;
	private transient  String mobileverifycode;
	private transient  String token;
	// 凭证有效期，单位：毫秒
	private transient  long expires;
	private transient  String refresh_token;
	private transient  String childuserid;
	
	private transient  String isgrant;
	private transient  String iscertified;
	
	private transient  String usergroupid;
	private transient  String groupid;
	private transient  String cnname;
	private transient  String groupname;
	/**
	 * 登录类型 1:用户密码登录; 2:短信验证码登录; 3:手机号登录
	 */
	private transient  String logintype;
	private transient  String aac002;
	private transient  String aac003;
	private transient  String eac012;

	private transient  List<SPermission> permlist;
	private transient  List<SPermission> spermlist;

	private transient  String aab301name;

	//残联项目增加
	private transient  String  s_user_create_by;
    private transient  String  uid;

    //人力资源项目
	private transient  String groupparentid;
	private transient  String type;
	private transient  String aab301;
	private transient  String aab998;
	private transient  Date createdate;
	private transient  boolean issetpwd;

	private transient  String salt;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public long getLogintimes() {
		return logintimes;
	}

	public void setLogintimes(long logintimes) {
		this.logintimes = logintimes;
	}

	public String getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getBaseinfoid() {
		return baseinfoid;
	}

	public void setBaseinfoid(String baseinfoid) {
		this.baseinfoid = baseinfoid;
	}

	public String getIsblacklist() {
		return isblacklist;
	}

	public void setIsblacklist(String isblacklist) {
		this.isblacklist = isblacklist;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getFromaddr() {
		return fromaddr;
	}

	public void setFromaddr(String fromaddr) {
		this.fromaddr = fromaddr;
	}

	public String getIsmainaccount() {
		return ismainaccount;
	}

	public void setIsmainaccount(String ismainaccount) {
		this.ismainaccount = ismainaccount;
	}

	public String getAae013() {
		return aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsvercode() {
		return isvercode;
	}

	public void setIsvercode(String isvercode) {
		this.isvercode = isvercode;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getMobileverifycode() {
		return mobileverifycode;
	}

	public void setMobileverifycode(String mobileverifycode) {
		this.mobileverifycode = mobileverifycode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getChilduserid() {
		return childuserid;
	}

	public void setChilduserid(String childuserid) {
		this.childuserid = childuserid;
	}

	public String getIsgrant() {
		return isgrant;
	}

	public void setIsgrant(String isgrant) {
		this.isgrant = isgrant;
	}

	public String getIscertified() {
		return iscertified;
	}

	public void setIscertified(String iscertified) {
		this.iscertified = iscertified;
	}

	public String getUsergroupid() {
		return usergroupid;
	}

	public void setUsergroupid(String usergroupid) {
		this.usergroupid = usergroupid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getLogintype() {
		return logintype;
	}

	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}

	public String getAac002() {
		return aac002;
	}

	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}

	public String getAac003() {
		return aac003;
	}

	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}

	public String getEac012() {
		return eac012;
	}

	public void setEac012(String eac012) {
		this.eac012 = eac012;
	}

	public List<SPermission> getPermlist() {
		return permlist;
	}

	public void setPermlist(List<SPermission> permlist) {
		this.permlist = permlist;
	}

	public List<SPermission> getSpermlist() {
		return spermlist;
	}

	public void setSpermlist(List<SPermission> spermlist) {
		this.spermlist = spermlist;
	}

	public String getAab301name() {
		return aab301name;
	}

	public void setAab301name(String aab301name) {
		this.aab301name = aab301name;
	}

	public String getS_user_create_by() {
		return s_user_create_by;
	}

	public void setS_user_create_by(String s_user_create_by) {
		this.s_user_create_by = s_user_create_by;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public String getAab301() {
		return aab301;
	}

	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}

	public String getAab998() {
		return aab998;
	}

	public void setAab998(String aab998) {
		this.aab998 = aab998;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public boolean isIssetpwd() {
		return issetpwd;
	}

	public void setIssetpwd(boolean issetpwd) {
		this.issetpwd = issetpwd;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}
}
