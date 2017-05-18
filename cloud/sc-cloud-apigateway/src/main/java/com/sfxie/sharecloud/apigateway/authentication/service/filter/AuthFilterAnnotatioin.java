package com.sfxie.sharecloud.apigateway.authentication.service.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 鉴权过滤器注解
 * 
 * @author xiesf
 * @since 2016-08-10
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthFilterAnnotatioin {

	/** 过滤器的顺序 */
	int order() default 0;

	/** 是否启用当前过滤器 */
	boolean enable() default true;
}
