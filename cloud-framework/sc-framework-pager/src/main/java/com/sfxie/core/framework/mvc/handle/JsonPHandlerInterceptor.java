package com.sfxie.core.framework.mvc.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 跨域问题拦截器 用于非生产环境测试
 * 
 * @author xiesf
 * @since 201
 *
 */
public class JsonPHandlerInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        // TODO Auto-generated method stub
        // 指定允许其他域名访问（必须）
        arg1.addHeader("Access-Control-Allow-Origin", "*");
        // 响应类型（非必须）
        arg1.addHeader("Access-Control-Allow-Methods", "POST,PUT,DELETE,GET");
        // 响应头设置（非必须）
        arg1.addHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        return true;
    }

}
