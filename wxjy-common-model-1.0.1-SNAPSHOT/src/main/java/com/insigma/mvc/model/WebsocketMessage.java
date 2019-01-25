package com.insigma.mvc.model;

public class WebsocketMessage {

    private String send_username;
    private String to_username;
    private String msg;
    private String sysdate;

    public String getSend_username() {
        return send_username;
    }

    public void setSend_username(String send_username) {
        this.send_username = send_username;
    }

    public String getTo_username() {
        return to_username;
    }

    public void setTo_username(String to_username) {
        this.to_username = to_username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }


}
