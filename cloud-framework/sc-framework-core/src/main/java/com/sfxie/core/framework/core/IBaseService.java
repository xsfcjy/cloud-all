package com.sfxie.core.framework.core;



/**
 * 服务基接口,实现类{@link com.sfxie.core.service.TransactionService}
 * 
 * @author XIESHENGFENG
 * @since 2013-06-23
 *
 */
public interface IBaseService {

	/**	新增实体	*/
	public void insertEntity(Object entity) throws Exception;
	/**	新增实体	*/
	public void insertEntity(Object entity1,Object entity2) throws Exception;
	/**	更新实体	*/
	public void updateEntity(Object entity) throws Exception;
	/**	删除实体	*/
	public void deleteEntity(Object entity) throws Exception;
}
