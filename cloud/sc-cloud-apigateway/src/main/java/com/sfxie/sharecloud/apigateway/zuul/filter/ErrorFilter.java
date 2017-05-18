package com.sfxie.sharecloud.apigateway.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sfxie.core.framework.mvc.exception.BusinessException;
import com.sfxie.core.framework.mvc.exception.ExceptionInfo;
import com.sfxie.core.framework.mvc.exception.MvcException;
import com.sfxie.core.utils.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/8/22
 * Time: 下午7:57
 * CopyRight: taobao
 * Descrption:异常filter
 */
@Component
public class ErrorFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info("ErrorFilter run!");
        //yingkhtodo:desc:forward
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().setCharacterEncoding("UTF-8");
        ZuulException a = (ZuulException) ctx.get("throwable");
        MvcException t = (MvcException) a.getCause();
        Object response;
        if (t instanceof MvcException) {
			((MvcException) t).notifyAllListeners();
			response = ((MvcException) t).getExceptionInfo();
			ctx.setResponseStatusCode(200);
			ctx.setResponseBody(JsonUtil.toJson(response));
		} else {
			ctx.setResponseStatusCode(a.nStatusCode);
		}
        return null;
    }
}
