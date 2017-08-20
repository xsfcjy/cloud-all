package com.sfxie.services.center.util;


import com.sfxie.services.core.security.SecurityUser;

public class ServicesContext {
	
	/**
	 * 是否启动数据库分区功能
	 */
	private static boolean isPartition = true;
	
	public static String getDefaultCreateCompanyCode(){
		return "test_company2";
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
		if(!isPartition)
			return null;
		if(null!= companyCode && (companyCode.equals("test_sfxie")
				|| companyCode.equals("test_organization1"))
		){
			return "1";
		}
		return "0";
	}
	/**
	 * 获取登录用户的公司分区字段值
	 * @return
	 */
	public static String getUserDefaultPartitionCompany(){
		if(!isPartition)
			return null;
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
