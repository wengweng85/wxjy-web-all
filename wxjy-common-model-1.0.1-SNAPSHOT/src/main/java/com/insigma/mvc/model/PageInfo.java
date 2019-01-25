package com.insigma.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PageInfo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    protected int curpage = 1;
    @JsonIgnore
    protected int limit = 10;
    @JsonIgnore
    protected int offset;
    @JsonIgnore
    protected int page=1;
    @JsonIgnore
    protected int size=10;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
