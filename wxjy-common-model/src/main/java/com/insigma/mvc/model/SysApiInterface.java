package com.insigma.mvc.model;

import java.util.Date;

public class SysApiInterface   extends PageInfo implements java.io.Serializable {
    private String interfaceId;

    private String interfaceCode;

    private String interfaceUrl;

    private String interfaceName;

    private String isoffical;

    private String interfaceType;
    
    private String interfaceDetailType;
    
    private String interfaceNetwork;

    private String interfacePotocol;

    private String interfaceStatus;
    
    private String[]  a_interfaceType;
    
    private String[]  a_interfaceNetwork;

    private String[]  a_interfacePotocol;

    private String[]  a_interfaceStatus;
    
    private String id;
    private String pid;
    private String name;
    private String checked;
    
    private String channelId;
    
    
    public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getInterfaceDetailType() {
		return interfaceDetailType;
	}

	public void setInterfaceDetailType(String interfaceDetailType) {
		this.interfaceDetailType = interfaceDetailType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String[] getA_interfaceType() {
		return a_interfaceType;
	}

	public void setA_interfaceType(String[] a_interfaceType) {
		this.a_interfaceType = a_interfaceType;
	}

	public String[] getA_interfaceNetwork() {
		return a_interfaceNetwork;
	}

	public void setA_interfaceNetwork(String[] a_interfaceNetwork) {
		this.a_interfaceNetwork = a_interfaceNetwork;
	}

	public String[] getA_interfacePotocol() {
		return a_interfacePotocol;
	}

	public void setA_interfacePotocol(String[] a_interfacePotocol) {
		this.a_interfacePotocol = a_interfacePotocol;
	}

	public String[] getA_interfaceStatus() {
		return a_interfaceStatus;
	}

	public void setA_interfaceStatus(String[] a_interfaceStatus) {
		this.a_interfaceStatus = a_interfaceStatus;
	}

	private String userid;

    private Date addtime;

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getInterfaceCode() {
		return interfaceCode;
	}

	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}

	public String getInterfaceUrl() {
		return interfaceUrl;
	}

	public void setInterfaceUrl(String interfaceUrl) {
		this.interfaceUrl = interfaceUrl;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getIsoffical() {
		return isoffical;
	}

	public void setIsoffical(String isoffical) {
		this.isoffical = isoffical;
	}

	public String getInterfaceNetwork() {
		return interfaceNetwork;
	}

	public void setInterfaceNetwork(String interfaceNetwork) {
		this.interfaceNetwork = interfaceNetwork;
	}

	public String getInterfacePotocol() {
		return interfacePotocol;
	}

	public void setInterfacePotocol(String interfacePotocol) {
		this.interfacePotocol = interfacePotocol;
	}


	public String getInterfaceStatus() {
		return interfaceStatus;
	}

	public void setInterfaceStatus(String interfaceStatus) {
		this.interfaceStatus = interfaceStatus;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	

  
}