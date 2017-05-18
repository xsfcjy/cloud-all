package com.sfxie.sharecloud.apigateway.authentication.entity.business;

public class ApiGatewayConfig {

	/** 接入系统id */
	private String appId;
	/** 授权KEY */
	private String appSecret;
	/** 配置类型: I-内部系统,O-外部系统 */
	private String type;
	/** 是否生效：0-未生效；1-已生效 */
	private String enable;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

}
