package com.sfxie.extension.mybatis.inform;

/**
 *  mybatis 执行sql判断是否启动通知拦截器接口
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午1:01:36 2016年3月15日
 * @example		
 *
 */
public interface IInformInterceptCondition {

	/**
	 * 判断是否拦截
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午7:13:13 2016年3月16日
	 * @example
	 * @param type
	 * @param startTimeMillionSecord
	 * @param endTimeMillionSecord
	 * @return	
	 *
	 */
	public boolean canIntercepted(String type,Long startTimeMillionSecord,Long endTimeMillionSecord);
}
