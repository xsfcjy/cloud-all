package com.sfxie.services.center.util;


import com.sfxie.services.core.security.SecurityUser;

public class ServicesContext {
	
	public static String getDefaultCreateCompanyCode(){
		return "00000000000000000000000000000000";
	}
	public static String getDefaultCreateUserId(){
		return "sfxie";
	}
	
	/**
	 * 根据公司编码获取转化后的分区字段值
	 * @param companyCode
	 * 			公司编码
	 * @return
	 */
	public static String getPartitionCompany(String companyCode){
		return "0";
	}

	/**
	 * 获取登录用户
	 * @return
	 */
	public static SecurityUser getLoginUser(){
		return new SecurityUser();
	}
	
}
