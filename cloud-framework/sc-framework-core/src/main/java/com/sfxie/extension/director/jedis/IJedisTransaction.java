package com.sfxie.extension.director.jedis;

public interface IJedisTransaction {

	/**	当前线程jedis数据异常回滚	*/
	public void rollback();
	/**	是否启动当前线程jedis异常回滚事务	*/
	public boolean isBeginTransaction();
	/** 释放当前线程jedis资源	*/
	public void clear();
}
