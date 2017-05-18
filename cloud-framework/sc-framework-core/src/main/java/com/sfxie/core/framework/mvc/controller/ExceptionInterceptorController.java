package com.sfxie.core.framework.mvc.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 异常拦截器类,拦截全局异常
 * 
 * @author xieshengfeng
 * @email xsfcy@126.com
 * @since 2015年7月20日下午3:59:46
 */
@Component
@ControllerAdvice
public class ExceptionInterceptorController extends ExceptionController {
}
