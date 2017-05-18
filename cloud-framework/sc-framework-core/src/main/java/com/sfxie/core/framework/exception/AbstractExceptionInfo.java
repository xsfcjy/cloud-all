package com.sfxie.core.framework.exception;

/**
 * 异常信息抽象类
 * 
 * @author xieshengfeng
 * @email xsfcy@126.com
 * @since 2015年7月20日下午1:40:31
 */
public abstract class AbstractExceptionInfo {

	public abstract void setErrorDbName(String errorCode);

	public abstract void setErrorDbTip(String errorCode);

	public abstract void setErrorCode(String errorCode);

	public abstract void setErrorLocalMsg(String errorLocalMsg);

	public abstract void setErrorFullMsg(String errorFullMsg);

	public abstract void setErrorLayer(String errorLayer);

	public abstract void setErrorClassName(String errorClassName);

	public abstract void setErrorMethodName(String errorMethodName);

	public abstract void setErrorLineNumber(int errorLineNumber);

	public abstract void logErrorInfo();

	public abstract String getErrorLocalMsg();

	public abstract String getErrorFullMsg();

}
