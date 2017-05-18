package com.sfxie.sharecloud.apigateway.authentication.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AuthenticationModel {

	/** 鉴权过滤器请求过来的参数 */
	private Map<String, List<String>> parameters;

	private Map<String, String> headers;
	/** 供过滤器内部使用的参数 */
	private Map<String, Object> innerParameters;

	public Map<String, List<String>> getParameters() {
		if (null == parameters) {
			parameters = new HashMap<String, List<String>>();
		}
		return parameters;
	}

	public void setParameters(Map<String, List<String>> parameters) {
		this.parameters = parameters;
	}

	public Map<String, String> getHeaders() {
		if (null == headers) {
			headers = new HashMap<String, String>();
		}
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	/**
	 * 获取过滤器内部使用的参数集合
	 * 
	 * @return
	 */
	public Map<String, Object> getInnerParameters() {
		if (null == innerParameters)
			innerParameters = new HashMap<String, Object>();
		return innerParameters;
	}

}
