package com.insigma.mvc.model;

/**
 * 短信发送记录
 * Created by liuds on 2017/11/15.
 */
public class SSmsLog implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String messagetype;//消息类型
    private String id;
    private String optype;
    private String send_userid;
    private String receive_userid;
    private String receive_mobile;
    private String sendtime;
    private String content;
    private String success;
    private String failreason;
    private String userlogid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptype() {
        return optype;
    }

    public void setOptype(String optype) {
        this.optype = optype;
    }

    public String getSend_userid() {
        return send_userid;
    }

    public void setSend_userid(String send_userid) {
        this.send_userid = send_userid;
    }

    public String getReceive_userid() {
        return receive_userid;
    }

    public void setReceive_userid(String receive_userid) {
        this.receive_userid = receive_userid;
    }

    public String getReceive_mobile() {
        return receive_mobile;
    }

    public void setReceive_mobile(String receive_mobile) {
        this.receive_mobile = receive_mobile;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFailreason() {
        return failreason;
    }

    public void setFailreason(String failreason) {
        this.failreason = failreason;
    }

    public String getUserlogid() {
        return userlogid;
    }

    public void setUserlogid(String userlogid) {
        this.userlogid = userlogid;
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }
}
