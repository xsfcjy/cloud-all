package com.sfxie.extension.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库字段名映射注解;用于AutoUpdateService识别数据库字段
 * @author xieshengfeng
 * @since 2015-05-15
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface KeyGenerator {

	/**
	 * 数据库字段名称
	 * @return 
	 */
	Class<?> value() default IKeyGenerator.class;

}