package com.sfxie.sharecloud.apigateway.authentication.properties.msg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 提示信息编码类
 * 
 * @author xiesf
 * @since 2016-08-11
 */
@RefreshScope
@ConfigurationProperties(prefix = "code.auth")
public class CodeErrorAuth {

	public CodeErrorAuth() {
	}

	private String emptyTime;
	private String emptyAppId;
	private String emptyToken;
	private String timeout;
	private String denyToken;
	private String denyInterface;
	private String emptyInterface;
	private String emptyAccess;
	private String unableInterface;
	private String errorMethod;
	private String timeFormat;

	public String getEmptyTime() {
		return emptyTime;
	}

	public void setEmptyTime(String emptyTime) {
		this.emptyTime = emptyTime;
	}

	public String getEmptyAppId() {
		return emptyAppId;
	}

	public void setEmptyAppId(String emptyAppId) {
		this.emptyAppId = emptyAppId;
	}

	public String getEmptyToken() {
		return emptyToken;
	}

	public void setEmptyToken(String emptyToken) {
		this.emptyToken = emptyToken;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getDenyToken() {
		return denyToken;
	}

	public void setDenyToken(String denyToken) {
		this.denyToken = denyToken;
	}

	public String getDenyInterface() {
		return denyInterface;
	}

	public void setDenyInterface(String denyInterface) {
		this.denyInterface = denyInterface;
	}

	public String getEmptyInterface() {
		return emptyInterface;
	}

	public void setEmptyInterface(String emptyInterface) {
		this.emptyInterface = emptyInterface;
	}

	public String getEmptyAccess() {
		return emptyAccess;
	}

	public void setEmptyAccess(String emptyAccess) {
		this.emptyAccess = emptyAccess;
	}

	public String getUnableInterface() {
		return unableInterface;
	}

	public void setUnableInterface(String unableInterface) {
		this.unableInterface = unableInterface;
	}

	public String getErrorMethod() {
		return errorMethod;
	}

	public void setErrorMethod(String errorMethod) {
		this.errorMethod = errorMethod;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

}
