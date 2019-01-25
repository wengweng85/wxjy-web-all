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
    private String logtime_string;
    private String interface_id;
    private String channel_name;
    private String interface_name;

    public String getLogtime_string() {
        return logtime_string;
    }

    public void setLogtime_string(String logtime_string) {
        this.logtime_string = logtime_string;
    }

    public String getInterface_name() {
        return interface_name;
    }

    public void setInterface_name(String interface_name) {
        this.interface_name = interface_name;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

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