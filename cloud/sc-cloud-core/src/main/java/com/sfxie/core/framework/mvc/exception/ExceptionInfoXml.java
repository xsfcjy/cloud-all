package com.sfxie.core.framework.mvc.exception;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.sfxie.core.framework.logger.log4j.LoggerUtil;
import com.sfxie.core.framework.mvc.exception.logger.LoggerEventHandlerImpl;

/**
 * 异常信息类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-14上午10:31:23
 */
@XmlRootElement(name = "exception")
public class ExceptionInfoXml  extends ExceptionInfo{

	/**	异常发生时所在的层(service/dao/controller)	*/
	private String errorLayer;
	/**	异常代码	*/
	private String errorCode;
	/**	如果是sql错误，此字段对应的是sql报错的字段名	*/
	private String errorDbName;
	/**	如果是sql错误，此字段对应的是sql报错的具体提示	*/
	private String errorDbTip;
	/**	异常的本地化信息	*/
	private String errorLocalMsg;
	/**	异常的完整信息	*/
	private String errorFullMsg;
	/**	异常产生类	*/
	private String errorClassName;
	/**	异常方法	*/
	private String errorMethodName;
	/**	异常所在行数	*/
	private int errorLineNumber;
	
	
	@XmlElement
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	@XmlElement
	public String getErrorLocalMsg() {
		return errorLocalMsg;
	}
	public void setErrorLocalMsg(String errorLocalMsg) {
		this.errorLocalMsg = errorLocalMsg;
	}
	@XmlElement
	public String getErrorFullMsg() {
		return errorFullMsg;
	}
	public void setErrorFullMsg(String errorFullMsg) {
		this.errorFullMsg = errorFullMsg;
	}
	@XmlElement
	public String getErrorLayer() {
		return errorLayer;
	}
	public void setErrorLayer(String errorLayer) {
		this.errorLayer = errorLayer;
	}
	public String getErrorClassName() {
		return errorClassName;
	}
	public void setErrorClassName(String errorClassName) {
		this.errorClassName = errorClassName;
	}
	public String getErrorMethodName() {
		return errorMethodName;
	}
	public void setErrorMethodName(String errorMethodName) {
		this.errorMethodName = errorMethodName;
	}
	public int getErrorLineNumber() {
		return errorLineNumber;
	}
	public void setErrorLineNumber(int errorLineNumber) {
		this.errorLineNumber = errorLineNumber;
	}
	public String getErrorDbName() {
		return errorDbName;
	}
	public void setErrorDbName(String errorDbName) {
		this.errorDbName = errorDbName;
	}
	public String getErrorDbTip() {
		return errorDbTip;
	}
	public void setErrorDbTip(String errorDbTip) {
		this.errorDbTip = errorDbTip;
	}
	public void logErrorInfo(){
		Logger logger = LoggerUtil.instance(LoggerEventHandlerImpl.class);
		logger.error("##	错误代码: "+this.errorCode);
		logger.error("##	代码层数: "+this.errorLayer);
    	StringBuffer sb = new StringBuffer();
    	sb.append("##	代码位置: ").append(this.errorClassName).append(".").append(this.errorMethodName).append("()")
    	  .append("[行号: ").append(this.errorLineNumber).append("]");
    	logger.error(sb.toString());
    }
}
