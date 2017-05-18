package com.sfxie.sharecloud.apigateway.authentication.service.filter;

import org.springframework.stereotype.Component;

import com.sfxie.core.framework.mvc.exception.BusinessException;
import com.sfxie.core.utils.CollectionUtil;
import com.sfxie.sharecloud.apigateway.authentication.entity.AuthenticationModel;

/**
 * 参数空判断过滤器
 * 
 * @author xiesf
 * @since 2016-08-08
 */

@AuthFilterAnnotatioin(order = 10)
@Component
public class EmptyFilter extends AbstractAuthFilter {

	@Override
	public void filter(AuthenticationModel authenticationModel,
			AuthFilterChain authFilterChain) throws BusinessException {
		// 验证时间戳参数(ts)不能为空
		if (CollectionUtil.isEmpty(authenticationModel.getParameters().get("ts")))
			throw new BusinessException(this.msgErrorAuth.getEmptyTime(), this.codeErrorAuth.getEmptyTime());

		String appId = authenticationModel.getHeaders().get("_$APPID");
		// 验证appId不能为空
		if (null == appId)
			throw new BusinessException(this.msgErrorAuth.getEmptyAppId(), this.codeErrorAuth.getEmptyAppId());
		// 验证token不能为空
		if (null == authenticationModel.getHeaders().get("_$TOKEN"))
			throw new BusinessException(this.msgErrorAuth.getEmptyToken(), this.codeErrorAuth.getEmptyToken());
		authFilterChain.doFilter(authenticationModel);
	}
}
