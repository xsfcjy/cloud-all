package com.sfxie.exception.framework.implement.exception;


/**
 * redis层异常类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-14上午10:12:07
 */
public class RedisException extends MvcException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RedisException(String errorCode,Throwable t){
		super(errorCode,t);
	}
	public RedisException(String errorCode,Exception t){
		super(errorCode,t);
	}
	public RedisException(Exception t){
		super(t);
	}
	public RedisException(Throwable t){
		super(t);
	}
}
