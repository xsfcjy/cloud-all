package com.sfxie.extension.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分区注解;用于AutoUpdateService识别数据库分区字段(如果有用cobar分区)
 * @author xieshengfeng
 * @since 2015-05-15
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PartitionField {

	/**
	 * 数据库字段名称
	 * @return 
	 */
//	String field() ;

	/**
	 * 数据库字段类型
	 * @return
	 */
//	String type() default DBFieldType.VARCHAR;
}