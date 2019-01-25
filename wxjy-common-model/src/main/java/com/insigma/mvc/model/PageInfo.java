package com.insigma.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PageInfo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    protected transient  int curpage = 1;
    protected transient  int limit = 10;
    protected transient  int offset;

    /*protected int page=1;
    protected int size=10;*/

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
