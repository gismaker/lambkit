package com.lambkit.plugin.jwt.impl;

import java.util.Date;
import java.util.Set;

import com.lambkit.plugin.jwt.IJwtAble;

public class JwtUser implements IJwtAble {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7591772644123280489L;
	
	private Long userId;
	private String userName;

	private Set<String> roles;
    private Set<String> forces;
    
	@Override
	public Set<String> getRoles() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public Set<String> getForces() {
		// TODO Auto-generated method stub
		return forces;
	}

	@Override
	public Date getLastModifyPasswordTime() {
		// TODO Auto-generated method stub
		return new Date(System.currentTimeMillis() - 60L * 1000L * 60L * 24);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public void setForces(Set<String> forces) {
		this.forces = forces;
	}

}
