package com.sfxie.exception.framework.implement.exception;



/**
 * 异常处理包装执行抽象类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015年7月20日上午9:46:20
 */
public abstract class AbstractExceptionWrapper implements ExceptionWrapper {

	@Override
	public abstract Object doMethod(Object... parameters) throws MvcException;

	/**
	 * 此方法用于执行doMethod前做一些前置处理,比如身份验证等<br>
	 * 如果不让执行后续的doMethod方法,则抛出ControllerException即可
	 */
	public void supports() throws ControllerException{
		
	}
	/**
	 * 产成dao层异常信息<br>
	 * 如要需要为此异常添加一个事件监听,可重写此方法,在此方法返回的异常上添加事件监听器<br>
	 * 如果需要设置异常的一些信息,如errorCode属性信息等,可以在此方法设置
	 * @param e
	 * 		被抛出异常实体
	 * @param parameters
	 * 		参数数组
	 * @return
	 * 		DaoException
	 */
	public DaoException generateDaoException(Exception e,Object... parameters){
		DaoException exception = new DaoException(e);
		exception.setStackTrace(e.getStackTrace());
		exception.setParameters(parameters);
		return exception;
	}
	/**
	 * 产成service层异常信息<br>
	 * 如要需要为此异常添加一个事件监听,可重写此方法,在此方法返回的异常上添加事件监听器<br>
	 * 如果需要设置异常的一些信息,如errorCode属性信息等,可以在此方法设置
	 * @param e
	 * 		被抛出异常实体
	 * @param parameters
	 * 		参数数组
	 * @return
	 * 		ServiceException
	 */
	public ServiceException generateServiceException(Exception e,Object... parameters){
		ServiceException exception = new ServiceException(e);
		exception.setStackTrace(e.getStackTrace());
		exception.setParameters(parameters);
		return exception;
	}
	/**
	 * 产成controller层异常信息<br>
	 * 如要需要为此异常添加一个事件监听,可重写此方法,在此方法返回的异常上添加事件监听器<br>
	 * 如果需要设置异常的一些信息,如errorCode属性信息等,可以在此方法设置
	 * @param e
	 * 		被抛出异常实体
	 * @param parameters
	 * 		参数数组
	 * @return
	 * 		ControllerException
	 */
	public ControllerException generateControllerException(Exception e,Object... parameters){
		ControllerException exception = new ControllerException(e);
		exception.setStackTrace(e.getStackTrace());
		exception.setParameters(parameters);
		return exception;
	}
	/**
	 * 产成mvc层异常信息<br>
	 * 如要需要为此异常添加一个事件监听,可重写此方法,在此方法返回的异常上添加事件监听器<br>
	 * 如果需要设置异常的一些信息,如errorCode属性信息等,可以在此方法设置
	 * @param e
	 * 		被抛出异常实体
	 * @param parameters
	 * 		参数数组
	 * @return
	 * 		MvcException
	 */
	public MvcException generateMvcException(Exception e,Object... parameters){
		MvcException exception = new MvcException(e);
		exception.setStackTrace(e.getStackTrace());
		exception.setParameters(parameters);
		return exception;
	}
	/**
	 * 产成redis层异常信息<br>
	 * 如要需要为此异常添加一个事件监听,可重写此方法,在此方法返回的异常上添加事件监听器<br>
	 * 如果需要设置异常的一些信息,如errorCode属性信息等,可以在此方法设置
	 * @param e
	 * 		被抛出异常实体
	 * @param parameters
	 * 		参数数组
	 * @return
	 * 		RedisException
	 */
	public RedisException generateRedisException(Exception e,Object... parameters){
		RedisException exception = new RedisException(e);
		exception.setStackTrace(e.getStackTrace());
		exception.setParameters(parameters);
		return exception;
	}
	/**
	 * 产成sql层异常信息
	 * @param e
	 * @return
	 */
	public SqlException generateSqlException(Exception e,Object... parameters){
		SqlException exception = new SqlException("sqlExecuteError",e);
		exception.setStackTrace(e.getStackTrace());
		exception.setParameters(parameters);
		return exception;
	}
}
