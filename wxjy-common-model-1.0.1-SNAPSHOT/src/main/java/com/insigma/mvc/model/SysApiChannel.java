package com.insigma.mvc.model;

import java.util.Date;

public class SysApiChannel extends PageInfo implements java.io.Serializable {
	
	
    private String channelId;

    private String channelCode;

    private String channelName;

    private String channelType;

    private String channelDescribe;

    private String channelIcon;

    private String channelStatus;

    private String userid;

    private Date addtime;

    private String auditid;

    private Date audittime;

    private String channelAppkey;

    private String channelAppSecret;
    
    private String[]  a_channelType;

    private String[]  a_channelStatus;
    
    private String selectnodes;

    public String[] getA_channelType() {
		return a_channelType;
	}

	public void setA_channelType(String[] a_channelType) {
		this.a_channelType = a_channelType;
	}

	public String[] getA_channelStatus() {
		return a_channelStatus;
	}

	public void setA_channelStatus(String[] a_channelStatus) {
		this.a_channelStatus = a_channelStatus;
	}

	public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType == null ? null : channelType.trim();
    }

    public String getChannelDescribe() {
        return channelDescribe;
    }

    public void setChannelDescribe(String channelDescribe) {
        this.channelDescribe = channelDescribe == null ? null : channelDescribe.trim();
    }

    public String getChannelIcon() {
        return channelIcon;
    }

    public void setChannelIcon(String channelIcon) {
        this.channelIcon = channelIcon == null ? null : channelIcon.trim();
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus == null ? null : channelStatus.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getChannelAppkey() {
        return channelAppkey;
    }

    public void setChannelAppkey(String channelAppkey) {
        this.channelAppkey = channelAppkey == null ? null : channelAppkey.trim();
    }

	public String getSelectnodes() {
		return selectnodes;
	}

	public void setSelectnodes(String selectnodes) {
		this.selectnodes = selectnodes;
	}

    public String getChannelAppSecret() {
        return channelAppSecret;
    }

    public void setChannelAppSecret(String channelAppSecret) {
        this.channelAppSecret = channelAppSecret;
    }
}