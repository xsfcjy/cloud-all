package com.sfxie.exception.framework.implement.exception.info;
/**
 * 异常信息实体
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午7:13:08 2015年10月22日
 * @example		
 *
 */
public class ExceptionEntity {
	/**	异常发生时所在的层(service/dao/controller)	*/
	private String layer;
	/**	异常代码	*/
	private String code;
	/**	异常的本地化信息	*/
	private String localMsg;
	/**	异常的完整信息	*/
	private String fullMsg;
	/**	异常产生类	*/
	private String className;
	/**	异常方法	*/
	private String methodName;
	/**	异常所在行数	*/
	private int lineNumber;
	/**	自定义异常提示信息(优先级最高，如果此异常有值，则返回此提示信息)	*/
	private String selfErrorInfo;
	
	
	public String getLayer() {
		return layer;
	}
	public void setLayer(String layer) {
		this.layer = layer;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocalMsg() {
		return localMsg;
	}
	public void setLocalMsg(String localMsg) {
		this.localMsg = localMsg;
	}
	public String getFullMsg() {
		return fullMsg;
	}
	public void setFullMsg(String fullMsg) {
		this.fullMsg = fullMsg;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getSelfErrorInfo() {
		return selfErrorInfo;
	}
	/**
	 * 	设置自定义异常提示信息(优先级最高，如果此异常有值，则返回此提示信息)
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午5:17:30 2015年9月14日
	 * @param selfErrorInfo	
	 *
	 */
	public void setSelfErrorInfo(String selfErrorInfo) {
		this.selfErrorInfo = selfErrorInfo;
	}
	
	
}
