package com.sfxie.services.center.util;


import com.sfxie.services.core.security.SecurityUser;
import com.sfxie.utils.StringUtils;

public class ServicesContext {
	
	public static String getDefaultCreateCompanyId(){
		return StringUtils.getUUID();
	}
	public static String getDefaultCreateUserId(){
		return "sfxie";
	}

	/**
	 * 获取登录用户
	 * @return
	 */
	public static SecurityUser getLoginUser(){
		return new SecurityUser();
	}
	
}
