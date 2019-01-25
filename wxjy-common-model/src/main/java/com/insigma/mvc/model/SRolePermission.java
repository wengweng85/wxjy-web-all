package com.insigma.mvc.model;

import java.util.List;


/**
 *  ”√ªß±Ì
 * 
 */
public class SRolePermission  implements java.io.Serializable {
	private String username;
	List<SRole> roles;
	List<SPermission> permissions;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<SRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SRole> roles) {
		this.roles = roles;
	}

	public List<SPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SPermission> permissions) {
		this.permissions = permissions;
	}
}
