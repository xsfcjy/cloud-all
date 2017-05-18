package com.sfxie.exception.framework.implement.exception;


/**
 * 异常处理包装执行类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015年7月17日下午1:18:16
 */
public interface ExceptionWrapper {
	
	public void supports() throws ControllerException;
	/**
	 * 执行方法
	 * @param parameters...
	 * 		参数数组
	 * @return
	 * @throws MvcException
	 */
	public Object doMethod(Object... parameters ) throws MvcException; 
	
	/**
	 * 产成dao层异常信息
	 * @param e
	 * @return
	 */
	public DaoException generateDaoException(Exception e,Object... parameters);
	/**
	 * 产成service层异常信息
	 * @param e
	 * @return
	 */
	public ServiceException generateServiceException(Exception e,Object... parameters);
	/**
	 * 产成controller层异常信息
	 * @param e
	 * @return
	 */
	public ControllerException generateControllerException(Exception e,Object... parameters);
	/**
	 * 产成mvc层异常信息
	 * @param e
	 * @return
	 */
	public MvcException generateMvcException(Exception e,Object... parameters);
	/**
	 * 产成redis层异常信息
	 * @param e
	 * @return
	 */
	public RedisException generateRedisException(Exception e,Object... parameters);
	/**
	 * 产成sql层异常信息
	 * @param e
	 * @return
	 */
	public SqlException generateSqlException(Exception e,Object... parameters);
}
