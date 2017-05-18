package com.sfxie.extension.mybatis.exception;

/**
 * 分区字段为空异常
 * @author xieshengfeng
 * @email  xsfcy@126.com
 * @since  2015年5月16日 下午9:01:58
 * @todo   
 *
 */
public class PartitionEmptyException extends ExtensionMybatisException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PartitionEmptyException(Exception e){
		super("partitionEmptyException",e);
	}

	public PartitionEmptyException(String errorCode,Throwable t){
		super(errorCode,t);
	}

}
