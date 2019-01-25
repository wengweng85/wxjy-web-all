package com.insigma.mvc.model;

import java.util.Date;

/**
 * Created by LENOVO on 2017/8/28.
 */
public class SFileUpload implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private String logid;
    private String businessid;
    private String businesstype;
    private String filename;
    private String filesize;
    private String filetype;
    private String filepath;
    private String filestatus;
    private String filefinalpath;
    private String filetime;
    private String filelength;
    private String fileuploadtype;
    private String reason;


    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilestatus() {
        return filestatus;
    }

    public void setFilestatus(String filestatus) {
        this.filestatus = filestatus;
    }

    public String getFilefinalpath() {
        return filefinalpath;
    }

    public void setFilefinalpath(String filefinalpath) {
        this.filefinalpath = filefinalpath;
    }

    public String getFiletime() {
        return filetime;
    }

    public void setFiletime(String filetime) {
        this.filetime = filetime;
    }

    public String getFilelength() {
        return filelength;
    }

    public void setFilelength(String filelength) {
        this.filelength = filelength;
    }

    public String getFileuploadtype() {
        return fileuploadtype;
    }

    public void setFileuploadtype(String fileuploadtype) {
        this.fileuploadtype = fileuploadtype;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
