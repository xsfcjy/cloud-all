package com.sfxie.core.framework.mvc.exception.logger;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.sfxie.core.framework.exception.AbstractExceptionInfo;
import com.sfxie.core.framework.exception.ExceptionEventListener;
import com.sfxie.core.framework.exception.FrameworkException;
import com.sfxie.core.framework.mvc.exception.MvcException;
/**
 * 异常事件监听器-日记
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午7:09:33 2015年10月22日
 * @example		
 *
 */
public abstract class LoggerEventHandler  implements ExceptionEventListener<LoggerEvent> ,Serializable{  
	
	private static final long serialVersionUID = 1L;
	
	private FrameworkException exception;
	
	private AbstractExceptionInfo exceptionInfo;
	
	public void setExceptionInfo(AbstractExceptionInfo exceptionInfo){
		this.exceptionInfo = exceptionInfo;
	}
	
	
	public AbstractExceptionInfo getExceptionInfo() {
		return exceptionInfo;
	}


	public LoggerEventHandler( FrameworkException exception) {
		this.exception = exception;
	}

	public FrameworkException getException() {
		return exception;
	}
	
	
	/**
	 * 设置常用的异常的信息
	 * @param exceptionInfo
	 */
	protected boolean generateExceptionInfo(AbstractExceptionInfo exceptionInfo){
    	StackTraceElement[] s = exception.getStackTrace();
    	boolean notSpringComponent = true;
    	for (int i = 0; i < s.length; i++) {  
    		StackTraceElement se = s[i];
    		String className = se.getClassName().replaceAll("\\$\\w*", "");
    		try {
				Class<?> clazz = Class.forName(className);
				if(isBusinessException(clazz,exceptionInfo)){
					String errorMethodName1 = se.getMethodName(); 
					int errorLineNumber = se.getLineNumber();
					exceptionInfo.setErrorClassName(className);
					exceptionInfo.setErrorMethodName(errorMethodName1);
					exceptionInfo.setErrorLineNumber(errorLineNumber);
					exceptionInfo.setErrorLocalMsg(exception.getLocalizedMessage());
					exceptionInfo.setErrorFullMsg(getExceptionMsg(exception));
					exceptionInfo.setErrorCode(((MvcException)exception).getErrorCode());
					notSpringComponent = false;
					return true;
				}
				
			} catch (ClassNotFoundException e1) {
//				e1.printStackTrace();
			}
        }
    	if(notSpringComponent){
    		StackTraceElement se = s[0];
    		String className = se.getClassName().replaceAll("\\$\\w*", "");
    		try {
				Class<?> clazz = Class.forName(className);
				if(isBusinessException(clazz,exceptionInfo)){
					String errorMethodName1 = se.getMethodName(); 
					int errorLineNumber = se.getLineNumber();
					exceptionInfo.setErrorClassName(className);
					exceptionInfo.setErrorMethodName(errorMethodName1);
					exceptionInfo.setErrorLineNumber(errorLineNumber);
					exceptionInfo.setErrorLocalMsg(exception.getLocalizedMessage());
					exceptionInfo.setErrorFullMsg(getExceptionMsg(exception));
					exceptionInfo.setErrorCode(((MvcException)exception).getErrorCode());
					exceptionInfo.setErrorLayer("other");
					notSpringComponent = false;
					return true;
				}
				
			} catch (ClassNotFoundException e1) {
//				e1.printStackTrace();
			}
    	}
    	return false;
	}
    /**
     * 判断是否为业务处理类
     * @param clazz
     * @return
     */
    private boolean isBusinessException(Class<?> clazz,AbstractExceptionInfo exceptionInfo){
//    	return FrameworkException.class.isAssignableFrom(clazz);
    	RestController restController = clazz.getAnnotation(RestController.class);
    	if(null!=restController){
    		exceptionInfo.setErrorLayer("controller");
    		return true;
    	}
    	Controller controller = clazz.getAnnotation(Controller.class);
    	if(null!=controller){
    		exceptionInfo.setErrorLayer("controller");
    		return true;
    	}
    	Service service = clazz.getAnnotation(Service.class);
    	if(null!=service){
    		exceptionInfo.setErrorLayer("service");
    		return true;
    	}
    	Repository repository = clazz.getAnnotation(Repository.class);
    	if(null!=repository){
    		exceptionInfo.setErrorLayer("repository");
    		return true;
    	}
    	Component component = clazz.getAnnotation(Component.class);
    	if(null!=component){
    		exceptionInfo.setErrorLayer("component");
    		return true;
    	}
    	return false;
    }
    /**
	 * 获取完整异常信息
	 * @param e
	 * @return
	 */
    private String getExceptionMsg(Throwable e){
		StringBuffer msg = new StringBuffer();
		if (e != null) {  
            String message = e.toString();  
            int length = e.getStackTrace().length;  
            if (length > 0) {  
                msg.append(message + "\n");  
                for (int i = 0; i < length; i++) {  
            		msg.append("\t" + e.getStackTrace()[i] + "\n"); 
                }  
            } else {  
                msg.append(message);  
            }  
        } 
		return msg.toString();
	}
}
