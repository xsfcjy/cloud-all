package com.sfxie.extension.logger.dyncpath;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import com.sfxie.extension.logger.exception.Log4jException;

/**
 * 自定义log4j可以动态变化日志记录目录的基类
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午7:09:12 2016年3月17日
 * @example		
 *
 */
public abstract class DyncPathLogger {
	
	private String target;
	/***
	 * 设置日志记录文件具体的目录<br>
	 * 根目录是tomcat根目录:即${catalina.home}
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午7:07:55 2016年3月17日
	 * @example
	 * @return	
	 *
	 */
	public  void setTarget(String target) {
		this.target = target;
	}
	

	protected String getTarget() {
		return target;
	}


	private  Logger getLogger(Class<?> clazz) {
		Logger logger = Logger.getLogger(clazz); // 生成新的Logger
		try{
			logger.removeAllAppenders(); // 清空Appender，特別是不想使用現存實例時一定要初始化
			logger.setLevel(level()); // 设定Logger級別。
			logger.setAdditivity(false); // 设定是否继承父Logger。默认为true，继承root输出；设定false后将不输出root。
			FileAppender appender = (FileAppender) logger.getAppender(clazz.getName());
			if(null==appender){
				appender = new RollingFileAppender();
				PatternLayout layout = new PatternLayout();
				layout.setConversionPattern(null!=conversionPattern()?conversionPattern():"%m%n"); // log的输出形式
				appender.setLayout(layout);
				appender.setFile(System.getProperty("catalina.home")+getTarget()); // log输出路径
				appender.setEncoding("UTF-8"); // log的字符编码
				appender.setAppend(true); // 日志合并方式： true:在已存在log文件后面追加 false:新log覆盖以前的log
				appender.activateOptions(); // 适用当前配置
				appender.setName(clazz.getName());
				logger.addAppender(appender); // 将新的Appender加到Logger中
			}else{
				appender.setFile(System.getProperty("catalina.home")+getTarget()); // log输出路径
			}
		}catch(Exception e){
			throw new Log4jException("定义可以动态变化日志记录目录的log4j错误!");
		}
		return logger;
	}
	
	/**
	 * 打印INFO级别日志
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午7:33:34 2016年3月17日
	 * @example
	 * @param clazz
	 * @param info	
	 *
	 */
	public  void info(Class<?> clazz,Object info){
		getLogger(clazz).info(info);
	}
	/**
	 * 打印ERROR级别日志
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午7:33:34 2016年3月17日
	 * @example
	 * @param clazz
	 * @param info	
	 *
	 */
	public void error(Class<?> clazz,Object info){
		getLogger(clazz).error(info);
	}
	/**
	 * 打印DEBUG级别日志
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午7:33:34 2016年3月17日
	 * @example
	 * @param clazz
	 * @param info	
	 *
	 */
	public void debug(Class<?> clazz,Object info){
		getLogger(clazz).error(info);
	}
	/**
	 * 设置目录级别
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午7:11:44 2016年3月17日
	 * @example
	 * @return	
	 *
	 */
	protected abstract Level level();
	
	/**
	 * 设置日志内容格式
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午7:11:44 2016年3月17日
	 * @example
	 * @return	
	 *
	 */
	protected abstract String conversionPattern();
	
	

}
