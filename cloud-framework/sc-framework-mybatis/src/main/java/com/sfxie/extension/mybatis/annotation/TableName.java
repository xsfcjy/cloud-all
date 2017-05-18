package com.sfxie.extension.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库表名映射注解;用于AutoUpdateService识别数据库表名
 * @author xieshengfeng
 * @since 2015-05-15
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableName {

	/**
	 * 数据库表名称
	 * @return 
	 */
	String value() default "";
	
	Class<?> partitionName() default String.class;

}