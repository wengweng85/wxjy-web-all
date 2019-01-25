package com.insigma.mvc.model;

public class SAppVersion {

    private String app_id;
    private String bus_uuid;
    private int version;
    private String content;
    private String aae011;
    private String aae012;
    private String ecc064;
    private String aae036;
    private String aae100;

    private String download_url;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getBus_uuid() {
        return bus_uuid;
    }

    public void setBus_uuid(String bus_uuid) {
        this.bus_uuid = bus_uuid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getAae011() {
        return aae011;
    }

    public void setAae011(String aae011) {
        this.aae011 = aae011;
    }

    public String getAae012() {
        return aae012;
    }

    public void setAae012(String aae012) {
        this.aae012 = aae012;
    }

    public String getEcc064() {
        return ecc064;
    }

    public void setEcc064(String ecc064) {
        this.ecc064 = ecc064;
    }

    public String getAae036() {
        return aae036;
    }

    public void setAae036(String aae036) {
        this.aae036 = aae036;
    }

    public String getAae100() {
        return aae100;
    }

    public void setAae100(String aae100) {
        this.aae100 = aae100;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }
}
