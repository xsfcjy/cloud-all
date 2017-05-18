package com.sfxie.exception.framework.implement.exception;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.sfxie.exception.framework.ExceptionEventListener;
import com.sfxie.exception.framework.FrameworkException;
import com.sfxie.exception.framework.implement.exception.event.logger.LoggerEventHandlerImpl;
import com.sfxie.exception.framework.implement.exception.info.ExceptionEntity;

/**
 * mvc异常类,必须继承spring的{@link org.springframework.dao.DataAccessException}类,不然事务控制会失常
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-14上午10:12:44
 */
public class MvcException extends FrameworkException {
	
	private static final long serialVersionUID = 1L;
	
	/**	异常信息实体	*/
	private ExceptionEntity exceptionEntity;

	
	/**	方法执行时的参数(没有注解的pojo)	*/
	protected Object[] parameters;
	/**	方法执行时的参数(有@XmlRootElement注解的pojo)	*/
	protected Object[] xmlParameters;
	
	/**	另外一些异常信息存放列表	*/
	protected List<String> otherExceptionMsg;
	
	/**
	 * 特定初始化异常监听器<br>
	 * 常用于在子类中实现此方法以添加特定的异常处理事件监听器
	 */
	protected void initEventListener(){
		
	};
	public MvcException(String errorMsg){
		super(errorMsg);
		initEventListeners();
	}
	public MvcException(String errorMsg ,String errorCode){
		super(errorMsg);
		this.errorCode = errorCode;
		initEventListeners();
	}
	public MvcException(String errorCode,Throwable t){
		super(t.getMessage(),t);
		initEventListeners();
		this.errorCode = errorCode;
	}
	public MvcException(String errorCode,Exception t){
		super(t.getMessage(),t);
		initEventListeners();
		this.errorCode = errorCode;
	}
	public MvcException(Exception t){
		super(t.getMessage(),t);
		initEventListeners();
	}
	public MvcException(Throwable t){
		super(t.getMessage(),t);
		initEventListeners();
	}
	
	private void mappedExceptionEntity(){
		if(null==exceptionEntity){
			exceptionEntity = new ExceptionEntity();
		}
		setBusinessExceptionInfo(this,exceptionEntity);
	}
	/**
	 * 默认初始化异常处理EventHandler
	 */
	protected void initEventListeners(){
		final MvcException e = this;
		this.addEventListener(new LoggerEventHandlerImpl(e));
		initEventListener();
	}
	
	/**
	 * 通知所有的异常处理监听器处理异常
	 */
	@Override
	public void notifyAllListeners( ) {
		super.notifyAllListeners();
		mappedExceptionEntity();
	}

	public Object[] getParameters() {
		if(null==parameters){
			parameters = new Object[]{};
		}
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		if(null!=parameters){
			List<Object> parametersList = new ArrayList<Object>();
			List<Object> xmlParametersList = new ArrayList<Object>();
			for(int i=0;i<parameters.length;i++){
				Object param = parameters[i];
				XmlRootElement xmlRootElement = param.getClass().getAnnotation(XmlRootElement.class);
				if(null!=xmlRootElement){
					xmlParametersList.add(param);
				}else{
					parametersList.add(param);
				}
			}
			if(parametersList.size()>0)
				this.parameters = parametersList.toArray(getParameters());
			if(xmlParametersList.size()>0)
				this.xmlParameters = xmlParametersList.toArray(getXmlParameters());
		}
	}
	public ExceptionEntity getExceptionEntity() {
		if(null==exceptionEntity){
			exceptionEntity = new ExceptionEntity();
		}
		return exceptionEntity;
	}
	
	 /**	设置异常的业务信息	*/
    private void setBusinessExceptionInfo(Exception e,ExceptionEntity exceptionEntity){
    	StackTraceElement[] s = e.getStackTrace();
    	for (int i = 0; i < s.length; i++) {  
    		StackTraceElement se = s[i];
    		String className = se.getClassName().replaceAll("\\$\\w*", "");
    		try {
				Class<?> clazz = Class.forName(className);
				if(isBusinessException(clazz)){
					String errorMethodName1 = se.getMethodName(); 
					int errorLineNumber = se.getLineNumber();
					exceptionEntity.setClassName(className);
					exceptionEntity.setMethodName(errorMethodName1);
					exceptionEntity.setLineNumber(errorLineNumber);
					exceptionEntity.setLocalMsg(e.getLocalizedMessage());
					exceptionEntity.setFullMsg(getExceptionMsg(e));
					Throwable t = parseException(e);
					if (t instanceof ServiceException){ 										//service层执行异常
						exceptionEntity.setLayer("service");
						exceptionEntity.setCode(((ServiceException)t).getErrorCode());
					}else if(t instanceof DaoException){										//dao层执行异常
						exceptionEntity.setLayer("dao");										
						exceptionEntity.setCode(((DaoException)t).getErrorCode());
					}else if(t instanceof SqlException){									//controller层执行异常
						exceptionEntity.setLayer("sql");
						exceptionEntity.setCode(((SqlException)t).getErrorCode());
					}else if(t instanceof RedisException){										//redis层执行异常
						exceptionEntity.setLayer("redis");
						exceptionEntity.setCode(((RedisException)t).getErrorCode());
					}else if(t instanceof ControllerException){									//controller层执行异常
						exceptionEntity.setLayer("controller");
						exceptionEntity.setCode(((ControllerException)t).getErrorCode());
					}
					else if(t instanceof MvcException){										//mvc执行异常
						exceptionEntity.setLayer("mvc");
						exceptionEntity.setCode(((MvcException)t).getErrorCode());
					}
					return ;
				}
			} catch (ClassNotFoundException e1) {
//				e1.printStackTrace();
			}
        }
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
                || tmp instanceof MvcException){
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
    /**
     * 判断是否为业务处理类
     * @param clazz
     * @return
     */
    private boolean isBusinessException(Class<?> clazz){
    	RestController restController = clazz.getAnnotation(RestController.class);
    	if(null!=restController)
    		return true;
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
    public Object[] getXmlParameters() {
    	if(null==xmlParameters){
    		xmlParameters = new Object[]{};
		}
		return xmlParameters;
	}
	public List<String> getOtherExceptionMsg() {
		if(null==otherExceptionMsg)
			otherExceptionMsg = new ArrayList<String>();
		return otherExceptionMsg;
	}
    
    
}
