package com.insigma.mvc.model;

import java.util.List;

public class SolrQueryBean  {
	
	private String q;
	private int start = 0;
	private int rows = 10;
	private List<SolrResultBean> list;
	private Long total;
	private int qttime;
	private long numFound;
	private int curpage;
	private int limit;


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

	private String datatype;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<SolrResultBean> getList() {
		return list;
	}

	public void setList(List<SolrResultBean> list) {
		this.list = list;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public int getQttime() {
		return qttime;
	}

	public void setQttime(int qttime) {
		this.qttime = qttime;
	}

	public long getNumFound() {
		return numFound;
	}

	public void setNumFound(long numFound) {
		this.numFound = numFound;
	}

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }
}
