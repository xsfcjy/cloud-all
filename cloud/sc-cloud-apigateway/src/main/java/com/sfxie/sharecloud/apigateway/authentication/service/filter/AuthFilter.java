package com.sfxie.sharecloud.apigateway.authentication.service.filter;

import com.sfxie.sharecloud.apigateway.authentication.entity.AuthenticationModel;
import com.sfxie.core.framework.mvc.exception.BusinessException;

/**
 * 鉴权过滤器接口
 * 
 * @author xiesf
 * @since 2016-08-10
 *
 */
public interface AuthFilter {
	public void filter(AuthenticationModel authenticationModel,AuthFilterChain authFilterChain) throws BusinessException;
}
