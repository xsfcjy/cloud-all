package com.sfxie.extension.mybatis.exception;

/**
 * 分区字段为空异常
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since  2015年5月16日 下午9:01:58
 * @todo   
 *
 */
public class ConditionEmptyException extends ExtensionMybatisException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConditionEmptyException(Exception e){
		super("conditionEmptyException",e);
	}

	public ConditionEmptyException(String errorCode,Throwable t){
		super(errorCode,t);
	}

}
