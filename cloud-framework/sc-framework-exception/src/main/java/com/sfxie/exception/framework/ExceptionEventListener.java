package com.sfxie.exception.framework;
/**
 * 异常事件监听器
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-17上午9:18:35 
 * @param <T>
 */
public interface ExceptionEventListener<T extends ExceptionEvent> {
	/**	处理方法		*/
  public void handleEvent(T event);  

}  
