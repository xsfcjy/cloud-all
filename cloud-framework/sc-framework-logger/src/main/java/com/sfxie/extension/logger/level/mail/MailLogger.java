package com.sfxie.extension.logger.level.mail;

import com.sfxie.extension.logger.SystemLogger;
/**
 * 邮件发送logger
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午2:07:17 2016年3月10日
 * @example		
 *
 */
public class MailLogger {
	/**
	 * INFO级别发送邮件 
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午10:59:06 2016年3月8日
	 * @example
	 * @param curentClass
	 * @param info	
	 *
	 */
	public static void mail(Class<?> curentClass,Object info){
		SystemLogger.instance(curentClass).log(MailLevel.MAILINFO, info);
	}
	/**
	 * 错误级别发送邮件 
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午10:59:06 2016年3月8日
	 * @example
	 * @param curentClass
	 * @param info	
	 *
	 */
	public static void mailError(Class<?> curentClass,Object info){
		SystemLogger.instance(curentClass).log(MailLevel.MAILERROR, info);
	}
}
