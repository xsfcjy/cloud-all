package com.sfxie.core.framework.mvc.handle;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.sfxie.utils.jacson.codehaus.JsonUtil;

/**
 * 分页拦截器，结合com.github.pagehelper分页工具进行mybatis组件的分页<br>
 * 前端需要传递页记录数(pageSize)和当前页码数(pageNum)参数过来
 * 
 * @author xiesf
 * @since 2017-05-01
 *
 */
public class PageHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String pageSize = request.getParameter("pageSize");
		String pageNum = request.getParameter("pageNumber");
		if (null != pageSize && null != pageNum) {
			PageHelper.startPage(Integer.valueOf(pageNum),
					Integer.valueOf(pageSize));
		}
		String ss = IOUtils.toString(request.getInputStream(), "UTF-8");
		if (null != ss) {
			Map<?, ?> params = JsonUtil.fromJSON(ss, Map.class);
			if (null != params) {
				Object pageSize1 = params.get("pageSize");
				Object pageNum1 = params.get("pageNumber");
				if (null != pageSize1 && null != pageNum1) {
					PageHelper.startPage(Integer.valueOf(pageNum1.toString()),
							Integer.valueOf(pageSize1.toString()));
				}
			}
			return true;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
