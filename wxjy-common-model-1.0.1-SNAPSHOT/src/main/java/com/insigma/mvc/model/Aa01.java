package com.insigma.mvc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//@Entity
//@Table(name = "aa01")
public class Aa01 implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
    // Fields   
	//@Id
    private String aaa001;

	private String aaa002;

    private String aaa105;
    
    private String aaa005;

	public String getAaa001() {
		return aaa001;
	}

	public String getAaa002() {
		return aaa002;
	}

	public void setAaa002(String aaa002) {
		this.aaa002 = aaa002;
	}

	public String getAaa105() {
		return aaa105;
	}

	public void setAaa105(String aaa105) {
		this.aaa105 = aaa105;
	}

	public void setAaa001(String aaa001) {
		this.aaa001 = aaa001;
	}

	public String getAaa005() {
		return aaa005;
	}

	public void setAaa005(String aaa005) {
		this.aaa005 = aaa005;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}