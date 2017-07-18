package com.sfxie.services.core.security;

import java.util.Date;

/**
 * services所有模块的登录用户信息
 * @author xiesf
 * @since 2017年7月18日 下午2:19:24
 */
public class SecurityUser {

	private String id;
	
	private String name;
	
	private Date loginTime;

	public String getId() {
		return null == id ? "sfxie" : id;
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

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	
	
}
