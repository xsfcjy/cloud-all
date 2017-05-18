package com.sfxie.sharecloud.apigateway.authentication.entity.business;

public class ApiGatewayInterface {

	/** 接口URL */
	private String serverUrl;
	/** 方法名 */
	private String methodName;
	/** 是否生效：0-未生效；1-已生效 */
	private boolean enable;
	/** 匹配方式：1-通配符,2-正则,3-全路径 */
	private String matchType;

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public boolean getEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

}
