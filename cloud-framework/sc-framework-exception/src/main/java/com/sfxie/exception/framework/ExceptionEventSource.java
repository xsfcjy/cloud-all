package com.sfxie.exception.framework;
/**
 * 异常事件源
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-17上午9:19:52
 */
public interface ExceptionEventSource {

	/**
	 * 添加异常处理监听器
	 * @param listener
	 */
	void addEventListener(ExceptionEventListener<? extends ExceptionEvent> listener);
	/**
	 * 移出异常处理监听器
	 * @param listener
	 */
	void removeEventListener(ExceptionEventListener<? extends ExceptionEvent> listener);
	/**
	 * 通知特定异常处理监听器
	 * @param event
	 */
	void notifyListener( ExceptionEvent event);
	/**
	 * 通知所有异常处理监听器
	 */
	void notifyAllListeners();

}