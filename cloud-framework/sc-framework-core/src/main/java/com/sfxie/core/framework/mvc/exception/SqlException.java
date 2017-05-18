package com.sfxie.core.framework.mvc.exception;


/**
 * 执行sql异常类(在mybatis或者statement中执行sql时抛出的BadSqlGrammarException异常)
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since 2015-7-14上午10:12:07
 */
public class SqlException extends MvcException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SqlException(String errorCode,Throwable t){
		super(errorCode,t);
	}
	public SqlException(String errorCode,Exception t){
		super(errorCode,t);
	}
	public SqlException(Exception t){
		super(t);
	}
	public SqlException(Throwable t){
		super(t);
	}
}
