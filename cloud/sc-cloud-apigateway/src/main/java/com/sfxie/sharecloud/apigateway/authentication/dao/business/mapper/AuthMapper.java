package com.sfxie.sharecloud.apigateway.authentication.dao.business.mapper;

import java.util.List;
import java.util.Map;

import com.sfxie.sharecloud.apigateway.authentication.entity.business.ApiGatewayConfig;
import com.sfxie.sharecloud.apigateway.authentication.entity.business.ApiGatewayInterface;

public interface AuthMapper {
	/**
	 * 获取用户的鉴权密钥
	 * 
	 * @param appId
	 *            用户申请的appId
	 */
	public ApiGatewayConfig getApiGatewayConfig(String appId);

	/**
	 * 根据用户的appId获取用户可以访问的接口列表
	 * 
	 * @param params
	 * @return
	 */
	public List<ApiGatewayInterface> getInterfacesByAppId(
			Map<String, Object> params);
}
