package com.sfxie.services.center.util;


import com.sfxie.utils.StringUtils;

public class ServiceContext {

	public static String getDefaultCreateCompanyId(){
		return StringUtils.getUUID();
	}
	public static String getDefaultCreateUserId(){
		return "testUser";
	}
}
