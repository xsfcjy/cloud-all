package com.sfxie.core.framework.mvc.exception;

import com.sfxie.core.framework.mvc.exception.service.ServiceLoggerEventHandlerImpl;


/**
 * 服务层异常类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-14上午10:12:29
 */
public class ServiceException extends MvcException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String errorCode,Throwable t){
		super(errorCode,t);
	}
	public ServiceException(String errorCode,Exception t){
		super(errorCode,t);
	}
	public ServiceException(Exception t){
		super(t);
	}
	public ServiceException(Throwable t){
		super(t);
	}
	public ServiceException(String errorCode){
		super(errorCode);
	}
	/**
	 * 初始化Dao层异常处理EventHandler
	 */
	protected void initEventListeners(){
		final MvcException e = this;
		this.addEventListener(new ServiceLoggerEventHandlerImpl(e));
		initEventListener();
	}
}
