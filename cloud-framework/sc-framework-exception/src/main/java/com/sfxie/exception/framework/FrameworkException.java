package com.sfxie.exception.framework;

import java.util.LinkedList;
import java.util.List;

import org.springframework.dao.DataAccessException;



public abstract class FrameworkException extends DataAccessException  implements ExceptionEventSource{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 异常代码
	 */
	protected String errorCode;
	
	/**
	 * 异常事件监听器列表
	 */
	protected List<ExceptionEventListener<? extends ExceptionEvent>> listeners = new LinkedList<ExceptionEventListener<? extends ExceptionEvent>>(); 
	
	public FrameworkException(String msg) {
		super(msg);
	}

	public FrameworkException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	@Override
	public void addEventListener(ExceptionEventListener<? extends ExceptionEvent> listener) {
		listeners.add(listener);  
	}
	@Override
	public void removeEventListener(ExceptionEventListener<? extends ExceptionEvent> listener) {
		listeners.remove(listener);  
	}
	
	/**
	 * 通知特定event的异常处理handler
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void notifyListener(ExceptionEvent event) {
		for (ExceptionEventListener listener : listeners) {  
            try {  
                listener.handleEvent(event);  
            } catch (ClassCastException e) { 
            	//不是自己的ExceptionEvent不处理
            }  
        }  
	}
	public void notifyAllListeners( ) {
		for (ExceptionEventListener<?> listener : listeners) {  
            try {  
                listener.handleEvent(null);  
            } catch (ClassCastException e) {  
            }  
        }  
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
