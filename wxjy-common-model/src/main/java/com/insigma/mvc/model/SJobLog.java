package com.insigma.mvc.model;

import java.sql.Timestamp;
/**
 * Created by Administrator on 2017/11/17.
 */
public class SJobLog implements java.io.Serializable {

    private String Joblogid;
    private Timestamp Jobstarttime;
    private String Jobdetail;
    private String Jobtype;
    private String Success;
    private String Jobsendinfo;
    private Timestamp Jobendtime;

    public String getJoblogid() {
        return Joblogid;
    }

    public void setJoblogid(String joblogid) {
        Joblogid = joblogid;
    }

    public Timestamp getJobstarttime() {
        return Jobstarttime;
    }

    public void setJobstarttime(Timestamp jobstarttime) {
        Jobstarttime = jobstarttime;
    }

    public String getJobdetail() {
        return Jobdetail;
    }

    public void setJobdetail(String jobdetail) {
        Jobdetail = jobdetail;
    }

    public String getJobtype() {
        return Jobtype;
    }

    public void setJobtype(String jobtype) {
        Jobtype = jobtype;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public String getJobsendinfo() {
        return Jobsendinfo;
    }

    public void setJobsendinfo(String jobsendinfo) {
        Jobsendinfo = jobsendinfo;
    }

    public Timestamp getJobendtime() {
        return Jobendtime;
    }

    public void setJobendtime(Timestamp jobendtime) {
        Jobendtime = jobendtime;
    }
}
