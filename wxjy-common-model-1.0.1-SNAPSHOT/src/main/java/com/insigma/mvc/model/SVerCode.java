package com.insigma.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by LENOVO on 2017/10/19.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SVerCode implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String vercodeid;
    private String optype;
    private String mobile;
    private String vercode;
    private Date createdate;
    private Date enddate;
    private String userlogid;
    private String aae100;
    private String inputcode;
    private Date inputtime;
    private String verresult;

    private String message;//短信内容
    private String messagetype;//消息类型

    public String getVercodeid() {
        return vercodeid;
    }

    public void setVercodeid(String vercodeid) {
        this.vercodeid = vercodeid;
    }

    public String getOptype() {
        return optype;
    }

    public void setOptype(String optype) {
        this.optype = optype;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public String getUserlogid() {
        return userlogid;
    }

    public void setUserlogid(String userlogid) {
        this.userlogid = userlogid;
    }

    public String getAae100() {
        return aae100;
    }

    public void setAae100(String aae100) {
        this.aae100 = aae100;
    }

    public String getInputcode() {
        return inputcode;
    }

    public void setInputcode(String inputcode) {
        this.inputcode = inputcode;
    }

    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public String getVerresult() {
        return verresult;
    }

    public void setVerresult(String verresult) {
        this.verresult = verresult;
    }
}
