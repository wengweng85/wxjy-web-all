package com.insigma.mvc.model;


/**
 * 邮件发送记录
 * Created by liuds on 2017/11/15.
 */
public class SEmailLog implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    //消息类型
    private String messagetype;
    private String id;
    private String optype;
    private String send_userid;
    private String receive_userid;
    private String receive_email;
    private String sendtime;
    private String title;
    private String content;
    private String success;
    private String failreason;
    private String userlogid;
    //简历所属人的个人内码
    private String resume_eec001;

    //账号属性
    private String userid;
    private String baseinfoid;



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

    public String getReceive_email() {
        return receive_email;
    }

    public void setReceive_email(String receive_email) {
        this.receive_email = receive_email;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getResume_eec001() {
        return resume_eec001;
    }

    public void setResume_eec001(String resume_eec001) {
        this.resume_eec001 = resume_eec001;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBaseinfoid() {
        return baseinfoid;
    }

    public void setBaseinfoid(String baseinfoid) {
        this.baseinfoid = baseinfoid;
    }
}
