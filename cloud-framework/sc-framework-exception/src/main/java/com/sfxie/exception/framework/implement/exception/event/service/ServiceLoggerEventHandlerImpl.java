package com.sfxie.exception.framework.implement.exception.event.service;

import java.util.Date;

import com.sfxie.context.BatchNumberHolder;
import com.sfxie.core.framework.logger.log4j.LoggerUtil;
import com.sfxie.exception.framework.implement.exception.MvcException;
import com.sfxie.exception.framework.implement.exception.event.logger.LoggerEvent;
import com.sfxie.exception.framework.implement.exception.event.logger.LoggerEventHandler;
import com.sfxie.exception.framework.implement.exception.info.ExceptionEntity;
import com.sfxie.utils.DateHelper;
import com.sfxie.utils.XmlUtils;
import com.sfxie.utils.jacson.codehaus.JsonUtil;

/**
 * logger异常处理监听者实现类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015年7月20日下午2:39:34
 */
public class ServiceLoggerEventHandlerImpl extends LoggerEventHandler{

	
	public ServiceLoggerEventHandlerImpl( MvcException exception) {
		super(exception);
	}


	@Override
	public void handleEvent(LoggerEvent event) {
		MvcException exception = this.getException();
		ExceptionEntity exceptionEntity = exception.getExceptionEntity();
		String dateString = DateHelper.formatLongDate(new Date(BatchNumberHolder.getBatchNumber()));
		String nameString = "业务异常";
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n###############################################################");
		sb.append(nameString);
		sb.append("###############################################################");
		sb.append("\r\n异常发生时间: ");
		sb.append(dateString);
		sb.append("\r\n异常抛出位置: ").append(exceptionEntity.getClassName()).append(".").append(exceptionEntity.getMethodName()).append("()")
    	  .append("[行号: ").append(exceptionEntity.getLineNumber()).append("]");
		sb.append("\r\n业务方法参数: ");
    	if(null!=exception.getParameters() && exception.getParameters().length>0){
    		sb.append("\r\n    参数(json): \r\n\t");
    		sb.append(JsonUtil.toJSON(exception.getParameters()));
    	}
    	if(null!=exception.getXmlParameters() && exception.getXmlParameters().length>0){
    		for(int i=0;i<exception.getXmlParameters().length;i++){
    			Object obj = exception.getXmlParameters()[i];
    			try {
    				sb.append("\r\n    参数(xml) ");
					sb.append(i);
					sb.append(": \r\n\t");
					sb.append(XmlUtils.serializerXmlString(obj));
				} catch (Exception e) {
				} 
    		}
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
//		if(exception instanceof BusinessException || exception.getCause() instanceof BusinessException){
//			sb.append("\r\n异常完整信息: ");
//			sb.append("\t");
//			if(exception.getCause() instanceof BusinessException){
//				sb.append(exception.getCause().getLocalizedMessage());
//			}else if(exception instanceof BusinessException){
//				sb.append(exception.getLocalizedMessage());
//			}
//			sb.append("\r\n###############################################################");
//			sb.append(nameString);
//			sb.append("###############################################################");
//			LoggerUtil.businessError(ServiceLoggerEventHandlerImpl.class, sb.toString());
//		}else{
		sb.append("\r\n异常完整信息: ");
		sb.append("\t");
		sb.append(exceptionEntity.getFullMsg());
		sb.append("\r\n###############################################################");
		sb.append(nameString);
		sb.append("###############################################################");
		LoggerUtil.service(ServiceLoggerEventHandlerImpl.class, sb.toString());
//		}
//		logger.error(sb.toString());
	}
}
