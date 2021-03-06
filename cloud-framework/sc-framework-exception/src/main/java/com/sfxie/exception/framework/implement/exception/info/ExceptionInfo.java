package com.sfxie.exception.framework.implement.exception.info;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sfxie.exception.framework.FrameworkException;
import com.sfxie.exception.framework.implement.exception.ControllerException;
import com.sfxie.exception.framework.implement.exception.DaoException;
import com.sfxie.exception.framework.implement.exception.MvcException;
import com.sfxie.exception.framework.implement.exception.RedisException;
import com.sfxie.exception.framework.implement.exception.ServiceException;
import com.sfxie.exception.framework.implement.exception.SqlException;

/**
 * 异常信息抽象类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015年7月20日下午1:40:31
 */
public abstract class ExceptionInfo {
	
	public abstract void setErrorCode(String errorCode);
	public abstract void setErrorLocalMsg(String errorLocalMsg);
	public abstract void setErrorFullMsg(String errorFullMsg) ;
	public abstract void setErrorLayer(String errorLayer);
	public abstract void setErrorClassName(String errorClassName);
	public abstract void setErrorMethodName(String errorMethodName);
	public abstract void setErrorLineNumber(int errorLineNumber);
	public abstract void logErrorInfo();
	public abstract String getErrorLocalMsg();
	public abstract String getErrorFullMsg();
	
	/**
	 * 获取异常信息
	 * @param type
	 * 		json or xml
	 * @param e
	 * 		异常
	 * @return
	 */
	public static ExceptionInfo gainExceptionInfo(String type,Exception e){
		ExceptionInfo exceptionInfo;
		if(type.equals("xml")){
			exceptionInfo = (ExceptionInfo) new ExceptionInfoXml();
		}else{
			exceptionInfo = (ExceptionInfo) new ExceptionInfoJson();
		}
		setBusinessExceptionInfo(e,exceptionInfo);
		Throwable t = parseException(e);
		if (t instanceof ServiceException){ 										//service层执行异常
			exceptionInfo.setErrorLayer("service");
			exceptionInfo.setErrorCode(((ServiceException)t).getErrorCode());
		}else if(t instanceof DaoException){										//dao层执行异常
			exceptionInfo.setErrorLayer("dao");										
			exceptionInfo.setErrorCode(((DaoException)t).getErrorCode());
		}else if(t instanceof SqlException){									//controller层执行异常
			exceptionInfo.setErrorLayer("sql");
			exceptionInfo.setErrorCode(((SqlException)t).getErrorCode());
		}else if(t instanceof RedisException){										//redis层执行异常
			exceptionInfo.setErrorLayer("redis");
			exceptionInfo.setErrorCode(((RedisException)t).getErrorCode());
		}else if(t instanceof ControllerException){									//controller层执行异常
			exceptionInfo.setErrorLayer("controller");
			exceptionInfo.setErrorCode(((ControllerException)t).getErrorCode());
		}
		else if(t instanceof MvcException){										//mvc执行异常
			exceptionInfo.setErrorLayer("mvc");
			exceptionInfo.setErrorCode(((MvcException)t).getErrorCode());
		}else{																		//其它执行异常
			exceptionInfo.setErrorLayer("other");
			exceptionInfo.setErrorCode("other");
		}
		exceptionInfo.setErrorLocalMsg(t.getLocalizedMessage());
		exceptionInfo.setErrorFullMsg(getExceptionMsg(t));
		return exceptionInfo;
	}
	/**
	 * 从异常堆栈中找到MvcException异常<br>
	 * 如果找不到MvcException,则返回e
	 * @param e
	 * @return
	 */
    public static Throwable parseException(Throwable e){
        Throwable tmp = e;
        int breakPoint = 0;
        while(tmp.getCause()!=null){
            if(tmp.equals(tmp.getCause())){
                break;
            }
            if (tmp instanceof ServiceException || tmp instanceof DaoException || 
            	tmp instanceof ControllerException || tmp instanceof RedisException 
                || tmp instanceof MvcException || tmp instanceof FrameworkException){
            	return tmp;
    		}
            tmp=tmp.getCause();
            breakPoint++;
            if(breakPoint>1000){
                break;
            }
        } 
        return e;
    }
	/**
	 * 获取完整异常信息
	 * @param e
	 * @return
	 */
    public static String getExceptionMsg(Throwable e){
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
    /**	设置异常的业务信息	*/
    private static void setBusinessExceptionInfo(Exception e,ExceptionInfo exceptionInfo){
    	StackTraceElement[] s = e.getStackTrace();
    	for (int i = 0; i < s.length; i++) {  
    		StackTraceElement se = s[i];
    		String className = se.getClassName().replaceAll("\\$\\w*", "");
    		try {
				Class<?> clazz = Class.forName(className);
				if(isBusinessException(clazz)){
					String errorMethodName1 = se.getMethodName(); 
					int errorLineNumber = se.getLineNumber();
					exceptionInfo.setErrorClassName(className);
					exceptionInfo.setErrorMethodName(errorMethodName1);
					exceptionInfo.setErrorLineNumber(errorLineNumber);
					return ;
				}
			} catch (ClassNotFoundException e1) {
//				e1.printStackTrace();
			}
        }
    	
    	//没有找到业务处理类则设置第一个原因为异常信息
    	String errorClassName1 = e.getStackTrace()[0].getClassName();
		String errorMethodName1 = e.getStackTrace()[0].getMethodName(); 
		int errorLineNumber = e.getStackTrace()[0].getLineNumber();
		exceptionInfo.setErrorClassName(errorClassName1);
		exceptionInfo.setErrorMethodName(errorMethodName1);
		exceptionInfo.setErrorLineNumber(errorLineNumber);
	}
    /**
     * 判断是否为业务处理类
     * @param clazz
     * @return
     */
    private static boolean isBusinessException(Class<?> clazz){
    	Controller controller = clazz.getAnnotation(Controller.class);
    	if(null!=controller)
    		return true;
    	Service service = clazz.getAnnotation(Service.class);
    	if(null!=service)
    		return true;
    	Repository repository = clazz.getAnnotation(Repository.class);
    	if(null!=repository)
    		return true;
    	Component component = clazz.getAnnotation(Component.class);
    	if(null!=component)
    		return true;
    	return false;
    }
}
