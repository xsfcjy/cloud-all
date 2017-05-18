package com.sfxie.extension.mybatis.exception;

/**
 * 查询参数为空异常
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since  2015年5月16日 下午9:01:58
 * @todo   
 *
 */
public class ParameterMapEmptyException extends ExtensionMybatisException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParameterMapEmptyException(Exception e){
		super("parameterMapEmptyException",e);
	}

	public ParameterMapEmptyException(String errorCode,Throwable t){
		super(errorCode,t);
	}

}
