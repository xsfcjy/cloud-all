package com.sfxie.exception.framework.implement.exception;

import com.sfxie.exception.framework.implement.exception.event.controller.ControllerLoggerEventHandlerImpl;

/**
 * Controller层异常类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-14上午10:12:07
 */
public class ControllerException extends MvcException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ControllerException(String errorCode,Throwable t){
		super(errorCode,t);
	}
	public ControllerException(String errorCode,Exception t){
		super(errorCode,t);
	}
	public ControllerException(Exception t){
		super(t);
	}
	public ControllerException(Throwable t){
		super(t);
	}
	
	protected void initEventListeners(){
		final MvcException e = this;
		this.addEventListener(new ControllerLoggerEventHandlerImpl(e));
		initEventListener();
	}
}
