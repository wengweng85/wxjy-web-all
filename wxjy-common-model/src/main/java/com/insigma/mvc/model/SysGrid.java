package com.insigma.mvc.model;

/**
 * 系统框架表之网格信息
 */
public class SysGrid  extends PageInfo implements java.io.Serializable {
	
	private String abz182	;//varchar2(20)			网格编号
	private String aab301	;//	y		行政区划-对应s_group表groupid
	private String ab_bf022	;//	y		上级网格编号
	private String abz181	;//y		所属机构
	private String aae005	;//	y		联系电话
	private String ab_bf023	;//y		网格名称
	private String ab_bf026	 ;//	y		网格级别
	private String ab_bf027	;//	y		协管员数
	private String ab_bf027_cou;
	private String aae100	;//	y		有效标志
	private String aae006	;//	y		联系地址
	private String aae004	;//y		联系人
	private String ab_bf024	 ;//y		监控范围
	private String abf013	;//	y		实有人数
	private String abf014	;//	y		监察员数
	private String abf014_cou;
	private String ab_bf025	;//	y		网格负责人
	private String abf057;//y		排序编号
	private String ab_bf023_1	;// y		网格名称（定位用）
	
	private String id;
	private String parentid;
	private String isParent;
	private String name;
	private String usertype;
	private String usercou;
	private String usercou_div;
	private String isgrid;
	
	public String getUsercou_div() {
		return usercou_div;
	}
	public void setUsercou_div(String usercou_div) {
		this.usercou_div = usercou_div;
	}
	public String getAb_bf027_cou() {
		return ab_bf027_cou;
	}
	public void setAb_bf027_cou(String ab_bf027_cou) {
		this.ab_bf027_cou = ab_bf027_cou;
	}
	public String getAbf014_cou() {
		return abf014_cou;
	}
	public void setAbf014_cou(String abf014_cou) {
		this.abf014_cou = abf014_cou;
	}
	public String getIsgrid() {
		return isgrid;
	}
	public void setIsgrid(String isgrid) {
		this.isgrid = isgrid;
	}
	public String getUsercou() {
		return usercou;
	}
	public void setUsercou(String usercou) {
		this.usercou = usercou;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbz182() {
		return abz182;
	}
	public void setAbz182(String abz182) {
		this.abz182 = abz182;
	}
	public String getAab301() {
		return aab301;
	}
	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}
	public String getAb_bf022() {
		return ab_bf022;
	}
	public void setAb_bf022(String ab_bf022) {
		this.ab_bf022 = ab_bf022;
	}
	public String getAbz181() {
		return abz181;
	}
	public void setAbz181(String abz181) {
		this.abz181 = abz181;
	}
	public String getAae005() {
		return aae005;
	}
	public void setAae005(String aae005) {
		this.aae005 = aae005;
	}
	public String getAb_bf023() {
		return ab_bf023;
	}
	public void setAb_bf023(String ab_bf023) {
		this.ab_bf023 = ab_bf023;
	}
	public String getAb_bf026() {
		return ab_bf026;
	}
	public void setAb_bf026(String ab_bf026) {
		this.ab_bf026 = ab_bf026;
	}
	public String getAb_bf027() {
		return ab_bf027;
	}
	public void setAb_bf027(String ab_bf027) {
		this.ab_bf027 = ab_bf027;
	}
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	public String getAae006() {
		return aae006;
	}
	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}
	public String getAae004() {
		return aae004;
	}
	public void setAae004(String aae004) {
		this.aae004 = aae004;
	}
	public String getAb_bf024() {
		return ab_bf024;
	}
	public void setAb_bf024(String ab_bf024) {
		this.ab_bf024 = ab_bf024;
	}
	public String getAbf013() {
		return abf013;
	}
	public void setAbf013(String abf013) {
		this.abf013 = abf013;
	}
	public String getAbf014() {
		return abf014;
	}
	public void setAbf014(String abf014) {
		this.abf014 = abf014;
	}
	public String getAb_bf025() {
		return ab_bf025;
	}
	public void setAb_bf025(String ab_bf025) {
		this.ab_bf025 = ab_bf025;
	}
	public String getAbf057() {
		return abf057;
	}
	public void setAbf057(String abf057) {
		this.abf057 = abf057;
	}
	public String getAb_bf023_1() {
		return ab_bf023_1;
	}
	public void setAb_bf023_1(String ab_bf023_1) {
		this.ab_bf023_1 = ab_bf023_1;
	}
	
	

}
