package com.sfxie.core.framework.mvc.handle;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 分页功能需要用到的过滤器<br>
 * 主要是包装{@link javax.servlet.http.HttpServletRequest}，使其的inputstream可以重复获取
 * @author xiesf
 * @since 2017-05-01
 *
 */
public class PagerFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		if(!(request instanceof MAPIHttpServletRequestWrapper)){
        	if(request instanceof HttpServletRequest) {  
        		request = new MAPIHttpServletRequestWrapper((HttpServletRequest) request);  
        	}
        }
		filterChain.doFilter(request, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
