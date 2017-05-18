package com.sfxie.extension.logger.level;

import org.apache.log4j.Level;


/**
 * 
 * @description 自定义系统级别，用来记录系统级日志.<br>
 * 				框架日志(SYSTEM),控制层日志(CONTROLLER),业务日志(SERVICE),数据持久层日志(DAO)<br>
 * 
 * 1. 当 level="DEBUG" ,打印: debug,info,error,system,business,controller,service,dao<br>
 * 2. 当 level="INFO" ,打印: info,error,system,business,controller,service,dao<br>
 * 3. 当 level="ERROR" ,打印: error,controller,service,dao<br>
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	上午9:19:39 2016年3月8日
 * @example		
 *
 */
public class SystemLevel extends Level {
	private static final long serialVersionUID = 7288304330257085144L;

	/** 数字小的级别高 (日志打印的时候,级别低的打印输出一般会同时输出到级别高的日志文件上,但是可以通过配置 filter 来过滤不需要的级别打印)*/
	static private final int SYSTEM_INT = Level.INFO_INT + 1; 		//20001
	static private final int CONTROLLER_INT = Level.ERROR_INT + 1;	//40001
	static private final int SERVICE_INT = Level.ERROR_INT + 2;		//40002
	static private final int DAO_INT = Level.ERROR_INT + 3;  		//40003
	static private final int BUSINESS_INT = Level.INFO_INT + 2;  	//20001
	static private final int BUSINESSERROR_INT = Level.ERROR_INT + 4;  	//40004
	static private final int CONSOLE_INT = Level.INFO_INT + 5;  	//40004
//	error:40000, info:20000 ,debug:10000 

	/**	系统框架层	*/
	private static String SYSTEM_STR = "SYSTEM";
	/**	控制层	*/
	private static String CONTROLLER_STR = "CONTROLLER";
	/**	业务逻辑层	*/
	private static String SERVICE_STR = "SERVICE";
	/**	数据持久层	*/
	private static String DAO_STR = "DAO";
	/**	业务日志记录	*/
	private static String BUSINESS_STR = "BUSINESS";
	/**	业务错误日志记录	*/
	private static String BUSINESSERROR_STR = "BUSINESSERROR";
	/**	控制台打印日志记录(console)	*/
	private static String CONSOLE_STR = "CONSOLE";
	
	/**	系统框架层	*/
	public static final SystemLevel SYSTEM = new SystemLevel(SYSTEM_INT, SYSTEM_STR,5);
	/**	控制层	*/
	public static final SystemLevel CONTROLLER = new SystemLevel(CONTROLLER_INT, CONTROLLER_STR,6);
	/**	业务逻辑层	*/
	public static final SystemLevel SERVICE = new SystemLevel(SERVICE_INT, SERVICE_STR,7);
	/**	数据持久层	*/
	public static final SystemLevel DAO = new SystemLevel(DAO_INT, DAO_STR,8);
	/**	业务日志记录	*/
	public static final SystemLevel BUSINESS = new SystemLevel(BUSINESS_INT, BUSINESS_STR,9);
	/**	业务错误日志记录	*/
	public static final SystemLevel BUSINESSERROR = new SystemLevel(BUSINESSERROR_INT, BUSINESS_STR,10);
	/**	控制台打印日志记录(console)	*/
	public static final SystemLevel CONSOLE = new SystemLevel(CONSOLE_INT, CONSOLE_STR,11);

	protected SystemLevel(int level, String strLevel, int syslogEquiv) {
		super(level, strLevel, syslogEquiv);
	}

	/**
	 * Convert the string passed as argument to a level. If the conversion
	 * fails, then this method returns {@link #CONTROLLER}.
	 */
	public static Level toLevel(String sArg) {
		return (Level) toLevel(sArg, SystemLevel.SYSTEM);
	}

	public static Level toLevel(String sArg, Level defaultValue) {
		if (sArg == null) {
			return defaultValue;
		}
		String stringVal = sArg.toUpperCase();
		
		if(stringVal.equals(SYSTEM_STR)) {
			return SystemLevel.SYSTEM;
		}else if(stringVal.equals(CONTROLLER_STR)) {
			return SystemLevel.CONTROLLER;
		}else if (stringVal.equals(SERVICE_STR)) {
			return SystemLevel.SERVICE;
		}else if (stringVal.equals(DAO_STR)) {
			return SystemLevel.DAO;
		}else if (stringVal.equals(BUSINESS_STR)) {
			return SystemLevel.BUSINESS;
		}else if (stringVal.equals(BUSINESSERROR_STR)) {
			return SystemLevel.BUSINESSERROR;
		}else if (stringVal.equals(CONSOLE_STR)) {
			return SystemLevel.CONSOLE;
		}

		return Level.toLevel(sArg, (Level) defaultValue);
	}
	public static Level toLevel(int i) throws IllegalArgumentException {
		switch (i) {
			case SYSTEM_INT:
				return SystemLevel.SYSTEM;
			case CONTROLLER_INT:
				return SystemLevel.CONTROLLER;
			case SERVICE_INT:
				return SystemLevel.SERVICE;
			case DAO_INT:
				return SystemLevel.DAO;
			case BUSINESS_INT:
				return SystemLevel.BUSINESS;
			case BUSINESSERROR_INT:
				return SystemLevel.BUSINESSERROR;
			case CONSOLE_INT:
				return SystemLevel.CONSOLE;
		}
		return Level.toLevel(i);
	}

}
