package com.sfxie.core.framework.mvc.exception.logger;

import java.util.Date;

import com.sfxie.core.framework.exception.AbstractExceptionInfo;
import com.sfxie.core.framework.logger.log4j.LoggerUtil;
import com.sfxie.core.framework.mvc.exception.ExceptionInfoJson;
import com.sfxie.core.framework.mvc.exception.MvcException;
import com.sfxie.core.utils.DateHelper;
import com.sfxie.core.utils.JsonUtil;

/**
 * logger异常处理监听者实现类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015年7月20日下午2:39:34
 */
public class LoggerEventHandlerImpl extends LoggerEventHandler{

	
	public LoggerEventHandlerImpl( MvcException exception) {
		super(exception);
	}


	@Override
	public void handleEvent(LoggerEvent event) {
		MvcException exception = (MvcException) this.getException();
		ExceptionInfoJson exceptionEntity = (ExceptionInfoJson) exception.getExceptionInfo();
		String dateString = DateHelper.formatLongDate(new Date());
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n###############################################################");
		sb.append(dateString);
		sb.append("###############################################################");
		sb.append("\r\n异常代码编号: ");
		sb.append(exceptionEntity.getErrorCode());
		sb.append("\r\n异常代码层数: ");
		sb.append(exceptionEntity.getErrorLayer());
		sb.append("\r\n异常发生时间: ");
		sb.append(dateString);
		sb.append("\r\n异常抛出位置: ").append(exceptionEntity.getErrorClassName()).append(".").append(exceptionEntity.getErrorMethodName()).append("()")
    	  .append("[行号: ").append(exceptionEntity.getErrorLineNumber()).append("]");
		sb.append("\r\n业务方法参数: ");
    	if(null!=exception.getParameters() && exception.getParameters().length>0){
    		sb.append("\r\n    参数(json): \r\n\t");
    		sb.append( JsonUtil.toJson(exception.getParameters()));
    	}
    	if(null!=exception.getOtherExceptionMsg() && exception.getOtherExceptionMsg().size()>0){
    		sb.append("\r\n附带信息: ");
    		for(int i=0;i<exception.getOtherExceptionMsg().size();i++){
    			String obj = exception.getOtherExceptionMsg().get(i);
    			try {
    				sb.append("\r\n    附带信息");
					sb.append(i);
					sb.append(": \r\n\t");
					sb.append(obj);
				} catch (Exception e) {
				} 
    		}
    	}
    	sb.append("\r\n异常完整信息: ");
    	sb.append("\t");
		sb.append(exceptionEntity.getErrorFullMsg());
    	sb.append("\r\n###############################################################");
		sb.append(dateString);
		sb.append("###############################################################");

		LoggerUtil.system(LoggerEventHandlerImpl.class, sb.toString());
	}


	@Override
	public AbstractExceptionInfo exceptionInfo() {
		ExceptionInfoJson info = new ExceptionInfoJson();
		generateExceptionInfo(info);
		return info;
	}
}
