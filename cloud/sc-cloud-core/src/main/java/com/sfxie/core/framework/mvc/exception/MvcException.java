package com.sfxie.core.framework.mvc.exception;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


import com.sfxie.core.framework.exception.FrameworkException;
import com.sfxie.core.framework.mvc.exception.logger.LoggerEventHandlerImpl;

/**
 * mvc异常类,必须继承spring的{@link org.springframework.dao.DataAccessException}类,不然事务控制会失常
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-14上午10:12:44
 */
public class MvcException extends FrameworkException {
	
	private static final long serialVersionUID = 1L;
	
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
            	tmp instanceof ControllerException || tmp instanceof MvcException){
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
