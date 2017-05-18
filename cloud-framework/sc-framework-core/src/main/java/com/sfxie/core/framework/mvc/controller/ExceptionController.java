package com.sfxie.core.framework.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfxie.core.framework.mvc.exception.ExceptionInfo;
import com.sfxie.core.framework.mvc.exception.MvcException;

/**
 * 拥有全局异常处理的控制类
 * 
 * @author xieshengfeng
 * @email xsfcy@126.com
 * @since 2015-7-14上午10:27:28
 */
public class ExceptionController {
	// @Resource
	// private ExceptionResponseHandler exceptionResponseHandler;
	/**
	 * 异常处理方法
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler
	public @ResponseBody Object exception(HttpServletRequest request,
			Exception e) {
		Throwable t = ExceptionInfo.parseException(e);
		t.printStackTrace();
		Object response;
		if (t instanceof MvcException) {
			((MvcException) t).notifyAllListeners();
			response = ((MvcException) t).getExceptionInfo();
		} else {
			response = ExceptionInfo.gainExceptionInfo("json", e);
		}
		return response;
	}

}
