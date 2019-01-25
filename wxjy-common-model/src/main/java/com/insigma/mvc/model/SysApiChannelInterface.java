package com.insigma.mvc.model;

import java.util.Date;

public class SysApiChannelInterface {
    private String channelInterfaceId;

    private String channelId;

    private String interfaceId;

    private String isvalid;

    private String userid;

    private Date addtime;

    public String getChannelInterfaceId() {
        return channelInterfaceId;
    }

    public void setChannelInterfaceId(String channelInterfaceId) {
        this.channelInterfaceId = channelInterfaceId == null ? null : channelInterfaceId.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId == null ? null : interfaceId.trim();
    }

    
    public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
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
}