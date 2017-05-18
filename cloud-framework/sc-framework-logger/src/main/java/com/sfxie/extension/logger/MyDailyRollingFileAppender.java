package com.sfxie.extension.logger;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;
/**
 * 自定义日记处理类
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午7:10:14 2015年10月22日
 * @example		
 *
 */
public class MyDailyRollingFileAppender extends DailyRollingFileAppender {
	
	
    @Override
	public boolean isAsSevereAsThreshold(Priority priority) {  
		  //只判断是否相等，而不判断优先级   
		return this.getThreshold().equals(priority);  
	}  
}
