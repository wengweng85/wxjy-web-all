package com.insigma.mvc.model;

import java.util.Date;

public class Ac60ExcelTemp extends PageInfo implements java.io.Serializable {

	private String excel_temp_id; // varchar2(36) 导入临时表之临时表主键
	private String excel_batch_number; // 导入临时表之导入批次号
	private String aac003; // 导入临时表之姓名
	private String aac002; // 导入临时表之身份证号
	private String aac010;// 导入临时表之劳动者户口所在地
	private String aac004; //导入临时表之性别
	private String aac005; //导入临时表之民族
	private String aac007; //导入临时表之年龄
	private String aac033; //导入临时表之健康状况
	private String aac024; //导入临时表之政治面貌
	private String aac011; //导入临时表之学历代码
	private String aae006; //导入临时表之联系电话
	private String aae015; //导入临时表之劳动技能
	private String aac029; //导入临时表之户主姓名
	private String aac030; //导入临时表之是否在校生
	private String aac031; //导入临时表之主要致贫原因
	private String adc001; //导入临时表之就业新式
	private String adc002; //导入临时表之就业地域
	private String adc003; //导入临时表之就业地
	private String adc004; //导入临时表之就业工种
	private String adc005; //导入临时表之就业时间
	private String adc006; //导入临时表之是否签订劳动合同或协议
	private String adc007; //导入临时表之是否参加社会保险
	private String adc008; //导入临时表之月均工资
	private String adc009; //导入临时表之岗位类型
	private String adc010; //导入临时表之公益性岗位安置地址
	private String adc011; //导入临时表之公益性岗位安置单位名称
	private String adc012; //导入临时表之安置时间
	private String adc013; //导入临时表之岗位名称
	private String adc014; //导入临时表之是否参加就业培训
	private String adc015; //导入临时表之培训类型
	private String adc016; //导入临时表之培训开始时间
	private String adc017; //导入临时表之培训截止时间
	private String adc018; //导入临时表之培训类别
	private String adc019; //导入临时表之取得证书
	private String adc020; //导入临时表之培训补贴（元）
	private String adc021; //导入临时表之是否就业
	private String adc022; //导入临时表之创业时间
	private String adc023; //导入临时表之是否参加创业培训
	private String adc024; //导入临时表之创业类型
	private String adc025; //导入临时表之企业或实体地址
	private String adc026; //导入临时表之企业或实体名称
	private String adc027; //导入临时表之产业类别
	private String adc028; //导入临时表之是否享受小额担保贷款
	private String adc029; //导入临时表之贷款金额（万元）
	private String adc030; //导入临时表之吸纳劳动力人数
	private String adc031; //导入临时表之是否在工商部门注册登记
	private String adc032; //导入临时表之是否有就业愿望
	private String adc033; //导入临时表之就业意向地
	private String adc034; //导入临时表之期望月薪（元）
	private String adc035; //导入临时表之就业服务需求
	private String adc036; //导入临时表之提供就业政策咨询次数(次)
	private String adc037; //导入临时表之提供就业信息次数（次）
	private String adc038; //导入临时表之提供职业指导与介绍次数（次）
	private String adc039; //导入临时表之提供培训次数（次）
	private String adc040; //导入临时表之提供创业服务次数(次)
	private String adc041; //导入临时表之是否享受职业介绍补贴
	private String adc042; //导入临时表之是否享受社会保险补贴
	private String adc043; //导入临时表之是否享受岗位补贴
	private String adc044; //导入临时表之是否享受其他政策扶持
	private String excel_isvalid;// 导入临时表之是否正确数据
	private Long excel_rownum;// 导入临时表之行数
	private Date excel_impdate;// 导入临时表之导入日期
	private String excel_errormsg;//导入临时表之错误数据原因
	private String excel_isop;//导入临时表之是否已经处理
	private Date excel_opdate;// 导入临时表之处理日期
	private String excel_errormsg_groupby_total;//根据错误类型分组
	
	
	public String getExcel_errormsg_groupby_total() {
		return excel_errormsg_groupby_total;
	}
	public void setExcel_errormsg_groupby_total(String excel_errormsg_groupby_total) {
		this.excel_errormsg_groupby_total = excel_errormsg_groupby_total;
	}
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
	public String getAac010() {
		return aac010;
	}
	public void setAac010(String aac010) {
		this.aac010 = aac010;
	}
	public String getAac004() {
		return aac004;
	}
	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}
	public String getAac005() {
		return aac005;
	}
	public void setAac005(String aac005) {
		this.aac005 = aac005;
	}
	public String getAac007() {
		return aac007;
	}
	public void setAac007(String aac007) {
		this.aac007 = aac007;
	}
	public String getAac033() {
		return aac033;
	}
	public void setAac033(String aac033) {
		this.aac033 = aac033;
	}
	
	public String getAac024() {
		return aac024;
	}
	public void setAac024(String aac024) {
		this.aac024 = aac024;
	}
	public String getAac011() {
		return aac011;
	}
	public void setAac011(String aac011) {
		this.aac011 = aac011;
	}
	public String getAae006() {
		return aae006;
	}
	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}

	public String getAae015() {
		return aae015;
	}
	public void setAae015(String aae015) {
		this.aae015 = aae015;
	}
	public String getAac029() {
		return aac029;
	}
	public void setAac029(String aac029) {
		this.aac029 = aac029;
	}
	public String getAac030() {
		return aac030;
	}
	public void setAac030(String aac030) {
		this.aac030 = aac030;
	}
	public String getAac031() {
		return aac031;
	}
	public void setAac031(String aac031) {
		this.aac031 = aac031;
	}
	public String getAdc001() {
		return adc001;
	}
	public void setAdc001(String adc001) {
		this.adc001 = adc001;
	}
	public String getAdc002() {
		return adc002;
	}
	public void setAdc002(String adc002) {
		this.adc002 = adc002;
	}
	public String getAdc003() {
		return adc003;
	}
	public void setAdc003(String adc003) {
		this.adc003 = adc003;
	}
	public String getAdc004() {
		return adc004;
	}
	public void setAdc004(String adc004) {
		this.adc004 = adc004;
	}
	public String getAdc005() {
		return adc005;
	}
	public void setAdc005(String adc005) {
		this.adc005 = adc005;
	}
	public String getAdc006() {
		return adc006;
	}
	public void setAdc006(String adc006) {
		this.adc006 = adc006;
	}
	public String getAdc007() {
		return adc007;
	}
	public void setAdc007(String adc007) {
		this.adc007 = adc007;
	}
	public String getAdc008() {
		return adc008;
	}
	public void setAdc008(String adc008) {
		this.adc008 = adc008;
	}
	public String getAdc009() {
		return adc009;
	}
	public void setAdc009(String adc009) {
		this.adc009 = adc009;
	}
	public String getAdc010() {
		return adc010;
	}
	public void setAdc010(String adc010) {
		this.adc010 = adc010;
	}
	public String getAdc011() {
		return adc011;
	}
	public void setAdc011(String adc011) {
		this.adc011 = adc011;
	}
	public String getAdc012() {
		return adc012;
	}
	public void setAdc012(String adc012) {
		this.adc012 = adc012;
	}
	public String getAdc013() {
		return adc013;
	}
	public void setAdc013(String adc013) {
		this.adc013 = adc013;
	}
	public String getAdc014() {
		return adc014;
	}
	public void setAdc014(String adc014) {
		this.adc014 = adc014;
	}
	public String getAdc015() {
		return adc015;
	}
	public void setAdc015(String adc015) {
		this.adc015 = adc015;
	}
	public String getAdc016() {
		return adc016;
	}
	public void setAdc016(String adc016) {
		this.adc016 = adc016;
	}
	public String getAdc017() {
		return adc017;
	}
	public void setAdc017(String adc017) {
		this.adc017 = adc017;
	}
	public String getAdc018() {
		return adc018;
	}
	public void setAdc018(String adc018) {
		this.adc018 = adc018;
	}
	public String getAdc019() {
		return adc019;
	}
	public void setAdc019(String adc019) {
		this.adc019 = adc019;
	}
	public String getAdc020() {
		return adc020;
	}
	public void setAdc020(String adc020) {
		this.adc020 = adc020;
	}
	public String getAdc021() {
		return adc021;
	}
	public void setAdc021(String adc021) {
		this.adc021 = adc021;
	}
	public String getAdc022() {
		return adc022;
	}
	public void setAdc022(String adc022) {
		this.adc022 = adc022;
	}
	public String getAdc023() {
		return adc023;
	}
	public void setAdc023(String adc023) {
		this.adc023 = adc023;
	}
	public String getAdc024() {
		return adc024;
	}
	public void setAdc024(String adc024) {
		this.adc024 = adc024;
	}
	public String getAdc025() {
		return adc025;
	}
	public void setAdc025(String adc025) {
		this.adc025 = adc025;
	}
	public String getAdc026() {
		return adc026;
	}
	public void setAdc026(String adc026) {
		this.adc026 = adc026;
	}
	public String getAdc027() {
		return adc027;
	}
	public void setAdc027(String adc027) {
		this.adc027 = adc027;
	}
	public String getAdc028() {
		return adc028;
	}
	public void setAdc028(String adc028) {
		this.adc028 = adc028;
	}
	public String getAdc029() {
		return adc029;
	}
	public void setAdc029(String adc029) {
		this.adc029 = adc029;
	}
	public String getAdc030() {
		return adc030;
	}
	public void setAdc030(String adc030) {
		this.adc030 = adc030;
	}
	public String getAdc031() {
		return adc031;
	}
	public void setAdc031(String adc031) {
		this.adc031 = adc031;
	}
	public String getAdc032() {
		return adc032;
	}
	public void setAdc032(String adc032) {
		this.adc032 = adc032;
	}
	public String getAdc033() {
		return adc033;
	}
	public void setAdc033(String adc033) {
		this.adc033 = adc033;
	}
	public String getAdc034() {
		return adc034;
	}
	public void setAdc034(String adc034) {
		this.adc034 = adc034;
	}
	public String getAdc035() {
		return adc035;
	}
	public void setAdc035(String adc035) {
		this.adc035 = adc035;
	}
	public String getAdc036() {
		return adc036;
	}
	public void setAdc036(String adc036) {
		this.adc036 = adc036;
	}
	public String getAdc037() {
		return adc037;
	}
	public void setAdc037(String adc037) {
		this.adc037 = adc037;
	}
	public String getAdc038() {
		return adc038;
	}
	public void setAdc038(String adc038) {
		this.adc038 = adc038;
	}
	public String getAdc039() {
		return adc039;
	}
	public void setAdc039(String adc039) {
		this.adc039 = adc039;
	}
	public String getAdc040() {
		return adc040;
	}
	public void setAdc040(String adc040) {
		this.adc040 = adc040;
	}
	public String getAdc041() {
		return adc041;
	}
	public void setAdc041(String adc041) {
		this.adc041 = adc041;
	}
	public String getAdc042() {
		return adc042;
	}
	public void setAdc042(String adc042) {
		this.adc042 = adc042;
	}
	public String getAdc043() {
		return adc043;
	}
	public void setAdc043(String adc043) {
		this.adc043 = adc043;
	}
	public String getAdc044() {
		return adc044;
	}
	public void setAdc044(String adc044) {
		this.adc044 = adc044;
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
	
	
	

}