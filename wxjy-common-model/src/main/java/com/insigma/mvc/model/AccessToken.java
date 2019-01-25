package com.insigma.mvc.model;

/**
 *
 *  jwt access token
 *
 */
public class AccessToken implements java.io.Serializable {

    // 接口访问凭证
    private String token;

    private String userid;

    private String username;

    private String usertype;

    private String name;

    private String password;

	// 凭证有效期，单位：毫秒
	private long expires;

	public AccessToken(){

	}

	public AccessToken(String username, String userid, String name) {
		this.username = username;
		this.userid = userid;
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}
}
