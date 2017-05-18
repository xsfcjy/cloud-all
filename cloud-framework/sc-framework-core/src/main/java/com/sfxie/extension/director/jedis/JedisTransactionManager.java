package com.sfxie.extension.director.jedis;

public class JedisTransactionManager {

	private IJedisTransaction jedisTransaction;

	public IJedisTransaction getJedisTransaction() {
		return jedisTransaction;
	}

	public void setJedisTransaction(IJedisTransaction jedisTransaction) {
		this.jedisTransaction = jedisTransaction;
	}
	/**		是否启动当前线程jedis异常数据回滚事务 		*/
	public boolean isBeginTransaction(){
		return jedisTransaction.isBeginTransaction();
	}
	/** 	当前线程jedis数据回滚 		*/
	public void rollback(){
		jedisTransaction.rollback();
		
	}
	/**		释放当前线程jedis资源		*/
	public void clear(){
		jedisTransaction.clear();
	}
}
