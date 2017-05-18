package com.sfxie.extension.mybatis.exception;

/**
 * 删除实体条件超多异常
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since  2015年5月16日 下午9:01:58
 * @todo   
 *
 */
public class DeleteEntityConditionMoreException extends ExtensionMybatisException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DeleteEntityConditionMoreException(Exception e){
		super("deleteEntityConditionException",e);
	}

	public DeleteEntityConditionMoreException(String errorCode,Throwable t){
		super(errorCode,t);
	}
}
