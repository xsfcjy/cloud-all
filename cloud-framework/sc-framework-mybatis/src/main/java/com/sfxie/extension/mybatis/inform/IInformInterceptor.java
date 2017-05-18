package com.sfxie.extension.mybatis.inform;

/**
 * mybatis 执行sql通知拦截器接口
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午1:00:53 2016年3月15日
 * @example		
 *
 */
public interface IInformInterceptor extends IInformInterceptCondition{

	/**
	 * 
	 * @TODO	达到条件通知方法
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午9:27:19 2016年3月15日
	 * @example
	 * @param type
	 * @param sql
	 * @param params
	 * @param startTimeMillionSecord
	 * @param endTimeMillionSecord
	 * @return	返回值判断是否继续执行下一个通知拦截器.返回true则表示继续,false表示中止
	 *
	 */
	public boolean inform(String type,String sql,Long startTimeMillionSecord,Long endTimeMillionSecord);
}
