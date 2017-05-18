/****************************************************************
 ***        Copyright © EASTCOMPEACE        2012.08.22        ***
 ****************************************************************/

package com.sfxie.utils;

import org.apache.log4j.Logger;

/**
 * 日志工具类.
 * 
 * @author xieshengfeng
 *
 */
public class LoggerUtil {
	/**
	 * <code>log</code> 日志.
	 */
	private static Logger logger; 
	 
	public static Logger instance(Class<?> curentClass) {
		if (logger == null) {
			logger = Logger.getLogger(curentClass.getName());
		}
		
		return logger;
	}
}
