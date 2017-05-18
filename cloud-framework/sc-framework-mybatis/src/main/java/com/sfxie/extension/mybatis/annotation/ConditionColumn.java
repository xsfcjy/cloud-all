package com.sfxie.extension.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 条件注解,更新实体时用;用于AutoUpdateService识别条件字段
 * @author xieshengfeng
 * @since 2015-05-15
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionColumn {
}