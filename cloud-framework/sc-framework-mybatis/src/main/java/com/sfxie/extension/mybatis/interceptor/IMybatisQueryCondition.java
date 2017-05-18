package com.sfxie.extension.mybatis.interceptor;

import java.util.List;

/**
 * mybatis统一查询参数(从前端传过来的参数)获取接口
 * @author xieshengfeng
 * @since 2015-05-27
 */
public interface IMybatisQueryCondition {

	/**
	 * 获取查询参数列表
	 * @return
	 */
	public List<QueryConditionEntity> getQueryConditionEntityList();
	
}
