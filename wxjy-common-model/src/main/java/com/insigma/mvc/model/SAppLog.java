package com.insigma.mvc.model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class SAppLog extends PageInfo implements java.io.Serializable {

    private String logid;
    private String appkey;
    private String url;
    private Date logtime;
    private String interface_id;

    public String getInterface_id() {
        return interface_id;
    }

    public void setInterface_id(String interface_id) {
        this.interface_id = interface_id;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }
}