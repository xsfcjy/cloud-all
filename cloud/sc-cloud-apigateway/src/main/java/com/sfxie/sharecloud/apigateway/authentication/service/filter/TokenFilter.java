package com.sfxie.sharecloud.apigateway.authentication.service.filter;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sfxie.core.framework.mvc.exception.BusinessException;
import com.sfxie.core.utils.EncryptUtil;
import com.sfxie.sharecloud.apigateway.authentication.Constants;
import com.sfxie.sharecloud.apigateway.authentication.dao.business.mapper.AuthMapper;
import com.sfxie.sharecloud.apigateway.authentication.entity.AuthenticationModel;
import com.sfxie.sharecloud.apigateway.authentication.entity.business.ApiGatewayConfig;

/**
 * 判断token是否有效过滤器
 * 
 * @author xiesf
 * @since 2016-08-08
 */
@AuthFilterAnnotatioin(order = 30)
@Component
public class TokenFilter extends AbstractAuthFilter {
	@Resource
	private AuthMapper authMapper;
	@Resource
	protected Constants constants;

	@Override
	public void filter(AuthenticationModel authenticationModel,
			AuthFilterChain authFilterChain) throws BusinessException {
		List<String> ts = authenticationModel.getParameters().get("ts");
		// 验证时间戳参数是否有效(ts与当前时间比较，当小于第二个参数的值则有效)
		String appId = authenticationModel.getHeaders().get("_$APPID");
		ApiGatewayConfig apiGatewayConfig = getApiGatewayConfig(appId);
		if (null == apiGatewayConfig)
			throw new BusinessException(this.msgErrorAuth.getEmptyAccess(), this.codeErrorAuth.getEmptyAccess());
		validateToken(ts,
				authenticationModel.getHeaders().get("_$TOKEN"),
				apiGatewayConfig.getAppSecret(),
				authenticationModel.getHeaders().get("_$URI"),
				authenticationModel.getParameters());
		// 不是内部系统，则继续进行权限过滤
		if (!apiGatewayConfig.getType().equals("I")) {
			authFilterChain.doFilter(authenticationModel);
		}
	}
	/**
	 * 验证token
	 * 
	 * @param ts
	 *            时间戳
	 * @param token
	 *            第三方加密出来的token
	 * @param accessSecret
	 *            第三方申请的密钥
	 * @param params
	 *            第三方请求接口的参数集合
	 */
	private void validateToken(List<String> ts, String token,
			String accessSecret,String uri, Map<String, List<String>> params) {
		String tokenTemp = null;
		try {
			tokenTemp = EncryptUtil.hmacSha1(ts, accessSecret,uri, params);
		} catch (Exception e) {
			throw new BusinessException(e.getLocalizedMessage(), "error_deal");
		}
		if (!token.equals(tokenTemp)) {
			throw new BusinessException(this.msgErrorAuth.getDenyToken(), this.codeErrorAuth.getDenyToken());
		}

	}
	/**
	 * 获取第三方分配的密钥
	 * 
	 * @param appId
	 * @return
	 */
	private ApiGatewayConfig getApiGatewayConfig(String appId) {
		return authMapper.getApiGatewayConfig(appId);
	}

}
