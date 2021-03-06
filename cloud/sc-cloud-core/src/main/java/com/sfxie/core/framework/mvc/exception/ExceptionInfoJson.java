package com.sfxie.core.framework.mvc.exception;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sfxie.core.framework.logger.log4j.LoggerUtil;
import com.sfxie.core.framework.mvc.exception.logger.LoggerEventHandlerImpl;



/**
 * 异常信息类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-14上午10:31:23
 */
public class ExceptionInfoJson extends ExceptionInfo{
	
	/**	异常发生时所在的层(service/dao/controller)	*/
	@JsonIgnore
	private String errorLayer;
	/**	异常代码	*/
	private String errorCode;
	/**	如果是sql错误，此字段对应的是sql报错的字段名	*/
	@JsonIgnore
	private String errorDbName;
	/**	如果是sql错误，此字段对应的是sql报错的具体提示	*/
	@JsonIgnore
	private String errorDbTip;
	/**	异常的本地化信息	*/
	@JsonIgnore
	private String errorLocalMsg;
	/**	异常的完整信息	*/
	@JsonIgnore
	private String errorFullMsg;
	/**	异常产生类	*/
	@JsonIgnore
	private String errorClassName;
	/**	异常方法	*/
	@JsonIgnore
	private String errorMethodName;
	/**	异常所在行数	*/
	@JsonIgnore
	private int errorLineNumber;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorLocalMsg() {
		return errorLocalMsg;
	}
	public void setErrorLocalMsg(String errorLocalMsg) {
		this.errorLocalMsg = errorLocalMsg;
	}
	public String getErrorFullMsg() {
		return errorFullMsg;
	}
	public void setErrorFullMsg(String errorFullMsg) {
		this.errorFullMsg = errorFullMsg;
	}
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
	
	public void logErrorInfo(){
		Logger logger = LoggerUtil.instance(LoggerEventHandlerImpl.class);
		logger.error("##	错误代码: "+this.errorCode);
		logger.error("##	代码层数: "+this.errorLayer);
    	StringBuffer sb = new StringBuffer();
    	sb.append("##	代码位置: ").append(this.errorClassName).append(".").append(this.errorMethodName).append("()")
    	  .append("[行号: ").append(this.errorLineNumber).append("]");
    	logger.error("##	错误详细信息: "+this.errorFullMsg);
    	logger.error(sb.toString());
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
	public String getErrorMsg() {
		return errorLocalMsg;
	}
}
