package com.sfxie.sharecloud.apigateway.authentication.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sfxie.core.framework.core.TransactionService;
import com.sfxie.core.framework.mvc.exception.BusinessException;
import com.sfxie.sharecloud.apigateway.authentication.entity.AuthenticationModel;
import com.sfxie.sharecloud.apigateway.authentication.service.AuthService;
import com.sfxie.sharecloud.apigateway.authentication.service.filter.AuthFilterChain;

@Service
public class AuthServiceImpl extends TransactionService implements AuthService {


	@Resource
	private AuthFilterChain authFilterChain;

	@Override
	public void validate(AuthenticationModel authenticationModel)
			throws BusinessException {
		authFilterChain.startFilter(authenticationModel);
	}
}
