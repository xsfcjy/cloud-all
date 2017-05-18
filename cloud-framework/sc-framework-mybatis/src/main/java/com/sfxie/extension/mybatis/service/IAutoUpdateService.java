package com.sfxie.extension.mybatis.service;

import com.sfxie.core.framework.core.IBaseService;
import com.sfxie.exception.framework.implement.exception.MvcException;



/**
 * 封装的有根据实体自动生成增、删、改功能sql的service
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午4:25:15 2015年8月31日
 * @example		
 *
 */
public interface IAutoUpdateService  extends IBaseService{


	/**
	 * 根据实体类利用mybatis自动添加记录操作
	 * 
	 * @param entity
	 * @throws MvcException 
	 */
	public void insertEntity(Object entity) throws MvcException ;
	/**
	 * 根据实体类利用mybatis自动添加记录操作
	 * 
	 * @param entity
	 * @throws MvcException 
	 */
	public void insertEntity(Object entity1,Object entity2) throws MvcException;

	/**
	 * 根据实体类利用mybatis自动修改记录操作
	 * 
	 * @param entity
	 */
	public void updateEntity(Object entity) throws MvcException ;

	/**
	 * 根据实体类利用mybatis自动删除记录操作
	 * 
	 * @param entity
	 */
	public void deleteEntity(Object entity) throws MvcException;

}
