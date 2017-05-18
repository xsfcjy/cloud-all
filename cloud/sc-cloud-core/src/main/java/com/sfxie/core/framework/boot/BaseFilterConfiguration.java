package com.sfxie.core.framework.boot;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器基础配置抽象类
 * 
 * @description:
 * @author	xiesf
 * @email 	xsfcy@126.com
 * @date 	2016年8月3日
 * @copyright 	Conpyright (c) 2016 共享云平台有限公司 版权所有
 */
@Configuration
public abstract class BaseFilterConfiguration extends BaseCommandLineRunner {

	protected static Logger logger = Logger
			.getLogger(BaseFilterConfiguration.class);
}
