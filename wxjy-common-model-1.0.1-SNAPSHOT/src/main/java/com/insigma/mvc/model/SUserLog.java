package com.insigma.mvc.model;

import java.util.Date;

/**
 * Created by wengsh on 2014-12-19.
 */
public class SUserLog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

    private String logid;
	private String optype;
	private Date logstime;
	private Date logetime;
	private String message;
	private String opstatus;
	private String opreason;
	private String aae011;
	private String aae012;
	private String eec117;
	private String eec118;
	private String eec119;
	private String logstime_s;
	private String logetime_s;

	//残联项目专用
	private Date logtime;
	private String stackmsg;
	private String member_id;
	private String ip;
	private String success;


	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getOptype() {
		return optype;
	}

	public void setOptype(String optype) {
		this.optype = optype;
	}

	public Date getLogstime() {
		return logstime;
	}

	public void setLogstime(Date logstime) {
		this.logstime = logstime;
	}

	public Date getLogetime() {
		return logetime;
	}

	public void setLogetime(Date logetime) {
		this.logetime = logetime;
	}

	public String getOpstatus() {
		return opstatus;
	}

	public void setOpstatus(String opstatus) {
		this.opstatus = opstatus;
	}

	public String getOpreason() {
		return opreason;
	}

	public void setOpreason(String opreason) {
		this.opreason = opreason;
	}

	public String getAae011() {
		return aae011;
	}

	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}

	public String getAae012() {
		return aae012;
	}

	public void setAae012(String aae012) {
		this.aae012 = aae012;
	}

	public String getEec117() {
		return eec117;
	}

	public void setEec117(String eec117) {
		this.eec117 = eec117;
	}

	public String getEec118() {
		return eec118;
	}

	public void setEec118(String eec118) {
		this.eec118 = eec118;
	}

	public String getEec119() {
		return eec119;
	}

	public void setEec119(String eec119) {
		this.eec119 = eec119;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLogstime_s() {
		return logstime_s;
	}

	public void setLogstime_s(String logstime_s) {
		this.logstime_s = logstime_s;
	}

	public String getLogetime_s() {
		return logetime_s;
	}

	public void setLogetime_s(String logetime_s) {
		this.logetime_s = logetime_s;
	}

	public Date getLogtime() {
		return logtime;
	}

	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}

	public String getStackmsg() {
		return stackmsg;
	}

	public void setStackmsg(String stackmsg) {
		this.stackmsg = stackmsg;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
}
