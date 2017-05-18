package com.sfxie.extension.logger;


import org.apache.log4j.Logger;

import com.sfxie.extension.logger.level.SystemLevel;


/**
 * 日志工具类.
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	上午9:42:24 2016年3月8日
 * @example		
 *
 */
public class LoggerUtil {
	
	public static Logger instance(Class<?> curentClass) {
		return SystemLogger.instance(curentClass);
	}
	public static void info(Object info){
		instance(SystemLogger.class).info(info);
	}
	public static void error(Object info){
		instance(SystemLogger.class).error(info);
	}
	public static void debug(Object info){
		instance(SystemLogger.class).debug(info);
	}
	public static void console(Class<?> curentClass,Object info){
		instance(curentClass).log(SystemLevel.CONSOLE, info);
	}
	public static void info(Class<?> curentClass,Object info){
		instance(curentClass).info(info);
	}
	public static void error(Class<?> curentClass,Object info){
		instance(curentClass).error(info);
	}
	public static void debug(Class<?> curentClass,Object info){
		instance(curentClass).debug(info);
	}
	/**
	 * system层记录日志(保存在system.log文件中)-属于错误级别(INFO)
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午11:01:22 2016年3月8日
	 * @example
	 * @param curentClass
	 * @param info	
	 *
	 */
	public static void system(Class<?> curentClass,Object info){
		try{
			instance(curentClass).log(SystemLevel.SYSTEM, info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * controller层记录日志(保存在controller.log文件中)-属于错误级别(ERROR)
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午11:01:09 2016年3月8日
	 * @example
	 * @param curentClass
	 * @param info	
	 *
	 */
	public static void controller(Class<?> curentClass,Object info){
		try{
			instance(curentClass).log(SystemLevel.CONTROLLER, info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * service层记录日志(保存在service.log文件中)-属于错误级别(ERROR)
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午11:00:44 2016年3月8日
	 * @example
	 * @param curentClass
	 * @param info	
	 *
	 */
	public static void service(Class<?> curentClass,Object info){
		try{
			instance(curentClass).log(SystemLevel.SERVICE, info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * dao层记录日志(保存在dao.log文件中)-属于错误级别(ERROR)
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午10:59:51 2016年3月8日
	 * @example
	 * @param curentClass
	 * @param info	
	 *
	 */
	public static void dao(Class<?> curentClass,Object info){
		try{
			instance(curentClass).log(SystemLevel.DAO, info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 业务信息记录日志(保存在business.log文件中)
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午10:59:06 2016年3月8日
	 * @example
	 * @param curentClass
	 * @param info	
	 *
	 */
	public static void business(Class<?> curentClass,Object info){
		try{
			instance(curentClass).log(SystemLevel.BUSINESS, info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 业务错误信息记录日志(保存在business.error.log文件中)
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	上午10:59:06 2016年3月8日
	 * @example
	 * @param curentClass
	 * @param info	
	 *
	 */
	public static void businessError(Class<?> curentClass,Object info){
		try{
			instance(curentClass).log(SystemLevel.BUSINESSERROR, info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
