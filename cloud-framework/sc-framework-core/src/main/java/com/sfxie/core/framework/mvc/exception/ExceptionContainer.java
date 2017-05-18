package com.sfxie.core.framework.mvc.exception;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;

/**
 * 具有异常处理包装的容器类
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-17下午1:19:16
 */
public class ExceptionContainer {
	/**	全局普通参数对象	*/
	private static ThreadLocal<Object> pojoObject = new ThreadLocal<Object>();
	/**	全局xml参数对象	*/
	private static ThreadLocal<Object> xmlObject = new ThreadLocal<Object>();
	/**	设置当前线程的参数	*/
	private static void setParameters(Object... parameters){
		if(null!=parameters){
			List<Object> parametersList = new ArrayList<Object>();
			List<Object> xmlParametersList = new ArrayList<Object>();
			for(int i=0;i<parameters.length;i++){
				Object param = parameters[i];
				XmlRootElement xmlRootElement = param.getClass().getAnnotation(XmlRootElement.class);
				if(null!=xmlRootElement){
					xmlParametersList.add(param);
				}else{
					parametersList.add(param);
				}
			}
			if(parametersList.size()>0){
				Object[] parametersTemp  = parametersList.toArray(new Object[]{});
				pojoObject.set(parametersTemp);
			}
			if(xmlParametersList.size()>0){
				Object[] xmlParameters  = xmlParametersList.toArray(new Object[]{});
				xmlObject.set(xmlParameters);
			}
		}
		
	}
	/**	获取进入ExceptionContainer.controller方法的当前线程参数	*/
	public static Object[] getPojoParameters(){
		return (Object[]) pojoObject.get();
	}
	/**	获取进入ExceptionContainer.controller方法的当前线程参数	*/
	public static Object[] getXmlParameters(){
		return (Object[]) xmlObject.get();
	}
	/**	消除当前线程的参数	*/
	public static void clearParameters(){
		pojoObject.remove();
		xmlObject.remove();
	}
	/**
	 * controller层执行方法
	 * @param exceptionWrapper
	 * 			方法执行体内容
	 * @param parameters
	 * 			参数列表
	 * @return	Object
	 * @throws MvcException
	 */
	public static Object controller(ExceptionWrapper exceptionWrapper,Object... parameters) throws MvcException{
		try{
			setParameters(getParams(exceptionWrapper.getParameters(),parameters));
			exceptionWrapper.supports();
			Object result = exceptionWrapper.doMethod(getParams(exceptionWrapper.getParameters(),parameters));
			clearParameters();
			return result;
		}catch(ServiceException e){
			throw  e;
		}catch(DaoException e){
			throw  e;
		}catch(BusinessException e){
			throw e;
		}catch(BadSqlGrammarException e){
			throw exceptionWrapper.generateSqlException(e, getParams(exceptionWrapper.getParameters(),parameters));
		}catch(Exception e){
//			DataAccessException.class.isAssignableFrom(e.getClass()) || SQLException.class.isAssignableFrom(e.getClass())
			if(e instanceof DataAccessException  || e instanceof SQLException || (null!=e.getCause() && e.getCause() instanceof SQLException)){
				throw exceptionWrapper.generateSqlException(e, getParams(exceptionWrapper.getParameters(),parameters));
			}
			throw exceptionWrapper.generateControllerException(e, getParams(exceptionWrapper.getParameters(),parameters));
		}finally{
		}
	}
	
	private static Object[] getParams(Object[] objs1 ,Object[] objs2){
		return null!=objs1 ?objs1 :objs2;
	}
	/**
	 * service层执行方法
	 * @param exceptionWrapper
	 * 			方法执行体内容
	 * @param parameters
	 * 			参数列表
	 * @return	Object
	 * @throws ServiceException
	 */
	public static Object service(ExceptionWrapper exceptionWrapper,Object... parameters) throws MvcException{
		try{
			return exceptionWrapper.doMethod(getParams(exceptionWrapper.getParameters(),parameters));
		}catch(DaoException e){
			throw e;
		}catch(Exception e){
			ServiceException serviceException = exceptionWrapper.generateServiceException(e, getParams(exceptionWrapper.getParameters(),parameters));
			throw serviceException;
		}
	}
	/**
	 * dao层执行方法
	 * @param exceptionWrapper
	 * 			方法执行体内容
	 * @param parameters
	 * 			参数列表
	 * @return	Object
	 * @throws ServiceException
	 */
	public static Object dao(ExceptionWrapper exceptionWrapper,Object... parameters) throws DaoException{
		try{
			return exceptionWrapper.doMethod(getParams(exceptionWrapper.getParameters(),parameters));
		}catch(Exception e){
			throw exceptionWrapper.generateDaoException(e, getParams(exceptionWrapper.getParameters(),parameters));
		}
	}
	/**
	 * mvc执行方法
	 * @param exceptionWrapper
	 * 			方法执行体内容
	 * @param parameters
	 * 			参数列表
	 * @return	Object
	 * @throws ServiceException
	 */
	public static Object mvc(ExceptionWrapper exceptionWrapper,Object... parameters) throws MvcException{
		try{
			return exceptionWrapper.doMethod(getParams(exceptionWrapper.getParameters(),parameters));
		}catch(Exception e){
			throw exceptionWrapper.generateMvcException(e, getParams(exceptionWrapper.getParameters(),parameters));
		}
	}
	/**
	 * 其它执行方法
	 * @param exceptionWrapper
	 * 			方法执行体内容
	 * @param parameters
	 * 			参数列表
	 * @return	Object
	 * @throws ServiceException
	 */
	public static Object other(ExceptionWrapper exceptionWrapper,Object... parameters) throws Exception{
		try{
			return exceptionWrapper.doMethod(getParams(exceptionWrapper.getParameters(),parameters));
		}catch(Exception e){
			throw e;
		}
	}
}
