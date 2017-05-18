package com.sfxie.exception.framework.implement.exception;

import com.sfxie.exception.framework.implement.exception.event.business.BusinessLoggerEventHandlerImpl;

/**
 * 业务异常类,用于返回到error节点的业务信息提示
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午3:12:16 2016年2月24日
 * @example		
 *
 */
public class BusinessException extends MvcException {

	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}
	public BusinessException(String message,String code) {
		super(message,code);
	}
	public BusinessException(Exception t) {
		super(t);
	}
	/**
	 * 初始化Dao层异常处理EventHandler
	 */
	protected void initEventListeners(){
		final MvcException e = this;
		this.addEventListener(new BusinessLoggerEventHandlerImpl(e));
		initEventListener();
	}
}
