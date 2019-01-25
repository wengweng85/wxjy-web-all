package com.insigma.mvc.model;


public class SysSuggestKey implements java.io.Serializable {
	private String key;
	private String k_key;
    private String keyword;
	private String keytype;
	private String id;
	private String name;
	private String s_name;
	private String showname;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKeytype() {
		return keytype;
	}
	public void setKeytype(String keytype) {
		this.keytype = keytype;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getShowname() {
		return showname;
	}
	public void setShowname(String showname) {
		this.showname = showname;
	}


	public String getK_key() {
		return k_key;
	}

	public void setK_key(String k_key) {
		this.k_key = k_key;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
}