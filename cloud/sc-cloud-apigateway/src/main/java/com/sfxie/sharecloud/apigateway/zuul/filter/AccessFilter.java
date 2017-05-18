package com.sfxie.sharecloud.apigateway.zuul.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.sfxie.core.framework.core.SpringContext;
import com.sfxie.core.framework.mvc.exception.MvcException;
import com.sfxie.sharecloud.apigateway.authentication.entity.AuthenticationModel;
import com.sfxie.sharecloud.apigateway.authentication.service.AuthService;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/8/1
 * Time: 下午7:39
 * CopyRight: taobao
 * Descrption:权限filter
 * <p/>
 * 1. 校验App是否合法
 * 2. 校验token是否合法
 * 3. ip是否在黑名单中
 * 4. ip是否在白名单中
 */

public class AccessFilter extends ZuulFilter {
	
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);


    
    @Override
    public String filterType() {
        return FilterTypeEnum.pre.getCode();         //指定filter类型为PRE
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("---1. pre run---");
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().setCharacterEncoding("UTF-8");
        HttpServletRequest request = ctx.getRequest();

        ctx.getRequestQueryParams();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        log.info("getRouteHost:" + ctx.getRouteHost());
        //校验入参中是否有token
        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                //如果token不合法跳转到error页
                log.warn("access token is empty");
                ctx.getResponse().sendRedirect("/errorpage");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("---access token ok----");
        //校验app是否合法
        //校验
        try {
			validateUrl(ctx,request);
		} catch (MvcException e) {
			/**
			 * 这里我们通过 ctx.setSendZuulResponse(false) 令zuul过滤该请求，不对其进行路由，
			 * 然后通过 ctx.setResponseStatusCode(401) 设置了其返回的错误码，当然我们也可以进一步优化我们的返回，
			 * 比如，通过 ctx.setResponseBody(body) 对返回body内容进行编辑等
			 */
//            ctx.setSendZuulResponse(false);
//            ctx.setThrowable(e);
//            throw e;
		} catch (Exception e) {
			/**
			 * 这里我们通过 ctx.setSendZuulResponse(false) 令zuul过滤该请求，不对其进行路由，
			 * 然后通过 ctx.setResponseStatusCode(401) 设置了其返回的错误码，当然我们也可以进一步优化我们的返回，
			 * 比如，通过 ctx.setResponseBody(body) 对返回body内容进行编辑等
			 */
//            ctx.setSendZuulResponse(false);
//            ctx.setThrowable(e);
//            throw e;
		}
		return null;
    }
    
    
    private void validateUrl(RequestContext ctx,HttpServletRequest request) throws MvcException {

    	Map<String, List<String>> parametersMap = ctx.getRequestQueryParams();
		AuthenticationModel authenticationModel = new AuthenticationModel();
		authenticationModel.setParameters(parametersMap);
		// 设置第三方请求的URI地址,如: /plugin/
		String uri = request.getRequestURI();
		if (uri.endsWith("/"))
			uri = uri.substring(0, uri.lastIndexOf("/"));
		authenticationModel.getHeaders().put("_$URI", uri);
		// 设置第三方请求的URL地址,如: http://localhost:8081/plugin/
		authenticationModel.getHeaders().put("_$URL", request.getRequestURL().toString());
		// 设置第三方请求的方法
		authenticationModel.getHeaders().put("_$METHOD", request.getMethod());
		// 设置token
		authenticationModel.getHeaders().put("_$TOKEN", parametersMap.get("token").get(0).toString());
		// 移除token
		parametersMap.remove("token");
		// 设置appId
		authenticationModel.getHeaders().put("_$APPID", parametersMap.get("appId").get(0).toString());
		// 移除appId
		parametersMap.remove("appId");
		AuthService authService = SpringContext.getBeanByClass(AuthService.class);
		authService.validate(authenticationModel);
    }

}
