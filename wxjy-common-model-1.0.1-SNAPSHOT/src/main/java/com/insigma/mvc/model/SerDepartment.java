package com.insigma.mvc.model;

import java.util.Date;

public class SerDepartment {
    private String departmentId;

    private String departmentAddress;

    private String departmentLongitude;

    private String departmentLatitude;

    private String departmentTel;

    private String departmentLinkman;

    private String userid;

    private Date createtime;

    private String status;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress == null ? null : departmentAddress.trim();
    }

    public String getDepartmentLongitude() {
        return departmentLongitude;
    }

    public void setDepartmentLongitude(String departmentLongitude) {
        this.departmentLongitude = departmentLongitude == null ? null : departmentLongitude.trim();
    }

    public String getDepartmentLatitude() {
        return departmentLatitude;
    }

    public void setDepartmentLatitude(String departmentLatitude) {
        this.departmentLatitude = departmentLatitude == null ? null : departmentLatitude.trim();
    }

    public String getDepartmentTel() {
        return departmentTel;
    }

    public void setDepartmentTel(String departmentTel) {
        this.departmentTel = departmentTel == null ? null : departmentTel.trim();
    }

    public String getDepartmentLinkman() {
        return departmentLinkman;
    }

    public void setDepartmentLinkman(String departmentLinkman) {
        this.departmentLinkman = departmentLinkman == null ? null : departmentLinkman.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}