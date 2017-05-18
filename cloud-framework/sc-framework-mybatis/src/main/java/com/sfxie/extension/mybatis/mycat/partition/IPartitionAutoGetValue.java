package com.sfxie.extension.mybatis.mycat.partition;

/**
 * 
 * @TODO	自动获取分区字段值接口
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午3:32:00 2016-1-19
 * @example		
 *
 */
public interface IPartitionAutoGetValue {

	/**
	 * 获取分区字段值
	 * @return
	 */
	public String autoValue();
}
