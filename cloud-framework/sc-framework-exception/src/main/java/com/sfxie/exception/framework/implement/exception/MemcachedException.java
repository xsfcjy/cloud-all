package com.sfxie.exception.framework.implement.exception;


/**
 * Memcached层异常类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-14上午10:12:07
 */
public class MemcachedException extends MvcException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MemcachedException(String msg){
		super(msg);
	}
	public MemcachedException(String errorCode,Throwable t){
		super(errorCode,t);
	}
	public MemcachedException(String errorCode,Exception t){
		super(errorCode,t);
	}
	public MemcachedException(Exception t){
		super(t);
	}
	public MemcachedException(Throwable t){
		super(t);
	}
}
