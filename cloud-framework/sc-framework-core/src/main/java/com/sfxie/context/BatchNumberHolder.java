package com.sfxie.context;
/**
 * 全局处理批次号管理类<br>
 * 如果负载均衡,则需要另外考虑批次号的生成规则
 * @TODO	
 * @author 	xieshengfeng
 * @email  	xsfcy@126.com
 * @since 	下午5:29:29 2015年9月6日
 * @example		
 *
 */
public class BatchNumberHolder {
	/**	当前线程的处理批次号	*/
	public static ThreadLocal<Long> batchNumber = new ThreadLocal<Long>();
	/**
	 * 获当前线程的处理批次号(System.currentTimeMillis())
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午5:30:29 2015年9月6日
	 * @return	
	 *
	 */
	public synchronized static Long getBatchNumber(){
		Long l = batchNumber.get();
		if(null==l){
			batchNumber.set(System.currentTimeMillis());
		}
		return batchNumber.get();
	}
	/**
	 * 清除前线程的处理批次号
	 * @TODO	
	 * @author 	xieshengfeng
	 * @email  	xsfcy@126.com
	 * @since 	下午6:02:49 2015年9月6日	
	 *
	 */
	public static void clearBatchNumber(){
		batchNumber.remove();
	}
}
