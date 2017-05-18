package com.sfxie.sharecloud.apigateway.authentication.service;

import com.sfxie.core.framework.mvc.exception.MvcException;
import com.sfxie.sharecloud.apigateway.authentication.entity.AuthenticationModel;

public interface AuthService {
	/**
	 * 接口鉴权
	 * 
	 * @param authenticationModel
	 *            接口鉴权过滤器传过来的参数对象
	 * @throws MvcException
	 */
	public void validate(AuthenticationModel authenticationModel) throws MvcException;
}
