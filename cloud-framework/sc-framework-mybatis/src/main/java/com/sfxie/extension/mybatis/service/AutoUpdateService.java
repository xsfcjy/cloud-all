package com.sfxie.extension.mybatis.service;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.sfxie.exception.framework.implement.exception.MvcException;
import com.sfxie.extension.mybatis.dao.AutoUpdateMapper;
import com.sfxie.core.framework.core.IBaseService;
import com.sfxie.core.framework.core.TransactionService;

/**
 * 封装的有根据实体自动生成增、删、改功能sql的service
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午4:25:15 2015年8月31日
 * @example		
 *
 */
@Service
public class AutoUpdateService extends TransactionService implements IBaseService{

	@Resource
	private AutoUpdateMapper autoUpdateMapper;
	
	protected static final Log logger = LogFactory.getLog(AutoUpdateService.class);

	/**
	 * 根据实体类利用mybatis自动添加记录操作
	 * 
	 * @param entity
	 * @throws MvcException 
	 */
	public void insertEntity(Object entity) throws MvcException {
		autoUpdateMapper.insertEntity(entity);
	}
	/**
	 * 根据实体类利用mybatis自动添加记录操作
	 * 
	 * @param entity
	 * @throws MvcException 
	 */
	public void insertEntity(Object entity1,Object entity2) throws MvcException {
		autoUpdateMapper.insertEntity(entity1);
		autoUpdateMapper.insertEntity(entity2);
//		int i=1/0;
	}

	/**
	 * 根据实体类利用mybatis自动修改记录操作
	 * 
	 * @param entity
	 */
	public void updateEntity(Object entity) throws MvcException {
		autoUpdateMapper.updateEntity(entity);
	}

	/**
	 * 根据实体类利用mybatis自动删除记录操作
	 * 
	 * @param entity
	 */
	public void deleteEntity(Object entity) throws MvcException {
		autoUpdateMapper.deleteEntity(entity);
	}

}
