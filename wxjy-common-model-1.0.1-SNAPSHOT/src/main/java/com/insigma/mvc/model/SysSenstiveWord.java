package com.insigma.mvc.model;

/**
 * Created by wengsh on 2018/3/12.
 */
public class SysSenstiveWord  extends PageInfo implements java.io.Serializable  {

    private String wordid;
    private String wordname;


    public String getWordid() {
        return wordid;
    }

    public void setWordid(String wordid) {
        this.wordid = wordid;
    }

    public String getWordname() {
        return wordname;
    }

    public void setWordname(String wordname) {
        this.wordname = wordname;
    }
}
