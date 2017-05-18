package com.sfxie.sharecloud.apigateway.authentication.service.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.sfxie.core.framework.mvc.exception.BusinessException;
import com.sfxie.core.utils.StringUtils;
import com.sfxie.sharecloud.apigateway.authentication.Constants;
import com.sfxie.sharecloud.apigateway.authentication.dao.business.mapper.AuthMapper;
import com.sfxie.sharecloud.apigateway.authentication.entity.AuthenticationModel;
import com.sfxie.sharecloud.apigateway.authentication.entity.business.ApiGatewayInterface;

/**
 * 接口访问权限控制过滤器
 * 
 * @author xiesf
 * @since 2016-08-08
 */

@ConfigurationProperties(prefix = "authfilters")
@AuthFilterAnnotatioin(order = 40)
@Component
public class InterfaceFilter extends AbstractAuthFilter {
	@Resource
	private AuthMapper authMapper;
	@Resource
	protected Constants constants;

	/**
	 * 不用鉴权的url列表
	 */
	// private List<String> unfilterUrls = new ArrayList<String>();

	@Override
	public void filter(AuthenticationModel authenticationModel,
			AuthFilterChain authFilterChain) throws BusinessException {
		String url = authenticationModel.getHeaders().get("_$URI");
		if (url.endsWith("/"))
			url = url.substring(0, url.lastIndexOf("/"));
		String appId = authenticationModel.getHeaders().get("_$APPID");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appId", appId);
		params.put("url", url);

		List<ApiGatewayInterface> interfaces = authMapper.getInterfacesByAppId(params);
		if (interfaces.size() <= 0) {
			throw new BusinessException(this.msgErrorAuth.getEmptyInterface(),this.codeErrorAuth.getEmptyInterface());
		}

		String method = authenticationModel.getHeaders().get("_$METHOD");

		boolean isAuthentication = false;
		for (ApiGatewayInterface itf : interfaces) {
			// 通配符匹配方式处理
			if (itf.getMatchType().equals("1")) {
				if ( matchWildcard(url, itf.getServerUrl())) {
					judgeEnableInterface(itf.getEnable());
					judgeHttpMethod(method, itf.getMethodName());
					isAuthentication = true;
					break;
				}
			}
			// 正则匹配方式处理
			else if (itf.getMatchType().equals("2")) {
				if (matchRegrex(url, itf.getServerUrl())) {
					judgeEnableInterface(itf.getEnable());
					judgeHttpMethod(method, itf.getMethodName());
					isAuthentication = true;
					break;
				}
			}
			// 全路径方式处理
			else if (itf.getMatchType().equals("3")) {
				if (matchAll(url, itf.getServerUrl())) {
					judgeEnableInterface(itf.getEnable());
					judgeHttpMethod(method, itf.getMethodName());
					isAuthentication = true;
					break;
				}
			}
		}
		if (!isAuthentication) {
			throw new BusinessException(this.msgErrorAuth.getDenyInterface(),this.codeErrorAuth.getDenyInterface());
		}
	}

	/**
	 * 判断接口是否有效
	 * @param enable
	 */
	private void judgeEnableInterface(boolean enable){
		if(!enable)
			throw new BusinessException(this.msgErrorAuth.getUnableInterface(),this.codeErrorAuth.getUnableInterface());
	}
	/**
	 * 判断http方法是否与配置的一致
	 * 
	 * @param method
	 * @param itfMethod
	 * @return
	 */
	private void judgeHttpMethod(String method, String itfMethod) {
		if(!method.equals(itfMethod))
			throw new BusinessException(StringUtils.format(this.msgErrorAuth.getErrorMethod(), itfMethod),this.codeErrorAuth.getErrorMethod());
	}

	/** 通配符匹配方式处理 */
	private boolean matchWildcard(String uri, String itf) {
		return StringUtils.match(itf, uri);
	}

	/** 正则匹配方式处理 */
	private boolean matchRegrex(String uri, String itf) {
		Pattern p = Pattern.compile(itf);
		Matcher m = p.matcher(uri);
		boolean b = m.matches();
		return b;
	}

	/** 全路径方式处理 */
	private boolean matchAll(String uri, String itf) {
		return uri.equals(itf);
	}
}
