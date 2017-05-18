package com.sfxie.sharecloud.apigateway.authentication.service.filter;

import javax.annotation.Resource;

import com.sfxie.sharecloud.apigateway.authentication.properties.msg.CodeErrorAuth;
import com.sfxie.sharecloud.apigateway.authentication.properties.msg.MsgErrorAuth;

/**
 * 鉴权过滤器
 * 
 * @author xiesf
 * @since 2016-08-10
 *
 */
public abstract class AbstractAuthFilter implements AuthFilter,
		Comparable<AbstractAuthFilter> {
	@Resource
	protected CodeErrorAuth codeErrorAuth;
	@Resource
	protected MsgErrorAuth msgErrorAuth;
	/** 过滤的处理顺序 */
	protected int order = 0;

	@Override
	public int compareTo(AbstractAuthFilter o) {
		return this.order - o.order;
	}
}
