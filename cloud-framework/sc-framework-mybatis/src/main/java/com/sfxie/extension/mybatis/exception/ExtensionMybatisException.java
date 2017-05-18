package com.sfxie.extension.mybatis.exception;


import com.sfxie.exception.framework.implement.exception.DaoException;

public class ExtensionMybatisException extends DaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExtensionMybatisException(Exception e){
		super("mybatisDaoException",e);
	}
	public ExtensionMybatisException(String e){
		super(e);
	}

	public ExtensionMybatisException(String errorCode,Throwable t){
		super(errorCode,t);
	}
}
